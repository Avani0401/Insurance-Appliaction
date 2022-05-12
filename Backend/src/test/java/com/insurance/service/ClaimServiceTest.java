package com.insurance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.insurance.dao.ClaimRepository;
import com.insurance.dao.NomineeRepository;
import com.insurance.dao.PolicyHolderRepository;
import com.insurance.dao.UserDao;
import com.insurance.entity.Claim;
import com.insurance.entity.ClaimStatus;
import com.insurance.entity.Policy;
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.User;
import com.insurance.entity.EmailRequest;
import com.insurance.entity.Nominee;


// TODO: Auto-generated Javadoc
/**
 * The Class ClaimServiceTest.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)

class ClaimServiceTest {
	
	
	/** The claim repo. */
	@MockBean
	private ClaimRepository claimRepo; 
	
	/** The nominee repo. */
	@MockBean
	private NomineeRepository nomineeRepo;
	
	/** The user dao. */
	@MockBean
	private UserDao userDao;
	
	/** The policyholderrepo. */
	@MockBean
	private PolicyHolderRepository policyholderrepo;
	
	/** The claimservice. */
	@Autowired
	private ClaimService claimservice;
	

	
	/**
	 * Gets the claim requests test.
	 *
	 * @return the claim requests test
	 */
	@Test
	void getClaimRequestsTest() {
		
		Claim firstclaim = new Claim();
		firstclaim.setClaimId(7);
		firstclaim.setIllness("Corona");
	
	  when(claimRepo.findByStatus(ClaimStatus.PENDING)).thenReturn(Stream.of(firstclaim).collect(Collectors.toList()));
		assertEquals(1,claimservice.getClaimRequests().size());
		}
	
	/**
	 * Creates the claim request test.
	 */
	@Test 
	void  createClaimRequestTest() {
		Claim newclaim = new Claim();
		newclaim.setClaimId(7);
		newclaim.setIllness("Corona");
		
		PolicyHolder policyholder=new PolicyHolder();
		User user=new User();
		user.setUserName("ram");
		user.setUserPassword("ram");
		user.setUserFirstName("ram");
		user.setUserLastName("sharma");
		user.setEmail("raj123@gmail.com");
		user.setPhone("9925889088");
		user.setEnabled(true);
		policyholder.setPolicyholderId(1);
		
		EmailRequest newemail= new EmailRequest();
		newemail.setTo(user.getEmail());
		newemail.setSubject("Policy Claim");
		newemail.setBody("Your claim request has been submitted successfully");
		when(userDao.findByPolicyHolder(policyholder)).thenReturn(user);
		
//		when(emailservice.sendEmail(newemail)).thenReturn();
		
		
		
		when(claimRepo.save(newclaim)).thenReturn(newclaim);
		assertEquals(newclaim,claimservice.createClaimRequest(newclaim, policyholder));
		
	}
	
	/**
	 * Gets the user claim request test.
	 *
	 * @return the user claim request test
	 */
	@Test
    void getUserClaimRequestTest() {
	PolicyHolder policyholder = new PolicyHolder();
	policyholder.setPolicyholderId(2);


	List<PolicyHolder> policyHolderList = new ArrayList<PolicyHolder>();

	Claim claim = new Claim();
	claim.setClaimId(33);
	claim.setPolicyholder(policyholder);

	List<Claim> claimlist = new ArrayList<Claim>();
	claimlist.add(claim);

	when(claimRepo.findByPolicyholder(policyholder)).thenReturn(claim);

	assertThat(claimservice.getUserClaimRequests(policyHolderList).containsAll(claimlist));
	}
	
	/**
	 * Approve claim request test.
	 */
	@Test
	 void approveClaimRequestTest() {
	User user=new User();
	user.setUserName("ram");
	user.setUserPassword("ram@123");
	user.setUserFirstName("ram");
	user.setUserLastName("sharma");
	user.setEmail("raj123@gmail.com");
	user.setPhone("9925889088");
	user.setEnabled(true);


	PolicyHolder policyholder = new PolicyHolder();
	policyholder.setPolicyholderId(2);
	policyholder.setUser(user);

	Claim claim = new Claim();
	claim.setClaimId(33);
	claim.setPolicyholder(policyholder);

	when(userDao.findByPolicyHolder(policyholder)).thenReturn(user);
	when(claimRepo.save(claim)).thenReturn(claim);
	assertEquals(claimservice.approveClaimRequest(claim), claim);

	}
	
	/**
	 * Reject claim request test.
	 */
	@Test
	 void rejectClaimRequestTest() {
	User user=new User();
	user.setUserName("ram");
	user.setUserPassword("ram@123");
	user.setUserFirstName("ram");
	user.setUserLastName("sharma");
	user.setEmail("raj123@gmail.com");
	user.setPhone("9925889088");
	user.setEnabled(true);


	PolicyHolder policyholder = new PolicyHolder();
	policyholder.setPolicyholderId(2);
	policyholder.setUser(user);

	Claim claim = new Claim();
	claim.setClaimId(33);
	claim.setPolicyholder(policyholder);

	String reason = "";
	when(userDao.findByPolicyHolder(policyholder)).thenReturn(user);
	when(claimRepo.save(claim)).thenReturn(claim);
	assertEquals(claimservice.rejectClaimRequest(claim,reason), claim);

	}
	
	/**
	 * Creates the claim request by nominee test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	 void createClaimRequestByNomineeTest() throws Exception {
		int policyholderid = 2;
	PolicyHolder policyholder = new PolicyHolder();
	policyholder.setPolicyholderId(policyholderid);

	int nomineeid=7;
	Nominee nominee = new Nominee();
	nominee.setNomineeId(nomineeid);

	Claim claim = new Claim();
	claim.setClaimId(33);
	claim.setPolicyholder(policyholder);

	when(nomineeRepo.findById(nomineeid)).thenReturn(nominee);
	when(policyholderrepo.findById(policyholderid)).thenReturn(Optional.of(policyholder));

	assertEquals(claimservice.createClaimRequestByNominee(claim,policyholderid, nomineeid),claim);

	}
	
	
	
	
	}


