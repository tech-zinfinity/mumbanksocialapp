package app.data.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation="registeredtrusts")
public class RegistredTrust {
	
	private String name;
	private String memberfirstName;
	private String membersecondName;
	private String mobileno;
	private String emailId;
	private String dob;
}
