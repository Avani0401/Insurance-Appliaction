package com.insurance.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.Role;

// TODO: Auto-generated Javadoc
/**
 * The Interface RoleDao.
 */
@Repository
public interface RoleDao extends CrudRepository<Role, String> {
	
	/**
	 * Find by role name.
	 *
	 * @param roleName the role name
	 * @return the role
	 */
	public Role findByRoleName(String roleName);

}
