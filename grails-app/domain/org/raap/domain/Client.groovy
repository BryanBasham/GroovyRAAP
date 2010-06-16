package org.raap.domain;

import java.util.Date
import java.util.Calendar

/**
 * The Client class represents a person who receives services from RAAP.
 * Clients are at the heart of the Client Services domain model.
 * 
 * @author bryan
 * @version 0.4
 */
public class Client {

	// Public Constants
	static final String STATE_CREATED = 'Created';
	static final String STATE_ACTIVE =  'Active';
	static final String STATE_INACTIVE = 'Inactive';
	
	// Basic identification
	String firstName
	String lastName
	Date dateOfBirth

	// Gender data
	Gender gender
	String genderInfo

	// Sexual orientation data
	Sexuality sexualOrientation
	String sexualOrientationInfo

	// Language data
	List<Language> languages  // in the future order might be used to indicate primary language
    String languageInfo

    // Ethnicity data
    Ethnicity race
    String raceInfo
    
    // Disability data
    boolean disability = false
    String disablilityInfo
    
    // Household demographics
    HouseholdInfo householdInfo

    // Contact info
    PhoneNumber homePhone
    PhoneNumber workPhone
    PhoneNumber cellPhone

    // Workflow data
    String state = STATE_CREATED
    List<Engagement> allEngagements
    
    //
    // Methods
    //

    /**
     * Gets the client's full name.
     * This is a computed (transient) value.
     */
    String getFullName() {
		return firstName + " " + lastName
	}

    /**
     * Gets the client's age.
     * This is a computed (transient) value.
     */
    int getAge() {
		Calendar today = Calendar.instance
		Calendar birthDate = Calendar.instance
		birthDate.setTime(dateOfBirth)

		def daysAlive = today.minus(birthDate)
		return daysAlive / 365 as int;
	}

    /**
     * Gets a comma-delimited list of the client's languages.
     * This is a computed (transient) value.
     */
    String getAllLanguages() {
    	def _numOfLanguages = languages.size();
    	def _allLanguages = "";
    	if ( _numOfLanguages == 0 ) {
    		_allLanguages = "Unknown";
    	} else if ( _numOfLanguages == 1 ) {
    		_allLanguages = languages[0];
    	} else {
    		def count = 1;
    		for ( lang in languages ) {
    			_allLanguages += lang;
    			count++;
    			if ( count < _numOfLanguages ) {
    				_allLanguages += ", ";
    			} else if ( count == _numOfLanguages ) {
    				_allLanguages += " and ";
    			}
    		}
    	}
    	return _allLanguages;
    }

    /**
     * Gets the client's current Intake notes.
     * This is a computed (transient) value.
     */
    Engagement getCurrentIntake() {
		return getCurrentEngagement().intake
	}

    /**
     * Gets the client's current Engagement object.
     * This is a computed (transient) value.
     */
    Engagement getCurrentEngagement() {
		return allEngagements.first()
	}
    
	String toString() { this.fullName }

    //
    // GORM constraints
    //
    
	static hasMany = [
				      languages : Language ,
				      allEngagements : Engagement
	                 ];
	static embedded = [
	                   'householdInfo',
	                   'homePhone',
	                   'workPhone',
	                   'cellPhone'
	                   ];

	// Flag fields as transient if they are not persistent
	static transients = [
	                     'age',
	                     'fullName',
	                     'allLanguages',
	                     'currentEngagement',
	                     'currentIntake'
	                     ]

    //
    // Data constraints
    //
	
    static constraints = {
		// Basic identification
		firstName ( blank:false )
		lastName ( blank:false )
        dateOfBirth ( nullable:true )
        
    	// Gender data
        gender ( nullable:false )
    	genderInfo ( nullable:true , blank:true , maxSize:255 )
    	
    	// Sexual orientation data
    	sexualOrientation ( nullable:false )
    	sexualOrientationInfo ( nullable:true , blank:true , maxSize:255 )

    	// Language data
    	languages ( minSize:1 )
        languageInfo ( nullable:true , blank:true , maxSize:255 )

        // Ethnicity data
        race ( nullable:false )
        raceInfo ( nullable:true , blank:true , maxSize:255 )
        
        // Disablility data
        disablilityInfo ( nullable:true , blank:true , maxSize:255 )

        // Workflow data
        state ( inList:[STATE_CREATED, STATE_ACTIVE, STATE_INACTIVE]  )
    }

    //
    // State management
    //

    // State transition definitions
    static fsm_def = [
       state : [
           Created : { flow ->
               flow.on ('event1') {
            	   from(STATE_CREATED).when({
            		   status == 'running'  // Depends on second flow
            	   }).to(STATE_ACTIVE).act({
            		   log.debug "Client is now active.";
            	   })
            	   from(STATE_CREATED).to(STATE_CREATED).act({
            		   log.debug "Client cannot be activated yet.";
            	   }) // Order is CRITICAL
               }
    		},
    	]
    ]
}