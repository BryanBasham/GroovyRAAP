package org.raap.domain;

/**
 * The IncomeLevel domain class represents an extensible set of household
 * income levels used for RAAP reporting.
 */
public class IncomeLevel {

	String range
	String dbCode

	static constraints = {
		dbCode(size:2..2, blank:false, unique:true)
		range(blank:false)
	}

	//
	// Methods
	//

	String toString() {
		return range
	}
	
}