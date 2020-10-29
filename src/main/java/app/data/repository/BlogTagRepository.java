package app.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.data.entity.BlogTag;

public interface BlogTagRepository extends MongoRepository<BlogTag, String>{

	@Query("{'name':?0}")
	public Optional<List<BlogTag>> findByName(String name);
	
	@Query(value = "{'name':?0}", delete=true)
	public void deleteByName(String name);
}
