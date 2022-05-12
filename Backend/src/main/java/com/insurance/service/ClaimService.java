package com.insurance.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.insurance.entity.Claim;
import com.insurance.entity.PolicyHolder;

// TODO: Auto-generated Javadoc
/**
 * The Interface ClaimService.
 */
@Component
public interface ClaimService {
	
	/**
	 * Gets the claim requests.
	 *
	 * @return the claim requests
	 */
	public List<Claim> getClaimRequests();//For Admin
	
	/**
	 * Creates the claim request.
	 *
	 * @param claimRequest the claim request
	 * @param policyHolder the policy holder
	 * @return the claim
	 */
	public Claim createClaimRequest(Claim claimRequest,PolicyHolder policyHolder); //For User
	
	/**
	 * Gets the user claim requests.
	 *
	 * @param policyHolder the policy holder
	 * @return the user claim requests
	 */
	public List<Claim> getUserClaimRequests(List<PolicyHolder> policyHolder);//For User
	
	/**
	 * Creates the claim request by nominee.
	 *
	 * @param claimRequest the claim request
	 * @param policyHolder the policy holder
	 * @param nomineeId the nominee id
	 * @return the claim
	 */
	public Claim createClaimRequestByNominee(Claim claimRequest,int policyHolder,int nomineeId);
	
	/**
	 * Approve claim request.
	 *
	 * @param claimRequest the claim request
	 * @return the claim
	 */
	public Claim approveClaimRequest(Claim claimRequest);
	
	/**
	 * Reject claim request.
	 *
	 * @param claimRequest the claim request
	 * @param reason the reason
	 * @return the claim
	 */
	public Claim rejectClaimRequest(Claim claimRequest, String reason);
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() ;
	
	/**
	 * Check claim exist.
	 *
	 * @param policyHolder the policy holder
	 * @return the boolean
	 */
	public Boolean checkClaimExist(PolicyHolder policyHolder);

	
}
