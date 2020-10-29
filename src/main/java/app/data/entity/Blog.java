package app.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Document(collection="BlogCategory")
public class Blog {

	@Id
	private String id;
	private String title;
	@CreatedDate
	private LocalDateTime createdOn;
	@LastModifiedDate
	private LocalDateTime updatedOn;
	private LocalDateTime publishedOn;
	private User author;
	private String coverImage;
	private String paragraph;
	private BlogCategory category;
	private List<BlogTag> tags;
	private int veiwCount;
	private int likes;
	private int commetCount;
	private int disLikes;
	private List<BlogComment> comments;
	private String status;
}
