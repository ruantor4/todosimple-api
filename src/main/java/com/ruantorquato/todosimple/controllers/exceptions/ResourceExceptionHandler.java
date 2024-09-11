package com.ruantorquato.todosimple.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.DataBindingException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ruantorquato.todosimple.services.exceptions.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> handleAllUncaugghtException(Exception exception, HttpServletRequest request) {
		final String errorMessage = "Unknown error occurred";
		HttpStatus status1 = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err1 = new StandardError(Instant.now(), status1.value(), errorMessage, exception.getMessage(),
				request.getRequestURI());
		

		return ResponseEntity.status(status1).body(err1);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> handleIntegrityViolationException(DataIntegrityViolationException exception,
			HttpServletRequest request) {
		final String errorMessage = "Failed to save entity with integrity problems: ";
		HttpStatus status1 = HttpStatus.CONFLICT;
		StandardError err1 = new StandardError(Instant.now(), status1.value(), errorMessage, exception.getMessage(),
				request.getRequestURI());
		err1.setMessage("Usuário duplicado");

		return ResponseEntity.status(status1).body(err1);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> handleConstraintViolationException(ConstraintViolationException exception,
			HttpServletRequest request) {
		final String errorMessage = "Failed to validate element";
		HttpStatus status1 = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError err1 = new StandardError(Instant.now(), status1.value(), errorMessage, exception.getMessage(),
				request.getRequestURI());
		err1.setMessage("Verifique sua senha, o tamanho deve ser entre 8 e 60");

		return ResponseEntity.status(status1).body(err1);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> handleObjectNotFoundException(ObjectNotFoundException exception,
			HttpServletRequest request) {
		final String errorMessage = "Failed to find the requested element";
		HttpStatus status1 = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError err1 = new StandardError(Instant.now(), status1.value(), errorMessage, exception.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status1).body(err1);
	}
	
	@ExceptionHandler(DataBindingException.class)
	public ResponseEntity<StandardError> handleDataBindingViolationException(DataBindingException exception,
			HttpServletRequest request) {
		final String errorMessage = "Failed to save entity with associated data";
		HttpStatus status1 = HttpStatus.CONFLICT;
		StandardError err1 = new StandardError(Instant.now(), status1.value(), errorMessage, exception.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status1).body(err1);
	}
	

}
