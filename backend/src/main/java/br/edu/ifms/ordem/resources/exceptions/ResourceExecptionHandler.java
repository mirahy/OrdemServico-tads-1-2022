package br.edu.ifms.ordem.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.ifms.ordem.services.exceptions.DataBaseExcepetion;
import br.edu.ifms.ordem.services.exceptions.ResourceNotFoundExcepetion;

@ControllerAdvice
public class ResourceExecptionHandler {
	
	@ExceptionHandler(ResourceNotFoundExcepetion.class)
	public ResponseEntity<StandarError> entityNotFound(ResourceNotFoundExcepetion e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandarError error = new StandarError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Recurso não encontrado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);	
	}
	
	@ExceptionHandler(DataBaseExcepetion.class)
	public ResponseEntity<StandarError> dataBase(DataBaseExcepetion e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandarError error = new StandarError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("DataBase exception");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);	
	}
}
