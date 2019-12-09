package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.repository.ContactRepo;
import reactor.core.publisher.Mono;

@Service
public class ContactService {

	@Autowired private ContactRepo contactrepo;
	
}
