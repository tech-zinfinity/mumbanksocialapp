package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.FeedBack;
import app.data.repository.FeedBackRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("feedback")
public class FeedbackController {
	
	@Autowired FeedBackRepo repo;	

	@PostMapping("add")
	public Mono<FeedBack> addFeedback(@RequestBody FeedBack f){
		return repo.save(f);
	}
	
	@GetMapping("getAll")
	public Flux<FeedBack> getAllFeedback(){
		return repo.findAll().sort((obj1, obj2)->obj2.getCreatedOn().compareTo(obj1.getCreatedOn()));
	}
	
	@GetMapping("getbById/{id}")
	public Mono<FeedBack> getFeedback(@PathVariable String id){
		return repo.findById(id);
	}
	
	@PostMapping("update")
	public Mono<FeedBack> update(@RequestBody FeedBack f){
		return repo.save(f);
	}
	
	@PostMapping("delete/{id}")
	public Mono<Void> delete(@PathVariable String id){
		return repo.deleteById(id);
	}
}
