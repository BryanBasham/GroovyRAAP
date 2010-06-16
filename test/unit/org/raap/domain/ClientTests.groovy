package org.raap.domain;

import grails.test.GrailsUnitTestCase;
//import FsmGrailsPlugin;

public class ClientTests extends GrailsUnitTestCase {

	protected void setUp() throws Exception {
//		FsmGrailsPlugin.mockFsm(Client)
		super.setUp()
	}

	protected void tearDown() throws Exception {
		Client.metaClass = null
	    super.tearDown()
	}
	
	public void testLanguages() {
		// No lanauges
		def languages = [];
		mockDomain(Client);
		def c = new Client(languages:languages);
		assertEquals "no languages", "Unknown", c.allLanguages;
		
		// One language
		languages << new Language(name: "English");
		c = new Client(languages:languages);
		assertEquals "one language", "English", c.allLanguages;
		
		// Two languages
		languages << new Language(name: "Spanish");
		c = new Client(languages:languages);
		assertEquals "two languages", "English and Spanish", c.allLanguages;
		
		// Three languages
		languages << new Language(name: "French");
		c = new Client(languages:languages);
		assertEquals "three languages", "English, Spanish and French", c.allLanguages;
	}
	
	void testStateTransitions() {
//		mockDomain(Client)
//		
//		def client = new Client()
//		
//		assertTrue 'Initial state is PreIntake', ( client.state == 'PreIntake' );
//		
//		// Attempt to transition to the Evaluation state
//		def prevState = client.fire('state', 'event1')
//		assertTrue 'Check previous state', ( prevState == 'PreIntake' );
//		assertTrue 'Now in Evalution state', ( client.state == 'Evaluation' );
	}

//	public void testGetAge() {
//		def dob = Date.parse("07/22/1964")
//		def c1 = new Client(dateOfBirth : dob)
//
//		assertTrue c1.age == 45;
//	}

}