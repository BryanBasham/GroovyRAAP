package org.raap.domain;

/**
 * The Gender domain class represents an extensible set of types of
 * human gender.  This is <emp>not</emp> the same as the person's
 * sexual orientation which is represented by the
 *  {@link org.raap.domain.Sexuality} domain class.
 */
public class Gender {

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