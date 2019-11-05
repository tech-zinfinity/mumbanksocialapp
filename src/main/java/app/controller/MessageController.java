package app.controller;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import app.http.request.SendMessageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("message")
public class MessageController {

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
	
}
