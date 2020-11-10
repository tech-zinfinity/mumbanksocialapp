package app.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Blog;
import app.data.entity.BlogCategory;
import app.data.entity.BlogComment;
import app.data.entity.BlogTag;
import app.data.repository.BlogCategoryRepository;
import app.data.repository.BlogCommentRepository;
import app.data.repository.BlogRepository;
import app.data.repository.BlogTagRepository;
import app.http.request.NameRequest;
import app.service.BlogService;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("blog")
public class BlogController {
	
	@Autowired private BlogCategoryRepository catrepo;
	@Autowired private BlogTagRepository tagrepo;
	@Autowired private BlogRepository blogrepo;
	@Autowired private BlogCommentRepository commentRepo;

	@PostMapping("/addCategory")
	public Mono<BlogCategory> addCategory(@RequestBody NameRequest cat){
		System.out.println(cat);
		System.out.println(catrepo.findByName(cat.getName()).isPresent());
		if(catrepo.findByName(cat.getName()).get().size()>0) {
			return Mono.just(null);
		}else {
			System.out.println("*****");
			return Mono.create(sink ->{
				sink.success(catrepo.insert(BlogCategory.builder().name(cat.getName()).active(true).added(true).build()));
			});
		}
	}
	@PostMapping("/deleteCategory")
	public Mono<Boolean> deletCategoryByName(@RequestBody NameRequest cat) {
		if(catrepo.findByName(cat.getName()).get().size()>0) {
			return Mono.just(null);
		}else {
			return Mono.create(sink ->{
				try {
					catrepo.deleteByName(cat.getName());
					sink.success(true);
				}catch (Exception e) {
					sink.success(false);
				}
			});
		}
	}
	
	@PostMapping("/addTag")
	public Mono<BlogTag> addTag(@RequestBody NameRequest cat){
		if(!tagrepo.findByName(cat.getName()).isPresent()) {
			return Mono.create(sink ->{
				sink.success(tagrepo.insert(BlogTag.builder().name(cat.getName()).active(true).added(true).build()));
			});
		}else {
			return Mono.just(null);
		}
	}
	@PostMapping("/deleteTag")
	public Mono<Boolean> deletTagByName(@RequestBody NameRequest cat) {
		if(!tagrepo.findByName(cat.getName()).isPresent()) {
			return Mono.create(sink ->{
				try {
					tagrepo.deleteByName(cat.getName());
					sink.success(true);
				}catch (Exception e) {
					sink.success(false);
				}
			});
		}else {
			return Mono.just(null);
		}
	}
	
	private Mono<BlogTag> addTags(String cat) {
		if(!tagrepo.findByName(cat).isPresent()) {
			return Mono.create(sink ->{
				sink.success(tagrepo.insert(BlogTag.builder().name(cat).build()));
			});
		}else {
			return Mono.just(null);
		}
	}
	
	@GetMapping("/getAllCategories")
	public List<BlogCategory> getAllCategories(){
		return this.catrepo.findAll();
	}
	
	@GetMapping("/getAllTags")
	public List<BlogTag> getAllTags(){
		return this.tagrepo.findAll();
	}
	
	@PostMapping("/addNewBlog")
	public Blog addBlog(@RequestBody Blog blog) {
		var b = blog;
		var c = b.getTags().stream().map(tag ->{
			if(!tag.isAdded()) {
				var g = tagrepo.findByName(tag.getName()).get();
				if(g.size()>0){
					return g.stream().findFirst().get();
				}
				tag.setAdded(true);
				tag.setActive(true);
				var d = tagrepo.insert(tag);
				return d;
			}
			return tag;
		}).collect(Collectors.toList());
		
		blog.setTags(c);
		return blogrepo.insert(blog);
	}
	
	@GetMapping("/requestForPublish/{id}")
	public Blog requestToPublish(@PathVariable("id") String id) {
		var b = blogrepo.findById(id);
		if(b.isPresent()) {
			var c= b.get();
			c.setStatus("REQUESTED");
			return blogrepo.save(c);
					
		}
		return null;
	}
	
	@GetMapping("/approveToPublish/{id}")
	public Blog approveToPublish(@PathVariable("id") String id) {
		var b = blogrepo.findById(id);
		if(b.isPresent()) {
			var c= b.get();
			c.setPublishedOn(LocalDateTime.now());
			c.setStatus("PUBLISHED");
			c.setActive(true);
			return blogrepo.save(c);
					
		}
		return null;
	}
	
	@GetMapping("/deactivateBlog/{id}")
	public Blog deactivateBlog(@PathVariable("id") String id) {
		var b = blogrepo.findById(id);
		if(b.isPresent()) {
			var c= b.get();
			c.setActive(false);
			return blogrepo.save(c);
					
		}
		return null;
	}
	
	@GetMapping("/getBlogsByUserId/{id}")
	public List<Blog> getBlogsByUserId(@PathVariable("id") String id) {
		return blogrepo.findByAuthor(id);
	}
	
	@GetMapping("/getBlogsById/{id}")
	public Optional<Blog> getBlogsById(@PathVariable("id") String id) {
		return blogrepo.findById(id);
	}
	
	@Autowired BlogService blogService;
	public List<Blog> getgetLatestBlogsByLimit(@PathVariable("limit") String limit){
		return blogService.getLatestActiveBlogsByLimit(Integer.parseInt(limit));
		
	}
	
	@PostMapping("/editBlog")
	public Blog editBlog(@RequestBody Blog blog) {
		var b  = blogrepo.findById(blog.getId());
		if(b.isPresent()){
			var c = b.get();
			c.setTitle(blog.getTitle());
			c.setParagraph(blog.getParagraph());
			c.setCoverImage(blog.getCoverImage());
			c.setTags(blog.getTags());
			c.setCategory(blog.getCategory());
			return blogrepo.save(c);
		}
		else {
			return null;
		}
	}
	
	@PostMapping("/addCommentToBlog/{id}")
	public Blog editBlog(@RequestBody BlogComment comment, @PathVariable("id") String id) {
		var b  = blogrepo.findById(id);
		if(b.isPresent()){
			var c = b.get();
			var comments  = c.getComments();
			if(comments.size()>0) {
				comments.add(comment);
				c.setComments(comments);
				c.setCommetCount(c.getCommetCount()+1);
				return blogrepo.save(c);
			}else {
				c.setComments(Arrays.asList(comment));
				c.setComments(comments);
				c.setCommetCount(c.getCommetCount()+1);
				return blogrepo.save(c);
			}
		}
		else {
			return null;
		}
	}
	
	@GetMapping("getAll/{page}/{size}")
	public Page<Blog> getAllBlogs(@PathVariable("page") int page, @PathVariable("size") int size){
		return blogrepo.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdOn")));
	}
	
	@GetMapping("getAllRequestedForApprovalBlogs")
	public List<Blog> getAllRequestedForApprovalBlogs(){
		return blogService.getLatestDarftBlogsSortedByDate();
	}
	
	@GetMapping("getLatestRequestedBlogsSortedByDate")
	public List<Blog> getLatestRequestedBlogsSortedByDate(){
		return blogService.getLatestRequestedBlogsSortedByDate();
	}
	
	@GetMapping("getAll/likeBlog/{id}")
	public Blog likeBlog(@PathVariable("id") String id){
		var b = blogrepo.findById(id);
		if(b.isPresent()) {
			var c = b.get();
			c.setLikes(c.getLikes()+1);
			return blogrepo.save(c);
		}else {
			return null;
		}
	}
	
	@GetMapping("getAll/disLikeBlog/{id}")
	public Blog disLikeBlog(@PathVariable("id") String id){
		var b = blogrepo.findById(id);
		if(b.isPresent()) {
			var c = b.get();
			c.setDisLikes(c.getDisLikes()+1);
			return blogrepo.save(c);
		}else {
			return null;
		}
	}
	
	@GetMapping("getAll/addView/{id}")
	public Blog addView(@PathVariable("id") String id){
		var b = blogrepo.findById(id);
		if(b.isPresent()) {
			var c = b.get();
			c.setVeiwCount(c.getVeiwCount()+1);
			return blogrepo.save(c);
		}else {
			return null;
		}
	}
	
	
}
