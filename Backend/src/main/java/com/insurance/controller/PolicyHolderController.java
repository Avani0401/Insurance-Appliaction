package com.insurance.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.entity.Policy;
import com.insurance.entity.PolicyHolder;
import com.insurance.helper.EmptyListException;
import com.insurance.helper.ErrorResponse;
import com.insurance.service.PolicyHolderService;
import com.insurance.service.PolicyService;


// TODO: Auto-generated Javadoc
/**
 * The Class PolicyHolderController.
 */
@RestController
public class PolicyHolderController {
	
	/** The policyholder service. */
	@Autowired
	private PolicyHolderService policyholderService;
	
	/** The policy service. */
	@Autowired
	private PolicyService policyService;
	
	

	/**
	 * Buy policies.
	 *
	 * @param policyholder the policyholder
	 * @param policyId the policy id
	 * @return the response entity
	 */
	@PostMapping("/buyPolicy/{policyId}")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<?> buyPolicies(@RequestBody PolicyHolder policyholder, @PathVariable("policyId") int policyId) {
		Policy singlepolicy = policyService.getPolicyById(policyId);
		PolicyHolder policyholdobj =this.policyholderService.buyPolicy(policyholder,singlepolicy);
		return new ResponseEntity<>(policyholdobj,HttpStatus.OK);
		
	}
	
	


	/**
	 * Policy history.
	 *
	 * @param page the page
	 * @return the response entity
	 */
	@GetMapping("/policyhistory/{page}")
	  @PreAuthorize("hasRole('User')")
	  public ResponseEntity<?> policyHistory(@PathVariable("page") Integer page){
	    
	    
	    Page<PolicyHolder> policyholderlist = this.policyholderService.userPolicyList(page);
	    

	    return new ResponseEntity<>(policyholderlist,HttpStatus.OK);

	  }


	/**
	 * All policy holder.
	 *
	 * @return the response entity
	 * @throws EmptyListException the empty list exception
	 */
	@GetMapping("/allpolicyholder")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<?> allPolicyHolder() throws EmptyListException{
		List<PolicyHolder> policyholderlist = this.policyholderService.allPolicyHolder();

		
		return new ResponseEntity<>(policyholderlist,HttpStatus.OK);

	}
	
	
	/**
	 * Premium calculation.
	 *
	 * @param policyHolderId the policy holder id
	 * @return the response entity
	 */
	@PostMapping("{policyHolderId}/payPremium")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<?> premiumCalculation(@PathVariable("policyHolderId") int policyHolderId){
		PolicyHolder policyholder = this.policyholderService.premiumCalculation(policyHolderId);
		
		
		return new ResponseEntity<>(policyholder,HttpStatus.OK);
	}
	
	/**
	 * Pay.
	 *
	 * @param policyHolderId the policy holder id
	 * @return the response entity
	 */
	@PostMapping("{policyHolderId}/pay")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<?> pay(@PathVariable("policyHolderId") int policyHolderId){
	PolicyHolder policyholder = this.policyholderService.pay(policyHolderId);
	
	return new ResponseEntity<>(policyholder,HttpStatus.OK);
	}


}
