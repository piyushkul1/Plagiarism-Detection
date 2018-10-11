package com.tripadvisor;

/**
 * An utility class containing utility methods for the application
 * 
 * @author piyush
 * @since 1.0
 */
public class PlagiarismUtil {

	/**
	 * An utility method to check if the string is integer
	 * 
	 * @param input
	 * @return isInteger
	 */
	public static boolean isStringInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
