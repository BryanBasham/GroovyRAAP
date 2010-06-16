package org.raap.domain;

/**
 * The ServiceType domain class represents an extensible set of types
 * services supplied by RAAP to its clients.
 */
public class ServiceType {
	
	//
	// Constants
	//
	
	static final String TYPE_INDIVIDUAL_COUNSELING = 'IC';
	
	//
	// Attributes
	//

	String name;
	String dbCode;
	Integer defaultDuration;

	//
	// Methods
	//

	String toString() { name }
	
    //
    // Data constraints
    //
	
	static constraints = {
		dbCode( blank:false, unique:true, size:2..2 )
		name( blank:false )
		defaultDuration( nullable:false, min:1, max:480 )  // 8 hr max
	}

}