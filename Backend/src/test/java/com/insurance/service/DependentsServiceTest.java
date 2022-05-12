package com.insurance.service;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.insurance.dao.DependentsRepository;
import com.insurance.dao.PolicyHolderRepository;
import com.insurance.entity.Dependents;
import com.insurance.entity.PolicyHolder;

// TODO: Auto-generated Javadoc
/**
 * The Class DependentsServiceTest.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class DependentsServiceTest {
	
	/** The dependentrepo. */
	@MockBean
	private DependentsRepository dependentrepo;
	
	/** The policydao. */
	@MockBean
	private PolicyHolderRepository policydao;
	
	/** The dependentservice. */
	@Autowired
	private DependentsService dependentservice; 

	/**
	 * Adds the dependents test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	 void addDependentsTest() throws Exception {
		Dependents dependent = new Dependents();
		dependent.setDependent_id(2);
		
		List<Dependents> dependentlist = new ArrayList<Dependents>();
		dependentlist.add(dependent);
		
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(1);
		
		when(policydao.findById(1)).thenReturn(Optional.of(policyholder));
		when(policydao.save(policyholder)).thenReturn(policyholder);
//		when(dependentservice.addDependents(1, dependentlist)).thenReturn(Stream.of(dependent).collect(Collectors.toList()));
//		
		assertEquals(dependentlist.get(0),dependentservice.addDependents(1, dependentlist).get(0));
		
		assertThat(policydao).isNotNull();
	}
	
	/**
	 * Gets the dependent test.
	 *
	 * @return the dependent test
	 */
	@Test
	 void getDependentTest() {
		Dependents dependent = new Dependents();
		dependent.setDependent_id(2);
		
		List<Dependents> dependentlist = new ArrayList<Dependents>();
		dependentlist.add(dependent);
		
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(1);
		
		when(policydao.findById(1)).thenReturn(Optional.of(policyholder));
		when(dependentrepo.findByPolicyHolder(policyholder)).thenReturn(dependentlist);
		
		assertThat(dependentlist).size().isPositive();
		
	}
}
