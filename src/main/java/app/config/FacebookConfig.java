package app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

import lombok.Data;

@PropertySource("classpath:application.properties")
@Configuration
@Data
public class FacebookConfig {
	
	
	@Value( "${spring.social.facebook.app-id}" )
	public String appId;
	
	@Value("${spring.social.facebook.app-secret}")
	public String appSecret;
	
	public String token = "EAAi4IyI9aj0BANnKm5SsVTRRIvMSBjQZBnsrwKm2VM5kLvY8aOZCrE2YYyJOsRWjiVKnMKlZAS8XhuT1jyFP28kG5ZA7SZAWLyZBJEZAEZBdW3CX86dCJV1FPjQuHCG4TejZAHxlqmAjZBEZC5BeTRg7WkMI3sX7gonir4WPJ0ZANQpa1pEptIJuAYaDxKFIwggZA4OR1THSamJzHPwZDZD";


}
