package com.insurance.helper;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorResponse.
 */
public class ErrorResponse {
    
    /** The message. */
    private String message;

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
      return message;
   }

   /**
    * Instantiates a new error response.
    */
   public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

/**
 * Instantiates a new error response.
 *
 * @param message the message
 */
public ErrorResponse(String message) {
		super();
		this.message = message;
	}

/**
 * Sets the message.
 *
 * @param message the new message
 */
public void setMessage(String message) {
     this.message = message;
   }
}