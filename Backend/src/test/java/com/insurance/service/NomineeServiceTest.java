package com.insurance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.insurance.dao.NomineeRepository;
import com.insurance.dao.PolicyHolderRepository;
import com.insurance.entity.Nominee;
import com.insurance.entity.PolicyHolder;

// TODO: Auto-generated Javadoc
/**
 * The Class NomineeServiceTest.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class NomineeServiceTest {
	
	/** The nomineeservice. */
	@Autowired
	private NomineeService nomineeservice;
	
	/** The nominee dao. */
	@MockBean
	private NomineeRepository nomineeDao;
	
	/** The policy holder dao. */
	@MockBean
	private PolicyHolderRepository policyHolderDao;
	
	/**
	 * Adds the nominee test.
	 */
	@Test
	 void addNomineeTest() {
		int policyHolderId = 1;
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(1);
		
		Nominee nominee1 = new Nominee();
		nominee1.setNomineeId(2);
		
		when(policyHolderDao.findById(policyHolderId)).thenReturn(Optional.of(policyholder));
	
	
		when(policyHolderDao.save(policyholder)).thenReturn(policyholder);
//		when(nomineeservice.addNominee(nominee1, policyHolderId)).thenReturn(nominee1);
		
		assertEquals(nominee1,nomineeservice.addNominee(nominee1, policyHolderId));
		
//		assertThat(policyHolderDao).isNotNull();
		
		
	}
	
}
