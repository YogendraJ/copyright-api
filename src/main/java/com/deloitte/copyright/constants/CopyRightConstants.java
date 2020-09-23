package com.deloitte.copyright.constants;

/**
 * Constants class used in the CopyRight API. This class will be used
 * to create all shared constants required by the CopyRight Application
 * @author Yogendra Joshi
 */
public class CopyRightConstants {

	// Private Constructor as this is a Utility class and all methods invoked are
	// static. No Instantiation Needed
	private CopyRightConstants() {
	}

	// Common Messages
	public static final String MANDATORY_MISSING_MSG = "Mandatory Element 'request' is missing in the API Request.";
	public static final String INTERNAL_SERVER_ERROR_MSG = "Internal Server Error Occured due to";

	// Exception Constants
	public static final String BAD_REQUEST = "Bad Request";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
}
