package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.repository.ContactRepo;
import app.data.repository.MessageTemplateRepo;
import app.data.repository.PhotoRepo;
import app.data.repository.RegisterdUserRepo;
import app.data.repository.RegistredTrustRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("reset")
public class ResetDataBaseController {
	
	@Autowired private RegistredTrustRepo rtrusetrepo;
	@Autowired private PhotoRepo photorepo;
	@Autowired private ContactRepo contactrepo;
	@Autowired private MessageTemplateRepo templaterepo;
	@Autowired private RegisterdUserRepo usersrepo;
	
	@DeleteMapping("RTrust")
	public Mono<Void> resetRTrust() {
		return rtrusetrepo.deleteAll();
	}
	
	@DeleteMapping("Photo")
	public Mono<Void> resetPhoto() {
		return photorepo.deleteAll();
	}
	
	@DeleteMapping("all")
	public Flux<Object> factoryReset(){
		return Flux.create(emitter ->{
			rtrusetrepo.deleteAll().subscribe(t -> emitter.next(t));
			photorepo.deleteAll().subscribe(t -> emitter.next(t));
			contactrepo.deleteAll().subscribe(t -> emitter.next(t));
			templaterepo.deleteAll().subscribe(t -> emitter.next(t));
			usersrepo.deleteAll().subscribe(t -> emitter.next(t));
			
			emitter.complete();
		});
	}
}
