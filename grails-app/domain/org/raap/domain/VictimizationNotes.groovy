package org.raap.domain;

import org.raap.domain.user.User

/**
 * The VictimizationNotes domain class represents a single case of
 * victimization against the Client.
 * 
 * @author bryan
 * @version 0.3
 */
public class VictimizationNotes {

	// The Client is the victim of some crime
	Intake relatedIntake
	
	// Information about the case
	CrimeType type
	Date incidentDate
	String perpetrator   // a future system might use a real Person object here
	County countyCrimeTookPlace
	boolean incidentReported = false
	String description
	String agency
	String caseNumber
	String respondingOfficer  // name and title
	

    //
    // GORM constraints
    //

	static belongsTo = [relatedIntake:Intake]
	
	static constraints = {
		relatedIntake(nullable:false)
		// Information about the case
		type(nullable:true)
		incidentDate(nullable:true)
		perpetrator(nullable:true , blank:true , size:0..50)
		countyCrimeTookPlace(nullable:true)
		description(nullable:true , blank:true , maxSize:2000)
		agency(nullable:true , blank:true , maxSize:100)
		caseNumber(nullable:true , blank:true , maxSize:32)
		respondingOfficer(nullable:true , blank:true , maxSize:100)
	}

	//
	// Methods
	//

//	String toString() {
//		return ???
//	}
	
}