package app.data.repository.regular;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.BlogComment;
import app.data.entity.BlogLikes;
import reactor.core.publisher.Mono;

public interface BlogCommentReactiveRepositiry extends ReactiveMongoRepository<BlogComment, String>{
	
	@Query("{'blogId':?0}")
	public Mono<BlogComment> findByBlogId(String blogId);
}
