package com.project.weather.travel.exception;


public class NoDataFoundException extends RuntimeException {

	//private static final long serialVersionUID = 1L;

	/**
	 * Throws custom exception when data is not found in the table
	 * @param errorMessage - message to be displayed when NoDataFoundException is thrown
	 */
	public NoDataFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}
