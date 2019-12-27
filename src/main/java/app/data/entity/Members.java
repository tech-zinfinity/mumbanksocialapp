package app.data.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Members {

	@Id
	private String id;
	private String memname;
	private String mobilenoF;
	private String mobilenoS;
	private String emailId;
	private LocalDate dob;
	private String category;

}
