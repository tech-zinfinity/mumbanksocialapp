package app.http.request;

import app.data.entity.Members;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMemberRequest {

	private String trustId;
	private Members member;
}
