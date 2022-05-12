package com.insurance.service;




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


import com.insurance.entity.Policy;
import com.insurance.entity.PolicyHolder;
import com.insurance.helper.EmptyListException;

// TODO: Auto-generated Javadoc
/**
 * The Interface PolicyHolderService.
 */
@Component
public interface PolicyHolderService {
	
	/**
	 * Buy policy.
	 *
	 * @param policyholder the policyholder
	 * @param singlepolicy the singlepolicy
	 * @return the policy holder
	 */
	public PolicyHolder buyPolicy( PolicyHolder policyholder, Policy singlepolicy);
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName();
	
	/**
	 * Find by policy holder id.
	 *
	 * @param policyholderId the policyholder id
	 * @return the policy holder
	 */
	public PolicyHolder findByPolicyHolderId(int policyholderId);
	
	/**
	 * User policy list.
	 *
	 * @param page the page
	 * @return the page
	 */
	public Page<PolicyHolder> userPolicyList(int page);


	/**
	 * All policy holder.
	 *
	 * @return the list
	 * @throws EmptyListException the empty list exception
	 */
	public List<PolicyHolder> allPolicyHolder() throws EmptyListException  ;
	
	/**
	 * Premium calculation.
	 *
	 * @param policyholderId the policyholder id
	 * @return the policy holder
	 */
	public PolicyHolder premiumCalculation(int policyholderId);
	
	/**
	 * Monthly policy holder.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<PolicyHolder> monthlyPolicyHolder() throws Exception;
	
	/**
	 * Yearly policy holder.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<PolicyHolder> yearlyPolicyHolder()throws Exception;
	
	/**
	 * Pay.
	 *
	 * @param policyholderId the policyholder id
	 * @return the policy holder
	 */
	public PolicyHolder pay(int policyholderId);
	
	/**
	 * History.
	 *
	 * @return the list
	 */
	public List<PolicyHolder> history();
	


}


