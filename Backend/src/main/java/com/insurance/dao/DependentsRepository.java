package com.insurance.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.insurance.entity.Dependents;
import com.insurance.entity.PolicyHolder;


// TODO: Auto-generated Javadoc
/**
 * The Interface DependentsRepository.
 */
public interface DependentsRepository extends CrudRepository<Dependents,Integer> {
	
	/**
	 * Find by policy holder.
	 *
	 * @param policyholder the policyholder
	 * @return the list
	 */
	public List<Dependents> findByPolicyHolder(PolicyHolder policyholder);
	
	


}
