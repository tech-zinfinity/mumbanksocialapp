package app.data.repository.regular;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.data.entity.Contact;

public interface ContactRepoReg extends MongoRepository<Contact, String>{

	@Query("{ 'dob' : ?0 }")
	public List<Contact> findByDob(LocalDate dob);
}
