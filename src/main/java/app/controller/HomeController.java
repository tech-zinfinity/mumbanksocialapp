package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Blog;
import app.data.entity.BlogCategory;
import app.data.repository.BlogCategoryRepository;
import app.data.repository.BlogRepository;
import app.model.CategoryBlogModel;
import app.service.BlogService;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("home")
public class HomeController {

	@Autowired private BlogService blogservice;
	@Autowired private BlogRepository blogrepo;
	@Autowired private BlogCategoryRepository catrepo;
	
	@GetMapping("getLatestPostByLimit/{limit}")
	public List<Blog> getLatestPostByLimit(@PathVariable("limit") int limit){
		return blogservice.getLatestActiveBlogsByLimit(limit);
	}
	
	public void getPopularPostByLimit(int limit) {
		
	}
	
	@GetMapping("getLatestPostByActiveCategoryByLimit/{limit}")
	public List<CategoryBlogModel> getLatestPostByActiveCategoryByLimit(@PathVariable("limit") int limit) {
		var cats = this.catrepo.findByActive(true);
		var fin = new ArrayList<CategoryBlogModel>();
		cats.forEach(cat ->{
			fin.add(CategoryBlogModel.builder().category(cat).blogs(blogservice.getLatestBlogByCategory(cat.getId(), limit)).build());
		});
		return fin;
	}
	
	public void getLatestPostByActiveCategory(String id) {
		
	}
	
	@GetMapping("addView/{id}")
	public Blog addView(@PathVariable("id") String id) {
		var b = blogrepo.findById(id);
		if (b.isPresent()) {
			var c= b.get();
			c.setVeiwCount(c.getVeiwCount()+1);
			return blogrepo.save(c);
		}
		return null;
	}
	
	@GetMapping("addLike/{id}")
	public Blog addLike(@PathVariable("id") String id) {
		var b = blogrepo.findById(id);
		if (b.isPresent()) {
			var c= b.get();
			c.setLikes((c.getLikes()+1));
			return blogrepo.save(c);
		}
		return null;
	}
	
	@GetMapping("addDisLike/{id}")
	public Blog addDisLike(@PathVariable("id") String id) {
		var b = blogrepo.findById(id);
		if (b.isPresent()) {
			var c= b.get();
			c.setDisLikes((c.getDisLikes()+1));
			return blogrepo.save(c);
		}
		return null;
	}
	
	@GetMapping("/getAllActiveCategories")
	public List<BlogCategory> getAllCategories(){
		return this.catrepo.findByActive(true);
	}
	
	@GetMapping("/getAllActivePostsByCategoryId/{id}")
	public List<Blog> getAllActivePostsByCategoryId(@PathVariable("id") String id){
		return blogservice.getLatestBlogByCategory(id, 0);
	}
}
