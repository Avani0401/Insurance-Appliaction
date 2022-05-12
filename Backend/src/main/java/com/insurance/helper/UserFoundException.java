package com.insurance.helper;

// TODO: Auto-generated Javadoc
/**
 * The Class UserFoundException.
 */
public class UserFoundException extends Exception{
		
		/**
		 * Instantiates a new user found exception.
		 */
		public UserFoundException() {
			super("User with this Username is already there!! Try another one");
		}
	
		/**
		 * Instantiates a new user found exception.
		 *
		 * @param msg the msg
		 */
		public UserFoundException(String msg) {
		super(msg);
		}
}
