package com.insurance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.PolicyHolder;
import com.insurance.entity.Role;
import com.insurance.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
@Repository
public interface UserDao extends CrudRepository<User, String> {
	
	/**
	 * Find by role.
	 *
	 * @param role the role
	 * @return the list
	 */
	public List<User> findByRole(Role role);
	
	/**
	 * Find by enabled.
	 *
	 * @param enabled the enabled
	 * @return the list
	 */
	public List<User> findByEnabled(boolean enabled);
	
	/**
	 * Find by user name.
	 *
	 * @param userName the user name
	 * @return the user
	 */
	public User findByUserName(String userName);
	
	/**
	 * Find by policy holder.
	 *
	 * @param policyHolder the policy holder
	 * @return the user
	 */
	public User findByPolicyHolder(PolicyHolder policyHolder);
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findByEmail(String email);
}
