package app.data.repository.regular;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.BlogLikes;
import reactor.core.publisher.Mono;

public interface BlogLikesReactiveRepositiry extends ReactiveMongoRepository<BlogLikes, String>{

	@Query("{'blogId':?0}")
	public Mono<BlogLikes> findByBlogId(String blogId);
}
