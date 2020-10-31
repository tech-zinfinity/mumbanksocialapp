package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import app.data.entity.Blog;
import app.data.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.data.mongodb.core.query.Criteria.where;


@Service
@Slf4j
public class BlogService {

	@Autowired private BlogRepository blogRepo;
	@Autowired private MongoOperations ops;
	
	public List<Blog> getLatestActiveBlogsByLimit(int limit) {
		try {
			   Query query = new Query(where("active").is(true));
			   query.limit(limit);
			   query.with( Sort.by(Sort.Direction.DESC, "createdOn"));
			   return ops.find(query, Blog.class);
		}catch (Exception e) {
			log.info("Fatal", e.getMessage());
			return null;
		}
	}
}
