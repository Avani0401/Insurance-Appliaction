package com.insurance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dao.DependentsRepository;
import com.insurance.dao.PolicyHolderRepository;
import com.insurance.entity.Dependents;
import com.insurance.entity.PolicyHolder;
import com.insurance.helper.DependentLimitExceeded;
import com.insurance.helper.NoUserExistsException;

// TODO: Auto-generated Javadoc
/**
 * The Class DependentsServiceImpl.
 */
@Service
public class DependentsServiceImpl implements DependentsService{
	
	/** The dependentrepo. */
	@Autowired
	private DependentsRepository dependentrepo;
	
	/** The policydao. */
	@Autowired
	private PolicyHolderRepository policydao;

	/**
	 * Adds the dependents.
	 *
	 * @param policyholderId the policyholder id
	 * @param dependent the dependent
	 * @return the list
	 * @throws DependentLimitExceeded the dependent limit exceeded
	 * @throws NoUserExistsException the no user exists exception
	 */
	@Override
	public List<Dependents> addDependents(int policyholderId, List<Dependents> dependent) throws DependentLimitExceeded, NoUserExistsException {
		PolicyHolder policyholder=this.policydao.findById(policyholderId).get();
		
		if(getDependents(policyholderId).size() > 4) {
			throw new DependentLimitExceeded();
		}
		
		else if (policyholder !=null) {
			
			policyholder.setDependents(dependent);
			
			for(Dependents i: dependent) {
				i.setPolicyHolder(policyholder);
			}
			
			this.policydao.save(policyholder);
			
			return dependent;
			
		}
		else {
			throw new NoUserExistsException();
		}
		
	}



	/**
	 * Gets the dependents.
	 *
	 * @param policyholderId the policyholder id
	 * @return the dependents
	 */
	@Override
	public List<Dependents> getDependents(int policyholderId) {
		PolicyHolder policyholder=this.policydao.findById(policyholderId).get();
		return this.dependentrepo.findByPolicyHolder(policyholder);
	}	
	






}
