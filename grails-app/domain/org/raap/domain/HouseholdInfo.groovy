package org.raap.domain;

/**
 * The HouseholdInfo domain class represents information about the Client's
 * household.
 */
public class HouseholdInfo {

	IncomeLevel income
	Integer adults  // the number of adults in the household
	Integer children  // the number of children in the household

	//
	// Methods
	//

	String getAdultSize() {
		if ( adults < 5 ) {
			return adults.toString();
		} else {
			return "5 or more";
		}
	}

	String getChildrenSize() {
		if ( children == 0 ) {
			return "none";
		} else if ( children < 5 ) {
			return children.toString();
		} else {
			return "5 or more";
		}
	}

	String toString() {
		return "Household: ${income.range} with ${adultSize} adults and ${childrenSize} children"
	}
	
	//
	// GORM constraints
	//

	static constraints = {
		income ( nullable:false )
		adults ( nullable:true, min:1 , max:5 )
		children ( nullable:true, min:0 , max:5 )
	}

	// Flag fields as transient if they are not persistent
	static transients = [ 'adultSize', 'childrenSize' ]

}