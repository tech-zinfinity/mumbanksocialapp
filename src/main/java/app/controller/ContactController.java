package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Contact;
import app.data.repository.ContactRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("contact")
public class ContactController {
	
	@Autowired private ContactRepo contactrepo;

	@PostMapping("addContact")
	public Mono<Contact> addContact(@RequestBody Contact contact){
		return contactrepo.save(contact);
	}
	
	@GetMapping("getAll")
	public Flux<Contact> getAllContacts(){
		return contactrepo.findAll();
	}
}
