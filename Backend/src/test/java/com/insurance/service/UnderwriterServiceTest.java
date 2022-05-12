package com.insurance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.insurance.dao.PolicyHolderRepository;
import com.insurance.dao.RoleDao;
import com.insurance.dao.UnderwriterRepository;
import com.insurance.dao.UserDao;
import com.insurance.entity.PolicyApproved;
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.Role;
import com.insurance.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UnderwriterServiceTest.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UnderwriterServiceTest {

	 	/** The user dao. */
	 	@MockBean
	    private UserDao userDao;

	    /** The role dao. */
    	@MockBean
	    private RoleDao roleDao;
	    
	    /** The policy holderdao. */
    	@MockBean
	    private PolicyHolderRepository policyHolderdao;
	    
		/** The underwriterdao. */
		@MockBean
		private UnderwriterRepository underwriterdao;
		
		/** The underwriterservice. */
		@Autowired
	    private UnderwriterService underwriterservice;
	    
	    /**
    	 * Gets the pendinguser test.
    	 *
    	 * @return the pendinguser test
    	 */
    	@Test
	    @WithMockUser(username="user1",password="pass",roles="Underwriter")
	    public void getpendinguserTest() {
	    	PolicyHolder policyholder = new PolicyHolder();
			policyholder.setPolicyholderId(1);
			
			List<PolicyHolder> policyholderlist = new ArrayList<PolicyHolder>();
			policyholderlist.add(policyholder);
			
			when(underwriterdao.findAllByPolicyApproved(PolicyApproved.PENDING)).thenReturn(policyholderlist);
			
			assertEquals(policyholderlist,underwriterservice.getpendingusers());
			
	    }
	    
	    /**
    	 * Allunderwriterlist test.
    	 */
    	@Test
	    @WithMockUser(username="user1",password="pass",roles="Underwriter")
	    
	    public void allunderwriterlistTest() {
	    	
	    	 Role role = new Role();
			 role.setRoleName("Underwriter");
			 
	    	 User user=new User();
			 user.setUserName("ram");
			 user.setUserPassword("ram@123");
			 user.setUserFirstName("ram");
			 user.setUserLastName("sharma");
			 user.setEmail("raj123@gmail.com");
			 user.setPhone("9925889088");
			 user.setEnabled(true);
			 Set<Role> userRoles = new HashSet<>();
			 userRoles.add(role);
			 user.setRole(userRoles);
			 
			 
			 List<User> userlist =new ArrayList<User>();
			 userlist.add(user);
			 
			when(roleDao.findById("Underwriter")).thenReturn(Optional.of(role));
			when(userDao.findByRole(role)).thenReturn(userlist);
		
//			when(underwriterservice.allUnderwriterList()).thenReturn(userlist);
//			assertThat(userDao).isNotNull();
			assertEquals(userlist.get(0),underwriterservice.allUnderwriterList().get(0));
			
	    }
}
