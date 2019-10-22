package app.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private String id;
	
	private String username;
	
	private String password;
	
	private String[] permissions;
	
	private String role;
	
	private boolean status;
}
