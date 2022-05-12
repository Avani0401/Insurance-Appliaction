package com.insurance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.insurance.dao.RoleDao;
import com.insurance.entity.Role;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleServiceTest.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RoleServiceTest {
	
	/** The role dao. */
	@MockBean
	private RoleDao roleDao;
	
	/** The roleservice. */
	@Autowired
	private RoleService roleservice;
	
	/**
	 * Creates the new role test.
	 */
	@Test
	void createNewRoleTest() {
		Role newrole =new Role();
		newrole.setRoleName("Test");
		newrole.setRoleDescription("Test Role");
		
		when(roleDao.save(newrole)).thenReturn(newrole);
		assertEquals(newrole,roleservice.createNewRole(newrole));
		
	}

}
