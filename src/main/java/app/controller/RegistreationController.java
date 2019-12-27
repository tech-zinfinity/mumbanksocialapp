package app.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.constant.UserRegistrationFlagType;
import app.data.entity.Category;
import app.data.entity.Contact;
import app.data.entity.Members;
import app.data.entity.RegisteredUsers;
import app.data.entity.RegistredTrust;
import app.data.repository.CategoryRepo;
import app.data.repository.ContactRepo;
import app.data.repository.RegisterdUserRepo;
import app.data.repository.RegistredTrustRepo;
import app.http.request.UpdateMemberRequest;
import app.utitlity.RandomStringGeneratorUtility;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

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
					return contactrepo.save(Contact.builder()
							.name(data.getName())
							.phoneno1(data.getMobileNo1())
							.phoneno2(data.getMobileNo2())
							.dob(data.getDob())
							.category(data.getCategory())
							.userId(data.getId())
							.RegistrationFlag(UserRegistrationFlagType.RUSER.toString())
							.build());
				}).doOnError(err -> new Exception());
	}
	
	@PostMapping("addTrust")
	public Flux<Object> addTrust(@RequestBody RegistredTrust user){
		return Flux.create(sink ->{
			user.getMembers().forEach(value -> {
				value.setId(String.valueOf(RandomStringGeneratorUtility.generatRandonString()));
			});
			trustrepo.save(user).subscribe(data ->{
				data.getMembers().stream().forEach(value ->{
					contactrepo
					.save(Contact.builder()
							.memId(value.getId())
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
	public Flux<Category> addCategory(@RequestBody Category category){
		return Flux.create((FluxSink<Category> sink) ->{
			categoryrepo.save(category).subscribe(data ->{
				categoryrepo.findAll().subscribe(t ->{
					sink.next(t);
				});
				sink.complete();
			});
		});
	}
	
	public Flux<Category> addCategorys(@RequestBody Category category){
		return Flux.generate( (SynchronousSink<Category> sink) ->{
			categoryrepo.save(category).subscribe(data ->{
				categoryrepo.findAll().subscribe(d ->{
					System.out.println(d);
					sink.next(d);
				});
				sink.complete();
			});
		});
	}
	
	
	@DeleteMapping("deleteCategory")
	public Mono<Void> deleteCategory(Category c){
		return categoryrepo.deleteAll();
	}
	
	public void deleteRegisteredUser(String id) {
		
	}
	
	@GetMapping("deleteTrust/{id}")
	public Mono<Void> deleteTrust(@PathVariable String id) {
		return trustrepo.deleteById(id);
	}
	
	@PostMapping("updateTrust")
	public Mono<RegistredTrust> updateTrust(@RequestBody RegistredTrust trust){
		return trustrepo.save(trust);
	}
	
	@PostMapping("updateUser")
	public Mono<RegisteredUsers> updateUser(@RequestBody RegisteredUsers user){
		return userrepo.save(user);
	}
	
	@GetMapping("deleteRUser/{id}")
	public Mono<Void> deleteRUser(@PathVariable String id) {
		return userrepo.deleteById(id);
	}
	
	@PostMapping("addMemberToTrust")
	public Mono<Members> updateMemberToTrust(@RequestBody UpdateMemberRequest request){
		return this.trustrepo.findById(request.getTrustId()).flatMap(data ->{
			Members m =request.getMember();
			m.setId(String.valueOf(RandomStringGeneratorUtility.generatRandonString()));
			ArrayList<Members> ma = data.getMembers();
			ma.add(m);
			data.setMembers(ma);
			return trustrepo.save(data).flatMap(value ->{
				return contactrepo
				.save(Contact.builder()
						.memId(m.getId())
						.name(m.getMemname())
						.phoneno1(m.getMobilenoF())
						.phoneno2(m.getMobilenoS())
						.dob(m.getDob())
						.category(value.getCategory()).build())
				.flatMap(tata ->{
					return Mono.just(m);
				});
			});
		});
	}
	
	@PostMapping("deleteMemberOfTrust")
	public Mono<Members> deleteMemberOfTrust(@RequestBody UpdateMemberRequest request){
		return this.trustrepo.findById(request.getTrustId()).flatMap(data ->{
			var m = data.getMembers();
			var bool = m.removeIf(some -> some.getId().equals(request.getMember().getId()));
			data.setMembers(m);
			if(bool==true) {
				return trustrepo.save(data).flatMap(test ->{
					return contactrepo.deleteByMemId(request.getMember().getId()).flatMap(value ->{
						return Mono.just(request.getMember());
					});
				});
			}else {
				return Mono.error(new Exception());
			}
		});
	}
}
