package com.insurance.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import com.insurance.entity.Nominee;
import com.insurance.entity.PolicyHolder;
import com.insurance.service.NomineeService;
import com.insurance.service.PolicyHolderService;

// TODO: Auto-generated Javadoc
/**
 * The Class NomineeControllerTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class NomineeControllerTest {
	
	/** The mock mvc. */
	private MockMvc mockMvc;
	
	/** The nominee service. */
	@MockBean
	private NomineeService nomineeService;
	
	/** The policy holder service. */
	@MockBean
	private PolicyHolderService policyHolderService;
	
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
	 * Test add nominee.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="raj",password="raj23",roles="User")
	 void testAddNominee() throws Exception {
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(1);
		
	
		
		Nominee nominee = new Nominee();
		nominee.setNomineeId(2);
		nominee.setNomineeDOB("2000-12-11");
		
		String uri = "/registerNominee/"+policyholder.getPolicyholderId();
		
		String inputJson = mapToJson(nominee);
		
		
		when(nomineeService.addNominee(nominee, policyholder.getPolicyholderId())).thenReturn(nominee);
		when(policyHolderService.findByPolicyHolderId( policyholder.getPolicyholderId())).thenReturn(policyholder);
	
		
		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		 
		  int status =mvcResult.getResponse().getStatus(); 
			assertEquals(200,status);
	
	

		}

	
	/**
	 * Test add nominee 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="raj",password="raj23",roles="User")
	 void testAddNominee1() throws Exception {
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(1);
		
	
		
		Nominee nominee = new Nominee();
		nominee.setNomineeId(2);
		nominee.setNomineeDOB("2020-12-11");
		
		String uri = "/registerNominee/"+policyholder.getPolicyholderId();
		
		String inputJson = mapToJson(nominee);
		
		
		when(nomineeService.addNominee(nominee, policyholder.getPolicyholderId())).thenReturn(nominee);
		when(policyHolderService.findByPolicyHolderId( policyholder.getPolicyholderId())).thenReturn(policyholder);
	
		
		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		 
		  int status =mvcResult.getResponse().getStatus(); 
			assertEquals(400,status);
	
	

		}
}

