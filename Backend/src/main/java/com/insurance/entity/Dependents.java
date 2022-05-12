package com.insurance.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Dependents.
 */
@Entity
public class Dependents {
	
	/** The dependent id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dependent_id;
	
	/** The dependent name. */
	private String dependent_name;
	
	/** The date of birth. */
	private LocalDate date_of_birth;
	
	/** The dependent mobile no. */
	private String dependent_mobile_no;
	
	/** The dependent relationship. */
	private String dependent_relationship;
	
	/** The dependent address. */
	private String dependent_address;


	/** The policy holder. */
	@ManyToOne
	@JoinColumn(name="policyholderId")
	private PolicyHolder policyHolder;
	
	/**
	 * Gets the policy holder.
	 *
	 * @return the policy holder
	 */
	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}

	/**
	 * Sets the policy holder.
	 *
	 * @param policyHolder the new policy holder
	 */
	public void setPolicyHolder(PolicyHolder policyHolder) {
		this.policyHolder = policyHolder;
	}

	/**
	 * Instantiates a new dependents.
	 */
	public Dependents() {
		super();

	}

	/**
	 * Gets the dependent id.
	 *
	 * @return the dependent id
	 */
	public int getDependent_id() {
		return dependent_id;
	}
	
	/**
	 * Sets the dependent id.
	 *
	 * @param dependent_id the new dependent id
	 */
	public void setDependent_id(int dependent_id) {
		this.dependent_id = dependent_id;
	}

	/**
	 * Gets the dependent name.
	 *
	 * @return the dependent name
	 */
	public String getDependent_name() {
		return dependent_name;
	}
	
	/**
	 * Sets the dependent name.
	 *
	 * @param dependent_name the new dependent name
	 */
	public void setDependent_name(String dependent_name) {
		this.dependent_name = dependent_name;
	}
	
	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}
	
	/**
	 * Sets the date of birth.
	 *
	 * @param date_of_birth the new date of birth
	 */
	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	/**
	 * Gets the dependent mobile no.
	 *
	 * @return the dependent mobile no
	 */
	public String getDependent_mobile_no() {
		return dependent_mobile_no;
	}
	
	/**
	 * Sets the dependent mobile no.
	 *
	 * @param dependent_mobile_no the new dependent mobile no
	 */
	public void setDependent_mobile_no(String dependent_mobile_no) {
		this.dependent_mobile_no = dependent_mobile_no;
	}
	
	/**
	 * Gets the dependent relationship.
	 *
	 * @return the dependent relationship
	 */
	public String getDependent_relationship() {
		return dependent_relationship;
	}
	
	/**
	 * Sets the dependent relationship.
	 *
	 * @param dependent_relationship the new dependent relationship
	 */
	public void setDependent_relationship(String dependent_relationship) {
		this.dependent_relationship = dependent_relationship;
	}
	
	/**
	 * Gets the dependent address.
	 *
	 * @return the dependent address
	 */
	public String getDependent_address() {
		return dependent_address;
	}
	
	/**
	 * Sets the dependent address.
	 *
	 * @param dependent_address the new dependent address
	 */
	public void setDependent_address(String dependent_address) {
		this.dependent_address = dependent_address;
	}
	
	
}
