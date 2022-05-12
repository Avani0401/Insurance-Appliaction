package com.insurance.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.insurance.dao.DependentsRepository;
import com.insurance.dao.PolicyHolderRepository;
import com.insurance.dao.UserDao;
import com.insurance.entity.Dependents;
import com.insurance.entity.EmailRequest;
import com.insurance.entity.Policy;
import com.insurance.entity.PolicyApproved;
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.User;
import com.insurance.helper.EmptyListException;
import com.insurance.helper.NoUserExistsException;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



// TODO: Auto-generated Javadoc
/**
 * The Class PolicyHolderServiceTest.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PolicyHolderServiceTest {

	
	

	/** The Constant PolicyApproved. */
	private static final PolicyApproved PolicyApproved = null;

	/** The policy holder service. */
	@Autowired
	PolicyHolderService policyHolderService;
	
	/** The policy holder repo. */
	@MockBean
	private PolicyHolderRepository policyHolderRepo;
	
	/** The user dao. */
	@MockBean
	private UserDao userDao;
	
	/** The dependentrepo. */
	@MockBean
	private DependentsRepository dependentrepo;
	
	/** The sec conxt. */
	@MockBean
	private SecurityContextHolder secConxt;
	
	/** The auth. */
	@MockBean
	private Authentication auth;
	
	/** The email service. */
	@MockBean
	private EmailService emailService;
	
	
	/**
	 * Buy policy test.
	 */
	@Test
	@WithMockUser(username="user1",password="pass",roles="User")
	void buyPolicyTest() {
	User user=new User();
	user.setUserName("ram");
	user.setUserPassword("ram@123");
	user.setUserFirstName("ram");
	user.setUserLastName("sharma");
	user.setEmail("raj123@gmail.com");
	user.setPhone("9925889088");
	user.setEnabled(true);
	Policy policy1 = new Policy();
	policy1.setPolicyNumber(1);

	PolicyHolder policyholder = new PolicyHolder();
	policyholder.setPolicyholderId(2);
	policyholder.setUser(user);
	policyholder.setDateOfBirthPolicyHolder("2000-08-15");
	
	
	when(policyHolderRepo.save(policyholder)).thenReturn(policyholder);
	
	assertEquals(policyHolderService.buyPolicy(policyholder, policy1), policyholder);
	
	}
	
	/**
	 * Buy policy test 1.
	 */
	@Test
	@WithMockUser(username="ram")
	void buyPolicyTest1() {
	User user=new User();
	user.setUserName("ram");
	user.setUserPassword("ram@123");
	user.setUserFirstName("ram");
	user.setUserLastName("sharma");
	user.setEmail("raj123@gmail.com");
	user.setPhone("9925889088");
	user.setEnabled(true);
	Policy policy1 = new Policy();
	policy1.setPolicyNumber(1);
	policy1.setMinAge((short) 5);
	policy1.setMaxAge((short)90);

	PolicyHolder policyholder = new PolicyHolder();
	policyholder.setPolicyholderId(2);
	policyholder.setUser(user);
	policyholder.setDateOfBirthPolicyHolder("2012-08-15");
	policyholder.setAccidents("");
	policyholder.setMedical("");
	
	when(policyHolderRepo.save(policyholder)).thenReturn(policyholder);
	
	assertEquals(policyHolderService.buyPolicy(policyholder, policy1), policyholder);
	
	}
	
	/**
	 * All policy holder test.
	 *
	 * @throws EmptyListException the empty list exception
	 */
	@Test
	 void allPolicyHolderTest() throws EmptyListException {
	PolicyHolder policyholder = new PolicyHolder();
	policyholder.setPolicyholderId(2);

	List<PolicyHolder> policyHolderList = new ArrayList<PolicyHolder>();
	policyHolderList.add(policyholder);
	when(policyHolderRepo.findAll()).thenReturn(policyHolderList);
	assertEquals(policyHolderList,policyHolderService.allPolicyHolder());
	}
	
	/**
	 * All policy holder test 1.
	 *
	 * @throws EmptyListException the empty list exception
	 */
	@Test
	void allPolicyHolderTest1() throws EmptyListException {

	List<PolicyHolder> policyHolderList = new ArrayList<PolicyHolder>();
	
	when(policyHolderRepo.findAll()).thenReturn(policyHolderList);
	assertThrows(EmptyListException.class, () -> policyHolderService.allPolicyHolder());
	}
	 
	/**
	 * Find by policy holder id test.
	 */
	@Test
	 void findByPolicyHolderIdTest() {
		int id=2;
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(2);
		when(policyHolderRepo.findById(id)).thenReturn(Optional.of(policyholder));
		assertEquals(policyholder,policyHolderService.findByPolicyHolderId(id));
		}
	
	/**
	 * Premium calculation test.
	 */
	@Test
	void premiumCalculationTest() {

	User user=new User();
	user.setUserName("ram");
	user.setUserPassword("ram@123");
	user.setUserFirstName("ram");
	user.setUserLastName("sharma");
	user.setEmail("raj123@gmail.com");
	user.setPhone("9925889088");
	user.setEnabled(true);

	Policy policy = new Policy();
	policy.setPolicyNumber(11);

	int id=2;
	PolicyHolder policyholder = new PolicyHolder();
	policyholder.setPolicyholderId(2);
	policyholder.setPolicy(policy);
	policyholder.setUser(user);

	when(policyHolderRepo.findById(id)).thenReturn(Optional.of(policyholder));
	Dependents dependents = new Dependents();
	dependents.setDependent_id(3);
	List<Dependents> dependentlist = new ArrayList<Dependents>();
	dependentlist.add(dependents);

	when(policyHolderRepo.save(policyholder)).thenReturn(policyholder);

	assertEquals(policyHolderService.premiumCalculation(id),policyholder);

	}
	
	/**
	 * Premium calculation test 1.
	 */
	@Test
	void premiumCalculationTest1() {

	User user=new User();
	user.setUserName("ram");
	user.setUserPassword("ram@123");
	user.setUserFirstName("ram");
	user.setUserLastName("sharma");
	user.setEmail("raj123@gmail.com");
	user.setPhone("9925889088");
	user.setEnabled(true);

	Policy policy = new Policy();
	policy.setPolicyNumber(11);

	int id=2;
	PolicyHolder policyholder = new PolicyHolder();
	policyholder.setPolicyholderId(2);
	policyholder.setPolicy(policy);
	policyholder.setUser(user);
	policyholder.setPremiumDuration(12);
	policyholder.setPolicyApproved(PolicyApproved.APPROVED);
	

	when(policyHolderRepo.findById(id)).thenReturn(Optional.of(policyholder));
	Dependents dependents = new Dependents();
	dependents.setDependent_id(3);
	List<Dependents> dependentlist = new ArrayList<Dependents>();
	dependentlist.add(dependents);
	

	EmailRequest emailRequest = new EmailRequest();
	emailRequest.setSubject("Insure Policy Approved");
	emailRequest.setBody("Hi "+ policyholder.getUser().getUserFirstName()+",\nYour "+policyholder.getPolicy().getPolicyName()+"policy has been approved. You have added "+policyholder.getDependents().size()+" dependents.\n Please pay your dues. Your Premium Amount is Rs"+ policyholder.getPolicyHolderPremium()+" only.");

	when(policyHolderRepo.save(policyholder)).thenReturn(policyholder);

	assertEquals(policyHolderService.premiumCalculation(id),policyholder);

	}
	
	/**
	 * Monthly policy holder test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	 void monthlyPolicyHolderTest() throws Exception {
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(2);
		

		List<PolicyHolder> policyHolderList = new ArrayList<PolicyHolder>();


		when(policyHolderRepo.findAll()).thenReturn(policyHolderList);
		assertThrows(EmptyListException.class,() -> policyHolderService.monthlyPolicyHolder(),"No data found");
		
		}
	
	/**
	 * Monthly policy holder test 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	 void monthlyPolicyHolderTest1() throws Exception {
		PolicyHolder policyholder = new PolicyHolder(); 
		policyholder.setPolicyholderId(2);
		policyholder.setPremiumDuration((short)1);
		

		List<PolicyHolder> policyHolderList = new ArrayList<PolicyHolder>();
		policyHolderList.add(policyholder);


		when(policyHolderRepo.findAll()).thenReturn(policyHolderList);
		assertEquals(policyHolderService.monthlyPolicyHolder(),policyHolderList);
		
		
		

	
		}
	
	/**
	 * Yearly policy holder test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void yearlyPolicyHolderTest() throws Exception {
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(2);

		List<PolicyHolder> policyHolderList = new ArrayList<PolicyHolder>();
		
		
		when(policyHolderRepo.findAll()).thenReturn(policyHolderList);
		assertThrows(EmptyListException.class,()->policyHolderService.yearlyPolicyHolder());

	
		}
	
	/**
	 * Yearly policy holder test 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void yearlyPolicyHolderTest1() throws Exception {
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(2);
		policyholder.setPremiumDuration((short)12);
		
		

		List<PolicyHolder> policyHolderList = new ArrayList<PolicyHolder>();
		policyHolderList.add(policyholder);

		
		when(policyHolderRepo.findAll()).thenReturn(policyHolderList);
		assertEquals(policyHolderService.yearlyPolicyHolder(),policyHolderList);

	
		}
	
	/**
	 * History test.
	 */
	@Test
	@WithMockUser(username="raj")
	void historyTest() {
		int policyholderid = 5;
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(policyholderid);
		
		List<PolicyHolder> policyholderlist = new ArrayList<PolicyHolder>();
		policyholderlist.add(policyholder);
		
		assertThat(policyHolderService.history().contains(policyholderlist));
		
	}
	
	/**
	 * Userpolicylist test.
	 */
	@Test
	@WithMockUser(username="raj")
	void userpolicylistTest() {
		
		User user=new User();
		user.setUserName("ram");
		user.setUserPassword("ram@123");
		user.setUserFirstName("ram");
		user.setUserLastName("sharma");
		user.setEmail("raj123@gmail.com");
		user.setPhone("9925889088");
		user.setEnabled(true);

		
		
		int policyholderid = 5;
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(policyholderid);
		List<PolicyHolder> policyholderlist = new ArrayList<PolicyHolder>();
		policyholderlist.add(policyholder);
		int page = 0;
		Pageable pageable =PageRequest.of(page,2);
		Page<PolicyHolder> policyholderpage = new PageImpl<PolicyHolder>(policyholderlist);
		
		
		when(policyHolderRepo.findByUser(user,pageable)).thenReturn(policyholderpage);
		when(policyHolderService.userPolicyList(page)).thenReturn(policyholderpage);
		assertEquals(policyholderpage,policyHolderService.userPolicyList(page));
		
	}
	
	
	/**
	 * Pay test.
	 */
	@Test
	void payTest() {
		
		User user=new User();
		user.setUserName("ram");
		user.setUserPassword("ram@123");
		user.setUserFirstName("ram");
		user.setUserLastName("sharma");
		user.setEmail("raj123@gmail.com");
		user.setPhone("9925889088");
		user.setEnabled(true);
		
		Policy policy1 = new Policy();
		policy1.setPolicyNumber(1);
		policy1.setMinAge((short) 5);
		policy1.setMaxAge((short)90);
		
		
		
		int policyholderid = 5;
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(policyholderid);
		
		policyholder.setUser(user);
		policyholder.setPolicy(policy1);
		policyholder.setPolicyHolderPremium(123456);
		
		EmailRequest emailRequest = new EmailRequest();

		emailRequest.setTo(policyholder.getUser().getEmail());
		emailRequest.setSubject("Payment Succesful");
		emailRequest.setBody("Hi "+ policyholder.getUser().getUserFirstName()+",\nYour "+policyholder.getPolicy().getPolicyName()+"amount has been paid."+" Amount paid is "+ policyholder.getPolicyHolderPremium()+" only.");

		this.emailService.sendEmail(emailRequest);

		
		when(policyHolderRepo.findById(policyholderid)).thenReturn(Optional.of(policyholder));
		
		assertEquals(policyHolderService.pay(policyholderid),policyholder);
		
	}
	


}
