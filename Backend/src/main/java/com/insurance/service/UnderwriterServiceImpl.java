package com.insurance.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.insurance.dao.PolicyHolderRepository;
import com.insurance.dao.RoleDao;
import com.insurance.dao.UnderwriterRepository;
import com.insurance.dao.UserDao;
import com.insurance.entity.EmailRequest;
import com.insurance.entity.PolicyApproved;
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.Role;
import com.insurance.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UnderwriterServiceImpl.
 */
@Service
public class UnderwriterServiceImpl implements UnderwriterService {
	
	   /** The user dao. */
   	@Autowired
	    private UserDao userDao;

	    /** The role dao. */
    	@Autowired
	    private RoleDao roleDao;
	    
	    /** The policy holderdao. */
    	@Autowired
	    private PolicyHolderRepository policyHolderdao;
	
	/** The underwriterdao. */
	@Autowired
	private UnderwriterRepository underwriterdao;
	
	/** The email service. */
	@Autowired
	private EmailService emailService;
	
	

	/** The format. */
	DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	
	
	/**
	 * Gets the pendingusers.
	 *
	 * @return the pendingusers
	 */
	@Override
	public List<PolicyHolder> getpendingusers() {
			
		List<PolicyHolder> policyholder=underwriterdao.findAllByPolicyApproved(PolicyApproved.PENDING);
		return policyholder;
				
		
	}
	
	/**
	 * All underwriter list.
	 *
	 * @return the list
	 */
	@Override
	  public List<User> allUnderwriterList(){
	    	Role role = roleDao.findById("Underwriter").get();
	    	List<User> user = userDao.findByRole(role);
	    	return user;

	  }

	
	/**
	 * Gets the date from string.
	 *
	 * @param string the string
	 * @param format the format
	 * @return the date from string
	 */
	public LocalDate getDateFromString(String string, DateTimeFormatter format)
	{
	
	// Converting the string to date
	// in the specified format
	LocalDate date = LocalDate.parse(string, format);
	
	// Returning the converted date
	return date;
	}
	
	/**
	 * Approvependingusers.
	 *
	 * @param policyHolder the policy holder
	 * @return the policy holder
	 */
	@Override
	public PolicyHolder approvependingusers(PolicyHolder policyHolder) {
		
		if(policyHolder.getPolicyApproved() == PolicyApproved.PENDING) {
			
			policyHolder.setPolicyApproved(PolicyApproved.APPROVED);
			policyHolder.setBuyOn(LocalDate.now().format(format));
			policyHolder.setMaturityDate(getDateFromString(policyHolder.getBuyOn(),format).plusYears(policyHolder.getPolicy().getTenure()).format(format));
			try {
				EmailRequest emailRequest = new EmailRequest();
				
				emailRequest.setTo(policyHolder.getUser().getEmail());
				emailRequest.setSubject("Insure Policy Approved");
				emailRequest.setBody("Hi "+ policyHolder.getUser().getUserFirstName()+",\nYour "+policyHolder.getPolicy().getPolicyName()+"policy has been approved. You have added "+policyHolder.getDependents().size()+" dependents.\n Please pay your dues. Your Premium Amount is Rs"+ policyHolder.getPolicyHolderPremium()+" only.");
			
				this.emailService.sendEmail(emailRequest);
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return policyHolderdao.save(policyHolder);
		}
		return policyHolderdao.save(policyHolder);
	}

	/**
	 * Rejectpendingusers.
	 *
	 * @param policyHolder the policy holder
	 * @param reason the reason
	 * @return the policy holder
	 */
	@Override
	public PolicyHolder rejectpendingusers(PolicyHolder policyHolder,String reason) {
		if(policyHolder.getPolicyApproved() == PolicyApproved.PENDING) {
			
			policyHolder.setPolicyApproved(PolicyApproved.REJECTED);
			try {
				EmailRequest emailRequest = new EmailRequest();
				
				emailRequest.setTo(policyHolder.getUser().getEmail());
				emailRequest.setSubject("Insure Policy Rejected");
				emailRequest.setBody("Hi "+ policyHolder.getUser().getUserFirstName()+",\nYour "+policyHolder.getPolicy().getPolicyName()+"policy has been rejected. Due to "+reason);
			
				this.emailService.sendEmail(emailRequest);
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return policyHolderdao.save(policyHolder);
		}
		return policyHolderdao.save(policyHolder);
	}
	
	

}
