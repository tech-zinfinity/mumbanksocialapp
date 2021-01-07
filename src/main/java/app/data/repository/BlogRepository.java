package app.data.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.Blog;

public interface BlogRepository extends MongoRepository<Blog, String>{

//	Query query = new Query();
//    query.limit(1);
//    query.with(new Sort(Sort.Direction.DESC, "dateOfBirth"));
//
//    mongoOperation.find(query, Patients.class);
	@Query("{'author.id' : ?0}")
	List<Blog> findByAuthor(String id);
	
	
}
