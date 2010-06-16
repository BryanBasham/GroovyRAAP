package org.raap.domain.user

import org.raap.domain.user.Role

/**
 * User domain class.
 */
class User implements java.io.Serializable {
	static transients = ['pass']
	static hasMany = [authorities: Role]
	static belongsTo = Role

	/** Username */
	String username
	/** User Real Name*/
	String userRealName
	/** MD5 Password */
	String passwd
	/** enabled */
	boolean enabled

	String email
	boolean emailShow

	/** description */
	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	/* The user's supervisor.  This is most important for
	 * ROLE_COUNSELOR and ROLE_CASE_MANAGER roles. */
	User supervisor

	String toString() {
		return userRealName;
	}

	static constraints = {
		username(blank: false, unique: true)
		userRealName(blank: false)
		passwd(blank: false)
		enabled()
	}
}
