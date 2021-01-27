package app.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import app.data.entity.Blog;
import app.data.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class BlogService {

	@Autowired private MongoOperations ops;
	@Autowired private BlogRepository blogrepo;
	
	public List<Blog> getLatestActiveBlogsByLimit(int limit) {
		try {
			Query query = new Query(Criteria.where("status").is("PUBLISHED"));
			   query.addCriteria(Criteria.where("active").is(true));
			   query.limit(limit);
			   query.with(Sort.by(Sort.Direction.DESC, "publishedOn"));
			   return ops.find(query, Blog.class);
		}catch (Exception e) {
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public List<Blog> getLatestDarftBlogsSortedByDate() {
		try {
			   Query query = new Query();
			   query.addCriteria(Criteria.where("status").is("DRAFT"));
			   query.with(Sort.by(Sort.Direction.DESC, "createdOn"));
			   return ops.find(query, Blog.class);
		}catch (Exception e) {
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public List<Blog> getLatestRequestedBlogsSortedByDate() {
		try {
			   Query query = new Query();
			   query.addCriteria(Criteria.where("status").is("REQUESTED"));
			   query.with(Sort.by(Sort.Direction.DESC, "createdOn"));
			   return ops.find(query, Blog.class);
		}catch (Exception e) {
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public List<Blog> getAllRequestedBlogs(){
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("status").is("REQUESTED"));
			query.with(Sort.by(Sort.Direction.DESC,"createdOn"));
			return ops.find(query, Blog.class);
		}
		catch(Exception e){
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public List<Blog> getAllApprovedBlogs() {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("status").is("PUBLISHED"));
			query.with(Sort.by(Sort.Direction.DESC,"createdOn"));
			return ops.find(query, Blog.class);
		}
		catch(Exception e){
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public List<Blog> getAllApprovedPopularBlogsByLimit(int limit) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("status").is("PUBLISHED"));
			query.addCriteria(Criteria.where("active").is(true));
			query.limit(limit);
			query.with(Sort.by(Sort.Direction.DESC,"veiwCount"));
			return ops.find(query, Blog.class);
//			return f.stream()
//					.sorted(Comparator.nullsLast((a,b) -> b.getPublishedOn().compareTo(a.getPublishedOn())))
//					.collect(Collectors.toList());
		}
		catch(Exception e){
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public List<Blog> getAllRejectedBlogs() {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("status").is("REJECTED"));
			query.with(Sort.by(Direction.DESC, "createdOn"));
			return ops.find(query, Blog.class);
		}
		catch(Exception e) {
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public List<Blog> getAllDraftBlogs() {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("status").is("DRAFT"));
			query.with(Sort.by(Direction.DESC, "createdOn"));
			return ops.find(query, Blog.class);
		}catch(Exception e) {
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public List<Blog> getLatestBlogByCategory(String catId, int limit){
		try {
			   Query query = new Query();
			   query.addCriteria(Criteria.where("category.id").is(catId));
			   query.addCriteria(Criteria.where("status").is("PUBLISHED"));
			   query.addCriteria(Criteria.where("active").is(true));
			   if(limit!=0) {
				   query.limit(limit);
			   }
			   query.with(Sort.by(Sort.Direction.DESC, "publishedOn"));
			   return ops.find(query, Blog.class);
		}catch (Exception e) {
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public Blog rejectBlog(String id) {
		try {
			var b = blogrepo.findById(id);
			if(b.isPresent()) {
				var c = b.get();
				c.setStatus("REJECTED");
				return ops.save(c);
			}else {
				return null;
			}
		}
		catch(Exception e){
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
	
	public boolean deleteBlog(String id) {
		try {
			var d = blogrepo.findById(id);
			if(d.isPresent()) {
				var b = d.get();
				 var cd = ops.remove(b).getDeletedCount();
				 if(cd == 1) {
					 return true;
				 }
				 else {
					 return false;
				 } 
			}	
		}
		catch(Exception e) {
			log.info("Fatal", e.getMessage());
			return false;
		}
		return false;
	}
		
}
