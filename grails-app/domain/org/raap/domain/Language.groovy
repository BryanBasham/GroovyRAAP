package org.raap.domain;

/**
 * The Language domain class represents an extensible set of languages
 * commonly spoken by RAAP clients.
 */
public class Language {

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