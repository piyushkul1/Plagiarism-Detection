package com.tripadvisor;

/**
 * Wrapper class for input values
 * 
 * @author piyush
 * @since 1.0
 */
public class PlagiarismInput {

	private String synonymsFile;
	private String file1;
	private String file2;
	private Integer tupleSize;

	/**
	 * Constructor for default tuple size
	 * 
	 * @param synonymsFile
	 * @param file1
	 * @param file2
	 */
	public PlagiarismInput(String synonymsFile, String file1, String file2) {
		super();
		this.synonymsFile = synonymsFile;
		this.file1 = file1;
		this.file2 = file2;
		this.tupleSize = PlagiarismConstants.DEFAULT_TUPLE_SIZE;
	}

	/**
	 * Constructor for custom tuple size
	 * 
	 * @param synonymsFile
	 * @param file1
	 * @param file2
	 * @param tupleSize
	 */
	public PlagiarismInput(String synonymsFile, String file1, String file2, Integer tupleSize) {
		super();
		this.synonymsFile = synonymsFile;
		this.file1 = file1;
		this.file2 = file2;
		this.tupleSize = tupleSize;
	}

	/**
	 * @return the synonymsFile
	 */
	public String getSynonymsFile() {
		return synonymsFile;
	}

	/**
	 * @param synonymsFile the synonymsFile to set
	 */
	public void setSynonymsFile(String synonymsFile) {
		this.synonymsFile = synonymsFile;
	}

	/**
	 * @return the file1
	 */
	public String getFile1() {
		return file1;
	}

	/**
	 * @param file1 the file1 to set
	 */
	public void setFile1(String file1) {
		this.file1 = file1;
	}

	/**
	 * @return the file2
	 */
	public String getFile2() {
		return file2;
	}

	/**
	 * @param file2 the file2 to set
	 */
	public void setFile2(String file2) {
		this.file2 = file2;
	}

	/**
	 * @return the tupleSize
	 */
	public Integer getTupleSize() {
		return tupleSize;
	}

	/**
	 * @param tupleSize the tupleSize to set
	 */
	public void setTupleSize(Integer tupleSize) {
		this.tupleSize = tupleSize;
	}

}
