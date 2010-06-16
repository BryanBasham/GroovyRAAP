package org.raap.domain

import org.raap.domain.user.User;

/**
 * The Engagement class represents a single time-period in which a Client
 * attempts to engage RAAP for services.
 * 
 * @author bryan
 */
class Engagement {
	
	// Public Constants
	static final String STATE_STARTED = 'Started';
	static final String STATE_ACCEPTED = 'Accepted';
	static final String STATE_REFERRED = 'Referred';
	static final String STATE_TERMINATED = 'Terminated';

	/**
	 * An engagement only exists within the context of a Client.
	 */
	Client client;

	/**
	 * The intake notes for this engagement.
	 */
	Intake intake;

	/**
	 * The Counselor assigned to this client's engagement
	 * for individual counseling.
	 */
	User individualCounselor;
	
	/**
	 * The set of services provided to this Client within this specific
	 * Engagement.  This is *all* services including individual counseling,
	 * group counseling, and case management.
	 */
	Set<Service> services;
	
	// Workflow
	String state = STATE_STARTED;
	String dispositionComment
	
	//
	// Methods
	//
	
	String toString() {
		return "[Engagement: Client='${client.fullName}' ${state}]";
	}
	
	//
	// GORM constraints
	//

	static hasMany = [
				      services : Service
//				      ,groups : Group
	                 ];
	static belongsTo = [ client:Client ]

    //
    // Data constraints
    //
	
	static constraints = {
		client ( nullable:false )
		intake ( nullable:false )
		individualCounselor ( nullable:true )
		
		// Workflow
		state ( inList:[ STATE_STARTED, STATE_ACCEPTED,
		                 STATE_REFERRED, STATE_TERMINATED ] )
		dispositionComment ( nullable:true , blank:true , maxSize:2000 )
	}
}
