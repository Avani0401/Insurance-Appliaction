package com.insurance.helper;

// TODO: Auto-generated Javadoc
/**
 * The Class UnauthorizedException.
 */
public class UnauthorizedException extends Exception {
	
	/**
	 * Instantiates a new unauthorized exception.
	 */
	public UnauthorizedException() {
		super("Invalid credentials");
		}
		
		/**
		 * Instantiates a new unauthorized exception.
		 *
		 * @param msg the msg
		 */
		public UnauthorizedException(String msg) {
		super(msg);
		}
}

