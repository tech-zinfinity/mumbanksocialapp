package app.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.Photo;

public interface PhotoRepo extends ReactiveMongoRepository<Photo, String>{

}
