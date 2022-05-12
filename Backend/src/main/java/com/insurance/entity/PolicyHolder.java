package com.insurance.entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


// TODO: Auto-generated Javadoc
/**
 * The Class PolicyHolder.
 */
@Entity
public class PolicyHolder {
	
	/** The policyholder id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int policyholderId;
	
	/** The maturity amount. */
	private double maturityAmount;
	
	/** The maturity date. */
	private String maturityDate;
	
	/** The medical. */
	private String medical;
	
	/** The accidents. */
	private String accidents; 
	
	/** The buy on. */
	private String buyOn;
	
	/** The residence. */
	private String residence;
	
	/** The city. */
	private String city;
	
	/** The state. */
	private String state;
	
	/** The pincode. */
	private int pincode;
	
	/** The date of birth policy holder. */
	private String dateOfBirthPolicyHolder;
	
	/** The policy holder premium. */
	private long policyHolderPremium;
	
	/** The premium duration. */
	private int premiumDuration;
	

	/**
	 * Gets the premium duration.
	 *
	 * @return the premium duration
	 */
	public int getPremiumDuration() {
		return premiumDuration;
	}


	/**
	 * Sets the premium duration.
	 *
	 * @param premiumDuration the new premium duration
	 */
	public void setPremiumDuration(int premiumDuration) {
		this.premiumDuration = premiumDuration;
	}


	/**
	 * Gets the policy holder premium.
	 *
	 * @return the policy holder premium
	 */
	public long getPolicyHolderPremium() {
		return policyHolderPremium;
	}


	/**
	 * Sets the policy holder premium.
	 *
	 * @param policyHolderPremium the new policy holder premium
	 */
	public void setPolicyHolderPremium(long policyHolderPremium) {
		this.policyHolderPremium = policyHolderPremium;
	}


/** The sex. */
//	@Enumerated(EnumType.STRING)
	private String sex;
	
	
	
	/** The nominee. */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "nomineeId")
	private Nominee nominee;
	
	/** The dependents. */
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="policyHolder")
	@JsonIgnore
	private List<Dependents> dependents = new ArrayList<>();
	
	/** The policy approved. */
	@Enumerated(EnumType.STRING)
	@Column(name="policy_approved", nullable=false, length=8)
	private PolicyApproved policyApproved;
	
	
	/** The user. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userEmail")
	private User user;
	
	
	/** The policy. */
	@ManyToOne
	@JoinColumn(name="policyNumber")
	private Policy policy;
	
	/** The has claimed. */
	private Boolean hasClaimed = false;
	
	
	/**
	 * Gets the dependents.
	 *
	 * @return the dependents
	 */
	public List<Dependents> getDependents() {
		return dependents;
	}


	/**
	 * Sets the dependents.
	 *
	 * @param dependents the new dependents
	 */
	public void setDependents(List<Dependents> dependents) {
		this.dependents = dependents;
	}


	/**
	 * Gets the nominee.
	 *
	 * @return the nominee
	 */
	public Nominee getNominee() {
		return nominee;
	}


	/**
	 * Sets the nominee.
	 *
	 * @param nominee the new nominee
	 */
	public void setNominee(Nominee nominee) {
		this.nominee = nominee;
	}


	/**
	 * Instantiates a new policy holder.
	 */
	public PolicyHolder(){ 
		super();
	}
	
	


	
	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}


	/**
	 * Sets the sex.
	 *
	 * @param sex the new sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}



	/**
	 * Gets the residence.
	 *
	 * @return the residence
	 */
	public String getResidence() {
		return residence;
	}


	/**
	 * Sets the residence.
	 *
	 * @param residence the new residence
	 */
	public void setResidence(String residence) {
		this.residence = residence;
	}


	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * Gets the pincode.
	 *
	 * @return the pincode
	 */
	public int getPincode() {
		return pincode;
	}


	/**
	 * Sets the pincode.
	 *
	 * @param pincode the new pincode
	 */
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}


	

	
	/**
	 * Gets the policyholder id.
	 *
	 * @return the policyholder id
	 */
	public int getPolicyholderId() {
		return policyholderId;
	}


	/**
	 * Sets the policyholder id.
	 *
	 * @param policyholderId the new policyholder id
	 */
	public void setPolicyholderId(int policyholderId) {
		this.policyholderId = policyholderId;
	}


	/**
	 * Gets the maturity amount.
	 *
	 * @return the maturity amount
	 */
	public double getMaturityAmount() {
		return maturityAmount;
	}


	/**
	 * Sets the maturity amount.
	 *
	 * @param d the new maturity amount
	 */
	public void setMaturityAmount(double d) {
		this.maturityAmount = d;
	}


	/**
	 * Gets the medical.
	 *
	 * @return the medical
	 */
	public String getMedical() {
		return medical;
	}


	/**
	 * Sets the medical.
	 *
	 * @param medical the new medical
	 */
	public void setMedical(String medical) {
		this.medical = medical;
	}


	/**
	 * Gets the accidents.
	 *
	 * @return the accidents
	 */
	public String getAccidents() {
		return accidents;
	}


	/**
	 * Sets the accidents.
	 *
	 * @param accidents the new accidents
	 */
	public void setAccidents(String accidents) {
		this.accidents = accidents;
	}


	

	

	/**
	 * Gets the maturity date.
	 *
	 * @return the maturity date
	 */
	public String getMaturityDate() {
		return maturityDate;
	}


	/**
	 * Sets the maturity date.
	 *
	 * @param maturityDate the new maturity date
	 */
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}


	/**
	 * Gets the buy on.
	 *
	 * @return the buy on
	 */
	public String getBuyOn() {
		return buyOn;
	}


	/**
	 * Sets the buy on.
	 *
	 * @param buyOn the new buy on
	 */
	public void setBuyOn(String buyOn) {
		this.buyOn = buyOn;
	}


	/**
	 * Gets the policy approved.
	 *
	 * @return the policy approved
	 */
	public PolicyApproved getPolicyApproved() {
		return policyApproved;
	}


	/**
	 * Sets the policy approved.
	 *
	 * @param policyApproved the new policy approved
	 */
	public void setPolicyApproved(PolicyApproved policyApproved) {
		this.policyApproved = policyApproved;
	}


	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * Gets the policy.
	 *
	 * @return the policy
	 */
	public Policy getPolicy() {
		return policy;
	}


	/**
	 * Sets the policy.
	 *
	 * @param policy the new policy
	 */
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}


	/**
	 * Gets the date of birth policy holder.
	 *
	 * @return the date of birth policy holder
	 */
	public String getDateOfBirthPolicyHolder() {
		return dateOfBirthPolicyHolder;
	}


	/**
	 * Sets the date of birth policy holder.
	 *
	 * @param dateOfBirthPolicyHolder the new date of birth policy holder
	 */
	public void setDateOfBirthPolicyHolder(String dateOfBirthPolicyHolder) {
		this.dateOfBirthPolicyHolder = dateOfBirthPolicyHolder;
	}


	/**
	 * Gets the checks for claimed.
	 *
	 * @return the checks for claimed
	 */
	public Boolean getHasClaimed() {
		return hasClaimed;
	}


	/**
	 * Sets the checks for claimed.
	 *
	 * @param hasClaimed the new checks for claimed
	 */
	public void setHasClaimed(Boolean hasClaimed) {
		this.hasClaimed = hasClaimed;
	}


	
	
	
	

	
	
	
	
	
	

	
}
