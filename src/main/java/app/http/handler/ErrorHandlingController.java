package app.http.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.constant.ExceptionCodeConstants;
import app.exception.EntityNotFoundException;
import app.http.response.ExceptionResponse;
import app.utitlity.RandomStringGeneratorUtility;

@RestControllerAdvice
public class ErrorHandlingController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> generateCommonException(Exception e) throws Exception{
		e.printStackTrace();
		return new ResponseEntity<ExceptionResponse>(		
				ExceptionResponse.builder()
				.id(RandomStringGeneratorUtility.generatRandonString())
				.code(ExceptionCodeConstants.GENERALEXCEPTIONCODE)
				.message("General Exception")
				.entity("GENERAL")
				.build(), HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionResponse> generateEntityNotFoundException(EntityNotFoundException e) throws EntityNotFoundException{
		return new ResponseEntity<ExceptionResponse>(		
				ExceptionResponse.builder()
				.id(RandomStringGeneratorUtility.generatRandonString())
				.code(ExceptionCodeConstants.ENTITYNOTFOUNDEXCEPTIONCODE)
				.message("Entity Not Found")
				.entity(e.getEntity())
				.build(), HttpStatus.NOT_FOUND);
	}
}
