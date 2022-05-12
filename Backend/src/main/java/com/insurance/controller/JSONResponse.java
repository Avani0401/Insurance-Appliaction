package com.insurance.controller;

// TODO: Auto-generated Javadoc
/**
 * The Class JSONResponse.
 */
public class JSONResponse {
	
	/** The error. */
	boolean error;
	
	/** The response. */
	String response;
	
	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return error;
	}
	
	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(boolean error) {
		this.error = error;
	}
	
	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}
	
	/**
	 * Sets the response.
	 *
	 * @param response the new response
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	
	/**
	 * Instantiates a new JSON response.
	 *
	 * @param error the error
	 * @param response the response
	 */
	public JSONResponse(boolean error, String response) {
		super();
		this.error = error;
		this.response = response;
	}
}
