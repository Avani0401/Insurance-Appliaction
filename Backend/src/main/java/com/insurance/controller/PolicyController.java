
package com.insurance.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.service.PolicyService;
import com.insurance.entity.Policy;
import com.insurance.helper.EmptyListException;
import com.insurance.helper.ErrorResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class PolicyController.
 */
@RestController
@RequestMapping("/policy")
public class PolicyController {
	
	/** The policy service. */
	@Autowired
	private PolicyService policyService;
	
	 /**
 	 * Inits the add policy.
 	 */
 	@PostConstruct
	    public void initAddPolicy() {
	        policyService.initAddPolicy();
	    }
	
	/**
	 * Gets the policies.
	 *
	 * @return the policies
	 * @throws EmptyListException the empty list exception
	 */
	@GetMapping("/")
	public ResponseEntity<?> getPolicies() throws EmptyListException{
		
			return new ResponseEntity<List<Policy>>(policyService.getPolicy(),HttpStatus.OK);
		
		
	}
	
	/**
	 * Gets the singlepolicy.
	 *
	 * @param policyId the policy id
	 * @return the singlepolicy
	 */
	@GetMapping("/{policyId}")
	public ResponseEntity<?> getsinglepolicy(@PathVariable("policyId") int policyId){
		Policy singlePolicy = policyService.getPolicyById(policyId);
		
		return new ResponseEntity<Policy>(singlePolicy,HttpStatus.OK);
	}
	
	/**
	 * Creates the policy.
	 *
	 * @param policy the policy
	 * @return the response entity
	 */
	@PostMapping("/")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
		Policy addPolicy = this.policyService.createPolicy(policy);
		return new ResponseEntity<Policy>(addPolicy,HttpStatus.CREATED);
		
	}
	
	/**
	 * Update policy.
	 *
	 * @param policyNumber the policy number
	 * @param policyUpdated the policy updated
	 * @return the response entity
	 */
	@PutMapping("/{policyNumber}")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Policy> updatePolicy(@PathVariable("policyNumber") int policyNumber, @RequestBody Policy policyUpdated){

		Policy updatePolicy = this.policyService.updatePolicy(policyNumber, policyUpdated);
		return new ResponseEntity<Policy>(updatePolicy,HttpStatus.OK);
	}
	
	
}
