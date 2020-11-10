package app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import app.data.entity.Blog;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class BlogService {

	@Autowired private MongoOperations ops;
	
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
	
	public List<Blog> getLatestBlogByCategory(String catId, int limit){
		try {
			   Query query = new Query();
			   query.addCriteria(Criteria.where("category.id").is(catId));
			   query.addCriteria(Criteria.where("status").is("PUBLISHED"));
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
	
}
