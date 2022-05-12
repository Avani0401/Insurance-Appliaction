package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insurance.dao.UserDao;
import com.insurance.entity.JwtRequest;
import com.insurance.entity.JwtResponse;
import com.insurance.entity.User;
import com.insurance.helper.NoUserExistsException;
import com.insurance.helper.UnauthorizedException;
import com.insurance.util.JwtUtil;

import java.util.HashSet;

import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtService.
 */
@Service
public class JwtService implements UserDetailsService {

    /** The jwt util. */
    @Autowired
    private JwtUtil jwtUtil;

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    /** The authentication manager. */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Creates the jwt token.
     *
     * @param jwtRequest the jwt request
     * @return the jwt response
     * @throws Exception the exception
     */
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        User user = userDao.findByUserName(userName);
        return new JwtResponse(user, newGeneratedToken);
    }

    /**
     * Load user by username.
     *
     * @param username the username
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    /**
     * Gets the authority.
     *
     * @param user the user
     * @return the authority
     */
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role ->{ 
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    /**
     * Authenticate.
     *
     * @param userName the user name
     * @param userPassword the user password
     * @throws UnauthorizedException the unauthorized exception
     * @throws NoUserExistsException the no user exists exception
     */
    private void authenticate(String userName, String userPassword) throws UnauthorizedException, NoUserExistsException{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new NoUserExistsException();
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException();
        }
    }
}
