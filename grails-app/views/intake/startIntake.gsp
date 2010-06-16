<%@ page import="org.raap.domain.Client" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Create Client for Intake</title>
    </head>
    <body>
        <div class="body">
            <h1>Create Client for Intake</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${client}">
            <div class="errors">
                <g:renderErrors bean="${client}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="savePreIntake" method="post" >
                <div class="dialog">
                
                <fieldset id='demographics'>
                	<legend>Demographics</legend>
                	
                    <div id="firstNameDIV" class="prop">
                        <label for="firstName"><g:message code="client.firstName.label" default="First Name" /></label>
                        <g:textField name="firstName" value="${client?.firstName}" />
                    </div>
                
                    <div id="lastNameDIV" class="prop">
                        <label for="lastName"><g:message code="client.lastName.label" default="Last Name" /></label>
                        <g:textField name="lastName" value="${client?.lastName}" />
                    </div>
                
                    <div id="dateOfBirthDIV" class="prop">
                        <label for="dateOfBirth"><g:message code="client.dateOfBirth.label" default="Date Of Birth" /></label>
                        <g:datePicker name="dateOfBirth" precision="day" value="${client?.dateOfBirth}" noSelection="['': '']" />
                    </div>
                
                    <div id="genderDIV" id class="prop">
                        <label for="gender"><g:message code="client.gender.label" default="Gender" /></label>
                        <g:select name="gender.id" from="${org.raap.domain.Gender.list()}" optionKey="id" value="${client?.gender?.id}" noSelection="['null': '']" />
                    </div>
                
                    <div id="raceDIV" class="prop">
                        <label for="race"><g:message code="client.race.label" default="Race" /></label>
                        <g:select name="race.id" from="${org.raap.domain.Ethnicity.list()}" optionKey="id" value="${client?.race?.id}" noSelection="['null': '']" />
                    </div>
                
                    <div id="languagesDIV" class="prop">
                        <label for="languages"><g:message code="client.languages.label" default="Languages" /></label>
                        <div id="languagesValuesDIV">
                        <g:each in="${org.raap.domain.Language.list()}">
							<div>
							<input type='checkbox' name="${it.dbCode}" value="${it.name}" />
							<span class="checkboxItem">${it.name}</span>
							</div>
						</g:each>
						</div>
                    </div>
                
                </fieldset>
                
                <fieldset id='contactInfo'>
                	<legend>Contact Information</legend>
                	
                    <div id="cellPhoneDIV" class="prop">
                        <label for="cellPhone"><g:message code="client.cellPhone.label" default="Cell Phone" /></label>
                        <g:textField name="_cellPhone_number" value="${client?.cellPhone?.number}" />
                    </div>
                
                    <div id="homePhoneDIV" class="prop">
                        <label for="homePhone"><g:message code="client.homePhone.label" default="Home Phone" /></label>
                        <g:textField name="_homePhone_number" value="${client?.cellPhone?.number}" />
                    </div>
                
                    <div id="workPhoneDIV" class="prop">
                        <label for="workPhone"><g:message code="client.workPhone.label" default="Work Phone" /></label>
                        <g:textField name="_workPhone_number" value="${client?.cellPhone?.number}" />
                    </div>
                
                </fieldset>
                
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
