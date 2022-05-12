package com.insurance.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.entity.Nominee;
import com.insurance.helper.ErrorResponse;
import com.insurance.service.NomineeService;
import com.insurance.service.PolicyHolderService;

// TODO: Auto-generated Javadoc
/**
 * The Class NomineeController.
 */
@RestController
public class NomineeController {

	/** The nominee service. */
	@Autowired
	private NomineeService nomineeService;
	
/** The format. */
DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	/**
	 * Gets the date from string.
	 *
	 * @param string the string
	 * @param format the format
	 * @return the date from string
	 */
	public LocalDate getDateFromString(String string, DateTimeFormatter format)
	{
		
		// Converting the string to date
		// in the specified format
		return LocalDate.parse(string, format);
		
	}
	
	/**
	 * Adds the nominee.
	 *
	 * @param nominee the nominee
	 * @param policyHolderId the policy holder id
	 * @param result the result
	 * @return the response entity
	 */
	@PostMapping({"/registerNominee/{policyHolderId}"})
	@PreAuthorize("hasRole('User')")
    public ResponseEntity<?> addNominee(@RequestBody @Valid Nominee nominee,@PathVariable("policyHolderId") int policyHolderId, BindingResult result){
		Nominee local = nomineeService.addNominee(nominee,policyHolderId);
		LocalDate dob = getDateFromString(nominee.getNomineeDOB(),format);
		
		LocalDate today=LocalDate.now();
		int age= Period.between(dob, today).getYears();
		if(age < 18) {
			ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setMessage("Nominee should be above the age of 18.");
    		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		else if (result.hasErrors()) {
    		String err="";
    		List<FieldError> errors = result.getFieldErrors();
    		
    		   for (FieldError error : errors ) {
    			   
    		     err+=error.getDefaultMessage();
    		     break;
    		   } 
    		   ErrorResponse errorResponse = new ErrorResponse();
       		    errorResponse.setMessage(err);
    		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    	}
		else	
			return new ResponseEntity<>(local, HttpStatus.OK);
        		
    }
}
