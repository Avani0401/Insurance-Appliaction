package com.insurance.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtResponse.
 */
public class JwtResponse {

    /** The user. */
    private User user;
    
    /** The jwt token. */
    private String jwtToken;

    /**
     * Instantiates a new jwt response.
     *
     * @param user the user
     * @param jwtToken the jwt token
     */
    public JwtResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the jwt token.
     *
     * @return the jwt token
     */
    public String getJwtToken() {
        return jwtToken;
    }

    /**
     * Sets the jwt token.
     *
     * @param jwtToken the new jwt token
     */
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
