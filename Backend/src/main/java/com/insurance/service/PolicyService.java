package com.insurance.service;


import java.util.List;

import com.insurance.entity.Policy;
import com.insurance.helper.EmptyListException;

// TODO: Auto-generated Javadoc
/**
 * The Interface PolicyService.
 */
public interface PolicyService {
	
	/**
	 * Gets the policy.
	 *
	 * @return the policy
	 * @throws EmptyListException the empty list exception
	 */
	public List<Policy> getPolicy() throws  EmptyListException;
	
	/**
	 * Gets the policy by id.
	 *
	 * @param policyNumber the policy number
	 * @return the policy by id
	 */
	public Policy getPolicyById(int policyNumber);
	
	/**
	 * Creates the policy.
	 *
	 * @param policy the policy
	 * @return the policy
	 */
	public Policy createPolicy(Policy policy);
	
	/**
	 * Update policy.
	 *
	 * @param policyNumber the policy number
	 * @param policy the policy
	 * @return the policy
	 */
	public Policy updatePolicy(int policyNumber, Policy policy);
	
	/**
	 * Inits the add policy.
	 */
	public void initAddPolicy();
	
	//public Policy setPremiumAmount(Policy policy);
	
	
}