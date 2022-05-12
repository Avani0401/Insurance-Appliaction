package com.insurance.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.User;
import com.insurance.service.PolicyService;
import com.insurance.service.UnderwriterService;

// TODO: Auto-generated Javadoc
/**
 * The Class UnderwriterControllerTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class UnderwriterControllerTest {

/** The mock mvc. */
private MockMvc mockMvc;
	
	/** The under writer service. */
	@MockBean
	UnderwriterService underWriterService;

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
	 * Test get pendig users.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="underwriter", password="underwriter123", roles="Underwriter")
	public void testGetPendigUsers() throws Exception {
		 PolicyHolder policyholder = new PolicyHolder();
		 policyholder.setPolicyholderId(10);
		 
 		 
 		 List<PolicyHolder> policyholderlist = new ArrayList<>();
 		 policyholderlist.add(policyholder);
 		 
 		 when(underWriterService.getpendingusers()).thenReturn(policyholderlist);
 		 String uri = "/pendingUser";
 		 
 		 MvcResult mvcResult =mockMvc.perform(MockMvcRequestBuilders.get(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
 		 
 		 int status = mvcResult.getResponse().getStatus();
 		 assertEquals(200,status);
	}
	
	/**
	 * Test all under writer list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="underwriter1", password="underwriter", roles="Admin")
	public void testAllUnderWriterList() throws Exception {
		
    	User user=new User();
		 user.setUserName("shayam123");
		 user.setUserPassword("shyam@123");
		 user.setUserFirstName("shyam");
		 user.setUserLastName("sharma");
		 user.setEmail("shyam123@gmail.com");
		 user.setPhone("91239088");
		 user.setEnabled(false);
		 
 		 
 		 List<User> userlist = new ArrayList<>();
 		 userlist.add(user);
 		 
 		 when(underWriterService.allUnderwriterList()).thenReturn(userlist);
 		 String uri = "/allUnderwriterlist";
 		 
 		 MvcResult mvcResult =mockMvc.perform(MockMvcRequestBuilders.get(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
 		 
 		 int status = mvcResult.getResponse().getStatus();
 		 assertEquals(200,status);
 		 
	}
	
	/**
	 * Test all under writer list 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="underwriter1", password="underwriter", roles="Admin")
	public void testAllUnderWriterList1() throws Exception {
		
    	User user=new User();
		 user.setUserName("shayam123");
		 user.setUserPassword("shyam@123");
		 user.setUserFirstName("shyam");
		 user.setUserLastName("sharma");
		 user.setEmail("shyam123@gmail.com");
		 user.setPhone("91239088");
		 user.setEnabled(false);
		 
 		 
 		 List<User> userlist = new ArrayList<>();
// 		 userlist.add(user);
 		 
 		 when(underWriterService.allUnderwriterList()).thenReturn(userlist);
 		 String uri = "/allUnderwriterlist";
 		 
 		 MvcResult mvcResult =mockMvc.perform(MockMvcRequestBuilders.get(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
 		 
 		 int status = mvcResult.getResponse().getStatus();
 		 assertEquals(404,status);
 		 
	}
	
	/**
	 * Test approve pending request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username="underwriter", password="underwriter123", roles="Underwriter")
	public void testApprovePendingRequest() throws Exception {
		 PolicyHolder policyholder = new PolicyHolder();
		 policyholder.setPolicyholderId(10);
		 
 		
 		 //when(underWriterService.approvependingusers(policyholder)).thenReturn(policyholder);
 		 String uri = "/approveBuyRequest";
 		 
 		 String inputJson = mapToJson(policyholder);
 		 
 		 MvcResult mvcResult =mockMvc.perform(MockMvcRequestBuilders.put(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
 		 
 		 int status = mvcResult.getResponse().getStatus();
 		 assertEquals(200,status);
	}
	
	/**
	 * Test reject pending request.
	 *
	 * @throws Exception the exception
	 */
	@Test
@WithMockUser(username="underwriter", password="underwriter123", roles="Underwriter")
public void testRejectPendingRequest() throws Exception {
PolicyHolder policyholder = new PolicyHolder();
policyholder.setPolicyholderId(10);
String reason="";

//when(underWriterService.approvependingusers(policyholder)).thenReturn(policyholder);
String uri = "/rejectBuyRequest?reason=Incomplete documents";

String inputJson = mapToJson(policyholder);

MvcResult mvcResult =mockMvc.perform(MockMvcRequestBuilders.put(uri)
.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

int status = mvcResult.getResponse().getStatus();
assertEquals(200,status);
}
}
