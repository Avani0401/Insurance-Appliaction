package com.insurance.service;
import com.insurance.entity.Dependents;
import com.insurance.helper.DependentLimitExceeded;
import com.insurance.helper.NoUserExistsException;

import java.util.List;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Interface DependentsService.
 */
@Component
public interface DependentsService {

	/**
	 * Adds the dependents.
	 *
	 * @param policyholderId the policyholder id
	 * @param dependent the dependent
	 * @return the list
	 * @throws DependentLimitExceeded the dependent limit exceeded
	 * @throws NoUserExistsException the no user exists exception
	 */
	public List<Dependents> addDependents(int policyholderId, List<Dependents> dependent) throws DependentLimitExceeded, NoUserExistsException;

	/**
	 * Gets the dependents.
	 *
	 * @param policyholderId the policyholder id
	 * @return the dependents
	 */
	public List<Dependents> getDependents(int policyholderId);

}
