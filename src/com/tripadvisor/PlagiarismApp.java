package com.tripadvisor;

/**
 * The CLI application performs Plagiarism detection using a <tt>N-tuple</tt>
 * comparison algorithm allowing for synonyms in the text. <br>
 * 
 * @author piyush
 * @since 1.0
 *
 */
public class PlagiarismApp {

	/**
	 * Entry point to the application. Below are the arguments that the application
	 * accepts:
	 * <ol>
	 * <li>Synonyms file name
	 * <li>Base file name
	 * <li>Comparison file name
	 * <li>(Optional) Tuple size
	 * <ol>
	 * 
	 * @param input arguments
	 */
	public static void main(String[] args) {

		// Validate inputs provided
		if (validateInput(args)) {

			// Parse the input values on successful validation
			PlagiarismInput input = parseInput(args);

			// Passing the input values to Plagiarism for computation
			PlagiarismChecker checker = new PlagiarismChecker();
			double matchPercentage = checker.calculatePlagiarism(input);

			// Printing the match ratio for the inputs provided
			System.out.println(matchPercentage + "%");

		} else {
			// Print usage instructions on validation failure
			System.out.println(getInstructions());
		}
	}

	/**
	 * 
	 * @param inputArgs
	 * @return isValid
	 */
	private static boolean validateInput(String[] inputArgs) {

		// Validating argument length
		if (inputArgs.length < PlagiarismConstants.MIN_INPUT_LENGTH
				|| inputArgs.length > PlagiarismConstants.MAX_INPUT_LENGTH) {
			return false;
		}

		// Validating if tuple size is integer
		if (inputArgs.length == PlagiarismConstants.MAX_INPUT_LENGTH) {
			if (!PlagiarismUtil.isStringInteger(inputArgs[3])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Method to parse the inputs provided.
	 * 
	 * @link PlagiarismInput
	 * @param input arguments
	 * @return wrapper object for input
	 */
	private static PlagiarismInput parseInput(String[] inputArgs) {

		// Create input wrapper on successful validation
		if (inputArgs.length == PlagiarismConstants.MIN_INPUT_LENGTH) {
			return new PlagiarismInput(inputArgs[0], inputArgs[1], inputArgs[2]);
		} else {
			return new PlagiarismInput(inputArgs[0], inputArgs[1], inputArgs[2], Integer.parseInt(inputArgs[3]));
		}
	}

	/**
	 * Method to return usage instructions to user on receiving improper arguments
	 * 
	 * @return usage instruction
	 */
	private static String getInstructions() {
		return "Usage: java -jar TripAdvisor.jar <synonyms_file> <input_file1> <input_file2> [<tuple_size>]";
	}

}
