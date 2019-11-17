package app.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.RegisteredUsers;

public interface RegisterdUserRepo extends ReactiveMongoRepository<RegisteredUsers, String>{

}
