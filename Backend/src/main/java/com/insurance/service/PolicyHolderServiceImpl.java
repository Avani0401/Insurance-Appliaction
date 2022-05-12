package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.insurance.entity.Dependents;
import com.insurance.entity.EmailRequest;
import com.insurance.entity.Policy;
import com.insurance.entity.PolicyApproved;
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.User;
import com.insurance.helper.EmptyListException;
import com.insurance.helper.NoUserExistsException;
import com.insurance.dao.DependentsRepository;
import com.insurance.dao.PolicyHolderRepository;
import com.insurance.dao.UserDao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class PolicyHolderServiceImpl.
 */
@Service
public class PolicyHolderServiceImpl implements PolicyHolderService {
	
	/** The policy holder dao. */
	@Autowired
	private PolicyHolderRepository policyHolderDao;
	
	/** The user dao. */
	@Autowired
	private UserDao userDao;
	
	/** The dependentrepo. */
	@Autowired
	private DependentsRepository dependentrepo;
	
	/** The email service. */
	@Autowired
	private EmailService emailService;
	
	/** The condition service. */
	@Autowired
	private ConditionService conditionService;
	
	/**
	 * Buy policy.
	 *
	 * @param policyholder the policyholder
	 * @param policy the policy
	 * @return the policy holder
	 */
	@Override
	public PolicyHolder buyPolicy(PolicyHolder policyholder,  Policy policy) {
		
		
		String name = getUserName();
		User user = userDao.findByUserName(name);
		DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		policyholder.setUser(user);
		policyholder.setPolicy(policy);
		policyholder.setBuyOn(LocalDate.now().format(format));
		
		
		PolicyHolder policyholder1 = conditionService.buyPolicyRule(policyholder, policy);
		

		
		this.policyHolderDao.save(policyholder1);
		
		
		return policyholder1;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	@Override
	public String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
    	    String currentUserName = authentication.getName();
    	    
    	    return currentUserName;
    	}
    
    	return null;
	}

	
	/**
	 * All policy holder.
	 *
	 * @return the list
	 * @throws EmptyListException the empty list exception
	 */
	@Override
	public List<PolicyHolder> allPolicyHolder() throws EmptyListException {
		List<PolicyHolder> policyholderlist= policyHolderDao.findAll();
		if (policyholderlist.isEmpty()) {
			throw new EmptyListException();
		}
	return policyholderlist;
	}

	/**
	 * Find by policy holder id.
	 *
	 * @param policyHolderId the policy holder id
	 * @return the policy holder
	 */
	@Override
	public PolicyHolder findByPolicyHolderId(int policyHolderId) {

	
	return this.policyHolderDao.findById(policyHolderId).get();
	}

	/**
	 * Premium calculation.
	 *
	 * @param policyHolderId the policy holder id
	 * @return the policy holder
	 */
	@Override             
	//Use Scheduler
	public PolicyHolder premiumCalculation(int policyHolderId) {
		
		PolicyHolder order = this.policyHolderDao.findById(policyHolderId).get();
		
		List<Dependents> dependents = this.dependentrepo.findByPolicyHolder(order);
		int count = dependents.size() + 1;
		
		int premiumDuration = order.getPremiumDuration(); //12 or 1
		
		int premium = order.getPolicy().getPremium();			
		
		long policyHolderPremium;
		if(premiumDuration == 12)
			policyHolderPremium =  (long) (premiumDuration*count*premium*0.98);
		else	
			policyHolderPremium = premiumDuration*count*premium;
		
		order.setPolicyHolderPremium(policyHolderPremium);
		order.setMaturityAmount(order.getPolicy().getSumInsured());
		
		
		policyHolderDao.save(order);
		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setTo(order.getUser().getEmail());
		if(order.getPolicyApproved() == PolicyApproved.APPROVED) {
					
				
				emailRequest.setSubject("Insure Policy Approved");
				emailRequest.setBody("Hi "+ order.getUser().getUserFirstName()+",\nYour "+order.getPolicy().getPolicyName()+"policy has been approved. You have added "+order.getDependents().size()+" dependents.\n Please pay your dues. Your Premium Amount is Rs"+ order.getPolicyHolderPremium()+" only.");
	
		}
		else {
			
	
				emailRequest.setSubject("Insure Policy");
				emailRequest.setBody("Thanks for your purchase. We'll send you the status of your purchase after assessment.");
		}
		this.emailService.sendEmail(emailRequest);
		return order;
	}


	/**
	 * Monthly policy holder.
	 *
	 * @return the list
	 * @throws EmptyListException the empty list exception
	 */
	@Override
	public List<PolicyHolder> monthlyPolicyHolder() throws EmptyListException{
		List<PolicyHolder> policyholderlist= policyHolderDao.findAll();
		List<PolicyHolder> local = new ArrayList<>();
		for(PolicyHolder p : policyholderlist) {
			
			if(p.getPremiumDuration() == 1)
				local.add(p);
		}
		if(local.isEmpty()) {
			throw new EmptyListException();
		}
		return local;
	}

	/**
	 * Yearly policy holder.
	 *
	 * @return the list
	 * @throws EmptyListException the empty list exception
	 */
	@Override
	public List<PolicyHolder> yearlyPolicyHolder() throws  EmptyListException{
	
		List<PolicyHolder> policyholderlist= policyHolderDao.findAll();
		List<PolicyHolder> local = new ArrayList<>();
		for(PolicyHolder p : policyholderlist) {
			if(p.getPremiumDuration() == 12)   
				local.add(p);
		}
		if(local.isEmpty()) {
			throw new  EmptyListException();
		}
		return local;
	}
	
	/**
	 * Pay.
	 *
	 * @param policyHolderId the policy holder id
	 * @return the policy holder
	 */
	@Override
	public PolicyHolder pay(int policyHolderId) {
	PolicyHolder order = this.policyHolderDao.findById(policyHolderId).get();

	EmailRequest emailRequest = new EmailRequest();

	emailRequest.setTo(order.getUser().getEmail());
	emailRequest.setSubject("Payment Succesful");
	emailRequest.setBody("Hi "+ order.getUser().getUserFirstName()+",\nYour "+order.getPolicy().getPolicyName()+"amount has been paid."+" Amount paid is "+ order.getPolicyHolderPremium()+" only.");

	this.emailService.sendEmail(emailRequest);
	return order;
	}

	/**
	 * User policy list.
	 *
	 * @param page the page
	 * @return the page
	 */
	@Override
	public Page<PolicyHolder> userPolicyList(int page) {
		String name = getUserName();
		User user = userDao.findByUserName(name);
		Pageable pageable =PageRequest.of(page, 5);
		return policyHolderDao.findByUser(user, pageable);
	}      

	/**
	 * History.
	 *
	 * @return the list
	 */
	@Override
	public List<PolicyHolder> history() {
		String name = getUserName();
		User user = userDao.findByUserName(name);
		return policyHolderDao.findByUser(user);
	}


	
	
	

	


	

}
