package com.insurance.advice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.insurance.helper.EmptyListException;
import com.insurance.helper.ErrorResponse;
import com.insurance.helper.InvalidInputException;

import javassist.tools.rmi.ObjectNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerAdvice.
 */
@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {
	


	/**
	 * Policy not found.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<String> policyNotFound(ObjectNotFoundException e){
		
		return  new ResponseEntity<String>("policy not found",HttpStatus.BAD_REQUEST);	
		
	}
	
	/**
	 * Handle null object.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullObject(NullPointerException e){
		return  new ResponseEntity<String>("Data cannot be null",HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handle empty input.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<String> handleEmptyInput(InvalidInputException e ){
		return new ResponseEntity<String>("Please provide valid data ", HttpStatus.BAD_REQUEST);
	}
	
	
	
	/**
	 * Handle sql exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> handleSqlException(SQLIntegrityConstraintViolationException e){
		return new ResponseEntity<String>("please add valid data", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handle null object.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(EmptyListException.class)
	public ResponseEntity<?> handleNullObject(EmptyListException e)  {


		return new ResponseEntity<>("No data found in Database", HttpStatus.NOT_FOUND);
	
	}
	
	/**
	 * Handle null object.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNullObject(NoSuchElementException e)  {


		return new ResponseEntity<>("No such element exists", HttpStatus.BAD_REQUEST);
	
	}
	


	

    /**
     * Handle http request method not supported.
     *
     * @param ex the ex
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {
    
    	return new ResponseEntity<>("Method not supported", HttpStatus.METHOD_NOT_ALLOWED);
    }
}


