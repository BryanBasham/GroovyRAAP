package org.raap.domain

class BindingUtils {

	static void populateClientObject(params, client) {
		// Bind basic Client properties
    	client.properties = params

        // Handle language values
        def newLanguages = [];
        Language.list().each {
            if ( params[it.dbCode] ) {
            	newLanguages << it
            }
        };
        client.languages = newLanguages;

        // Handle embedded value object creation
        client.homePhone =
        	new PhoneNumber(number: params._homePhone_number,
        			canLeaveMsg: null);
        client.workPhone =
        	new PhoneNumber(number: params._workPhone_number,
        			canLeaveMsg: null);
        client.cellPhone =
        	new PhoneNumber(number: params._cellPhone_number,
        			canLeaveMsg: null);

        // Handle household info
        client.householdInfo.properties = params
        
    }

	static void populateIntakeObject(params, intake) {
		// Bind basic Intake properties
		intake.properties = params;

		// Bind Client properties
		populateClientObject params, intake.client;
    }

}
