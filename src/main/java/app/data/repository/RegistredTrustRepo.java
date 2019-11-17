package app.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.RegistredTrust;

public interface RegistredTrustRepo extends ReactiveMongoRepository<RegistredTrust, String>{

}
