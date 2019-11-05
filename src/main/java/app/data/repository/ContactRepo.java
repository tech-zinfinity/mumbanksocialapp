package app.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.Contact;

public interface ContactRepo extends ReactiveMongoRepository<Contact, String>{

}
