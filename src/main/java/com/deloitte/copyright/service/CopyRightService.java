package com.deloitte.copyright.service;

/**
 * CopyRightService is the interface for all the functionalities that are 
 * implemented in CopyRight Application. The implementation of this interface
 * is named CopyRightServiceImpl.class and is created in a dedicated 'impl'
 * package. Any new class(es) created in this application must follow the same
 * standards for consistency and best coding practices
 * @author Yogendra Joshi
 */
public interface CopyRightService {

	/**
	 * This method is be used for getting the input from the user, e.g. as part of PostMan 
	 * JSON request and then looking for specific keywords that needs to be appended with
	 * its copyright counterpart 
	 * 
	 * E.g. if the input String contains the word 'We like Google Cloud',
	 * the method is supposed to return 'We like Google© Cloud'
	 * 
	 * @param request - String containing the input data provided by the user
	 * @return String - Modified String with copyright symbol added for few keywords
	 *         : Google, Deloitte, Amazon, etc.
	 */
	public String processRequest(String request);

}
