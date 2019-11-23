package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import app.config.FacebookConfig;

@SpringBootApplication
@EnableMongoAuditing
public class MumbanksocialappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MumbanksocialappApplication.class, args);
	}

}
