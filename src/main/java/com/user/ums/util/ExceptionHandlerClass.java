package com.user.ums.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.user.ums.exception.UserEmailAlreadyPresentException;
import com.user.ums.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			List<ObjectError> allErrors = ex.getAllErrors();
			Map<String, String> errors=new HashMap<>();
			allErrors.forEach(error -> {
			FieldError fieldError=(FieldError) error;
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
		return errorStructure(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
	}

	private ResponseEntity<Object> errorStructure(HttpStatus status,String message,Object rootCause)
	{
		return new ResponseEntity<Object>(Map.of("status",status.value(),
				"message",message,
				"rootcause",rootCause),status);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> userNotFound(UserNotFoundException userNotFoundException)
	{
		return errorStructure(HttpStatus.NOT_FOUND, userNotFoundException.getMessage(), "THE USER YOU WERE LOOKING FOR IS NOT FOUND IN DATABASE");

	}
	@ExceptionHandler(UserEmailAlreadyPresentException.class)
	public ResponseEntity<Object> emailAlreadyPresent(UserEmailAlreadyPresentException emailAlreadyPresentException)
	{
		return errorStructure(HttpStatus.BAD_REQUEST, emailAlreadyPresentException.getMessage(), "THIS EXCEPTION IS DUE TO THAT THE EMAIL ID IS ALREADY REGISTERED WITH OTHER USER PLEASE USE DIFFERENT EMAIL ID");


	}
}
