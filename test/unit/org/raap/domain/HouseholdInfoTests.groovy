package org.raap.domain

import grails.test.*

class HouseholdInfoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testValidation() {
    	mockDomain(HouseholdInfo)
    	def incomeLvl = new IncomeLevel(range:'$5,000 to $10,000')
    	
    	// test empty object fails
    	def hi = new HouseholdInfo();
    	assertFalse "empty object fails validation", hi.validate()
    	assertEquals "nullable", hi.errors.income
    	assertEquals "adults uninitialized", -1, hi.adults
    	assertEquals "min", hi.errors.adults
    	assertEquals "children uninitialized", -1, hi.children
    	assertEquals "min", hi.errors.children
    	
    	// test with income 
    	hi = new HouseholdInfo(income:incomeLvl)
    	assertFalse "only income fails validation", hi.validate()
    	assertEquals "adults uninitialized", -1, hi.adults
    	assertEquals "min", hi.errors.adults
    	assertEquals "children uninitialized", -1, hi.children
    	assertEquals "min", hi.errors.children
    	
    	// test without children 
    	hi = new HouseholdInfo(income:incomeLvl , adults:2)
    	assertFalse "without children fails validation", hi.validate()
    	assertEquals "adults initialized", 2, hi.adults
    	assertNull "no adults validation", hi.errors.adults
    	assertEquals "children uninitialized", -1, hi.children
    	assertEquals "min", hi.errors.children
    	
    	// test without adults 
    	hi = new HouseholdInfo(income:incomeLvl , children:3)
    	assertFalse "without adults fails validation", hi.validate()
    	assertEquals "adults uninitialized", -1, hi.adults
    	assertEquals "min", hi.errors.adults
    	assertEquals "children initialized", 3, hi.children
    	
    	// test with children over max
    	hi = new HouseholdInfo(income:incomeLvl , children:6)
    	assertFalse "with children over max fails validation", hi.validate()
    	assertEquals "children initialized", 6, hi.children
    	assertEquals "max", hi.errors.children
    	
    	// test with adults over max 
    	hi = new HouseholdInfo(income:incomeLvl , adults:6)
    	assertFalse "without adults fails validation", hi.validate()
    	assertEquals "adults initialized", 6, hi.adults
    	assertEquals "max", hi.errors.adults
    }

    void testAdultSize() {
    	def hi = new HouseholdInfo(adults: 2);
    	assertEquals "adults initialized", 2, hi.adults
    	assertEquals "2" , hi.adultSize;

    	hi = new HouseholdInfo(adults: 5);
    	assertEquals "5 or more" , hi.adultSize;
    }

    void testChildrenSize() {
    	def hi = new HouseholdInfo(children: 0);
    	assertEquals "children initialized", 0, hi.children
    	assertEquals "childrenSize OK", "none" , hi.childrenSize;
    	
    	hi = new HouseholdInfo(children: 2);
    	assertEquals "children initialized", 2, hi.children
    	assertEquals "childrenSize OK", "2" , hi.childrenSize;

    	hi = new HouseholdInfo(children: 5);
    	assertEquals "childrenSize OK", "5 or more" , hi.childrenSize;
    }
}
