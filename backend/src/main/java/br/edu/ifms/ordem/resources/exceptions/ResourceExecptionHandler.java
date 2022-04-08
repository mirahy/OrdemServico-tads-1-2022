package br.edu.ifms.ordem.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.ifms.ordem.services.exceptions.EntityNotFoundExcepetion;

@ControllerAdvice
public class ResourceExecptionHandler {
	
	@ExceptionHandler(EntityNotFoundExcepetion.class)
	public ResponseEntity<StandarError> entityNotFound(EntityNotFoundExcepetion e, HttpServletRequest request){
		
		StandarError error = new StandarError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Recurso não encontrado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);	
	}
}
