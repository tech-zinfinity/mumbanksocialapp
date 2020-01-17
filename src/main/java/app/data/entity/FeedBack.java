package app.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedBack {
	
	@Id
	private String id;

	private String name;
	private String mobileno;
	private String email;
	private String feedback;
	
	@CreatedDate
	private LocalDateTime createdOn;
}
