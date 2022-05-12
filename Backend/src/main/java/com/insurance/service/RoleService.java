package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dao.RoleDao;
import com.insurance.entity.Role;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleService.
 */
@Service
public class RoleService {

    /** The role dao. */
    @Autowired
    private RoleDao roleDao;

    /**
     * Creates the new role.
     *
     * @param role the role
     * @return the role
     */
    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
