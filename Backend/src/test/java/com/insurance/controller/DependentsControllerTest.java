package com.insurance.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.entity.Dependents;
import com.insurance.entity.PolicyHolder;
import com.insurance.helper.DependentLimitExceeded;
import com.insurance.service.DependentsService;
import com.insurance.service.NomineeService;
import com.insurance.service.PolicyHolderService;

import static org.mockito.Mockito.doThrow;



// TODO: Auto-generated Javadoc
/**
 * The Class DependentsControllerTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class DependentsControllerTest {

/** The mock mvc. */
private MockMvc mockMvc;
	
	/** The dependents service. */
	@MockBean
	private DependentsService dependentsService;
	
	/** The web application context. */
	@Autowired
	WebApplicationContext webApplicationContext;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(SecurityMockMvcConfigurers.springSecurity()).build();
		
	}
	
	/**
	 * Map to json.
	 *
	 * @param obj the obj
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	public String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(obj);
	 }
	
	/**
	 * Test add dependent.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="admin23",password="admin@1234",roles="User")
	 void testAddDependent() throws Exception {
		int policyholderid = 1;
		Dependents dependent = new Dependents();
		dependent.setDependent_id(5);
		List<Dependents> dependentlist = new ArrayList<>();
		dependentlist.add(dependent);
		
		when(dependentsService.addDependents(1, dependentlist)).thenReturn(dependentlist);
		
		String uri = "/"+policyholderid+"/addDependents/";
		
		String inputJson = mapToJson(dependentlist);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	 
	  int status =mvcResult.getResponse().getStatus(); 
		assertEquals(200,status);
	}
	
//	@Test(expected = DependentLimitExceeded.class)
//	@WithMockUser(username="admin23",password="admin@1234",roles="User")
//	 void testAddDependent1() throws DependentLimitExceeded{
//		int policyholderid = 1;
//		Dependents dependent = new Dependents();
//		Dependents dependent1 = new Dependents();
//		Dependents dependent2 = new Dependents();
//		Dependents dependent3 = new Dependents();
//		Dependents dependent4 = new Dependents();
//		Dependents dependent5 = new Dependents();
//		
//		List<Dependents> dependentlist = new ArrayList<>();
//		dependentlist.add(dependent);
//		dependentlist.add(dependent1);
//		dependentlist.add(dependent2);
//		dependentlist.add(dependent3);
//		dependentlist.add(dependent4);
//		dependentlist.add(dependent5);
//		
//		PolicyHolder policyHolder = new PolicyHolder();
//		when(dependentsService.getDependents(policyholderid)).thenReturn(dependentlist);
//		
//		doThrow(new DependentLimitExceeded.class).when(dependentsService.addDependents(1, dependentlist));
//
//
//
//	when(dependentsService.addDependents(1, dependentlist)).thenReturn(dependentlist);
//		
//		String uri = "/"+policyholderid+"/addDependents/";
//		
//		String inputJson = mapToJson(dependentlist);
//		
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//	 
//	  int status =mvcResult.getResponse().getStatus(); 
//	  
//		assertEquals(404,status);
//	}
	
	
	/**
 * Test getdepndent.
 *
 * @throws Exception the exception
 */
@Test
	@WithMockUser(username="admin23",password="admin@1234",roles="User")
	 void testGetdepndent() throws Exception {
		int policyholderid = 1;
		Dependents dependent = new Dependents();
		dependent.setDependent_id(5);
		List<Dependents> dependentlist = new ArrayList<>();
		dependentlist.add(dependent);
		
		when(dependentsService.getDependents(1)).thenReturn(dependentlist);
		
		String uri = "/"+policyholderid+"/getDependents/";
				
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	 
	  int status =mvcResult.getResponse().getStatus(); 
		assertEquals(200,status);
	}
	
	/**
	 * Test getdepndent 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="admin23",password="admin@1234",roles="User")
	 void testGetdepndent1() throws Exception {
		int policyholderid = 1;
		Dependents dependent = new Dependents();
		dependent.setDependent_id(5);
		List<Dependents> dependentlist = new ArrayList<>();
//		dependentlist.add(dependent);
		
		when(dependentsService.getDependents(1)).thenReturn(dependentlist);
		
		String uri = "/"+policyholderid+"/getDependents/";
				
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	 
	  int status =mvcResult.getResponse().getStatus(); 
		assertEquals(404,status);
	}
}
