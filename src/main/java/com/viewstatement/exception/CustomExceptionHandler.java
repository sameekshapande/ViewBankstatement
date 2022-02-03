package com.viewstatement.exception;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ServletRequestBindingException.class)
	public final ResponseEntity<Object> handleHeaderException(Exception ex,WebRequest request)
	{
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Bad Request",details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);		
	}
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex,WebRequest request)
    {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Server Error",details);
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(UserAccessDeniedException.class)
    public final ResponseEntity<Object> handleAllExceptions(UserAccessDeniedException ex,WebRequest request)
    {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("User does not have access to view the statement",details);
		return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Object> handleAllExceptions(ValidationException ex,WebRequest request)
    {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Validation Error Occured",details);
		return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(AccountIdNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(AccountIdNotFoundException ex,WebRequest request)
    {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Validation Error Occured",details);
		return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
}
