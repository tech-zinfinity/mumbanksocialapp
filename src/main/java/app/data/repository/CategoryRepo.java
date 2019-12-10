package app.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.Category;

public interface CategoryRepo extends ReactiveMongoRepository<Category, String>{

}
