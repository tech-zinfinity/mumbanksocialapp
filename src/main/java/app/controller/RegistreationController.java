package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.RegisteredUsers;
import app.data.entity.RegistredTrust;
import app.data.repository.RegisterdUserRepo;
import app.data.repository.RegistredTrustRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("register")
public class RegistreationController {

	@Autowired
	private RegisterdUserRepo userrepo;
	@Autowired
	private RegistredTrustRepo trustrepo;
	
	@PostMapping("addUser")
	public Mono<RegisteredUsers> addUser(@RequestBody RegisteredUsers user){
		System.out.println("adding things up");
		return userrepo.save(user);
	}
	@PostMapping("addTrust")
	public Mono<RegistredTrust> addTrust(@RequestBody RegistredTrust user){
		return trustrepo.save(user);
	}
	@GetMapping("getAllUser")
	public Flux<RegisteredUsers> getAllUser(){
		return userrepo.findAll();
	}
	@GetMapping("getAllTrust")
	public Flux<RegistredTrust> getAllTrust(){
		return trustrepo.findAll();
	}
}
