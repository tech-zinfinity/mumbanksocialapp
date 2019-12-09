package app.data.entity;

import java.time.LocalDate;

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
@Document(collection="rusers")
public class RegisteredUsers {

	@Id
	private String id;
	private String name;
	private String mobileNo1;
	private String mobileNo2;
	private String emailId;
	private LocalDate dob;
	private String category;

}
