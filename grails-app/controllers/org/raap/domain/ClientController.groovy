package org.raap.domain

import org.raap.domain.user.*;

class ClientController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def listForClinician = {
    	def clinician = User.get(params.id);
    	log.debug "clinician: " + clinician;
        
        def crit = Engagement.createCriteria();
        def engagementList = crit.list {
    		eq ( 'state', Engagement.STATE_ACCEPTED )
        	eq ( 'individualCounselor', clinician )
        };
        log.debug "engagementList: " + engagementList;
        def clientTotal = engagementList.size();
        
        // stats for each clinician
        def clientStatsList = [];
        for ( engagement in engagementList ) {
        	
        	// make client stats entry
        	def clientStats = ['client':engagement.client];
        	clientStatsList << clientStats;
        	
        	// count new session notes from IndivCounseling services
        	crit = Service.createCriteria();
        	clientStats.numOfSessions = crit.count {
        		eq ( 'state', Service.STATE_CREATED )
        		eq ( 'engagement', engagement )
        		'type' {
        			eq ( 'dbCode', ServiceType.TYPE_INDIVIDUAL_COUNSELING )
        		}
        	};
        	
        	// count new services (except IndivCounseling)
        	crit = Service.createCriteria();
        	clientStats.numOfServices = crit.count {
        		eq ( 'state', Service.STATE_CREATED )
        		eq ( 'engagement', engagement )
        		'type' {
        			ne ( 'dbCode', ServiceType.TYPE_INDIVIDUAL_COUNSELING )
        		}
        	};
        }
        log.info "clientStatsList: " + clientStatsList;
        
        ['clinician': clinician, 'clientStatsList': clientStatsList, 'clientTotal': clientTotal];
    }

    /**
     * The action that launches the "Create Client" form.
     */
    def create = {
        def client = new Client()
        client.properties = params
        client.householdInfo = new HouseholdInfo();
        return [client: client, languageList: Language.list()]
    }

    /**
     * The action that handles the "Create Client" form.
     */
    def save = {
        def client = new Client()

		// Bind the request parameters to the intake
		BindingUtils.populateClientObject params, client

        // Change state from PreIntake to Evaluation
        //fire_status 'Evaluation'

        if ( client.save(flush:true) ) {
            flash.message = "${message(code: 'client.created.message', args: [client.fullName])}"
            redirect(action: "show", id: client.id)
        }
        else {
            render(view: "create", model: [client: client])
        }
    }

    /**
     * The action that launches the "View Client" form.
     */
    def show = {
        def client = Client.get(params.id)
        if (!client) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'client.label', default: 'Client'), params.id])}"
            redirect(action: "list")
        }
        else {
            [client: client]
        }
    }

    /**
     * The action that launches the "Edit Client" form.
     */
    def edit = {
        def client = Client.get(params.id)
        if (!client) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'client.label', default: 'Client'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [client: client]
        }
    }

    /**
     * The action that handles the "Edit Client" form.
     */
    def update = {
        def client = Client.get(params.id)
        if (client) {
            if (params.version) {
                def version = params.version.toLong()
                if (client.version > version) {
                    
                    client.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'client.label', default: 'Client')] as Object[], "Another user has updated this Client while you were editing")
                    render(view: "edit", model: [client: client])
                    return
                }
            }

    		// Bind the request parameters to the intake
    		BindingUtils.populateClientObject params, client
            
            // Change state from PreIntake to Evaluation
            //fire_status 'Evaluation'

            // 
            if (!client.hasErrors() && client.save(flush: true)) {
                flash.message = "${message(code: 'client.updated.message', args: [client.fullName])}"
                redirect(action: "show", id: client.id)
            }
            else {
                render(view: "edit", model: [client: client])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'client.label', default: 'Client'), params.id])}"
            redirect(action: "list")
        }
    }

    /*
     * NOT USED
    def delete = {
        def client = Client.get(params.id)
        if (client) {
            try {
                client.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'client.label', default: 'Client'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'client.label', default: 'Client'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'client.label', default: 'Client'), params.id])}"
            redirect(action: "list")
        }
    }
     */
}
