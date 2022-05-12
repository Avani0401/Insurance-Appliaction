package com.insurance.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dao.PolicyHolderRepository;
import com.insurance.entity.Nominee;
import com.insurance.entity.PolicyHolder;

// TODO: Auto-generated Javadoc
/**
 * The Class NomineeService.
 */
@Service
public class NomineeService {
	
	
	/** The policy holder dao. */
	@Autowired
	private PolicyHolderRepository policyHolderDao;
	
	/**
	 * Adds the nominee.
	 *
	 * @param nominee the nominee
	 * @param policyHolderId the policy holder id
	 * @return the nominee
	 */
	public Nominee addNominee(Nominee nominee,int policyHolderId) {
		
		PolicyHolder policyHolder=this.policyHolderDao.findById(policyHolderId).get();
		policyHolder.setNominee(nominee);
		this.policyHolderDao.save(policyHolder);
		
		return nominee;
	}
	

}
