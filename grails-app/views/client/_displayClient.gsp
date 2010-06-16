
<fieldset id='demographics'>
    <legend>Demographics</legend>
    	
    <div id="fullNameDIV" class="prop">
        <label for="fullName"><g:message code="client.fullName.label" default="Full Name" /></label>
        <div class="value">${it.fullName}</div>
    </div>

  <g:if test="${it.dateOfBirth}">
    <div id="ageDIV" class="prop">
        <label for="age"><g:message code="client.age.label" default="Age" /></label>
        <div class="value">${it.age}</div>
    </div>

    <div id="dateOfBirthDIV" class="prop">
        <label for="dateOfBirth"><g:message code="client.dateOfBirth.label" default="Date Of Birth" /></label>
        <div class="value"><g:formatDate date="${it.dateOfBirth}" format="dd-MMM-yyyy" /></div>
    </div>
  </g:if>

  <g:if test="${it.gender}">
    <div id="genderDIV" class="prop">
        <label for="gender"><g:message code="client.gender.label" default="Gender" /></label>
        <div class="value">${it.gender}</div>
    </div>

	<g:if test="${it.genderInfo}">
    <div id="genderInfoDIV" class="prop">
        <label for="genderInfo"><g:message code="client.genderInfo.label" default="(additional info)&nbsp;" /></label>
        <div class="value">${it.genderInfo}</div>
    </div>
    </g:if>
  </g:if>

  <g:if test="${it.sexualOrientation}">
    <div id="sexualOrientationDIV" class="prop">
        <label for="sexualOrientation"><g:message code="client.sexualOrientation.label" default="Sexual Orientation" /></label>
        <div class="value">${it.sexualOrientation}</div>
    </div>

	<g:if test="${it.sexualOrientationInfo}">
    <div id="sexualOrientationInfoDIV" class="prop">
        <label for="sexualOrientationInfo"><g:message code="client.sexualOrientationInfo.label" default="(additional info)&nbsp;" /></label>
        <div class="value">${it.sexualOrientationInfo}</div>
    </div>
    </g:if>
  </g:if>

  <g:if test="${! it.languages?.empty}">
    <div id="languagesDIV" class="prop">
        <label for="languages"><g:message code="client.languages.label" default="Languages" /></label>
        <div style="text-align: left;" class="value">
            ${it.allLanguages}
        </div>
    </div>

	<g:if test="${it.languageInfo}">
    <div id="languageInfoDIV" class="prop">
        <label for="languageInfo"><g:message code="client.languageInfo.label" default="(additional info)&nbsp;" /></label>
        <div class="value">${it.languageInfo}</div>
    </div>
    </g:if>
  </g:if>

<g:if test="${it.race}">
    <div id="raceDIV" class="prop">
        <label for="race"><g:message code="client.race.label" default="Race" /></label>
        <div class="value">${it.race}</div>
    </div>

	<g:if test="${it.raceInfo}">
    <div id="raceInfoDIV" class="prop">
        <label for="raceInfo"><g:message code="client.raceInfo.label" default="(additional info)&nbsp;" /></label>
        <div class="value">${it.raceInfo}</div>
    </div>
    </g:if>
    
</g:if>
    
</fieldset>

<fieldset id='contactInfo'>
    <legend>Contact Information</legend>
    	
	<g:if test="${it.homePhone?.number}">
    <div id="homePhoneDIV" class="prop">
        <label for="homePhone"><g:message code="client.homePhone.label" default="Home Phone" /></label>
        <div class="value">${it.homePhone}</div>
    </div>
    </g:if>

	<g:if test="${it.cellPhone?.number}">
    <div id="cellPhoneDIV" class="prop">
        <label for="cellPhone"><g:message code="client.cellPhone.label" default="Cell Phone" /></label>
        <div class="value">${it.cellPhone}</div>
    </div>
    </g:if>

	<g:if test="${it.workPhone?.number}">
    <div id="workPhoneDIV" class="prop">
        <label for="workPhone"><g:message code="client.workPhone.label" default="Work Phone" /></label>
        <div class="value">${it.workPhone}</div>
    </div>
    </g:if>

</fieldset>

<fieldset id='householdInfo'>
    <legend>Household Information</legend>
    	
	<g:if test="${it.householdInfo?.income}">
    <div id="incomeLevelDIV" class="prop">
        <label for="incomeLevel">Income Level</label>
        <div class="value">${it.householdInfo?.income}</div>
    </div>
    </g:if>

    <div id="numOfAdultsDIV" class="prop">
        <label for="numOfAdults"># of Adults</label>
        <div class="value">${it.householdInfo?.adultSize}</div>
    </div>

    <div id="numOfChildrenDIV" class="prop">
        <label for="numOfChildren"># of Children</label>
        <div class="value">${it.householdInfo?.childrenSize}</div>
    </div>

</fieldset>
