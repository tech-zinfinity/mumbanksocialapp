package app.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.RegisteredUsers;
import reactor.core.publisher.Flux;

public interface RegisterdUserRepo extends ReactiveMongoRepository<RegisteredUsers, String>{

	public Flux<RegisteredUsers> findByDob(String dob);
}
