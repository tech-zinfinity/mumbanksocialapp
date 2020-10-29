package app.data.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Document(collection="BlogComment")
public class BlogComment {

	@Id
	private String id;
	private String message;
	private User user;
	private LocalDateTime createdOn;
	private String blogId;
	
}
