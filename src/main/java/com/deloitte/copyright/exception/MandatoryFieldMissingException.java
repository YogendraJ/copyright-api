package com.deloitte.copyright.exception;

/**
 * Exception class for the custom Mandatory Field Missing Exception
 * @author Yogendra Joshi
 */
public class MandatoryFieldMissingException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	 
    public MandatoryFieldMissingException(String message) {
        super(message);
    }
}
