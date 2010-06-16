package org.raap.domain

import org.raap.domain.user.User;

/**
 * The Service class represents a single service provided for a Client by
 * RAAP's counseling and case management staff.
 * 
 * @author bryan
 *
 */
class Service implements Comparable {
	
	// Public Constants
	static final String STATE_CREATED = 'Created';
	static final String STATE_APPROVED = 'Approved';
	static final String STATE_DENIED = 'Denied';
	
	// An intake only exists within the context of a client engagement
	Engagement engagement;
	
	// Service data
	ServiceType type;
	Date dateOfService;
	User providedBy;
	Integer duration;
	String notes;
	
	// Workflow
	String state = STATE_CREATED;

	//
	// Methods
	//
	
	String toString() {
		return "[Service: '${type}' for '${engagement.client.fullName}' on ${dateOfService} :: ${state}]";
	}
	 
	int compareTo(obj) {
		dateOfService.compareTo(obj.dateOfService)
	}
	
	//
	// GORM constraints
	//
	
	static belongsTo = [ engagement:Engagement ]

    //
    // Data constraints
    //
	
	static constraints = {
		engagement ( nullable:false )
		type ( nullable:false )
		dateOfService ( nullable:false )
		providedBy ( nullable:false )
		duration ( nullable:false )
		notes ( nullable:false , blank:false , maxSize:2000 )
		
		// Workflow
		state ( inList:[ STATE_CREATED, STATE_APPROVED, STATE_DENIED ] )
	}
}
