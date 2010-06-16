package org.raap.domain

import org.raap.domain.user.User

/**
 * The Comment class represents a single user's comment.
 * Comments can be associated with any number of domain entity types.
 * 
 * @author bryan
 * @version 0.1
 */
public class UserComment {
	
	String text
	User createdBy
	Date createDate

    //
    // Data constraints
    //
	
    static constraints = {
		text(blank:false, maxSize:1000)
		createdBy(nullable:false)
		createDate(nullable:false)
	}
}
