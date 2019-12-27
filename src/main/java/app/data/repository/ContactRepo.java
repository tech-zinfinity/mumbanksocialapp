package app.data.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.Contact;
import reactor.core.publisher.Mono;

public interface ContactRepo extends ReactiveMongoRepository<Contact, String>{

	@Query(value="{'memId' : ?0}", delete=true)
	public Mono<Void> deleteByMemId(String memId);
	
	@Query(value="{'userId' : ?0}", delete=true)
	public Mono<Void> deleteByUserId(String userId);
}
