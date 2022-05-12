package com.insurance.service;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.insurance.entity.Policy;
import com.insurance.entity.PolicyApproved;
import com.insurance.entity.PolicyHolder;

// TODO: Auto-generated Javadoc
/**
 * The Class ConditionService.
 */
@Service
public class ConditionService {
	
	/** The today. */
	LocalDate today=LocalDate.now();
	
	/** The format. */
	DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

	
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
	 * Buy policy rule.
	 *
	 * @param policyholder the policyholder
	 * @param policy the policy
	 * @return the policy holder
	 */
	public PolicyHolder buyPolicyRule(PolicyHolder policyholder, Policy policy) {
		int age= Period.between(getDateFromString(policyholder.getDateOfBirthPolicyHolder(),format), today).getYears();
		if("".equals(policyholder.getMedical()) && "".equals(policyholder.getAccidents()) && policy.getMinAge()< age && age<policy.getMaxAge()) {
			policyholder.setPolicyApproved(PolicyApproved.APPROVED);
			policyholder.setMaturityDate(today.plusYears(policy.getTenure()).format(format));
			
		}
		else {
			policyholder.setPolicyApproved(PolicyApproved.PENDING);
		}
		return policyholder;
	}

}
