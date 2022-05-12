package com.insurance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.PolicyApproved;
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UnderwriterRepository.
 */
@Repository
public interface UnderwriterRepository extends JpaRepository<PolicyHolder, Integer> 
{
	
	/**
	 * Find all by policy approved.
	 *
	 * @param policyApproved the policy approved
	 * @return the list
	 */
	public List<PolicyHolder> findAllByPolicyApproved(PolicyApproved policyApproved);
	
}


