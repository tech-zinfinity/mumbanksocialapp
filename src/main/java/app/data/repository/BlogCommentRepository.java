package app.data.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.data.entity.BlogComment;

public interface BlogCommentRepository extends MongoRepository<BlogComment, String>{
	
	@Query("{'blogId':?0}")
	public List<BlogComment> findByBlogId(String blogId);
}
