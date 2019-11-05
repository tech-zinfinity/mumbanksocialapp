package app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import app.http.request.SendWhatsappMessagRequest;
import reactor.core.publisher.Flux;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("wp")
public class WhatsppController {

	@GetMapping("hi")
	public String hi() {
		return "hello";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("send")
	public String sendWhatsappMessage(@RequestBody SendWhatsappMessagRequest request)  {
		
		request.getNumbers().stream().forEach(data -> {
			System.out.println(data.toString());
		});
		
		 request.getNumbers().stream().forEachOrdered(data ->{
			 String uri = "http://bulkmsg.onlinemart.co.in/http-api.php?"
						+ "username=Abhay1&password=pass12345&senderid=SAIRAJ&route=1&"
						+ "number=9773637141&message=something as test";
				System.out.println("coming here");
				Flux<String> response  = WebClient.create()
				.post()
				.uri(uri)
				.retrieve()
				.bodyToFlux(String.class);
				
				((Flux<String>) response.subscribe(value -> {
					System.out.println("reaching here ");
					System.out.println(value);
				})).doOnError(err ->{
					System.out.println("drop err"+err.getLocalizedMessage());
				});
				return;
		});
		 return "succesfull";
	}
}
