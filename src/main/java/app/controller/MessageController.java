package app.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import app.data.entity.MessageTemplate;
import app.data.repository.MessageTemplateRepo;
import app.http.request.SendMessageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("message")
public class MessageController {
	
	@Autowired MessageTemplateRepo messageTemplateRepo;

	@PostMapping("send")
	public Flux<String> sendMessage(@RequestBody SendMessageRequest request) {
		
		System.out.println("request"+request.toString());

		return Flux.fromStream(request.getNumbers().stream()
		.map(value -> {
			String uri = "http://sms.mdsmedia.in/http-api.php?"
					+ "username=Sahakar&password=pass1234&senderid=SAIRAJ&route=1&number="
					+ value+"&message="+request.getMessage();
			
			Mono<String> web  = WebClient.create()
			.post()
			.uri(uri)
			.retrieve()
			.bodyToMono(String.class);
			
			web.subscribe(data -> {System.out.println(value);});
			return value;
		}).collect(Collectors.toList()).stream());
	}
	
	@PostMapping("add")
	private Mono<MessageTemplate> addMessage(@RequestBody MessageTemplate template){
		return messageTemplateRepo.save(template);
	}
	
	@PostMapping("delete/{id}")
	private Mono<Void> deleteMessage(@PathVariable String id){
		return messageTemplateRepo.deleteById(id);
	}
	
	@GetMapping("getAll")
	private Flux<MessageTemplate> getAll(){
		return this.messageTemplateRepo.findAll();
	}
	
	
}
