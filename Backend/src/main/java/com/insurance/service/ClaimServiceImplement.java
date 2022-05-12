package com.insurance.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.insurance.dao.ClaimRepository;
import com.insurance.dao.NomineeRepository;
import com.insurance.dao.PolicyHolderRepository;
import com.insurance.dao.UserDao;
import com.insurance.entity.Claim;
import com.insurance.entity.ClaimStatus;
import com.insurance.entity.EmailRequest;
import com.insurance.entity.Nominee;
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Class ClaimServiceImplement.
 */
@Service
public class ClaimServiceImplement implements ClaimService{
	
	/** The claim repo. */
	@Autowired
	private ClaimRepository claimRepo; 
	
	/** The user dao. */
	@Autowired
	private UserDao userDao;
	
	/** The nominee dao. */
	@Autowired
	public NomineeRepository nomineeDao;
	
	/** The email service. */
	@Autowired
	private EmailService emailService;
	
	/** The policyholder dao. */
	@Autowired
	private PolicyHolderRepository policyholderDao;
	
	/** The logger. */
	private static Logger logger =LogManager.getLogger(ClaimServiceImplement.class);
	
	
	/**
	 * Gets the claim requests.
	 *
	 * @return the claim requests
	 */
	@Override
	public List<Claim> getClaimRequests() {
		
		List<Claim> requests = this.claimRepo.findByStatus(ClaimStatus.PENDING);
		requests.addAll(this.claimRepo.findByStatus(ClaimStatus.REJECTED));
		
		return requests;
	}
	

	/**
	 * Creates the claim request.
	 *
	 * @param claimRequest the claim request
	 * @param policyHolder the policy holder
	 * @return the claim
	 */
	@Override
	public Claim createClaimRequest(Claim claimRequest,PolicyHolder policyHolder ){
		claimRequest.setPolicyholder(policyHolder);
		claimRequest.getPolicyholder().setHasClaimed(true);
		User user = this.userDao.findByPolicyHolder(policyHolder);
		this.claimRepo.save(claimRequest);
		
		
		
		EmailRequest emailRequest = new EmailRequest();

		
		emailRequest.setTo(user.getEmail());
		emailRequest.setSubject("Policy Claim");
		emailRequest.setBody("Your claim request has been submitted successfully for policy : "+policyHolder.getPolicy());
	
		this.emailService.sendEmail(emailRequest);
	
		
		return claimRequest;
	}
	
	
	/**
	 * Creates the claim request by nominee.
	 *
	 * @param claimRequest the claim request
	 * @param policyHolderId the policy holder id
	 * @param nomineeId the nominee id
	 * @return the claim
	 */
	@Override
	public Claim createClaimRequestByNominee(Claim claimRequest,int policyHolderId,int nomineeId){
		Nominee nominee=this.nomineeDao.findById(nomineeId);
		PolicyHolder policyHolder=this.policyholderDao.findById(policyHolderId).get();
	
		
		
			if(policyHolder!=null && policyholderDao.findById(policyHolder.getPolicyholderId())!=null && policyHolder.getNominee()==nominee) {
				claimRequest.setPolicyholder(policyHolder);
				claimRequest.setByNominee(true);
				
				claimRequest.getPolicyholder().setHasClaimed(true);
				this.claimRepo.save(claimRequest);
	
				EmailRequest emailRequest = new EmailRequest();
				
				emailRequest.setTo(nominee.getEmail());
				emailRequest.setSubject("Policy Claim");
				emailRequest.setBody("Your claim request has been submitted successfully for policy : "+policyHolder.getPolicy());
			
				this.emailService.sendEmail(emailRequest);
			
			}
				return claimRequest;	
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
    	   return authentication.getName();
    	}
    	logger.debug("User not found.");
		
    	return null;
	}


	/**
	 * Gets the user claim requests.
	 *
	 * @param policyHolder the policy holder
	 * @return the user claim requests
	 */
	@Override
	public List<Claim> getUserClaimRequests(List<PolicyHolder> policyHolder) {
		List<Claim> local= new ArrayList<>();
		
 		for(PolicyHolder i: policyHolder) {
			Claim request = claimRepo.findByPolicyholder(i);
			if(request!=null)
				local.add(request);	
		}
 		
 		return local;

	}


	/**
	 * Approve claim request.
	 *
	 * @param claimRequest the claim request
	 * @return the claim
	 */
	@Override
	public Claim approveClaimRequest(Claim claimRequest) {
		

		if(claimRequest.getStatus() == ClaimStatus.PENDING) {
			claimRequest.setStatus(ClaimStatus.APPROVED);
			User user = this.userDao.findByPolicyHolder(claimRequest.getPolicyholder());
			//claimRequest.getPolicyholder().setHasClaimed(true);
			
			EmailRequest emailRequest = new EmailRequest();
			if(claimRequest.getByNominee()) {
				
			emailRequest.setTo(claimRequest.getPolicyholder().getNominee().getEmail());
			
			}
			else {
				emailRequest.setTo(user.getEmail());
			}
			emailRequest.setSubject("Claim Request Status");
			emailRequest.setBody("Your claim request has been approved by Insure.");
		
			this.emailService.sendEmail(emailRequest);
		
			return claimRepo.save(claimRequest);
		}
		
		
		return claimRepo.save(claimRequest);
	}


	/**
	 * Reject claim request.
	 *
	 * @param claimRequest the claim request
	 * @param reason the reason
	 * @return the claim
	 */
	@Override
	public Claim rejectClaimRequest(Claim claimRequest, String reason) {
		if(claimRequest.getStatus() == ClaimStatus.PENDING) {
			claimRequest.setStatus(ClaimStatus.REJECTED);
			
			User user = this.userDao.findByPolicyHolder(claimRequest.getPolicyholder());
			
			EmailRequest emailRequest = new EmailRequest();
			if(claimRequest.getByNominee()) {
				
				emailRequest.setTo(claimRequest.getPolicyholder().getNominee().getEmail());
				
				}
				else {
					emailRequest.setTo(user.getEmail());
				}
			emailRequest.setSubject("Claim Request Status");
			if(reason != null) {
				emailRequest.setBody("Your claim request has been rejected by Insure."+ "\nReason : "+reason);
			}
				
			this.emailService.sendEmail(emailRequest);
		
			return claimRepo.save(claimRequest);
		}
		return claimRepo.save(claimRequest);
		
		
	}


	/**
	 * Check claim exist.
	 *
	 * @param policyHolder the policy holder
	 * @return the boolean
	 */
	@Override
	public Boolean checkClaimExist(PolicyHolder policyHolder) {
		Claim localclaim= this.claimRepo.findByPolicyholder(policyHolder);
		if (localclaim != null) {
			return true;
		}
		return false;
	}





	

}
