package app.config;

import org.springframework.stereotype.Component;

import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterConfig {

	
	public static ConfigurationBuilder configurationBuilder() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("3IUIaaAGEEdkLTNX0rjLRVRCD")
		  .setOAuthConsumerSecret("r5dyOii1DsSx4Fss8IFwOsmxfrweeMDBLlRjvjJ7WhUv3hFJkD")
		  .setOAuthAccessToken("4530268134-t9B1pECxIln23PkesQpBMV4YAtByHaOsrvO7xQy")
		  .setOAuthAccessTokenSecret("5L8FPg3WfVeoKxq3kpbGnClDKUb5DYkIQup0mP5jqPj2V");
		return cb;
	}
}
