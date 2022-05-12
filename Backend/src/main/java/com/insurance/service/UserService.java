package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insurance.dao.RoleDao;
import com.insurance.dao.UserDao;
import com.insurance.entity.Policy;
import com.insurance.entity.Role;
import com.insurance.entity.User;
import com.insurance.helper.NoUserExistsException;
import com.insurance.helper.UserFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service
public class UserService {

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    /** The role dao. */
    @Autowired
    private RoleDao roleDao;

    /** The password encoder. */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Inits the role and user.
     */
    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);
        
        Role underwriterRole = new Role();
        underwriterRole.setRoleName("Underwriter");
        underwriterRole.setRoleDescription("Underwriter role for created record");
        roleDao.save(underwriterRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setEmail("admin@gmail.com");  
        adminUser.setPhone("9090909090");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
        
        User underwriterUser = new User();
        underwriterUser.setUserName("underwriter123");
        underwriterUser.setUserPassword(getEncodedPassword("underwriter@pass"));
        underwriterUser.setUserFirstName("underwriter");
        underwriterUser.setUserLastName("underwriter");
        underwriterUser.setEmail("harsh.malviya.9869@gmail.com");  
        underwriterUser.setPhone("9090909090");
        Set<Role> underwriterRoles = new HashSet<>();
        underwriterRoles.add(underwriterRole);
        underwriterUser.setRole(underwriterRoles);
        underwriterUser.setEnabled(true);
        userDao.save(underwriterUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        user.setEmail("kavani602@gmail.com");
//        user.setPhone("9925889088");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
        
        User user1 = new User();
        user1.setUserName("avani123");
        user1.setUserPassword(getEncodedPassword("avani@123"));
        user1.setUserFirstName("avani");
        user1.setUserLastName("sharma");
        user1.setEmail("kavani602@gmail.com");
        user1.setPhone("9925889999");
        Set<Role> user1Roles = new HashSet<>();
        user1Roles.add(userRole);
        user1.setRole(user1Roles);
        userDao.save(user1);
        
    }

    	/**
	     * Check if user exist.
	     *
	     * @param userName the user name
	     * @return true, if successful
	     */
	    public boolean checkIfUserExist(String userName)
    	{ 
    		return userDao.findByUserName(userName) !=null; 
    	}

    	
    	/**
	     * Check if email exist.
	     *
	     * @param email the email
	     * @return true, if successful
	     */
	    public boolean checkIfEmailExist(String email)
    	{ 
    		return userDao.findByEmail(email) !=null; 
    	}


    	/**
	     * Register new user.
	     *
	     * @param user the user
	     * @return the user
	     */
	    public User registerNewUser(User user) {
    		
    		Role role = roleDao.findById("User").get();
    		Set<Role> userRoles = new HashSet<>();
    		userRoles.add(role);
    		user.setRole(userRoles);
    		user.setUserPassword(getEncodedPassword(user.getUserPassword()));



    		return userDao.save(user);
    	
    	}

    
    /**
     * Register new underwriter.
     *
     * @param underwriter the underwriter
     * @return the user
     */
    public User registerNewUnderwriter(User underwriter) {
    		Role role = roleDao.findById("Underwriter").get();
    		Set<Role> underwriterRoles = new HashSet<>();
    		underwriterRoles.add(role);
    		underwriter.setRole(underwriterRoles);
    		underwriter.setUserPassword(getEncodedPassword(underwriter.getUserPassword()));
    		underwriter.setEnabled(false);
    		return userDao.save(underwriter);
        
	}

    /**
     * Gets the encoded password.
     *
     * @param password the password
     * @return the encoded password
     */
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
    /**
     * All user list.
     *
     * @return the list
     */
    public List<User> allUserList(){
    	Role role = roleDao.findById("User").get();
    	List<User> user;
    	user = userDao.findByRole(role);
    	return user;

    }
    
    
    /**
     * Gets the underwriter requests.
     *
     * @return the underwriter requests
     */
    public List<User> getUnderwriterRequests() {
    	List<User> underwriters = this.userDao.findByEnabled(false);
    	return underwriters;
    }
    
    /**
     * Approve underwriter request.
     *
     * @param underwriter the underwriter
     * @return the user
     */
    public User approveUnderwriterRequest(User underwriter) {

    	User localunderwriter = this.userDao.findByUserName(underwriter.getUserName());
    	localunderwriter.setEnabled(true);
    	userDao.save(localunderwriter);
    	return localunderwriter;
    }
    
    /**
     * Reject underwriter request.
     *
     * @param underwriter the underwriter
     * @return the user
     */
    public User rejectUnderwriterRequest(User underwriter) {

    	User localunderwriter = this.userDao.findByUserName(underwriter.getUserName());
    	localunderwriter.setEnabled(false);
    	userDao.save(localunderwriter);
    	return localunderwriter;
    }
    

    
}
