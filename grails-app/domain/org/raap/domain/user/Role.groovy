package org.raap.domain.user

import org.raap.domain.user.User

/**
 * Authority domain class.
 */
class Role {

	static hasMany = [people: User]

	/** description */
	String description
	/** ROLE String */
	String authority

	static constraints = {
		authority(blank: false, unique: true)
		description()
	}
	
	String toString() {
		return authority
	}
}
