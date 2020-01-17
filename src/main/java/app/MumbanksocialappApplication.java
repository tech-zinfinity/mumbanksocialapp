package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.config.FacebookConfig;

@SpringBootApplication
@EnableMongoAuditing
@EnableScheduling
@EnableCaching
public class MumbanksocialappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MumbanksocialappApplication.class, args);
	}

}
