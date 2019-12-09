package app.data.repository.regular;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.data.entity.Contact;

public interface ContactRepoReg extends MongoRepository<Contact, String>{

	public List<Contact> findByDob(String dob);
}
