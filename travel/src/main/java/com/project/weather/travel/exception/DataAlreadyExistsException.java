package com.project.weather.travel.exception;


public class DataAlreadyExistsException extends RuntimeException {
	
	/**
	 * Throws custom exception when data is already present in the table. This check is 
	 * done before entering new data in table
	 * @param errorMessage - message to be displayed when DataAlreadyExistsException is thrown
	 */
	public DataAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

}
