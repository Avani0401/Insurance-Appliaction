package com.insurance.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;


// TODO: Auto-generated Javadoc
/**
 * The Class Nominee.
 */
@Entity
public class Nominee {
	
	/** The nominee id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int nomineeId;
	
	/** The nominee first name. */
	@Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z ]*$",message = "Firstname can only contain alphabets")
	private String nomineeFirstName;
	
	/** The nominee last name. */
	@Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z ]*$",message = "Lastname can only contain alphabets")
	private String nomineeLastName;
	
	/** The nominee DOB. */
	private String nomineeDOB;
	
	/** The nomineemobile no. */
	private long nomineemobileNo;
	
	/** The nominee relationship. */
	private String nomineeRelationship;
	
	/** The email. */
	private String email;
	
	

	

	/**
	 * Gets the nominee DOB.
	 *
	 * @return the nominee DOB
	 */
	public String getNomineeDOB() {
		return nomineeDOB;
	}
	
	/**
	 * Sets the nominee DOB.
	 *
	 * @param nomineeDOB the new nominee DOB
	 */
	public void setNomineeDOB(String nomineeDOB) {
		this.nomineeDOB = nomineeDOB;
	}
	
	/**
	 * Gets the nominee id.
	 *
	 * @return the nominee id
	 */
	public int getNomineeId() {
		return nomineeId;
	}
	
	/**
	 * Sets the nominee id.
	 *
	 * @param nomineeId the new nominee id
	 */
	public void setNomineeId(int nomineeId) {
		this.nomineeId = nomineeId;
	}
	
	
	/**
	 * Gets the nominee first name.
	 *
	 * @return the nominee first name
	 */
	public String getNomineeFirstName() {
		return nomineeFirstName;
	}
	
	/**
	 * Sets the nominee first name.
	 *
	 * @param nomineeFirstName the new nominee first name
	 */
	public void setNomineeFirstName(String nomineeFirstName) {
		this.nomineeFirstName = nomineeFirstName;
	}
	
	/**
	 * Gets the nominee last name.
	 *
	 * @return the nominee last name
	 */
	public String getNomineeLastName() {
		return nomineeLastName;
	}
	
	/**
	 * Sets the nominee last name.
	 *
	 * @param nomineeLastName the new nominee last name
	 */
	public void setNomineeLastName(String nomineeLastName) {
		this.nomineeLastName = nomineeLastName;
	}
	
	/**
	 * Gets the nomineemobile no.
	 *
	 * @return the nomineemobile no
	 */
	public long getNomineemobileNo() {
		return nomineemobileNo;
	}
	
	/**
	 * Sets the nomineemobile no.
	 *
	 * @param nomineemobileNo the new nomineemobile no
	 */
	public void setNomineemobileNo(long nomineemobileNo) {
		this.nomineemobileNo = nomineemobileNo;
	}
	
	/**
	 * Gets the nominee relationship.
	 *
	 * @return the nominee relationship
	 */
	public String getNomineeRelationship() {
		return nomineeRelationship;
	}
	
	/**
	 * Sets the nominee relationship.
	 *
	 * @param nomineeRelationship the new nominee relationship
	 */
	public void setNomineeRelationship(String nomineeRelationship) {
		this.nomineeRelationship = nomineeRelationship;
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
	
	/**
	 * Instantiates a new nominee.
	 */
	public Nominee() {
		super();
	}

	

}
