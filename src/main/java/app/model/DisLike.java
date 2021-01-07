package app.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class DisLike {
	
	private String userId;
	private String userName;
	@CreatedDate
	private LocalDateTime createdOn;
}
