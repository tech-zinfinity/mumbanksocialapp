package app.controller;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.User;
import app.data.repository.UserRepository;
import app.exception.EntityNotFoundException;
import app.http.request.LoginRequest;

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
	public String login(@RequestBody LoginRequest user) throws EntityNotFoundException{
		String loginstring = null;
		if(!user.getUsername().isEmpty() && !user.getPassword().isEmpty()) {
			Optional<User> userdb  = userrepo.findByUsername(user.getUsername());
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
		return loginstring;
	}
	
}
