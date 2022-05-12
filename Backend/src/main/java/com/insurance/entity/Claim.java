package com.insurance.entity;

import java.time.LocalDate;

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
import javax.persistence.OneToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Claim.
 */
@Entity
public class Claim {
	
	/** The claim id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int claimId;
	
	/** The illness. */
	private String illness;
	
	/** The hospital name. */
	private String hospitalName;
	
	/** The issued date. */
	private LocalDate issued_date = LocalDate.now();
	
	/** The status. */
	@Enumerated(EnumType.STRING)
	@Column(name="status", length=8)
	private ClaimStatus status = ClaimStatus.PENDING;
	
	
	/** The policyholder. */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="policyholderId")
	private PolicyHolder policyholder;
	
	/** The by nominee. */
	private boolean byNominee = false;
	
	/**
	 * Gets the illness.
	 *
	 * @return the illness
	 */
	public String getIllness() {
		return illness;
	}
	
	/**
	 * Sets the illness.
	 *
	 * @param illness the new illness
	 */
	public void setIllness(String illness) {
		this.illness = illness;
	}
	
	/**
	 * Gets the hospital name.
	 *
	 * @return the hospital name
	 */
	public String getHospitalName() {
		return hospitalName;
	}
	
	/**
	 * Sets the hospital name.
	 *
	 * @param hospitalName the new hospital name
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	/**
	 * Gets the policyholder.
	 *
	 * @return the policyholder
	 */
	public PolicyHolder getPolicyholder() {
		return policyholder;
	}
	
	/**
	 * Sets the policyholder.
	 *
	 * @param policyholder the new policyholder
	 */
	public void setPolicyholder(PolicyHolder policyholder) {
		this.policyholder = policyholder;
	}
	
	/**
	 * Instantiates a new claim.
	 */
	public Claim() {
		super();

	}
	
	/**
	 * Gets the claim id.
	 *
	 * @return the claim id
	 */
	public int getClaimId() {
		return claimId;
	}
	
	/**
	 * Sets the claim id.
	 *
	 * @param claimId the new claim id
	 */
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	/**
	 * Gets the issued date.
	 *
	 * @return the issued date
	 */
	public LocalDate getIssued_date() {
		return issued_date;
	}
	
	/**
	 * Sets the issued date.
	 *
	 * @param issued_date the new issued date
	 */
	public void setIssued_date(LocalDate issued_date) {
		this.issued_date = issued_date;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public ClaimStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(ClaimStatus status) {
		this.status = status;
	}
	
	/**
	 * Gets the by nominee.
	 *
	 * @return the by nominee
	 */
	public boolean getByNominee() {
		return byNominee;
	}
	
	/**
	 * Sets the by nominee.
	 *
	 * @param byNominee the new by nominee
	 */
	public void setByNominee(boolean byNominee) {
		this.byNominee = byNominee;
	}
}
