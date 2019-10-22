package app.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EntityNotFoundException extends Exception{
	
	private static final long serialVersionUID = 5050233025377290136L;

	private int code;
	
	private String message;
	
	private String entity;
	
	public EntityNotFoundException(String entity){
		this.entity = entity;
	}
}
