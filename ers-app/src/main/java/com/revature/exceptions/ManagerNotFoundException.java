package com.revature.exceptions;

public class ManagerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8117730418519412463L;

	public ManagerNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public ManagerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ManagerNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ManagerNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ManagerNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
