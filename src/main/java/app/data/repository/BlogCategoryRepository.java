package app.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.data.entity.BlogCategory;

public interface BlogCategoryRepository extends MongoRepository<BlogCategory, String>{

	@Query("{'name':?0}")
	public Optional<List<BlogCategory>> findByName(String name);
	
	@Query(value = "{'name':?0}", delete=true)
	public void deleteByName(String name);
	
	@Query(value ="{'active':?0}")
	public List<BlogCategory> findByActive(boolean active);
}
