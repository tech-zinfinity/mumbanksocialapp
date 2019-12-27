package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Contact;
import app.data.entity.RegisteredUsers;
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
	
	@GetMapping("deleteContact/{id}")
	public Mono<Void> updateContact(@PathVariable String id){
		return contactrepo.deleteById(id);
	}
	
	@GetMapping("deleteContactByMenId/{id}")
	public Mono<Long> deleteContactByMenId(@PathVariable String id){
		System.out.println("coming here "+ id);
		return contactrepo.deleteByMemId(id);
	}
	
	@GetMapping("deleteContactByUserId/{id}")
	public Mono<Void> deleteByUserId(@PathVariable String id){
		System.out.println("coming here "+ id);
		return contactrepo.deleteByUserId(id);
	}
	
	@PostMapping("updateContactByRUserId")
	public Mono<Contact> updateContactByRUserId(@RequestBody RegisteredUsers user){
		
		return contactrepo.findByUserId(user.getId()).flatMap(data ->{
			if(user.getMobileNo2() != null || !user.getMobileNo2().isBlank()) {data.setPhoneno2(user.getMobileNo2());}
			data.setPhoneno1(user.getMobileNo1());
			data.setName(user.getName());
			data.setDob(user.getDob());
			data.setCategory(user.getCategory());
			return contactrepo.save(data);
		});
		
	}

}
