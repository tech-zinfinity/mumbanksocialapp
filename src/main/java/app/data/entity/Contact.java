package app.data.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {

	@Id
	private String id;
	
	private String name;
	
	private String phoneno1;
	
	private String phoneno2;
	
	private LocalDate dob;
	
	private String category;
	
	private String userId;
	
	private String RegistrationFlag;
	
	private String memId;
}
