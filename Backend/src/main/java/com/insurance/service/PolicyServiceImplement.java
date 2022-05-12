package com.insurance.service;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.insurance.entity.Policy;
import com.insurance.entity.PolicyApproved;
import com.insurance.entity.PolicyType;
import com.insurance.helper.EmptyListException;
import com.insurance.dao.PolicyRepository;



// TODO: Auto-generated Javadoc
/**
 * The Class PolicyServiceImplement.
 */
@Service
public class PolicyServiceImplement implements PolicyService {
	
	/** The repo. */
	@Autowired
	private PolicyRepository repo;
	
	/**
	 * Gets the policy.
	 *
	 * @return the policy
	 * @throws EmptyListException the empty list exception
	 */
	@Override
	public List<Policy> getPolicy() throws EmptyListException {
		List<Policy> allPolicyList=	this.repo.findAll();
		if (allPolicyList.isEmpty()) {
			throw new EmptyListException();
		}
		return allPolicyList;
	}
	
	/**
	 * Gets the policy by id.
	 *
	 * @param policyId the policy id
	 * @return the policy by id
	 */
	@Override
	public Policy getPolicyById(int policyId) {
		
		Policy policy =  this.repo.findById(policyId).get();

		
		return policy;
	}

	/**
	 * Creates the policy.
	 *
	 * @param policy the policy
	 * @return the policy
	 */
	@Override
	public Policy createPolicy(Policy policy) {
		
		return this.repo.save(policy);
	}

	/**
	 * Update policy.
	 *
	 * @param policyId the policy id
	 * @param policyUpdated the policy updated
	 * @return the policy
	 */
	@Override
	public Policy updatePolicy(int policyId, Policy policyUpdated) {
		if (this.repo.findById(policyId).get()!=null) {
			policyUpdated.setPolicyNumber(policyId);
			 this.repo.save(policyUpdated);
			 
		}
		return policyUpdated;
		
		
	}
	
	/**
	 * Inits the add policy.
	 */
	@Override
	public void initAddPolicy() {
		
		Policy policy = new Policy();
		
		
		policy.setPolicyNumber(1);
		policy.setPolicyName("Easy Life Insurance");
		policy.setPolicyType(PolicyType.life);
		policy.setSumInsured(500000);
		policy.setTenure((short)10);
		policy.setPremium(1000);
		policy.setDescription("Easy life Insurance provides you a lump sum amount of Rs 5lacs");
		policy.setMinAge((short)18);
		policy.setMaxAge((short)60);
		policy.setDuration((short)12);//yearly
		this.repo.save(policy);
		
		policy.setPolicyNumber(2);
		policy.setPolicyName("Term Life Insurance");
		policy.setPolicyType(PolicyType.life);
		policy.setSumInsured(100000);
		policy.setTenure((short)10);
		policy.setPremium(2000);
		policy.setDescription("Term life Insurance provides you a lump sum amount of Rs 10lacs");
		policy.setMinAge((short)20);
		policy.setMaxAge((short)60);
		policy.setDuration((short)12);
		this.repo.save(policy);
		
		policy.setPolicyNumber(3);
		policy.setPolicyName("YOLO Insurance");
		policy.setPolicyType(PolicyType.life);
		policy.setSumInsured(1500000);
		policy.setTenure((short)10);
		policy.setPremium(5000);
		policy.setDescription("You only live once, secure your future with this policy. Easy life Insurance provides you a lump sum amount of Rs 15lacs");
		policy.setMinAge((short)25);
		policy.setMaxAge((short)65);
		policy.setDuration((short)12);
		this.repo.save(policy);
		

		policy.setPolicyNumber(4);
		policy.setPolicyName("Oral Care Dental Insurance");
		policy.setPolicyType(PolicyType.dental);
		policy.setSumInsured(50000);
		policy.setTenure((short)5);
		policy.setPremium(500);
		policy.setDescription("Oral Care Dental Insurance covers your dental expenses, so that your smile doesn't falter.");
		policy.setMinAge((short)5);
		policy.setMaxAge((short)80);
		policy.setDuration((short)12);
		this.repo.save(policy);
		
		policy.setPolicyNumber(5);
		policy.setPolicyName("Happy Dent Insurance");
		policy.setPolicyType(PolicyType.dental);
		policy.setSumInsured(40000);
		policy.setTenure((short)5);
		policy.setPremium(400);
		policy.setDescription("Happy Dent, your friend in dental crisis.");
		policy.setMinAge((short)10);
		policy.setMaxAge((short)75);
		policy.setDuration((short)12);
		this.repo.save(policy);
		
		policy.setPolicyNumber(6);
		policy.setPolicyName("Smile Shield Insurance");
		policy.setPolicyType(PolicyType.dental);
		policy.setSumInsured(10000);
		policy.setTenure((short)5);
		policy.setPremium(100);
		policy.setDescription("Shielding your dental worries, to always keep you smiling.");
		policy.setMinAge((short)15);
		policy.setMaxAge((short)55);
		policy.setDuration((short)12);
		this.repo.save(policy);
			
	}
	
}
