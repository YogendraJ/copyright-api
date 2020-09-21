package com.deloitte.copyright.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deloitte.copyright.service.CopyRightService;
import com.deloitte.copyright.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * This is the core Implementation Service class for the CopyRightApplication
 * Mainly, it reads couple of properties from application.properties and 
 * decides what the replacementToken is, and the exact words that needs to be
 * replaced in the input String provided by the user / system
 * @author Yogendra Joshi
 *
 */
@Slf4j
@Service
public class CopyRightServiceImpl implements CopyRightService {

	/**Both the properties mentioned below are driven through 
	 * property file (application.properties) which can take 
	 * other tokens if the functionality is changed in future
	 */
	
	//Token to replace in the Input String. e.g. copyright symbol
	@Value("${replacement.token}")
	private String replacementToken;
	
	//List of Words that needs replacement e.g. Google, Amazon, etc.
	@Value("${wordset}")
	private Set<String> wordSet;
	
	/**
	 * This method takes the input from the user and replaces all occurrences of Oracle, 
	 * Google, Amazon, Microsoft, and Deloitte with its Copyright Counterparts.
	 */
	public String processRequest (String request) {

		log.info("Replacement Token -> " + replacementToken);
		log.info("List of Words to be Replaced -> " + wordSet);
		
		Map<String, String> valuesToReplace  = new HashMap<>();
		for (String replacementWord: wordSet) {
			valuesToReplace.put(replacementWord, replacementWord + replacementToken);
		}
		return StringUtils.replaceAllMultiple(request, valuesToReplace, false);
	}
}
