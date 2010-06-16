package org.raap.domain;

/**
 * The County domain class represents an extensible set of state
 * counties used for RAAP reporting.
 */
public class County {

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