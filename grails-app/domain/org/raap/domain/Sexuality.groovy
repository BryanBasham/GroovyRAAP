package org.raap.domain;

/**
 * The Sexuality domain class represents an extensible set of types
 * of human sexual orientation.  This is <emp>not</emp> the same as
 * the person's gender identification which is represented by the
 * {@link org.raap.domain.Gender} domain class.
 */
public class Sexuality {

	String name
	String dbCode

	static constraints = {
		dbCode(size:2..2, blank:false, unique:true)
		name(blank:false)
	}

	//
	// Methods
	//

	String toString() {
		return name
	}
	
}