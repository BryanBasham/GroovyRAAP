<%@ page import="org.raap.domain.Client" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Edit Intake</title>
    </head>
    <body>
        <div class="body">
            <h1>Edit Intake</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${intake}">
            <div class="errors">
                <g:renderErrors bean="${intake}" as="list" />
            </div>
            </g:hasErrors>
            <g:hasErrors bean="${client}">
            <div class="errors">
                <g:renderErrors bean="${client}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveIntake" method="post" >
                <g:hiddenField name="id" value="${intake?.id}" />
                <g:hiddenField name="version" value="${intake?.version}" />
                <div class="dialog">
		            
				  <g:ifUserHasRole roles="ROLE_COUNSELOR">
				  <g:if test="${intake?.engagement.dispositionComment != null}">
					<fieldset id='disposition'>
					    <legend>Intake Disposition</legend>
						<div id="commentDIV" class="prop">
							<label for="comment" class="textArea">Supervisor Notes</label>
					        <g:textArea id="dispositionComment" name="dispositionComment"
					                    value="${intake?.engagement.dispositionComment}"
					                    readonly="readonly" rows="3" />
						</div>
					</fieldset>
				  </g:if>
				  </g:ifUserHasRole>
		    
		            <g:render template="/client/editClient" bean="${intake?.client}" />
		            
                <fieldset id='caseNotes'>
                	<legend>Victimization Case Notes</legend>
                	
                    <div id="crimeTypeDIV" class="prop">
                        <label for="crimeType">Type of Crime</label>
                        <g:select name="type.id" from="${org.raap.domain.CrimeType.list()}"
                        	optionKey="id" value="${intake?.caseNotes?.type?.id}" />
                    </div>
                
                    <div id="incidentDateDIV" class="prop">
                        <label for="incidentDate"><g:message code="client.incidentDate.label" default="Date of Crime" /></label>
                        <g:datePicker name="incidentDate" precision="day" value="${intake?.caseNotes?.incidentDate}" />
                    </div>
                
                    <div id="countyCrimeTookPlaceDIV" class="prop">
                        <label for="countyCrimeTookPlace"><g:message code="client.countyCrimeTookPlace.label" default="County" /></label>
                        <g:select name="countyCrimeTookPlace.id" from="${org.raap.domain.County.list()}"
                        	optionKey="id" value="${intake?.caseNotes?.countyCrimeTookPlace?.id}" />
                    </div>
                
                </fieldset>
                
                <fieldset id='clientNeeds'>
                	<legend>Immediate RAAP Need</legend>
                	
                    <div id="servicesRequestedDIV" class="prop">
                        <label for="servicesRequested" class="textArea">RAAP Services Requested:</label>
                        <g:textArea name="servicesRequested" value="${intake?.servicesRequested}" rows="2" />
                    </div>
                
                    <div id="presentingProblemDIV" class="prop">
                        <label for="presentingProblem" class="textArea">Presenting Problems:</label>
                        <g:textArea name="presentingProblem" value="${intake?.presentingProblem}" rows="5" />
                    </div>
                
                    <div id="currentSymptomsDIV" class="prop">
                        <label for="currentSymptoms" class="textArea">Current Symptoms:</label>
                        <g:textArea name="currentSymptoms" value="${intake?.currentSymptoms}" rows="5" />
                    </div>
                
                </fieldset>
                
                <fieldset id='traumaInfo'>
                	<legend>Trauma Symptoms Information</legend>
                	
                    <div id="suicideHistoryDIV" class="prop">
                        <label for="suicideHistory" class="textArea">Suicide/Homicide History:</label>
                        <g:textArea name="suicideHistory" value="${intake?.suicideHistory}" rows="5" />
                    </div>
                
                    <div id="mentalHealthHistoryDIV" class="prop">
                        <label for="mentalHealthHistory" class="textArea">Mental Health History:</label>
                        <g:textArea name="mentalHealthHistory" value="${intake?.mentalHealthHistory}" rows="5" />
                    </div>
                
                    <div id="drugUseDIV" class="prop">
                        <label for="drugUse" class="textArea">Alcohol and Drug Use:</label>
                        <g:textArea name="drugUse" value="${intake?.drugUse}" rows="5" />
                    </div>
                
                    <div id="selfHarmBehaviorsDIV" class="prop">
                        <label for="selfHarmBehaviors" class="textArea">Behaviors of Self-Harm:</label>
                        <g:textArea name="selfHarmBehaviors" value="${intake?.selfHarmBehaviors}" rows="5" />
                    </div>
                
                    <div id="sexualIssuesDIV" class="prop">
                        <label for="sexualIssues" class="textArea">Sexual Issues:</label>
                        <g:textArea name="sexualIssues" value="${intake?.sexualIssues}" rows="5" />
                    </div>
                
                </fieldset>
                
                <fieldset id='historicalInfo'>
                	<legend>Historical Information</legend>
                	
                    <div id="assualtHistoryDIV" class="prop">
                        <label for="assualtHistory" class="textArea">Assualt History:</label>
                        <g:textArea name="assualtHistory" value="${intake?.assualtHistory}" rows="5" />
                    </div>
                
                    <div id="familyHistoryDIV" class="prop">
                        <label for="familyHistory" class="textArea">Family History:</label>
                        <g:textArea name="familyHistory" value="${intake?.familyHistory}" rows="5" />
                    </div>
                
                    <div id="traumaticEventsDIV" class="prop">
                        <label for="traumaticEvents" class="textArea">Traumatic Events:</label>
                        <g:textArea name="traumaticEvents" value="${intake?.traumaticEvents}" rows="5" />
                    </div>
                
                    <div id="therapyHistoryDIV" class="prop">
                        <label for="therapyHistory" class="textArea">History with Therapy:</label>
                        <g:textArea name="therapyHistory" value="${intake?.therapyHistory}" rows="5" />
                    </div>
                
                    <div id="careerEducationDIV" class="prop">
                        <label for="careerEducation" class="textArea">Career and Education:</label>
                        <g:textArea name="careerEducation" value="${intake?.careerEducation}" rows="5" />
                    </div>
                
                </fieldset>
                
				  <g:ifUserHasRole roles="ROLE_CLINICAL_SUPERVISOR">
			        <g:render template="intakeDisposition" />
				  </g:ifUserHasRole>
		    
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="save" class="save" value="${message(code: 'default.button.save.label', default: 'Save')}" /></span>
                    <span class="button"><g:submitButton name="saveAndComplete" class="save" value="${message(code: 'default.button.saveAndComplete.label', default: 'Save and Completed')}" /></span>
                    <span class="button"><g:submitButton name="cancel" class="cancel" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
