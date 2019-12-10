package app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Category;
import app.data.entity.Contact;
import app.data.entity.RegisteredUsers;
import app.data.entity.RegistredTrust;
import app.data.repository.CategoryRepo;
import app.data.repository.ContactRepo;
import app.data.repository.RegisterdUserRepo;
import app.data.repository.RegistredTrustRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("register")
public class RegistreationController {

	@Autowired RegisterdUserRepo userrepo;
	@Autowired RegistredTrustRepo trustrepo;
	@Autowired ContactRepo contactrepo;
	@Autowired CategoryRepo categoryrepo;
	
	@PostMapping("addUser")
	public Mono<Contact> addUser(@RequestBody RegisteredUsers user){
		System.out.println("adding things up");
		
		return userrepo.save(user)
				.flatMap(data ->{
					return contactrepo.save(Contact.builder().
							name(data.getName())
							.phoneno1(data.getMobileNo1())
							.phoneno2(data.getMobileNo2())
							.dob(data.getDob())
							.category(data.getCategory())
							.build());
				}).doOnError(err -> new Exception());
	}
	
	@PostMapping("addTrust")
	public Flux<Object> addTrust(@RequestBody RegistredTrust user){
		return Flux.create(sink ->{
			trustrepo.save(user).subscribe(data ->{
				data.getMembers().stream().forEach(value ->{
					contactrepo
					.save(Contact.builder()
							.name(value.getMemname())
							.phoneno1(value.getMobilenoF())
							.phoneno2(value.getMobilenoS())
							.dob(value.getDob())
							.category(value.getCategory()).build())
					.subscribe(t ->{
						sink.next(t);
					});
				});
				sink.complete();
			});
			
		})
		.doOnError(err -> {
			err.printStackTrace();
			new Exception();
			
		}).share();
	}
	@GetMapping("getAllUser")
	public Flux<RegisteredUsers> getAllUser(){
		return userrepo.findAll();
	}
	
	@GetMapping("getAllTrust")
	public Flux<RegistredTrust> getAllTrust(){
		return trustrepo.findAll();
	}
	
	@GetMapping("getAllCategory")
	public Flux<Category> getAllCategory(){
		return categoryrepo.findAll();
	}
	
	@PostMapping("addCategory")
	public Mono<Category> addCategory(@RequestBody Category category){
		return categoryrepo.save(category);
	}
}
