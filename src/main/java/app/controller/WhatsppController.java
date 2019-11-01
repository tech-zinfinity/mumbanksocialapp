package app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("wp")
public class WhatsppController {

	@GetMapping("hi")
	public String hi() {
		return "hello";
	}
}
