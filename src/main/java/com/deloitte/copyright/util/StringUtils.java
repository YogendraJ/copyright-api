package com.deloitte.copyright.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * StringUtils is a Utility class used for Basic String Operations. The
 * CopyRight Application uses this class for find and replace functionality for
 * certain Strings / Words. 
 * 
 * This class is created separately, so as to extend in future for 
 * more common String Utility function for the CopyRight API 
 */
@Slf4j
public class StringUtils {

	public static final Integer MATCHER_GROUP_FIRST = 1;

	// Private Constructor as this is a Utility class and all methods invoked are
	// static. No Instantiation Needed
	private StringUtils() {
	}

	/**
	 * The following method takes input as a String and then uses the
	 * 'replacementMap' to identify Keys / Values that needs to be replaced in the
	 * returned String
	 * 
	 * @param baseInput
	 * @param replacementMap
	 * @param isCaseSensitive (true - denotes the search needs to be case
	 *                        sensitive), (false - denotes search needs to be case
	 *                        insensitive)
	 * @return String - Modified String which contains the replaced String values
	 */
	public static String replaceAllMultiple(String baseInput, Map<String, String> replacementMap,
			boolean isCaseSensitive) {
		if (baseInput == null || "".equals(baseInput) || replacementMap == null || replacementMap.size() == 0) {
			// No further processing needed as baseInput is not as expected
			log.info("Base Input Provided is null, not proceeding with further processing");
			return baseInput;
		}

		// The following is used in cases where we need Case Insensitive Search and
		// Replace. The regex (?i) takes care of insensitivity
		if (!isCaseSensitive) {
			Map<String, String> altReplacements = new HashMap<>(replacementMap.size());
			for (String key : replacementMap.keySet())
				altReplacements.put(key.toLowerCase(), replacementMap.get(key));

			replacementMap = altReplacements;
		}

		StringBuilder patternFormat = new StringBuilder();

		if (!isCaseSensitive)
			// The regex (?i) takes care of insensitivity
			patternFormat.append("(?i)");

		patternFormat.append('(');
		boolean isFirst = true;
		for (String key : replacementMap.keySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				patternFormat.append('|');
			}

			patternFormat.append(Pattern.quote(key));
		}
		patternFormat.append(')');

		Pattern pattern = Pattern.compile(patternFormat.toString());
		Matcher matcher = pattern.matcher(baseInput);

		StringBuilder response = new StringBuilder();
		while (matcher.find()) {
			// Since we have only 1 group from the Matcher, group parameter will be 1
			// which is denoted by constant MATCHER_GROUP_1
			String match = matcher.group(MATCHER_GROUP_FIRST);
			if (!isCaseSensitive) {
				match = match.toLowerCase();
			}
			matcher.appendReplacement(response, replacementMap.get(match));
		}
		matcher.appendTail(response);
		return response.toString();
	}
}