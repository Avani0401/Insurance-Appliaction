package com.insurance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



// TODO: Auto-generated Javadoc
/**
 * The Class Policy.
 */
@Entity
@Table(name="policy")
public class Policy {
	
	/** The policy number. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int policyNumber;
	
	/** The policy name. */
	private String policyName;
	
	/** The sum insured. */
	private double sumInsured;
	
	/** The tenure. */
	private short tenure;
	
	/** The premium. */
	private int premium;
	
	/** The description. */
	@Column(length = 1500)
	private String description;
	
	/** The min age. */
	private short minAge;
	
	/** The max age. */
	private short maxAge;
	
	/** The duration. */
	private short duration; //monthly or yearly payment
	
	/** The policy type. */
	@Enumerated(EnumType.STRING)
	private PolicyType policyType;
	
	

	
	/**
	 * Gets the policy number.
	 *
	 * @return the policy number
	 */
	public int getPolicyNumber() {
		return policyNumber;
	}




	/**
	 * Sets the policy number.
	 *
	 * @param policyNumber the new policy number
	 */
	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}




	/**
	 * Gets the policy name.
	 *
	 * @return the policy name
	 */
	public String getPolicyName() {
		return policyName;
	}




	/**
	 * Sets the policy name.
	 *
	 * @param policyName the new policy name
	 */
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}




	







	/**
	 * Gets the policy type.
	 *
	 * @return the policy type
	 */
	public PolicyType getPolicyType() {
		return policyType;
	}




	/**
	 * Sets the policy type.
	 *
	 * @param policyType the new policy type
	 */
	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}




	/**
	 * Gets the sum insured.
	 *
	 * @return the sum insured
	 */
	public double getSumInsured() {
		return sumInsured;
	}




	/**
	 * Sets the sum insured.
	 *
	 * @param sumInsured the new sum insured
	 */
	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}




	/**
	 * Gets the tenure.
	 *
	 * @return the tenure
	 */
	public short getTenure() {
		return tenure;
	}




	/**
	 * Sets the tenure.
	 *
	 * @param tenure the new tenure
	 */
	public void setTenure(short tenure) {
		this.tenure = tenure;
	}




	/**
	 * Gets the premium.
	 *
	 * @return the premium
	 */
	public int getPremium() {
		return premium;
	}




	/**
	 * Sets the premium.
	 *
	 * @param premium the new premium
	 */
	public void setPremium(int premium) {
		this.premium = premium;
	}




	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}




	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}




	/**
	 * Gets the min age.
	 *
	 * @return the min age
	 */
	public short getMinAge() {
		return minAge;
	}




	/**
	 * Sets the min age.
	 *
	 * @param minAge the new min age
	 */
	public void setMinAge(short minAge) {
		this.minAge = minAge;
	}




	/**
	 * Gets the max age.
	 *
	 * @return the max age
	 */
	public short getMaxAge() {
		return maxAge;
	}




	/**
	 * Sets the max age.
	 *
	 * @param maxAge the new max age
	 */
	public void setMaxAge(short maxAge) {
		this.maxAge = maxAge;
	}




	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public short getDuration() {
		return duration;
	}




	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(short duration) {
		this.duration = duration;
	}



	/**
	 * Instantiates a new policy.
	 */
	public Policy() {
		super();

	}

	
	
}
