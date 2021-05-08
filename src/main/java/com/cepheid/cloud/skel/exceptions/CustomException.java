package com.cepheid.cloud.skel.exceptions;

public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException() {

	}
	
	public CustomException(String description) {
		super(description);
	}
}
