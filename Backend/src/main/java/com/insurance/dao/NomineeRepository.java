package com.insurance.dao;

import org.springframework.data.repository.CrudRepository;

import com.insurance.entity.Nominee;

// TODO: Auto-generated Javadoc
/**
 * The Interface NomineeRepository.
 */
public interface NomineeRepository extends  CrudRepository<Nominee,Integer> {

	/**
	 * Find by id.
	 *
	 * @param nomineeId the nominee id
	 * @return the nominee
	 */
	public Nominee findById(int nomineeId);
}
