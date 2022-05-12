package com.insurance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.insurance.dao.RoleDao;
import com.insurance.dao.UserDao;
import com.insurance.entity.Claim;
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.Role;
import com.insurance.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceTest.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {
	
	/** The userservice. */
	@Autowired
	UserService userservice;
	
	
	/** The userdao. */
	@MockBean
	UserDao userdao;
	


	/**
	 * Registernewusertest.
	 */
	@Test
	void registernewusertest() {
		 
		
		 User user=new User();
 		 user.setUserName("ram");
 		
 		 user.setUserPassword("ram@123");
 		 user.setUserFirstName("ram");
 		 user.setUserLastName("sharma");
 		 user.setEmail("raj123@gmail.com");
 		 user.setPhone("9925889088");
 		 user.setEnabled(true);
 		 
 		 
 		 when(userdao.save(user)).thenReturn(user);
 		 assertEquals(user,userservice.registerNewUser(user)); 
 		 
	}
	
	
	/**
	 * Check if user exist test.
	 */
	@Test
	void checkIfUserExistTest() {
		User user=new User();
		 user.setUserName("ram");
		 user.setUserPassword("ram@123");
		 user.setUserFirstName("ram");
		 user.setUserLastName("sharma");
		 user.setEmail("raj123@gmail.com");
		 user.setPhone("9925889088");
		 user.setEnabled(true);
		 
		 when(userdao.findByUserName(user.getUserName())).thenReturn(user);
 		 assertThat(userservice.checkIfUserExist(user.getUserName())).isTrue();
	}
	
	/**
	 * Check if email exist test.
	 */
	@Test
	void checkIfEmailExistTest() {
		User user=new User();
		 user.setUserName("ram");
		 user.setUserPassword("ram@123");
		 user.setUserFirstName("ram");
		 user.setUserLastName("sharma");
		 user.setEmail("raj123@gmail.com");
		 user.setPhone("9925889088");
		 user.setEnabled(true);
		 
		 when(userdao.findByEmail(user.getEmail())).thenReturn(user);
 		 assertThat(userservice.checkIfEmailExist(user.getEmail())).isTrue();
	}
	
	
	/**
	 * Register new under writer test.
	 */
	@Test
	void registerNewUnderWriterTest() {

 		User underwriterUser = new User();
 	    underwriterUser.setUserName("underwriter123");
 	    underwriterUser.setUserPassword("underwriter@pass");
 	    underwriterUser.setUserFirstName("underwriter");
 	    underwriterUser.setUserLastName("underwriter");
 	    underwriterUser.setEmail("underwriter@gmail.com");  
 	    underwriterUser.setPhone("1234567890");

 		 
 		 when(userdao.save(underwriterUser)).thenReturn(underwriterUser);
 		 assertEquals(underwriterUser,userservice.registerNewUnderwriter(underwriterUser));		 
	}
	
	/**
	 * All user list test.
	 */
	@Test
	void allUserListTest() {
		 Role role = new Role();
		 role.setRoleName("Admin");
		 
		 Set<Role> rl = new HashSet<>();
		 
		 rl.add(role);
		
		 User user=new User();
		 user.setUserName("ram");
		 user.setRole(rl);
		 user.setUserPassword("ram@123");
		 user.setUserFirstName("ram");
		 user.setUserLastName("sharma");
		 user.setEmail("raj123@gmail.com");
		 user.setPhone("9925889088");
		 user.setEnabled(true);
		 List<User> userlist = new ArrayList<User>();
		 userlist.add(user);
		 when(userdao.findByRole(role)).thenReturn(Stream.of(user).collect(Collectors.toList()));
		 assertThat(userservice.allUserList().containsAll(userlist));
	
		}
	
	/**
	 * Gets the underwriter requests test.
	 *
	 * @return the underwriter requests test
	 */
	@Test
	void getUnderwriterRequestsTest() {
		
		User user=new User();
		 user.setUserName("ram");
		 user.setUserPassword("ram@123");
		 user.setUserFirstName("ram");
		 user.setUserLastName("sharma");
		 user.setEmail("raj123@gmail.com");
		 user.setPhone("9925889088");
		 user.setEnabled(false);
		 List<User> userlist = new ArrayList<User>();
		 userlist.add(user);
		 when(userdao.findByEnabled(false)).thenReturn(Stream.of(user).collect(Collectors.toList()));
		 assertThat(userservice.getUnderwriterRequests().containsAll(userlist));
		
	}
	

	
	}
