package org.raap.domain;

public class PhoneNumber {

	String number
	Boolean canLeaveMsg

	String toString() { number }

	static constraints = {
		number(nullable : true)
		canLeaveMsg(nullable : true)
	}

}