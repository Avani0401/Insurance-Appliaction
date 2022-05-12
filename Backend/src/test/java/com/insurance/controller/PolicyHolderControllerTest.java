package com.insurance.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import com.insurance.entity.Policy;
import com.insurance.entity.PolicyHolder;

import com.insurance.service.JwtService;
import com.insurance.service.PolicyHolderService;

// TODO: Auto-generated Javadoc
/**
 * The Class PolicyHolderControllerTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class PolicyHolderControllerTest {

	/** The mock mvc. */
	private MockMvc mockMvc;
	
	/** The policyholderservice. */
	@MockBean
	PolicyHolderService policyholderservice;
	
	/** The jwtservice. */
	@MockBean
	JwtService jwtservice;


	
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
	 * Test buy policy.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="raj",password="raj123",roles="User")
	 void testBuyPolicy() throws Exception {
		int policynumber = 1;
		
		Policy policy1 = new Policy();
		policy1.setPolicyName("policy1");
		policy1.setPolicyNumber(policynumber);
		
		
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(35);
		policyholder.setMaturityAmount(100000);
		policyholder.setMedical("");
		policyholder.setAccidents("");
		policyholder.setBuyOn(LocalDate.now().format(myFormatObj));
		policyholder.setPolicy(policy1);
	    policyholder.setMaturityDate(LocalDate.of(2027, 12, 9).format(myFormatObj));
		policyholder.setDateOfBirthPolicyHolder(LocalDate.of(2000, 11, 11).format(myFormatObj));
		policyholder.setResidence("indore");
		policyholder.setState("mp");
		policyholder.setCity("indore");
		policyholder.setPincode(123445);
		policyholder.setPremiumDuration(1);
		policyholder.setNominee(null);
		policyholder.setPolicyHolderPremium(1000);
		policyholder.setDependents(null);
		
		when(policyholderservice.buyPolicy(policyholder,policy1)).thenReturn(policyholder);
		
		String inputJson = mapToJson(policyholder);
	
		String uri = "/buyPolicy/2";
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		  
		 int status =mvcResult.getResponse().getStatus();
		 
		 
		assertEquals(200,status);
	}
	
	/**
	 * Test buy policy 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="raj",password="raj123",roles="User")
	 void testBuyPolicy1() throws Exception {
		int policynumber = 1;
		
		Policy policy1 = new Policy();
		policy1.setPolicyName("policy1");
		policy1.setPolicyNumber(policynumber);
		
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(35);
		policyholder.setMaturityAmount(100000);
		policyholder.setMedical("");
		policyholder.setAccidents("");
		policyholder.setBuyOn(LocalDate.now().format(myFormatObj));
//		policyholder.setPolicy(policy1);
	    policyholder.setMaturityDate(LocalDate.of(2027, 12, 9).format(myFormatObj));
		policyholder.setDateOfBirthPolicyHolder(LocalDate.of(2000, 11, 11).format(myFormatObj));
		policyholder.setResidence("indore");
		policyholder.setState("mp");
		policyholder.setCity("indore");
		policyholder.setPincode(123445);
		policyholder.setPremiumDuration(1);
		policyholder.setNominee(null);
		policyholder.setPolicyHolderPremium(1000);
		policyholder.setDependents(null);
		
		//when(policyholderservice.buyPolicy(policyholder,policy1)).thenReturn(policyholder);
		
		String inputJson = mapToJson(policyholder);
	
		String uri = "/buyPolicy/2999";
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		  
		 int status =mvcResult.getResponse().getStatus();
		 
		 
		assertEquals(400,status);
	}

	/**
	 * Test policy history.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="raj",password="raj123",roles="User")
	public void TestPolicyHistory() throws Exception {
		
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(35);
		List<PolicyHolder> policyholderlist = new ArrayList<>();
		policyholderlist.add(policyholder);
		
		
		Page<PolicyHolder> policyholderlist1 =  new PageImpl<PolicyHolder>(policyholderlist); 
		
	
		Integer page =1;
		
		String uri = "/policyhistory/"+page;
		
		when(policyholderservice.userPolicyList(page)).thenReturn(policyholderlist1);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		 int status =mvcResult.getResponse().getStatus();
		 
		assertEquals(200,status);
	}
	
	/**
	 * Test policy history 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="raj",password="raj123",roles="User")
	void TestPolicyHistory1() throws Exception {

		
//		List<PolicyHolder> policyholderlist = new ArrayList<>();

		Integer page = 3;
		String uri = "/policyhistory"+page;
		
//		when(policyholderservice.policyHistory()).thenReturn(policyholderlist);
//		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		 int status =mvcResult.getResponse().getStatus();
		 
		assertEquals(404,status);
	}

	/**
	 * Test all policy holder.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="raj",password="raj123",roles="Admin")
	 void testAllPolicyHolder() throws Exception {
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(35);
		
		List<PolicyHolder> policyholderlist = new ArrayList<>();
		policyholderlist.add(policyholder);
		
		String uri = "/allpolicyholder";
		
		when(policyholderservice.allPolicyHolder()).thenReturn(policyholderlist);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		
		 int status =mvcResult.getResponse().getStatus();
		 
		
		 
		assertEquals(200,status);
		
	}
	
//	@Test
//	@WithMockUser(username="raj",password="raj123",roles="Admin")
//	 void testAllPolicyHolder1() throws Exception {
//		List<PolicyHolder> policyholderlist = new ArrayList<>();
//		String uri = "/allpolicyholder";
//		
//		when(policyholderservice.allPolicyHolder()).thenReturn(policyholderlist);
//		
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
//	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//		
//		 int status =mvcResult.getResponse().getStatus();
//		 
//		
//		 
//		assertEquals(404,status);
//		
//	}
	
	
	/**
 * Test pay permium.
 *
 * @throws Exception the exception
 */
@Test
	@WithMockUser(username="raj",password="raj123",roles="User")
	 void testPayPermium() throws Exception {
		
		
		PolicyHolder policyholder = new PolicyHolder();
		policyholder.setPolicyholderId(35);
		
		String uri = "/"+policyholder.getPolicyholderId()+"/payPremium";
		
		when(policyholderservice.premiumCalculation(policyholder.getPolicyholderId())).thenReturn(policyholder);
		
		String inputJson = mapToJson(policyholder);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andExpect(status().isOk()).andReturn();
		
		 int status =mvcResult.getResponse().getStatus();
		 
		 
		assertEquals(200,status);
		
		
	}
//	@Test
//	@WithMockUser(username="raj",password="raj123",roles="User")
//	 void testPayPermium1() throws Exception {
//		
//		
//       PolicyHolder policyholder = new PolicyHolder();		
//		String uri = "/"+policyholder.getPolicyholderId()+"/payPremium";
//		String inputJson = mapToJson(policyholder);
//		
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
//	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//		
//		 int status =mvcResult.getResponse().getStatus();
//		 
//		 
//		assertEquals(404,status);
//		
//		
//	}
}
