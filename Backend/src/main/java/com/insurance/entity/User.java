package com.insurance.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@Entity
public class User {

    
    /** The user name. */
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,15}$",message = "Username must be of 6 to 15 length with no special characters")
    private String userName;
    
    
    /** The user first name. */
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z ]*$",message = "Firstname can only contain alphabets")
    private String userFirstName;
    
    /** The user last name. */
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z ]*$",message = "Lastname can only contain alphabets")
    private String userLastName;
    
    /** The user password. */
    @Column(nullable = false)
    private String userPassword;

    /** The email. */
    @Id
    @Column(nullable = false)
    private String email;
    
    /** The phone. */
    @Column(nullable = false)
    @Pattern(regexp="^\\d{10}$",message="invalid mobile number entered")
    private String phone;
    
    /**
     * Gets the phone.
     *
     * @return the phone
     */
    public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** The role. */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
	
	/** The policy holder. */
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="user")
	@JsonIgnore
	private Set<PolicyHolder> policyHolder= new HashSet<>();

    /**
     * Gets the policy holder.
     *
     * @return the policy holder
     */
    public Set<PolicyHolder> getPolicyHolder() {
		return policyHolder;
	}

	/**
	 * Sets the policy holder.
	 *
	 * @param policyHolder the new policy holder
	 */
	public void setPolicyHolder(Set<PolicyHolder> policyHolder) {
		this.policyHolder = policyHolder;
	}

	/** The enabled. */
	private boolean enabled=true;
	
    /**
     * Checks if is enabled.
     *
     * @return true, if is enabled
     */
    public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user first name.
     *
     * @return the user first name
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Sets the user first name.
     *
     * @param userFirstName the new user first name
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * Gets the user last name.
     *
     * @return the user last name
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Sets the user last name.
     *
     * @param userLastName the new user last name
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * Gets the user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the user password.
     *
     * @param userPassword the new user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public Set<Role> getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
