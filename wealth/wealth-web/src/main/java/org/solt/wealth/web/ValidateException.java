package org.solt.wealth.web;

public class ValidateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4028366470897491632L;

	public ValidateException() {
		super();
	}

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(Throwable cause) {
		super(cause);
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}
}
