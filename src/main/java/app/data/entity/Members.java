package app.data.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Members {

	private String memname;
	private String mobileno1;
	private String mobileno2;
	private String emailId;
	private LocalDate dob;
	private String category;

}
