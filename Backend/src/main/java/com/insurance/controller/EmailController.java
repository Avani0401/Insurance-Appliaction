package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.entity.EmailRequest;
import com.insurance.service.EmailService;
import com.sendgrid.Response;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailController.
 */
@Controller
@CrossOrigin
public class EmailController {
	
	/** The emailservice. */
	@Autowired
	private EmailService emailservice;
	
	/**
	 * Sendemail.
	 *
	 * @param emailrequest the emailrequest
	 * @return the response entity
	 */
	@PostMapping("/sendemail")
	public ResponseEntity<?> sendemail(@RequestBody EmailRequest emailrequest)
	{
		Response response=emailservice.sendEmail(emailrequest);
		if(response.getStatusCode()==200||response.getStatusCode()==202)
			return ResponseEntity.ok(new JSONResponse(false,"Email sent successfully"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JSONResponse(true, "Unexpected error occured"));		    
	}
}
