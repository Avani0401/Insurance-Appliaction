package com.insurance.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.insurance.entity.User;
import com.insurance.helper.ErrorResponse;
import com.insurance.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
public class UserController {

    /** The user service. */
    @Autowired
    private UserService userService;

    /**
     * Inits the role and user.
     */
    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    /**
     * Register new underwriter.
     *
     * @param underwriter the underwriter
     * @param result the result
     * @return the response entity
     */
    @PostMapping({"/registerNewUnderwriter"})
    public ResponseEntity<?> registerNewUnderwriter(@RequestBody @Valid User underwriter, BindingResult result){
    	if(userService.checkIfEmailExist(underwriter.getEmail())) {
    		ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setMessage("Email already exists!! Please try another one.");
    		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    	}
    	else if(userService.checkIfUserExist(underwriter.getUserName())) {
    		ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setMessage("Username already exists!! Please Try another one.");
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
    		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    	}
    	else
    
    		return new ResponseEntity<>(userService.registerNewUnderwriter(underwriter), HttpStatus.OK);
		
    }
  
    /**
     * Register new user.
     *
     * @param user the user
     * @param result the result
     * @return the response entity
     */
    @PostMapping({"/registerNewUser"})
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid User user, BindingResult result) {
    	if(userService.checkIfEmailExist(user.getEmail())) {
    		ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setMessage("Email already exists!! Please try another one.");
    		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    	}
 
    else if(userService.checkIfUserExist(user.getUserName())) {
    		ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setMessage("Username already exists!! Please try another one.");
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
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
    	
    	else
    		return new ResponseEntity<>(userService.registerNewUser(user), HttpStatus.OK);
    }
    
 
    
    /**
     * All user list.
     *
     * @return the response entity
     */
    @GetMapping("/alluserlist")
    @PreAuthorize("hasAnyRole('Admin','Underwriter')")
    public ResponseEntity<?> allUserList(){
    	List<User> user = this.userService.allUserList();
    	if(user.isEmpty()) {
    		ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setMessage("Record not found");
    		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    	}
        return new ResponseEntity<>(user, HttpStatus.OK); 
    }
    
    
    
    /**
     * Gets the underwriter requests.
     *
     * @return the underwriter requests
     */
    @GetMapping("/getUnderwriterRequests")
	@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getUnderwriterRequests(){
		List<User> underwriters = this.userService.getUnderwriterRequests();
		if(underwriters.size() == 0) {
	    	ErrorResponse errorResponse = new ErrorResponse();
	    	errorResponse.setMessage("Record not found");
	    	return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }
	        return new ResponseEntity<>(underwriters, HttpStatus.OK); 	
    }
    
    /**
     * Approve underwriter request.
     *
     * @param underwriter the underwriter
     * @return the response entity
     */
    @PutMapping("/approveUnderwriterRequests")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<User> approveUnderwriterRequest(@RequestBody User underwriter){
    	try {
    		User localUnderwriter =  userService.approveUnderwriterRequest(underwriter);
    		return ResponseEntity.of(Optional.of(localUnderwriter));
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}		
    }
    
    /**
     * Reject underwriter request.
     *
     * @param underwriter the underwriter
     * @return the response entity
     */
    @PutMapping("/rejectUnderwriterRequests")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<User> rejectUnderwriterRequest(@RequestBody User underwriter){
    	try {
    		User localUnderwriter =  userService.rejectUnderwriterRequest(underwriter);
    		return ResponseEntity.of(Optional.of(localUnderwriter));
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}		
    }	
}
