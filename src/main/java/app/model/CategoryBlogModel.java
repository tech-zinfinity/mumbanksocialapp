package app.model;

import java.util.List;

import app.data.entity.Blog;
import app.data.entity.BlogCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CategoryBlogModel {

	private BlogCategory category;
	private List<Blog> blogs;
}
