package org.raap.workflow

import org.raap.domain.*;
import org.raap.domain.user.User
import org.raap.domain.user.Role


/**
 * This web controller manages the overall user experience and workflow.
 * 
 * @author bryan
 * @version 0.3
 */
class WorkflowController {
	
	//
	// Public constants
	//
	
	// Names for all RAAP job roles
	public static final String COUNSELOR_ROLE = "ROLE_COUNSELOR";
	public static final String INTAKE_COORDINATOR_ROLE = "ROLE_INTAKE_COORDINATOR";
	public static final String CLINICAL_SUPERVISOR_ROLE = "ROLE_CLINICAL_SUPERVISOR";
	public static final String ADMIN_ROLE = "ROLE_ADMIN";
//	public static final String XYZ_ROLE = "ROLE_XYZ";
//	public static final String XYZ_ROLE = "ROLE_XYZ";
	// The complete list of RAAP job roles
	public static final ALL_ROLES =
		[ COUNSELOR_ROLE, INTAKE_COORDINATOR_ROLE,
		  CLINICAL_SUPERVISOR_ROLE, ADMIN_ROLE ];
	
	// These variables are thread-safe because the Grails Front Controller
	// make a unique instance for each HTTP request.
	private model

	// This is the webapp's root index.
	def index = { redirect action: "dashboard" }
    def dashboard = {
    	this.model = [ modelType: "Dashboard", user: request.user ];
    	
    	// Build the Dashboard model based on each of the user's roles
    	if ( request.isUserInRole(COUNSELOR_ROLE) ) _addCounselorModel()
    	if ( request.isUserInRole(INTAKE_COORDINATOR_ROLE) ) _addIntakeCoordinatorModel()
    	if ( request.isUserInRole(CLINICAL_SUPERVISOR_ROLE) ) _addClinicalSupervisorModel()
    	if ( request.isUserInRole(ADMIN_ROLE) ) _addAdminModel()
    	
    	render view: "dashboard" , model: this.model
    }
	
	//
	// Private methods
	//

	/*
	 * Builds the GUI model for the Counselor role.
	 */
    private void _addCounselorModel(model) {
    	// Retrieve intakes where user is the "Individual Counselor"
		this.model.counselor_activeClients =
    		Engagement.findAllWhere( individualCounselor: request.user );
		log.debug "counselor_activeClients: " + this.model.counselor_activeClients
    	
    	// Retrieve intakes where user is the "Intake Counselor"
    	def crit1 = Intake.createCriteria();
    	this.model.counselor_scheduledIntakeList =
    		crit1.list {
    			 eq  ( "intakeCounselor", request.user )
    			'in' ( "state", [Intake.STATE_SCHEDULED, Intake.STATE_SAVED] )
    		};
//		this.model.counselor_scheduledIntakeList =
//    		Intake.findAllWhere( intakeCounselor: request.user, state: Intake.STATE_SCHEDULED );
        log.debug "counselor_scheduledIntakeList: " + this.model.counselor_scheduledIntakeList
    }

	/*
	 * Builds the GUI model for the Intake Coordinator role.
	 */
    private void _addIntakeCoordinatorModel(model) {
    	// Retrieve intakes with no intakeDate scheduled
    	this.model.intakeCoor_preIntakeClients =
    		Intake.findAllWhere( state: Intake.STATE_CREATED );
    	log.debug "intakeCoor_preIntakeClients: " + this.model.intakeCoor_preIntakeClients

    	// List *all* scheduled intakes
    	this.model.intakeCoor_scheduledIntakeList =
    		Intake.findAllWhere( state: Intake.STATE_SCHEDULED );
    	log.debug "intakeCoor_scheduledIntakeList: " + this.model.intakeCoor_scheduledIntakeList
    }

	/*
	 * Builds the GUI model for the Clinical Supervisor role.
	 */
    private void _addClinicalSupervisorModel(model) {
    	
    	// List *all* supervised clinicians
    	def clinicians = User.findAllWhere(enabled:true, supervisor: request.user);
    	log.debug "clinicalSuper_clinicians: " + clinicians;
    	this.model.clinicalSuper_clinicianSummaries = clinicians.collect {
    		def summary = [ user: it ];
    		summary.numOfActiveClients =
    			Engagement.countByIndividualCounselor(it);
    		return summary;
    	}
    	log.debug "clinicalSuper_clinicianSummaries: " + this.model.clinicalSuper_clinicianSummaries;
    	
    	// List *all* scheduled intakes
    	this.model.clinicalSuper_completedIntakeList =
    		Intake.findAllWhere( state: Intake.STATE_COMPLETED );
    	log.debug "clinicalSuper_completedIntakeList: " + this.model.clinicalSuper_completedIntakeList;

    	// Retrieve intakes with no intakeDate scheduled
    	def crit1 = Intake.createCriteria();
    	this.model.clinicalSuper_intakeSummary =
    		crit1.list {
    			projections {
    				groupProperty("state")
    				count("state")
    			}
    		};
    	log.debug "clinicalSuper_intakeSummary: " + this.model.clinicalSuper_intakeSummary

    }

	/*
	 * Builds the GUI model for the System Administor role.
	 */
    private void _addAdminModel(model) {
    	// Retrieve all active users
    	this.model.admin_activeUsers =
    		User.findAllWhere( enabled: true );
    	log.debug "admin_activeUsers: " + this.model.admin_activeUsers
    }
    
}
