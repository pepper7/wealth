package org.solt.wealth.service;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4034161323854483050L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
