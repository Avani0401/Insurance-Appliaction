package com.insurance.service;


import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.insurance.dao.PolicyRepository;
import com.insurance.entity.Policy;
import com.insurance.helper.EmptyListException;



// TODO: Auto-generated Javadoc
/**
 * The Class PolicyServiceTest.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PolicyServiceTest {
	
	/** The policyservice. */
	@Autowired
	private PolicyService policyservice;
	
	/** The policyrepository. */
	@MockBean
	private PolicyRepository policyrepository;
	
	/**
	 * Gets the policytest.
	 *
	 * @return the policytest
	 * @throws EmptyListException the empty list exception
	 */
	@Test
	void getpolicytest() throws EmptyListException {
		
		Policy firstpolicy = new Policy();
		firstpolicy.setPolicyNumber(7);
		firstpolicy.setPolicyName("bestpolicy");
	
	  when(policyrepository.findAll()).thenReturn(Stream.of(firstpolicy).collect(Collectors.toList()));
		assertEquals(1,policyservice.getPolicy().size());
		}
	
	/**
	 * Gets the policytest 1.
	 *
	 * @return the policytest 1
	 * @throws EmptyListException the empty list exception
	 */
	@Test
	void getpolicytest1() throws EmptyListException {
		
		
		List<Policy> policylist = new ArrayList<Policy>();
	  when(policyrepository.findAll()).thenReturn(policylist);
	  //new test case written
		assertThrows(EmptyListException.class,() -> policyservice.getPolicy());
		}
	
	/**
	 * Singlepolicytest.
	 */
	@Test
	void singlepolicytest() {
		
		Policy newpolicy = new Policy();
		newpolicy.setPolicyNumber(8);
		newpolicy.setPolicyName("secondpolicy");
		when(policyrepository.findById(8)).thenReturn(Optional.of(newpolicy));
		assertEquals(newpolicy, policyservice.getPolicyById(8));
		
		
		
	}
	
	/**
	 * Createpolicytest.
	 */
	@Test 
	void  createpolicytest() {
		Policy newpolicy = new Policy();
		newpolicy.setPolicyNumber(8);
		newpolicy.setPolicyName("newpolicy");
		
		when(policyrepository.save(newpolicy)).thenReturn(newpolicy);
		assertEquals(newpolicy,policyservice.createPolicy(newpolicy));
	}
	
	/**
	 * Updatepolicytest.
	 */
	@Test
	void updatepolicytest() {
		
		Policy policy2 = new Policy();
		policy2.setPolicyNumber(8);
		policy2.setPolicyName("policy2");
		
		when(policyrepository.findById(policy2.getPolicyNumber())).thenReturn(Optional.of(policy2));
		when(policyrepository.save(policy2)).thenReturn(policy2);
		
		assertEquals(policy2,policyservice.updatePolicy(8,policy2));
		
	}

}
