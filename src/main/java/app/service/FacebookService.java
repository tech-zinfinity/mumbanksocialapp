package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import app.config.FacebookConfig;
import app.model.FacebookAccessToken;
import reactor.core.publisher.Mono;

@Service
public class FacebookService {

	public static final String fbBaseUrl = "https://graph.facebook.com/oauth/access_token?";
	public static final String fbBaseUrllong = "https://graph.facebook.com/v5.0/oauth/access_token?grant_type=fb_exchange_token&";
	@Autowired private FacebookConfig config;
	
	public Mono<FacebookAccessToken> getAccessToken(String token) {
		return WebClient.create()
				.get()
				.uri(fbBaseUrllong+"client_id="+config.appId+"&client_secret="+config.appSecret+
						"&fb_exchange_token="+token)
				.retrieve()
				.bodyToMono(FacebookAccessToken.class);
	}
	
	public Mono<FacebookAccessToken> getLongedLivedAccessToken(){
		return WebClient.create()
				.get()
				.uri(fbBaseUrllong+"client_id="+config.appId+"&client_secret="+config.appSecret+
						"&fb_exchange_token="+config.getToken())
				.retrieve()
				.bodyToMono(FacebookAccessToken.class);
	}
	
	public Mono<String> getMe(String token){
		return WebClient.create()
				.get()
				.uri("https://graph.facebook.com/me?fields=id,name&access_token="+token)
				.retrieve()
				.bodyToMono(String.class);
	}
	
	public Mono<String> getMyFeed(String token){
		return WebClient.create()
				.get()
				.uri("https://graph.facebook.com/me/feed?access_token="+token)
				.retrieve()
				.bodyToMono(String.class);
	}
	
	public Mono<String> postStatus(String token, String message){
		return WebClient.create()
				.get()
				.uri("https://graph.facebook.com/me/feed?message="+message+"&access_token="+token)
				.retrieve()
				.bodyToMono(String.class);
	}
	
}
