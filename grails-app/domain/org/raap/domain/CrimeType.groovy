package org.raap.domain;

/**
 * The CrimeType domain class represents an extensible set of crime types
 * used for RAAP reporting.
 */
public class CrimeType {

	String name
	String dbCode

	static constraints = {
		dbCode(size:3..3, blank:false, unique:true)
		name(blank:false)
	}

	//
	// Methods
	//

	String toString() {
		return name
	}
	
}