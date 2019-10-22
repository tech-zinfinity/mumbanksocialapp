package app.data.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import app.data.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	Optional<User> findByUsername(String username);
}
