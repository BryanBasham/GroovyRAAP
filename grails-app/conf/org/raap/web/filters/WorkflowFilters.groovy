package org.raap.web.filters

import org.raap.domain.user.User
import org.raap.domain.user.Role
import org.raap.workflow.WorkflowController

class WorkflowFilters {
	
	def filters = {
		// Populate user information into the flash scope
		userInfoInjection(uri:'/**') {
			before = {
				if ( request.remoteUser ) {
					
					// Store the user object for use by the View components
					request.user = User.findByUsername(request.remoteUser)
					log.debug "userInfoInjection: " + request.user
					
					// Put user/role info into View model
					// HACK: I'm doing this because the Request methods
					// for JavaEE security do not appear to work in the View
					// components (only in the controller).
					request.userName = request.remoteUser
					request.userRoles = []
					WorkflowController.ALL_ROLES.each { role ->
						if ( request.isUserInRole(role) ) {
							request.userRoles << role;
							log.debug "Role: " + role
						}
					}
				}
				//
				return true
			}
		}
	}
}
