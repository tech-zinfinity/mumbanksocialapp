package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.FacebookAccessToken;
import app.service.FacebookService;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("fb")
public class FacebookController {

	@Autowired private FacebookService fbservice;
	
	@GetMapping("access/usertoken")
	public Mono<FacebookAccessToken> getFacebookAccessToken(){
		return fbservice.getLongedLivedAccessToken();
	}
	
	@GetMapping("access/longtoken")
	public Mono<FacebookAccessToken> getFbAccess(){
		return Mono.just(FacebookAccessToken.builder().access_token("EAAi4IyI9aj0BAONhUMcmoNnykkfbhnDaXZAB1aNR0vZBSwZCASf3gEC6sXMbCjymCJ03v3YLDio8AOeAE1KClL4TKYrZB70iLkswSUjg5mqvBmtCdFnhrFOd1uOb2Uw1fEVvQ8WzfomTu08MlQ44sOdPF3ZCM4sqSynomgw1t7QZDZD").build());
	}
	
	@GetMapping("access/apptoken/{token}")
	public Mono<FacebookAccessToken> getFbAppToken(@PathVariable String token){
		return fbservice.getAccessToken(token);
	}
	
	@GetMapping("me/{token}")
	public Mono<String> getMe(@PathVariable String token){
		return fbservice.getMe(token);
	}
	
	@GetMapping("me/feed/{token}")
	public Mono<String> getFeed(@PathVariable String token){
		return fbservice.getMyFeed(token);
	}
	
	@GetMapping("me/feed/{token}/{message}")
	public Mono<String> postStatus(@PathVariable String token, @PathVariable String message){
		return fbservice.postStatus(token, message);
		
	}
}
