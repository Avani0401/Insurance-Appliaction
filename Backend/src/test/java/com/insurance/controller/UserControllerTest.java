package com.insurance.controller;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.insurance.entity.User;
import com.insurance.service.JwtService;
import com.insurance.service.UserService;



// TODO: Auto-generated Javadoc
/**
 * The Class UserControllerTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class UserControllerTest  {

	/** The mock mvc. */
	private MockMvc mockMvc;
	

	
	/** The jwtservice. */
	@MockBean
	JwtService jwtservice;

	/** The user service. */
	@MockBean
	UserService userService;
	
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
     * Test register new user.
     *
     * @throws Exception the exception
     */
    @Test
   void testRegisterNewUser() throws Exception {
    	
    	 User user=new User();
 		 user.setUserName("ramsingh");
 		 user.setUserPassword("ramsingh");
 		 user.setUserFirstName("ram");
 		 user.setUserLastName("sharma");
 		 user.setEmail("raj123@gmail.com");
 		 user.setPhone("9925889088");
 		 user.setEnabled(true);
 		
 		 
 		 String inputJson = mapToJson(user);
  		 
 		 String uri = "/registerNewUser";
 		  MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
 	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
 		  
 		  
 		 int status =mvcResult.getResponse().getStatus();
 		 
 		assertEquals(200,status);
 		
    }
    
    /**
     * Test register new user 1.
     *
     * @throws Exception the exception
     */
    @Test
    void testRegisterNewUser1() throws Exception{
    	
    	
    	User user=new User();
		 user.setUserName("shayam123");
		 user.setUserPassword("shyam@123");
		 user.setUserFirstName("shyam");
		 user.setUserLastName("sharma");
		 user.setEmail("shyam123@gmail.com");
		 user.setPhone("9123908834");
		 user.setEnabled(true);
        
        String inputJson = mapToJson(user);
        when(userService.checkIfUserExist(user.getUserName())).thenReturn(true);
 		 
		 String uri = "/registerNewUser";
		  MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
		  
		  
		 int status =mvcResult.getResponse().getStatus();
		 
		assertEquals(400,status);

    	
    }
    
    /**
     * Test register new user 2.
     *
     * @throws Exception the exception
     */
    @Test
    void testRegisterNewUser2() throws Exception{
    	
    	
    	User user=new User();
		 user.setUserName("shayam123");
		 user.setUserPassword("shyam@123");
		 user.setUserFirstName("shyam");
		 user.setUserLastName("sharma");
		 user.setEmail("shyam123@gmail.com");
		 user.setPhone("9123908834");
		 user.setEnabled(true);
        
        String inputJson = mapToJson(user);
        when(userService.checkIfEmailExist(user.getEmail())).thenReturn(true);
 		 
		 String uri = "/registerNewUser";
		  MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
		  
		  
		 int status =mvcResult.getResponse().getStatus();
		 
		assertEquals(400,status);

    	
    }
    
    /**
     * Test register under writer.
     *
     * @throws Exception the exception
     */
    @Test
    void testRegisterUnderWriter() throws Exception{
    	
    	
    	User user=new User();
		 user.setUserName("shayam123");
		 user.setUserPassword("shyam@123");
		 user.setUserFirstName("shyam");
		 user.setUserLastName("sharma");
		 user.setEmail("shyam123@gmail.com");
		 user.setPhone("9123908833");
		 user.setEnabled(false);
        
        String inputJson = mapToJson(user);
 		 
		 String uri = "/registerNewUnderwriter";
		  MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
		  
		  
		 int status =mvcResult.getResponse().getStatus();
		 
		assertEquals(200,status);

    	
    }
    
    /**
     * Test register under writer 1.
     *
     * @throws Exception the exception
     */
    @Test
    void testRegisterUnderWriter1() throws Exception{
    	
    	
    	User user=new User();
		 user.setUserName("shayam123");
		 user.setUserPassword("shyam@123");
		 user.setUserFirstName("shyam");
		 user.setUserLastName("sharma");
		 user.setEmail("shyam123@gmail.com");
		 user.setPhone("9123908834");
		 user.setEnabled(false);
        
        String inputJson = mapToJson(user);
        when(userService.checkIfUserExist(user.getUserName())).thenReturn(true);
 		 
		 String uri = "/registerNewUnderwriter";
		  MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
		  
		  
		 int status =mvcResult.getResponse().getStatus();
		 
		assertEquals(400,status);

    	
    }
    
    /**
     * Test register under writer 2.
     *
     * @throws Exception the exception
     */
    @Test
    void testRegisterUnderWriter2() throws Exception{
    	
    	
    	User user=new User();
		 user.setUserName("shayam123");
		 user.setUserPassword("shyam@123");
		 user.setUserFirstName("shyam");
		 user.setUserLastName("sharma");
		 user.setEmail("shyam123@gmail.com");
		 user.setPhone("9123908845");
		 user.setEnabled(false);
        
        String inputJson = mapToJson(user);
        when(userService.checkIfEmailExist(user.getEmail())).thenReturn(true);
 		 
		 String uri = "/registerNewUnderwriter";
		  MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
		  
		  
		 int status =mvcResult.getResponse().getStatus();
		 
		assertEquals(400,status);

    	
    }
    
   
    /**
     * Test all user list.
     *
     * @throws Exception the exception
     */
    @Test
    @WithMockUser(username="admin1",password="admin@1234",roles="Admin")
    void TestAllUserList() throws Exception {
    	
    	User user=new User();
    	user.setUserName("ram");
    	user.setUserPassword("ram@123");
    	user.setUserFirstName("ram");
    	user.setUserLastName("sharma");
    	user.setEmail("raj123@gmail.com");
    	user.setPhone("9925889088");
    	user.setEnabled(true);
    	List<User> userlist = new ArrayList<User>();
    	userlist.add(user);

    String uri = "/alluserlist";
    when(userService.allUserList()).thenReturn(userlist);    
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();


    int status =mvcResult.getResponse().getStatus();

    assertEquals(200,status);
    }
    
    /**
     * Test all user list 1.
     *
     * @throws Exception the exception
     */
    @Test
    @WithMockUser(username="admin1",password="admin@1234",roles="Admin")
    void TestAllUserList1() throws Exception {
    	
    	
    	List<User> userlist = new ArrayList<User>();
    	

    String uri = "/alluserlist";
    when(userService.allUserList()).thenReturn(userlist);    
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();


    int status =mvcResult.getResponse().getStatus();

    assertEquals(404,status);
    }
    
    /**
     * Test register new user 3.
     *
     * @throws Exception the exception
     */
    @Test
    void testRegisterNewUser3() throws Exception{
    User user=new User();
    user.setUserName("ram");
    user.setUserPassword("shyam@123");
    user.setUserFirstName("shyam");
    user.setUserLastName("sharma");
    user.setEmail("shyam123@gmail.com");
    user.setPhone("9123908834");
    user.setEnabled(true);
    String inputJson = mapToJson(user);
    String uri = "/registerNewUser";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
    .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
    int status =mvcResult.getResponse().getStatus();
    assertEquals(400,status);
    }

    /**
     * Test register under writer 3.
     *
     * @throws Exception the exception
     */
    @Test
    void testRegisterUnderWriter3() throws Exception{
    User user=new User();
    user.setUserName("sh");
    user.setUserPassword("shyam@123");
    user.setUserFirstName("shyam");
    user.setUserLastName("sharma");
    user.setEmail("shyam123@gmail.com");
    user.setPhone("9123908834");
    user.setEnabled(false);
    String inputJson = mapToJson(user);
    when(userService.checkIfUserExist(user.getUserName())).thenReturn(true);
    String uri = "/registerNewUnderwriter";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
    .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
    int status =mvcResult.getResponse().getStatus();
    assertEquals(400,status);
    }

    /**
     * Gets the underwriter requests.
     *
     * @return the underwriter requests
     * @throws Exception the exception
     */
    @Test
    @WithMockUser(username="admin1",password="admin@1234",roles="Admin")
    void getUnderwriterRequests() throws Exception {
    User user=new User();
    user.setUserName("sh");
    user.setUserPassword("shyam@123");
    user.setUserFirstName("shyam");
    user.setUserLastName("sharma");
    user.setEmail("shyam123@gmail.com");
    user.setPhone("9123908834");
    user.setEnabled(false);
    List<User> userlist = new ArrayList<User>();
    userlist.add(user);
    when(userService.getUnderwriterRequests()).thenReturn(userlist);
    String uri = "/getUnderwriterRequests";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    int status =mvcResult.getResponse().getStatus(); assertEquals(200,status); }

    /**
     * Gets the underwriter requests 2.
     *
     * @return the underwriter requests 2
     * @throws Exception the exception
     */
    @Test
    @WithMockUser(username="admin1",password="admin@1234",roles="Admin")
    void getUnderwriterRequests2() throws Exception {
    List<User> userlist = new ArrayList<User>();
    when(userService.getUnderwriterRequests()).thenReturn(userlist);
    String uri = "/getUnderwriterRequests";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    int status =mvcResult.getResponse().getStatus(); assertEquals(404,status); }


	
}
