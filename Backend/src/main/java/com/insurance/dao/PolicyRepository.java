

package com.insurance.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.Policy;

// TODO: Auto-generated Javadoc
/**
 * The Interface PolicyRepository.
 */
@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
	
	/**
	 * Find by id.
	 *
	 * @param policyNumber the policy number
	 * @return the optional
	 */
	public Optional<Policy> findById(int policyNumber);
	
}
