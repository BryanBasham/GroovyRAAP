<html>
    <head>
        <meta name="layout" content="main" />
        <script type="text/javascript" src="${resource(dir:'js',file:'_intakeDisposition.js')}"></script>
        <g:set var="entityName" value="${message(code: 'intake.label', default: 'Counseling Intake Notes')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            
        <g:form controller="intake">
            <g:hiddenField name="id" value="${intake?.id}" />
            <div class="dialog">
            
            <g:render template="/client/displayClient" bean="${intake?.client}" />
            
            <fieldset id='caseNotes'>
                <legend>Victimization Case Notes</legend>
                	
           		<g:if test="${intake?.caseNotes?.type}">
                <div id="crimeTypeDIV" class="prop">
                    <label for="crimeType">Type of Crime</label>
                    <div class="value">${intake?.caseNotes?.type}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.caseNotes?.incidentDate}">
                <div id="incidentDateDIV" class="prop">
                    <label for="incidentDate">Date of Crime</label>
                    <div class="value"><g:formatDate date="${intake?.caseNotes?.incidentDate}" format="dd-MMM-YYYY" /></div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.caseNotes?.countyCrimeTookPlace}">
                <div id="countyCrimeTookPlaceDIV" class="prop">
                    <label for="countyCrimeTookPlace">County</label>
                    <div class="value">${intake?.caseNotes?.countyCrimeTookPlace}</div>
                </div>
                </g:if>
            
            </fieldset>
            
            <fieldset id='clientNeeds'>
                <legend>Immediate RAAP Need</legend>
                	
           		<g:if test="${intake?.servicesRequested}">
                <div id="servicesRequestedDIV" class="prop">
                    <label for="servicesRequested" class="textArea">RAAP Services Requested:</label>
                    <div class="value">${intake?.servicesRequested?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.presentingProblem}">
                <div id="presentingProblemDIV" class="prop">
                    <label for="presentingProblem" class="textArea">Presenting Problem:</label>
                    <div class="value">${intake?.presentingProblem?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.currentSymptoms}">
                <div id="currentSymptomsDIV" class="prop">
                    <label for="currentSymptoms" class="textArea">Current Symptoms:</label>
                    <div class="value">${intake?.currentSymptoms?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
            </fieldset>
            
            <fieldset id='traumaInfo'>
                <legend>Trauma Symptoms Information</legend>
                	
           		<g:if test="${intake?.suicideHistory}">
                <div id="suicideHistoryDIV" class="prop">
                    <label for="suicideHistory" class="textArea">Suicide/Homicide History:</label>
                    <div class="value">${intake?.suicideHistory?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.mentalHealthHistory}">
                <div id="mentalHealthHistoryDIV" class="prop">
                    <label for="mentalHealthHistory" class="textArea">Mental Health History:</label>
                    <div class="value">${intake?.mentalHealthHistory?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.drugUse}">
                <div id="drugUseDIV" class="prop">
                    <label for="drugUse" class="textArea">Alcohol and Drug Use:</label>
                    <div class="value">${intake?.drugUse?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.selfHarmBehaviors}">
                <div id="selfHarmBehaviorsDIV" class="prop">
                    <label for="selfHarmBehaviors" class="textArea">Behaviors of Self-Harm:</label>
                    <div class="value">${intake?.selfHarmBehaviors?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.sexualIssues}">
                <div id="sexualIssuesDIV" class="prop">
                    <label for="sexualIssues" class="textArea">Sexual Issues:</label>
                    <div class="value">${intake?.sexualIssues?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
            </fieldset>
            
            <fieldset id='historicalInfo'>
                <legend>Historical Information</legend>
                	
           		<g:if test="${intake?.assualtHistory}">
                <div id="assualtHistoryDIV" class="prop">
                    <label for="assualtHistory" class="textArea">Assualt History:</label>
                    <div class="value">${intake?.assualtHistory?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.familyHistory}">
                <div id="familyHistoryDIV" class="prop">
                    <label for="familyHistory" class="textArea">Family History:</label>
                    <div class="value">${intake?.familyHistory?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.traumaticEvents}">
                <div id="traumaticEventsDIV" class="prop">
                    <label for="traumaticEvents" class="textArea">Traumatic Events:</label>
                    <div class="value">${intake?.traumaticEvents?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.therapyHistory}">
                <div id="therapyHistoryDIV" class="prop">
                    <label for="therapyHistory" class="textArea">History with Therapy:</label>
                    <div class="value">${intake?.therapyHistory?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
           		<g:if test="${intake?.careerEducation}">
                <div id="careerEducationDIV" class="prop">
                    <label for="careerEducation" class="textArea">Career and Education:</label>
                    <div class="value">${intake?.careerEducation?.encodeAsHTML()}</div>
                </div>
                </g:if>
            
            </fieldset>
            
			  <g:ifUserHasRole roles="ROLE_CLINICAL_SUPERVISOR">
		        <g:render template="intakeDisposition" />
			  </g:ifUserHasRole>
		    
            </div>
            <div class="buttons">
                    <span class="button"><g:actionSubmit class="evaluate" action="evalIntake" value="Evaluate Intake" /></span>
                    <span class="button"><g:actionSubmit class="edit" action="editIntake" value="Edit Intake" /></span>
                    <span class="button"><g:actionSubmit class="cancel" action="cancel" value="Cancel" /></span>
            </div>
        </div>
        </g:form>
    </body>
</html>
