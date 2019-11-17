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
@Document(collation="registredusers")
public class RegisteredUsers {

	private String name;
	private String mobileNo;
	private String emailId;
	private String dob;
}
