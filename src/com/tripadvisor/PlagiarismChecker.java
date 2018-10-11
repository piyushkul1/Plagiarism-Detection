package com.tripadvisor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class performs the computation for checking similarity of tuples between
 * two input files.
 * 
 * @author piyush
 * @since 1.0
 *
 */
public class PlagiarismChecker {

	/**
	 * This method builds the synonyms maps and tuple list for calculating
	 * similarity between them
	 * 
	 * @param input wrapper
	 * @return match percentage
	 */
	public double calculatePlagiarism(PlagiarismInput input) {

		// Create synonyms maps from file
		SynonymsMap map = new SynonymsMap(input.getSynonymsFile());

		// Generate tuple list for two input files
		List<Tuple<String>> tupleList1 = getTupleList(input.getFile1(), input.getTupleSize());
		List<Tuple<String>> tupleList2 = getTupleList(input.getFile2(), input.getTupleSize());

		// Return match percentage
		return calculateSimilarity(tupleList1, tupleList2, map);
	}

	/**
	 * 
	 * This method reads the input files and generates a N-tuple list. In case the
	 * file read fails, an error is printed and the file is set to blank.
	 * 
	 * @param fileName
	 * @param tupleSize
	 * @return List of tuples
	 */
	public List<Tuple<String>> getTupleList(String fileName, Integer tupleSize) {
		List<Tuple<String>> tupleList = new ArrayList<Tuple<String>>();
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				String[] words = line.toLowerCase().split("\\W");
				tupleList.addAll(generateTuples(words, tupleSize));
			}
		} catch (IOException e) {
			System.err.println("Error reading file " + e.getMessage());
			System.err.println("Setting file to empty");
		}
		return tupleList;
	}

	/**
	 * This method generates a tuple list of particular size for an array of words
	 * 
	 * @param words
	 * @param tupleSize
	 * @return List of tuples
	 */
	public List<Tuple<String>> generateTuples(String[] words, Integer tupleSize) {
		List<Tuple<String>> tuples = new ArrayList<Tuple<String>>();
		if (words.length >= tupleSize) {
			for (int i = 0; i <= words.length - tupleSize; i++) {
				Tuple<String> tuple = new Tuple<String>(tupleSize);
				for (int j = i; j < i + tupleSize; j++) {
					tuple.addElement(words[j]);
				}
				tuples.add(tuple);
			}
		}
		return tuples;
	}

	/**
	 * This method accepts two tuple list and a synonyms map to compute the match
	 * percentage between the lists.
	 * 
	 * @param tupleList1
	 * @param tupleList2
	 * @param synonymsMap
	 * @return match percentage
	 */
	public double calculateSimilarity(List<Tuple<String>> tupleList1, List<Tuple<String>> tupleList2,
			SynonymsMap synonymsMap) {
		double matchPercentage = 0d;
		int matchCount = 0;
		if (tupleList1.isEmpty() || tupleList2.isEmpty()) {
			return matchPercentage;
		}
		for (Tuple<String> tuple1 : tupleList1) {
			for (Tuple<String> tuple2 : tupleList2) {
				if (isTupleMatch(tuple1, tuple2, synonymsMap)) {
					matchCount++;
				}
			}
		}
		matchPercentage = (matchCount / (double) tupleList1.size()) * 100;
		return matchPercentage;
	}

	/**
	 * This method checks whether the two tuples passed match
	 * 
	 * @param tuple1
	 * @param tuple2
	 * @param map
	 * @return tuple match
	 */
	public boolean isTupleMatch(Tuple<String> tuple1, Tuple<String> tuple2, SynonymsMap map) {
		List<String> words1 = tuple1.getElements();
		List<String> words2 = tuple2.getElements();
		for (int i = 0; i < words1.size(); i++) {
			String word1 = words1.get(i);
			String word2 = words2.get(i);
			if (!word1.equals(word2)) {
				if (!map.isSynonym(word1, word2)) {
					return false;
				}
			}
		}
		return true;
	}

}
