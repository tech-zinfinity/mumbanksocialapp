package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Photo;
import app.data.repository.PhotoRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("photo")
public class PhotoController {

	@Autowired PhotoRepo photorepo;
	
	@PostMapping("add")
	public Mono<Photo> addPhoto(@RequestBody Photo photo){
		return photorepo.save(photo);
	}
	
	@GetMapping("getAll")
	public Flux<Photo> getAllPhotos(){
		return photorepo.findAll();
	}
	
	@GetMapping("delete/{id}")
	public Mono<Void> deletePhoto(@PathVariable String id){
		return photorepo.deleteById(id);
	}
}

