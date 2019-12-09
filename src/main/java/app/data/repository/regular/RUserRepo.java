package app.data.repository.regular;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.data.entity.RegisteredUsers;

public interface RUserRepo extends MongoRepository<RegisteredUsers, String>{

	public List<RegisteredUsers> findByDob(String dob);
}
