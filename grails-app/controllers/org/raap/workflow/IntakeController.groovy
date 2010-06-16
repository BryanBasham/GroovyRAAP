package org.raap.workflow

import org.raap.domain.*;
import org.raap.domain.user.User
import org.raap.domain.user.Role
//
import org.raap.workflow.WorkflowController;
import org.raap.domain.BindingUtils;

/**
 * This web controller manages the "client intake" process.
 * 
 * @author bryan
 * @version 0.3
 */
class IntakeController {

    /**
     * There is no "index" action for the Intake controller.
     * Send the user back to their Dashboard.
     */
    def index = {
    	redirect(controller: "workflow", action: "dashboard")
    }
    
    /**
     * The generic action for any Cancel button:
     * Send the user back to their Dashboard.
     */
    def cancel = index
    
    /**
     * The action that launches the "Start Intake" form.
     */
    def startIntake = {
    	return []
    }

    /**
     * The action that handles the "Start Intake" form.
     */
    def savePreIntake = {
    	// Create Client object
    	def client = new Client()
    	def sexualOrientation = Sexuality.findWhere(dbCode:'UN');
    	client.sexualOrientation = sexualOrientation;
    	def unknownIncomeLvl = IncomeLevel.findWhere(dbCode:'UN');
    	client.householdInfo = new HouseholdInfo(income:unknownIncomeLvl);
		// Bind the request parameters to the Client object
		BindingUtils.populateClientObject params, client
    	
		// Create Engagement object
		def engagement = new Engagement();
    	client.addToAllEngagements(engagement)
    	// Create Intake object
    	def intake = new Intake()
    	engagement.intake = intake
    	intake.engagement = engagement
    	intake.caseNotes = new VictimizationNotes()
    	
        if (client.save(flush: true)) {
            flash.message = "${message(code: 'intake.created.message', args: [intake.client.fullName])}"
            redirect(controller: 'workflow', action: "dashboard")
        }
        else {
            render(view: "startIntake", model: [client: client])
        }
    }
    
    /**
     * The action that launches the "Schedule Intake" form.
     */
    def editIntakeSchedule = {
    	def intake = Intake.get(params.id)
    	
    	// save intake form for next action (should use Web Flow)
    	session.intakeId = intake.id
    	
    	render(view: "scheduleIntake",
    			model: ['intake': intake , counselorsList: _createCounselorsList()])
    }
    
    /**
     * The action that handles the "Schedule Intake" form.
     */
    def scheduleIntake = {
    	def intake = Intake.get(session.intakeId)
    	
    	// Determine the action based upon which Submit button was pushed.
    	if ( params.cancel ) {
    		// Cancel the operation
    		flash.message = "Operation canceled"
			redirect(controller: 'workflow', action: 'dashboard')
			
    	} else if ( params.schedule ) {
    		// Schedule the intake
    		intake.properties = params
    		// Change the state
    		intake.state = Intake.STATE_SCHEDULED
    		// Persist the Intake
    		if ( intake.save(flush:true) ) {
                flash.message = "${message(code: 'intake.scheduled.message', args: [intake.client.fullName])}"
    			redirect(controller: 'workflow', action: 'dashboard')
    		} else {
    			return [ 'intake': intake , 'counselorsList': _createCounselorsList() ]
    		}
    		
    	} else {
    		flash.message = "Unknown error"
    		redirect(controller: 'workflow', action: 'dashboard')
    	}

    	// clear session values
    	session.intakeId = null
    	session.counselorsList = null
    }

    /**
     * The action that launches the "Edit Intake" form.
     */
    def editIntake = {
    	def intake = Intake.get(params.id);
        if (!intake) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'intake.label', default: 'Intake Notes'), params.id])}"
            redirect(action: "list")
        } else {
            return ['intake': intake, counselorsList: _createCounselorsList()]
        }
    }

    /**
     * The action that handles the "Edit Intake" form.
     */
    def saveIntake = {
    	def intake = Intake.get(params.id)
    	def save = false

    	// Determine the action based upon which Submit button was pushed.
    	if ( params.cancel ) {
    		// Cancel the operation
            flash.message = "${message(code: 'intake.editCanceled.message', args: [intake.client.fullName])}"

    	} else if ( params.save ) {
    		save = true;
    		// Bind the request parameters to the intake
    		BindingUtils.populateIntakeObject params, intake
    		// Send messsage to the user
            flash.message = "${message(code: 'intake.updated.message', args: [intake.client.fullName, ''])}"

    	} else if ( params.saveAndComplete ) {
    		save = true;
    		// Bind the request parameters to the intake
    		BindingUtils.populateIntakeObject params, intake
    		// Send messsage to the user
            flash.message = "${message(code: 'intake.updated.message', args: [intake.client.fullName, ' and flagged as complete'])}"
    		
    	} else {
    		flash.message = "Unknown error"
    	}
    	
    	// change state (if requested)
    	if ( params.save ) {
    		intake.state = Intake.STATE_SAVED
    	} else if ( params.saveAndComplete ) {
    		intake.state = Intake.STATE_COMPLETED
    	}
    	
    	if ( save ) {
    		// Persist the Intake
    		if ( intake.save(flush:true) ) {
    			redirect(controller: 'workflow', action: 'dashboard')
    		} else {
    			flash.message = "Could not save the intake notes.  Please fix errors and try again."
    			render(view: "editIntake", model: [ 'intake': intake ] )
    		}
    	} else {
    		// Send the user back to the 
    		redirect(controller: 'workflow', action: 'dashboard')
    	}
    }
    
    /**
     * The action that launches the "View Intake" form.
     */
    def showIntake = {
    	def intake = Intake.get(params.id)
    	if (!intake) {
    		flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'intake.label', default: 'Intake'), params.id])}";
    		redirect(action: "list")
    	} else {
    		['intake': intake, counselorsList: _createCounselorsList()]
    	}
    }
    
    /**
     * The action that handles the "Evaluate" action for the "View Intake" form.
     */
    def evalIntake = {
    	// Make sure we have a valid Intake object
    	def intake = Intake.get(params.id);
    	if (!intake) {
    		flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'intake.label', default: 'Intake'), params.id])}";
    		redirect(action: "cancel")
    	}
    	
    	def engagement = intake.engagement;
    	
    	// Now 
    	def disposition = params.disposition;
    	if ( disposition ) {
    		switch (disposition) {
    		case "APPROVE":
    			engagement.properties = params;
    			engagement.state = Engagement.STATE_ACCEPTED;
                flash.message = "${message(code: 'intake.approved.message', args: [engagement.client.fullName])}"
    			break;
    		case "SEND_BACK":
    			engagement.properties = params;
    			intake.state = Intake.STATE_SAVED;
                flash.message = "${message(code: 'intake.sentBack.message', args: [engagement.client.fullName, engagement.intakeCounselor])}"
    			break;
    		case "REFER":
    			engagement.properties = params;
    			engagement.state = Engagement.STATE_REFERRED;
                flash.message = "${message(code: 'intake.referred.message', args: [engagement.client.fullName])}"
    			break;
    		}
        	redirect(controller: "workflow", action: "dashboard")
    	} else {
    		flash.message = "You must pick a disposition for this intake.";
    		redirect(action: "showIntake");
    	}
    }
    
    // 
    // Private methods
    //

    private _createCounselorsList() {
    	// gather all active Counselor user
    	def counselorRole = Role.findByAuthority(WorkflowController.COUNSELOR_ROLE)
    	def counselorsList = new ArrayList()
    	User.findAllWhere( enabled: true ).each {
    		if ( it.authorities.contains(counselorRole) ) {
    			counselorsList.add it
    		}
    	}
    	return counselorsList
    }

}
