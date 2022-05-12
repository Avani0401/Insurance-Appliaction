package com.insurance.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtRequest.
 */
public class JwtRequest {

    /** The user name. */
    private String userName;
    
    /** The user password. */
    private String userPassword;

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the user password.
     *
     * @param userPassword the new user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
