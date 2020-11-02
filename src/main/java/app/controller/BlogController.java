package app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Blog;
import app.data.entity.BlogCategory;
import app.data.entity.BlogTag;
import app.data.repository.BlogCategoryRepository;
import app.data.repository.BlogCommentRepository;
import app.data.repository.BlogRepository;
import app.data.repository.BlogTagRepository;
import app.http.request.NameRequest;
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
	
	
}
