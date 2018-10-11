package com.tripadvisor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * This class stores the synonyms read from a file and provides utility
 * functions to read synonyms and match words.
 * 
 * @author piyush
 * @since 1.0
 */
public class SynonymsMap {
	private final HashMap<String, HashSet<String>> table;

	/**
	 * @param fileName
	 */
	public SynonymsMap(String fileName) {
		table = fileName == null ? new HashMap<String, HashSet<String>>(0) : createMap(fileName);
	}

	/**
	 * This method accepts a file name containing synonyms and creates a map of the
	 * words with their synonyms. In case the file read fails, an error is printed
	 * and the file is set to blank.
	 * 
	 * @param fileName
	 * @return synonyms map
	 */
	public HashMap<String, HashSet<String>> createMap(String fileName) {
		HashMap<String, HashSet<String>> table = new HashMap<>();
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				String[] words = line.toLowerCase().split("\\W");
				HashSet<String> synonymSet = new HashSet<String>(Arrays.asList(words));
				for (String word : words) {
					if (!table.containsKey(word)) {
						table.put(word, synonymSet);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error in reading synonyms file" + e.getMessage());
			System.err.println("Setting synonyms list to empty");
		}
		return table;
	}

	/**
	 * This method returns the synonyms of a word
	 * 
	 * @param word
	 * @return synonyms
	 */
	public HashSet<String> getSynonyms(String word) {
		return table.get(word);
	}

	/**
	 * This method checks whether the two words are synonyms
	 * 
	 * @param word1
	 * @param word2
	 * @return is synonym
	 */
	public boolean isSynonym(String word1, String word2) {
		if (table.containsKey(word1)) {
			if (getSynonyms(word1).contains(word2)) {
				return true;
			}
		}
		return false;
	}
}
