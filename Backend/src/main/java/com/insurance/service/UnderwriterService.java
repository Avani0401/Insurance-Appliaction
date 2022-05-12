package com.insurance.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.insurance.entity.PolicyHolder;
import com.insurance.entity.Role;
import com.insurance.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UnderwriterService.
 */
@Component
public interface UnderwriterService {
	
	/**
	 * Gets the pendingusers.
	 *
	 * @return the pendingusers
	 */
	public List<PolicyHolder> getpendingusers(); 
	
	/**
	 * All underwriter list.
	 *
	 * @return the list
	 */
	public List<User> allUnderwriterList();
	
	/**
	 * Approvependingusers.
	 *
	 * @param policyHolder the policy holder
	 * @return the policy holder
	 */
	public PolicyHolder approvependingusers(PolicyHolder policyHolder);
	
	/**
	 * Rejectpendingusers.
	 *
	 * @param policyHolder the policy holder
	 * @param reason the reason
	 * @return the policy holder
	 */
	public PolicyHolder rejectpendingusers(PolicyHolder policyHolder,String reason);

}
