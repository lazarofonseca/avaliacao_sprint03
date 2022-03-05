package br.com.lazaro.api.controllers.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.lazaro.api.services.exception.ErrorException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ErrorException.class)
	public ResponseEntity<StandarError> modelNotFound(ErrorException e, HttpServletRequest request){
		
		StandarError error = new StandarError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Id not found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}	
	
}
