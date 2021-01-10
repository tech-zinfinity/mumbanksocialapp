package app.data.repository.regular;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.Blog;
import reactor.core.publisher.Flux;

public interface BlogReactiveRepository extends ReactiveMongoRepository<Blog, String>{

	@Query("{'author.id' : ?0}")
	Flux<Blog> findByAuthor(String id);
}
