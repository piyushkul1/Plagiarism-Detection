package com.tripadvisor;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic N-tuple class for storing a list of elements and the tuple size
 * 
 * @author piyush
 * @since 1.0
 * @param <E>
 */
public class Tuple<E> {

	private List<E> elements;
	private Integer size;

	/**
	 * Construct the tuple when size is unknown
	 * 
	 * @param size
	 */
	public Tuple() {
		this.elements = new ArrayList<E>();
	}

	/**
	 * Construct the tuple using size
	 * 
	 * @param size
	 */
	public Tuple(Integer size) {
		this.elements = new ArrayList<E>(size);
		this.size = size;
	}

	/**
	 * @return the elements
	 */
	public List<E> getElements() {
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<E> elements) {
		this.elements = elements;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * Add an element in the tuple
	 * 
	 * @param element
	 */
	public void addElement(E element) {
		this.elements.add(element);
	}

}
