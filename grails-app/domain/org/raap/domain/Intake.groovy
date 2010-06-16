package org.raap.domain;

import org.raap.domain.user.User

/**
 * The Intake domain class represents the counselor's notes
 * on the Client's intake.  An intake object is created as soon as a
 * Client is newly created (and put into the PreIntake state).  The
 * intake workflow is managed by data in this (intake notes) object.
 * 
 * @author bryan
 * @version 0.1
 */
public class Intake {
	
	// Public Constants
	static final String STATE_CREATED = 'Created';
	static final String STATE_SCHEDULED =  'Scheduled';
	static final String STATE_SAVED = 'Partial';
	static final String STATE_COMPLETED = 'Completed';
	static final String STATE_SIGNED_OFF = 'SignedOff';
	
	// An intake only exists within the context of a Client
	Engagement engagement
	
	// Information about the crime
	VictimizationNotes caseNotes = new VictimizationNotes()
	
	// Immediate RAAP need
	String servicesRequested
	String presentingProblem
	String currentSymptoms
	
	// Trauma symptoms information
	String suicideHistory
	String mentalHealthHistory
	String drugUse
	String selfHarmBehaviors
	String sexualIssues
	
	// Historical information
	String assualtHistory
	String familyHistory
	String traumaticEvents
	String therapyHistory
	String careerEducation
	
	// Workflow
	String state = STATE_CREATED
	User intakeCounselor
	User intakeSupervisor
	Date intakeDate  // both day and time
	int appointmentMisses = 0
	boolean intakeComplete = false
	
	//
	// Methods
	//
	
	String toString() {
		return "[Intake: Client='${engagement.client.fullName}' ${state}]";
	}
	
	Client getClient() {
		return engagement.client;
	}
	
	//
	// GORM constraints
	//
	
	static belongsTo = [ engagement:Engagement ]
	static mapping = {
		caseNotes lazy:false
		engagement lazy:false
	}
	static transients = [ 'client' ]

    //
    // Data constraints
    //
	
	static constraints = {
		engagement ( nullable:false )
		
		// Case notes
		caseNotes ( nullable:false )
		
		// Immediate RAAP need
		servicesRequested ( nullable:true , blank:true , maxSize:2000 )
		presentingProblem ( nullable:true , blank:true , maxSize:2000 )
		currentSymptoms ( nullable:true , blank:true , maxSize:2000 )
		
		// Trauma symptoms information
		suicideHistory ( nullable:true , blank:true , maxSize:2000 )
		mentalHealthHistory ( nullable:true , blank:true , maxSize:2000 )
		drugUse ( nullable:true , blank:true , maxSize:2000 )
		selfHarmBehaviors ( nullable:true , blank:true , maxSize:2000 )
		sexualIssues ( nullable:true , blank:true , maxSize:2000 )
		
		// Historical information
		assualtHistory ( nullable:true , blank:true , maxSize:2000 )
		familyHistory ( nullable:true , blank:true , maxSize:2000 )
		traumaticEvents ( nullable:true , blank:true , maxSize:2000 )
		therapyHistory ( nullable:true , blank:true , maxSize:2000 )
		careerEducation ( nullable:true , blank:true , maxSize:2000 )
		
		// Workflow
		state ( inList:[ STATE_CREATED, STATE_SCHEDULED,
		                 STATE_SAVED, STATE_COMPLETED,
		                 STATE_SIGNED_OFF ] )
		intakeCounselor ( nullable:true )
		intakeSupervisor ( nullable:true )
		intakeDate ( nullable:true )
		appointmentMisses ( range:0..5 )
	}
	
}