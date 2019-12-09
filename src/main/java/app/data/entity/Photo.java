package app.data.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "photo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

	@Id
	private String id;
	private String label;
	private String url;
	private String file;
	private String type;
	private String firestoreURL;
	private String redirectionUrl;
	
	@CreatedBy
	private String user;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedBy
	private String lastModifiedUser;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
}
