
<fieldset id='demographics'>
	<legend>Demographics</legend>
	
    <div id="firstNameDIV" class="prop">
        <label for="firstName"><g:message code="client.firstName.label" default="First Name" /></label>
        <g:textField name="firstName" value="${it?.firstName}" />
    </div>

    <div id="lastNameDIV" class="prop">
        <label for="lastName"><g:message code="client.lastName.label" default="Last Name" /></label>
        <g:textField name="lastName" value="${it?.lastName}" />
    </div>

    <div id="dateOfBirthDIV" class="prop">
        <label for="dateOfBirth"><g:message code="client.dateOfBirth.label" default="Date Of Birth" /></label>
        <g:datePicker name="dateOfBirth" precision="day" value="${it?.dateOfBirth}" noSelection="['': '']" />
    </div>

    <div id="genderDIV" class="prop">
        <label for="gender"><g:message code="client.gender.label" default="Gender" /></label>
        <g:select name="gender.id" from="${org.raap.domain.Gender.list()}" optionKey="id" value="${it?.gender?.id}" />
    </div>

    <div id="genderInfoDIV" class="prop">
        <label for="genderInfo"><g:message code="client.genderInfo.label" default="(additional info)&nbsp;" /></label>
        <g:textField name="genderInfo" value="${it?.genderInfo}" />
    </div>

    <div id="sexualOrientationDIV" class="prop">
        <label for="sexualOrientation"><g:message code="client.sexualOrientation.label" default="Sexual Orientation" /></label>
        <g:select name="sexualOrientation.id" from="${org.raap.domain.Sexuality.list()}" optionKey="id" value="${it?.sexualOrientation?.id}" />
    </div>

    <div id="sexualOrientationInfoDIV" class="prop">
        <label for="sexualOrientationInfo"><g:message code="client.sexualOrientationInfo.label" default="(additional info)&nbsp;" /></label>
        <g:textField name="sexualOrientationInfo" value="${it?.sexualOrientationInfo}" />
    </div>

    <div id="raceDIV" class="prop">
        <label for="race"><g:message code="client.race.label" default="Race" /></label>
        <g:select name="race.id" from="${org.raap.domain.Ethnicity.list()}" optionKey="id" value="${it?.race?.id}" />
    </div>

    <div  id="raceInfoDIV"class="prop">
        <label for="raceInfo"><g:message code="client.raceInfo.label" default="(additional info)&nbsp;" /></label>
        <g:textField name="raceInfo" value="${it?.raceInfo}" />
    </div>

    <div id="languagesDIV" class="prop">
        <label for="languages"><g:message code="client.languages.label" default="Languages" /></label>
        <div id="languagesValuesDIV">
        <g:each in="${org.raap.domain.Language.list()}" var="lang">
			<div>
			<input type='checkbox' name="${lang.dbCode}" value="${lang.name}"
			  <g:if test="${it.languages.contains(lang)}">
			    checked="checked"
			  </g:if>
			 />
			<span class="checkboxItem">${lang.name}</span>
			</div>
		</g:each>
		</div>
    </div>

    <div  id="languageInfoDIV"class="prop">
        <label for="languageInfo"><g:message code="client.languageInfo.label" default="(additional info)&nbsp;" /></label>
        <g:textField name="languageInfo" value="${it?.languageInfo}" />
    </div>

</fieldset>

<fieldset id='contactInfo'>
	<legend>Contact Information</legend>
	
    <div id="homePhoneDIV" class="prop">
        <label for="homePhone"><g:message code="client.homePhone.label" default="Home Phone" /></label>
        <g:textField name="_homePhone_number" value="${it?.homePhone?.number}" />
    </div>

    <div id="cellPhoneDIV" class="prop">
        <label for="cellPhone"><g:message code="client.cellPhone.label" default="Cell Phone" /></label>
        <g:textField name="_cellPhone_number" value="${it?.cellPhone?.number}" />
    </div>

    <div id="workPhoneDIV" class="prop">
        <label for="workPhone"><g:message code="client.workPhone.label" default="Work Phone" /></label>
        <g:textField name="_workPhone_number" value="${it?.workPhone?.number}" />
    </div>

</fieldset>

<fieldset id='householdInfo'>
	<legend>Household Information</legend>
	
    <div id="incomeLevelDIV" class="prop">
        <label for="incomeLevel"><g:message code="client.incomeLevel.label" default="Income Level" /></label>
        <g:select name="income.id" from="${org.raap.domain.IncomeLevel.list()}" optionKey="id" value="${it?.householdInfo?.income?.id}" />
    </div>

    <div id="numOfAdultsDIV" class="prop">
        <label for="numOfAdults"><g:message code="client.numOfAdults.label" default="# of Adults" /></label>
        <g:select name="adults" from="${1..5}" value="${it?.householdInfo?.adults}" />
    </div>

    <div id="numOfChildrenDIV" class="prop">
        <label for="numOfChildren"><g:message code="client.numOfChildren.label" default="# of Children" /></label>
        <g:select name="children" from="${0..5}" value="${it?.householdInfo?.children}" />
    </div>

</fieldset>
