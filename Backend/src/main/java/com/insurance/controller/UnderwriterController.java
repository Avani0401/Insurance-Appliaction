package com.insurance.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.entity.PolicyHolder;
import com.insurance.entity.User;
import com.insurance.helper.ErrorResponse;
import com.insurance.service.UnderwriterService;


// TODO: Auto-generated Javadoc
/**
 * The Class UnderwriterController.
 */
@RestController
public class UnderwriterController {
	
	/** The underwriter service. */
	@Autowired
	private UnderwriterService underwriterService;
	
	/**
	 * Gets the pendingusers.
	 *
	 * @return the pendingusers
	 */
	@GetMapping("/pendingUser")
	@PreAuthorize("hasRole('Underwriter')")
	public ResponseEntity<?> getpendingusers() {
		try {
			List<PolicyHolder> policyholder = this.underwriterService.getpendingusers();
			return ResponseEntity.of(Optional.of(policyholder));
		} catch (Exception e) {
			ErrorResponse er = new ErrorResponse();
			er.setMessage("No data found");
			return new ResponseEntity<>(er.getMessage(),HttpStatus.NOT_FOUND);
		}		
	}
	
	
	/**
	 * All underwriter list.
	 *
	 * @return the response entity
	 */
	@GetMapping("/allUnderwriterlist")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<?>  allUnderwriterList(){
		List<User> underwriterlist = underwriterService.allUnderwriterList();
		if (underwriterlist.isEmpty()) {
			ErrorResponse er = new ErrorResponse();
			er.setMessage("There is no underwriter");
			return new ResponseEntity<>(er.getMessage(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(underwriterlist, HttpStatus.OK);
	}
	
	
	/**
	 * Approve pending request.
	 *
	 * @param policyHolder the policy holder
	 * @return the response entity
	 */
	@PutMapping("/approveBuyRequest")
	@PreAuthorize("hasRole('Underwriter')")
	public ResponseEntity<?> approvePendingRequest(@RequestBody PolicyHolder policyHolder) {
		try {
		PolicyHolder policyholder = this.underwriterService.approvependingusers(policyHolder);
		return new ResponseEntity<>(policyholder,HttpStatus.OK);
		}catch(Exception e) {
			ErrorResponse er = new ErrorResponse();
			er.setMessage(" Error!!!! ");
			return new ResponseEntity<>(er.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	/**
	 * Reject pending request.
	 *
	 * @param policyHolder the policy holder
	 * @param reason the reason
	 * @return the response entity
	 */
	@PutMapping("/rejectBuyRequest")
	@PreAuthorize("hasRole('Underwriter')")
	public ResponseEntity<?> rejectPendingRequest(@RequestBody PolicyHolder policyHolder, @RequestParam String reason) {
		try {
		PolicyHolder policyholder = this.underwriterService.rejectpendingusers(policyHolder,reason);
		return new ResponseEntity<>(policyholder,HttpStatus.OK);
		}catch(Exception e) {
			ErrorResponse er = new ErrorResponse();
			er.setMessage(" Error!!!! ");
			return new ResponseEntity<>(er.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	
}


