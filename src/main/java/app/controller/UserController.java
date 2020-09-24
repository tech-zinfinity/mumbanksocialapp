package app.controller;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.User;
import app.data.repository.UserRepository;
import app.exception.EntityNotFoundException;
import app.http.request.LoginRequest;
import app.http.response.LoginResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired private UserRepository userrepo;

	@GetMapping("hi")
	public String hi() {
		return "hello";
	}
	
	@PostMapping("add") 
	public User addUser(@RequestBody User user){
		return userrepo.save(user);
	}
	
	@PostMapping("login")
	public LoginResponse login(@RequestBody LoginRequest user) throws EntityNotFoundException{
		System.out.println(user.toString());
		String loginstring = null;
		Optional<User> userdb = Optional.empty();
		if(!user.getUsername().isEmpty() && !user.getPassword().isEmpty()) {
			userdb  = userrepo.findByUsername(user.getUsername());
			if(userdb.isPresent()) {
				if(user.getPassword().equals(userdb.get().getPassword())) {
					String input = user.getUsername()+":"+user.getPassword();
					loginstring = Base64.getEncoder().encodeToString(input.getBytes());
				}else {
					throw new EntityNotFoundException();
				}
			}else {
				throw new EntityNotFoundException();
			}
		}
		return LoginResponse.builder().token(loginstring).user(userdb.get()).build();
	}
	
	@GetMapping("all")
	public Flux<User> getAll(){
		return Flux.fromStream(userrepo.findAll().stream());
	}
	
	@GetMapping("delete/{id}")
	public void deleteById(@PathVariable("id") String id){
		userrepo.deleteById(id);
	}
	
	@DeleteMapping("delete/all")
	public void deleteAll(){
		this.userrepo.deleteAll();
	}
		
}
