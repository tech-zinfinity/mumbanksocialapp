package app.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import app.data.entity.MessageTemplate;

public interface MessageTemplateRepo extends ReactiveMongoRepository<MessageTemplate, String>{

}
