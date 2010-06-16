package org.raap.controllers

import org.raap.domain.*;
import org.raap.domain.user.User;
import org.raap.domain.user.Role;

// Utilities
import grails.converters.*

/**
 * This web controller manages the "client engagement" process.
 * This is the central controller for all actions to the Client
 * and Engagement records while the client is active.  This includes:
 * <ul>
 *   <li>Adding a service record</li>
 *   <li>Adding a session note</li>
 *   <li>... and so on</li>
 * </ul>
 * 
 * @author bryan
 */
class EngagementController {
	
	// Local, shared reference to this request's Engagement object
	def engagement = null;
	// Local, shared reference to this request's Client object
	def client = null;
	// Local, shared reference to this request's User object
	def user = null;

    /**
     * There is no "index" action.
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
     * This action launches the "Add a Service" form.
     */
    def addService = {
    	client = Client.get(params.id);
    	def service = new Service();
    	service.engagement = client.currentEngagement;
    	log.info "engagement id: " + service.engagement.id;
    	def user = User.findByUsername(request.remoteUser);
    	service.providedBy = user;
    	
    	return [ 'service': service ];
    }
    
    /**
    /**
     * This action launches the "Add a Service" form.
     */
    def addServiceByEngagement = {
    	engagement = Engagement.get(params.id);
    	def service = new Service(engagement: engagement);
    	log.info "engagement id: " + service.engagement.id;
    	def user = User.findByUsername(request.remoteUser);
    	service.providedBy = user;
    	
    	render(view: 'addService', model: [ 'service': service ]);
    }
    
    /**
     * An Ajax action to retrieve the service types list in JSON format.
     * This data will be used by the webapp on the Browser.
     */
    def getServiceTypes = {
    	render ServiceType.list() as JSON;
    }

    /**
     * This action handles the "Add a Service" form.
     */
    def saveService = {
    	_saveService params, "addService";
    }

    /**
     * This action launches the "Add Session Notes" form.
     * 
     */
    def addSessionNote = {
    	
    	// Create a service that is associated with the Client's
    	// current Engagement object.
    	client = Client.get(params.id);
    	def service = new Service();
    	service.engagement = client.currentEngagement;
    	log.info "engagement id: " + service.engagement.id;
    	
    	// Define the specific service type
    	def indivCounselingService = ServiceType.findByDbCode('IC');
    	service.type = indivCounselingService;
    	
    	// Define the current user as the person providing the service
    	def user = User.findByUsername(request.remoteUser);
    	service.providedBy = user;
    	
    	return [ 'service': service ];
    }
    
    /**
     * This action handles the "Add Session Notes" form.
     */
    def saveSessionNote = {
    	// Session notes are embedded in the Service object
    	_saveService params, "addSessionNote";
    }

    /**
     * This action launches the "Show Client Engagement" screen.
     * This screen is the user's central entry point to viewing
     * lots of client and engagement information.
     */
    def show = {
    	def model = [:];
    	
    	// Retrieve the Client from the ID in the request params
        log.info "client id: " + params.id;
        model.client = client = Client.get(params.id);

    	// Retrieve the current Engagement object from the Client
        model.engagement = engagement = client.currentEngagement;
    	log.info "engagement id: " + engagement.id;

    	// Retrieve the current User object from the request
    	model.user = user = User.findByUsername(request.remoteUser);
    	
    	// Build the "permissions model"
    	model.permissions = _createPermissions(engagement, user);
    	
    	// Build permission-based view model elements
    	if ( model.permissions.canShowIntake ) {
    		model.intake = engagement.intake;
    	}
    	if ( model.permissions.canShowSessionNotes ) {
        	def crit1 = Service.createCriteria();
    		model.sessionNotes = crit1.list {
    			eq ( 'engagement', engagement )
    			eq ( 'type', ServiceType.findByDbCode(ServiceType.TYPE_INDIVIDUAL_COUNSELING))
    		}
    	}
    	if ( model.permissions.canShowServices ) {
        	def crit1 = Service.createCriteria();
    		model.services = crit1.list {
    			eq ( 'engagement', engagement )
    		}
    	}
    	
    	// Render the 'show' view with this model
    	return model;
    }

    /**
     * This action launches the "Show Session Notes" screen.
     * This screen allows the Clinical Supervisor to view all new
     * notes from individual counseling sessions.
     */
    def showSessionNotes = {
    	def client = Client.get(params.id);
    	def engagement = client.currentEngagement;
    	
    	def query = Service.createCriteria();
    	def sessionList = query.list {
    		eq ( 'state', Service.STATE_CREATED )
    		eq ( 'engagement', engagement )
    		'type' {
    			eq ( 'dbCode', ServiceType.TYPE_INDIVIDUAL_COUNSELING )
    		}
    	};
    	log.info "sessionList: " + sessionList;
    	
    	// return model
    	[ 'sessionList': sessionList ];
    }
    
    //
    // Private methods
    //

    private _saveService(params, errorView) {
    	def service = new Service(params);

    	// Determine the action based upon which Submit button was pushed.
    	if ( params.cancelAction ) {
    		// Cancel the operation
    		flash.message = "Operation canceled"
			redirect(controller: 'workflow', action: 'dashboard')
			
    	} else if ( params.saveAction ) {
    		// Persist the Intake
    		if ( service.save(flush:true) ) {
                flash.message = "${message(code: 'service.created.message', args: [service.engagement.client.fullName])}"
    			redirect(controller: 'workflow', action: 'dashboard')
    		} else {
        		flash.message = "Please fix these errors and resubmit this form.";
    			render(view:errorView, model: [ 'service': service ]);
    		}
    		
    	} else {
    		flash.message = "Unknown action selected."
    		redirect(controller: 'workflow', action: 'dashboard')
    	}
    }

    private _createPermissions(Engagement engagement, User user) {
    	def permissions = [:] ; // empty map
    	
    	// Determine role predicates
    	def isCaseManager =
    		false; // TODO
    	def isIndividualCounselor =
    		(user == engagement?.individualCounselor);
    	def isClinicalSupervisor =
    		(user == engagement?.individualCounselor.supervisor);
    	
    	// Store role flags
    	permissions.isCaseManager = isCaseManager;
    	permissions.isIndividualCounselor = isIndividualCounselor;
    	permissions.isClinicalSupervisor = isClinicalSupervisor;
    	
    	// Can show the demographics?
    	permissions.canShowDemographics =
    		(isIndividualCounselor || isClinicalSupervisor || isCaseManager);
    	
    	// Can show the contact info?
    	permissions.canShowContactInfo =
    		(isIndividualCounselor || isClinicalSupervisor || isCaseManager);
    	
    	// Can show the intake?
    	permissions.canShowIntake =
    		(isIndividualCounselor || isClinicalSupervisor || isCaseManager);
    	
    	// Can show the session notes?
    	permissions.canShowSessionNotes =
    		(isIndividualCounselor || isClinicalSupervisor);
    	
    	// Can show the other services?
    	permissions.canShowServices =
    		(isIndividualCounselor || isClinicalSupervisor || isCaseManager);
    	
    	return permissions;
    }
}
