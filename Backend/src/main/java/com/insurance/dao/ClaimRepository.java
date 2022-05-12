package com.insurance.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.entity.Claim;
import com.insurance.entity.ClaimStatus;
import com.insurance.entity.PolicyHolder;


// TODO: Auto-generated Javadoc
/**
 * The Interface ClaimRepository.
 */
public interface ClaimRepository extends JpaRepository<Claim, Integer> {
	
	/**
	 * Find by id.
	 *
	 * @param claimId the claim id
	 * @return the claim
	 */
	public Claim findById(int claimId);

	/**
	 * Find by status.
	 *
	 * @param approved the approved
	 * @return the list
	 */
	public List<Claim> findByStatus(ClaimStatus approved);

	/**
	 * Find by policyholder.
	 *
	 * @param policyholder the policyholder
	 * @return the claim
	 */
	public Claim findByPolicyholder(PolicyHolder policyholder);

}
