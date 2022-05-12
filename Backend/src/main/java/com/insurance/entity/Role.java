package com.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Role.
 */
@Entity
public class Role {

    /** The role name. */
    @Id
    private String roleName;
    
    /** The role description. */
    private String roleDescription;

    /**
     * Gets the role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the role name.
     *
     * @param roleName the new role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets the role description.
     *
     * @return the role description
     */
    public String getRoleDescription() {
        return roleDescription;
    }

    /**
     * Sets the role description.
     *
     * @param roleDescription the new role description
     */
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
