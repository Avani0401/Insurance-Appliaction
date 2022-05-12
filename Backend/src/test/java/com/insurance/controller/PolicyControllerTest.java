package com.insurance.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.insurance.entity.Policy;
import com.insurance.service.PolicyService;

// TODO: Auto-generated Javadoc
/**
 * The Class PolicyControllerTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
public class PolicyControllerTest {
	
	/** The mock mvc. */
	private MockMvc mockMvc;
	
	/** The web application context. */
	@Autowired
	WebApplicationContext webApplicationContext;
	
	/** The policy service. */
	@MockBean
	private PolicyService policyService;
	
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
	 * Testget policies.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="admin12",password="admin@123",roles="Admin")
	void testgetPolicies() throws Exception {
		
		Policy policy = new Policy();
		policy.setPolicyNumber(1);
		
		List<Policy> policylist = new ArrayList<>();
		policylist.add(policy);
		
		String uri = "/policy/";
		
		when(policyService.getPolicy()).thenReturn(policylist);
		
		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		 
		  int status =mvcResult.getResponse().getStatus(); 
			assertEquals(200,status);
		
	}
	
//	@Test
//	@WithMockUser(username="admin12",password="admin@123",roles="Admin")
//	void testgetPolicies1() throws Exception {
//		
////		Policy policy = new Policy();
////		policy.setPolicyNumber(1);
//		
//		List<Policy> policylist = new ArrayList<>();
////		policylist.add(policy);
//		
//		String uri = "/policy/";
//		
//		when(policyService.getPolicy()).thenReturn(policylist);
//		
//		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
//	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//		 
//		  int status =mvcResult.getResponse().getStatus(); 
//			assertEquals(404,status);
//		
//	}
	
	/**
 * Testgetsinglepolicy.
 *
 * @throws Exception the exception
 */
@Test
	@WithMockUser(username="admin12",password="admin@123",roles="Admin")
	void testgetsinglepolicy() throws Exception {
		
		int policyNumber=1;
		Policy policy = new Policy();
		policy.setPolicyNumber(policyNumber);
		

		
		String uri = "/policy/" + policyNumber;
		
		when(policyService.getPolicyById(1)).thenReturn(policy);
		
		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		 
		  int status =mvcResult.getResponse().getStatus(); 
			assertEquals(200,status);
					
		
	}
	
//	@Test
//	@WithMockUser(username="admin12",password="admin@123",roles="Admin")
//	void testgetsinglepolicy1() throws Exception {
//		
//		int policyNumber=999;
////		Policy policy = new Policy();
////	   policy.setPolicyNumber(policyNumber);
//		
//
//		
//		String uri = "/policy/" + policyNumber;
//		
//		//when(policyService.singlePolicy(policyNumber)).thenReturn(null);
//		
//		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
//	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//		 
//		  int status =mvcResult.getResponse().getStatus(); 
//			assertEquals(404,status);
//			
//			
//			
//			
//		
//	}
	
	

}
