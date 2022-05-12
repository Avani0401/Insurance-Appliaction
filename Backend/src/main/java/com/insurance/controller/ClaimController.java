package com.insurance.controller;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

import com.insurance.entity.Claim;
import com.insurance.entity.PolicyHolder;
import com.insurance.helper.ErrorResponse;
import com.insurance.service.ClaimService;
import com.insurance.service.PolicyHolderService;

// TODO: Auto-generated Javadoc
/**
 * The Class ClaimController.
 */
@RestController
public class ClaimController {
	
	/** The claim service. */
	@Autowired
	private ClaimService claimService;
	
	/** The policy holder service. */
	@Autowired
	private PolicyHolderService policyHolderService;
	
	/** The logger. */
	private static Logger logger =LogManager.getLogger(ClaimController.class);
	
	/**
	 * Gets the claim requests.
	 *
	 * @return the claim requests
	 */
	@GetMapping("/getAdminClaimRequests")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<?> getClaimRequests(){
		List<Claim> requests = claimService.getClaimRequests();
		if(requests.isEmpty())
		{
			ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setMessage("Record not found");
    		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(requests, HttpStatus.OK);
	}
	
	/**
	 * Gets the user claim request.
	 *
	 * @return the user claim request
	 */
	@GetMapping("/getUserClaimRequests")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<?> getUserClaimRequest() {
		List<PolicyHolder> policyHolder=this.policyHolderService.history();
		List<Claim> requests = this.claimService.getUserClaimRequests(policyHolder);
		if(requests.isEmpty())
		{
			ErrorResponse errorResponse = new ErrorResponse();
    		errorResponse.setMessage("Record not found");
    		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(requests, HttpStatus.OK);
	}
	
	/**
	 * Creates the claim request.
	 *
	 * @param claimReq the claim req
	 * @param policyHolderId the policy holder id
	 * @return the response entity
	 */
	@PostMapping("{policyHolderId}/claim")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<?> createClaimRequest(@RequestBody Claim claimReq,@PathVariable("policyHolderId") int policyHolderId) {
		try {
			PolicyHolder policyHolder=this.policyHolderService.findByPolicyHolderId(policyHolderId);

			if(Boolean.FALSE.equals(policyHolder.getHasClaimed())) {
				Claim claimreq = this.claimService.createClaimRequest(claimReq,policyHolder);
				return ResponseEntity.of(Optional.of(claimreq));
			}else {
				ErrorResponse errorResponse = new ErrorResponse();
	    		errorResponse.setMessage("User has already claimed for this policy.");
	    		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * Creates the claim request by nominee.
	 *
	 * @param claimReq the claim req
	 * @param policyHolderId the policy holder id
	 * @param nomineeId the nominee id
	 * @return the response entity
	 */
	@PostMapping("claimbyNominee/{policyHolderId}/{nomineeId}")//without login
	public ResponseEntity<?> createClaimRequestByNominee(@RequestBody Claim claimReq,@PathVariable("policyHolderId") int policyHolderId ,@PathVariable("nomineeId") int nomineeId) {
			
			PolicyHolder policyHolder=this.policyHolderService.findByPolicyHolderId(policyHolderId);
			
			if (this.claimService.checkClaimExist(policyHolder)) {
				ErrorResponse errorResponse = new ErrorResponse();
	    		errorResponse.setMessage("User has already claimed for this policy.");
	    		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
			}
					
			Claim claimreq = this.claimService.createClaimRequestByNominee(claimReq,policyHolderId,nomineeId);
			
			if(claimreq.getPolicyholder() == null) {
				ErrorResponse errorResponse = new ErrorResponse();
	    		errorResponse.setMessage("Invalid Credentials! Please check your Ids");
	    		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    	
			}
//			else if(Boolean.TRUE.equals(policyHolder.getHasClaimed())) {
//				ErrorResponse errorResponse = new ErrorResponse();
//	    		errorResponse.setMessage("User has already claimed for this policy.");
//	    		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
//			}
	
			return ResponseEntity.of(Optional.of(claimreq));
		
	}
	
	/**
	 * Approve pending request.
	 *
	 * @param claim the claim
	 * @return the response entity
	 */
	@PutMapping("/approveClaimRequest")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Claim> approvePendingRequest(@RequestBody Claim claim) {
		try {
		Claim claimReq = this.claimService.approveClaimRequest(claim);
		logger.debug(claimReq.getClass());
		return ResponseEntity.of(Optional.of(claimReq));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
	
	/**
	 * Reject pending request.
	 *
	 * @param claim the claim
	 * @param reason the reason
	 * @return the response entity
	 */
	@PutMapping("/rejectClaimRequest")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Claim> rejectPendingRequest(@RequestBody Claim claim, @RequestParam String reason) {
		try {
		Claim claimReq = this.claimService.rejectClaimRequest(claim, reason);
		
		return ResponseEntity.of(Optional.of(claimReq));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
}
