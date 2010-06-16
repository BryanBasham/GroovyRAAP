package org.raap.domain;

/**
 * The Ethnicity domain class represents an extensible set of state
 * counties used for RAAP reporting.
 */
public class Ethnicity {

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