package app.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.FeedBack;

public interface FeedBackRepo extends ReactiveMongoRepository<FeedBack, String>{

}
