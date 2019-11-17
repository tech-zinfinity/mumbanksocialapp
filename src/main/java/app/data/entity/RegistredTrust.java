package app.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="rtrusts")
public class RegistredTrust {
	
	@Id
	private String id;
	private String name;
	private String memberfirstName;
	private String membersecondName;
	private String mobileno;
	private String emailId;
	private String dob;
}
