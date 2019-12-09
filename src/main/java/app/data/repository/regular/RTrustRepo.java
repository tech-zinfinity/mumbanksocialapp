package app.data.repository.regular;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.data.entity.RegistredTrust;

public interface RTrustRepo extends MongoRepository<RegistredTrust, String>{

}
