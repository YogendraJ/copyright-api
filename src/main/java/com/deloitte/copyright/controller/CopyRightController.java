package com.deloitte.copyright.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.deloitte.copyright.constants.CopyRightConstants;
import com.deloitte.copyright.exception.MandatoryFieldMissingException;
import com.deloitte.copyright.model.CopyRightRequest;
import com.deloitte.copyright.model.CopyRightResponse;
import com.deloitte.copyright.service.CopyRightService;

import lombok.extern.slf4j.Slf4j;

/**
 * CopyRightController is the REST Controller for the CopyRight API.  
 * 
 * It provides POST mapping on "/copyright" URL pattern.
 * 
 * POST Pattern - Takes the user input and sends it to CopyRightService to 
 * find and replace few words with Copyright Symbols  
 * @author Yogendra Joshi
 *
 */
@Slf4j
@EnableAutoConfiguration
@RequestMapping("/")
@RestController
@EnableWebMvc
public class CopyRightController {

	@Autowired
	CopyRightService service;

	/**
	 * The method processData takes input from the third party system and runs it through
	 * the Custom StringUtils class to find and replace instances of few words
	 * 
	 * @param request - This request parameter should contain the String
	 * on which copyright symbol needs to be added 
	 * 
	 * @return Response - This is the response returned by the CopyRight API
	 * with the copyright symbol added to the input string provided by the user 
	 */
	@PostMapping(path = "/copyright")
	@ResponseBody
	public CopyRightResponse processRequest(@RequestBody CopyRightRequest request) {

		// Data is the key input required for the CopyRight API. If it isn't passed,
		// the processing stops right here
		if (null == request.getRequest() || request.getRequest().isBlank() 
				|| request.getRequest().isEmpty()) {
			log.error(CopyRightConstants.MANDATORY_MISSING_MSG);
			throw new MandatoryFieldMissingException(CopyRightConstants.MANDATORY_MISSING_MSG);
		}
		
		String responseString = service.processRequest(request.getRequest());
		CopyRightResponse response = new CopyRightResponse();
		response.setResponse(responseString);
		return response;
	}
}
