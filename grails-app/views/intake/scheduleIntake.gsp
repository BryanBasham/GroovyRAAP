<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'client.label', default: 'Client')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${intake}">
            <div class="errors">
                <g:renderErrors bean="${intake}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="scheduleIntake" method="post" >
                <div class="dialog">
                
                <fieldset id='scheduleIntake'>
                	<legend>Schedule Intake</legend>
                	
                    <div id="clientDIV" class="prop">
                        <label for="client"><g:message code="client.firstName.label" default="Client" />:</label>
                        <span>${intake.client?.fullName}</span>
                    </div>
                
                    <div id="intakeCounselorDIV" class="prop">
                        <label for="intakeCounselor"><g:message code="client.intakeCounselor.label" default="Intake Counselor" /></label>
                        <g:select name="intakeCounselor.id" from="${counselorsList}" optionKey="id" value="${intake?.intakeCounselor?.id}" noSelection="['null': '']" />
                    </div>
                
                    <div id="intakeDateDIV" class="prop">
                        <label for="intakeDate"><g:message code="client.intakeDate.label" default="Intake Date" /></label>
                        <g:datePicker name="intakeDate" precision="minute" value="${intake?.intakeDate}" noSelection="['': '']" />
                    </div>
                    
                </fieldset>
                
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="schedule" class="save" value="${message(code: 'default.button.schedule.label', default: 'Schedule')}" /></span>
                    <span class="button"><g:submitButton name="cancel" class="save" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
