package app.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

	@Id
	private String id;
	
	private String name;
	
	private String code;
	
}
