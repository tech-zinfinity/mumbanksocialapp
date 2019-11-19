package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.repository.RegistredTrustRepo;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("reset")
public class ResetDataBaseController {
	
	@Autowired private RegistredTrustRepo rtrusetrepo;

	@DeleteMapping("RTrust")
	public Mono<Void> resetRTrust() {
		return rtrusetrepo.deleteAll();
	}
}
