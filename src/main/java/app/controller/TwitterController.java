package app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.service.TwitterService;
import app.utitlity.FileConvertingUtility;
import twitter4j.TwitterException;

@RestController
@RequestMapping("tweet")
public class TwitterController {

	@Autowired TwitterService twitterService;
	
	@GetMapping("send")
	public String getTweet() {
		try {
			return twitterService.createTweet("some");
		} catch (TwitterException e) {
			System.out.println(e);
			e.printStackTrace();
			return e.getErrorMessage();
		}
	}
	
	@PostMapping("sendImage")
	public String tweetWithImage(@RequestParam("message") String message, @RequestParam("file") MultipartFile file) {
		try {
			return twitterService.addImage(message, FileConvertingUtility.convert(file));
		} catch (TwitterException e) {
			System.out.println(e);
			return e.getLocalizedMessage();
		} catch (IOException e) {
			return e.getLocalizedMessage();
		}
	}
}
