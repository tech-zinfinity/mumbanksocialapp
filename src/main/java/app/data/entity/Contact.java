package app.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

	@Id
	private String id;
	
	private String name;
	
	private String phoneno;
}
