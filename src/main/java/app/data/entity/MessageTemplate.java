package app.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("message-template")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageTemplate {

	@Id
	private String id;
	private String message;
	private String title;
	private boolean active;
	private String auther;
	private String type;
}
