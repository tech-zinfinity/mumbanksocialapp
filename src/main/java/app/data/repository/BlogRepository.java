package app.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.data.entity.Blog;

public interface BlogRepository extends MongoRepository<Blog, String>{

//	Query query = new Query();
//    query.limit(1);
//    query.with(new Sort(Sort.Direction.DESC, "dateOfBirth"));
//
//    mongoOperation.find(query, Patients.class);
}
