<html>
    <head>
        <meta name="layout" content="main" />
        <title>Add a Service</title>
    </head>
    <body>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${service}">
            <div class="errors">
                <g:renderErrors bean="${service}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveSessionNote" method="post" >
              <g:hiddenField name="engagement.id" value="${service.engagement.id}" />
              <g:hiddenField name="providedBy.id" value="${service.providedBy.id}" />
              <g:hiddenField name="type.id" value="${service.type.id}" />
            
                <div class="dialog">
                <fieldset id='serviceDetails'>
                	<legend>Session Notes</legend>
                	
                    <div id="clientDIV" class="prop">
                        <label for="client"><g:message code="client.duration.label" default="Client" />:</label>
                        <span>${service.engagement.client?.fullName}</span>
                    </div>
                
                    <div id="dateOfServiceDIV" class="prop">
                        <label for="dateOfService"><g:message code="client.dateOfService.label" default="Date of Service:" /></label>
                        <g:datePicker name="dateOfService" precision="minute" value="${service?.dateOfService}" noSelection="['': '']" />
                    </div>
                    
                    <div id="durationDIV" class="prop">
                        <label for="duration"><g:message code="client.duration.label" default="Duration:" /></label>
                        <g:textField name="duration" value="${service?.duration}" />
                    </div>
                
                    <div id="notesDIV" class="prop">
                        <label for="notes" class="textArea">Additional Notes:</label>
                        <g:textArea name="notes" value="${service?.notes}" rows="5" />
                    </div>
                
                </fieldset>
                </div>
                
                <div class="buttons">
                    <span class="button"><g:submitButton name="saveAction" class="save" value="${message(code: 'default.button.schedule.label', default: 'Save')}" /></span>
                    <span class="button"><g:submitButton name="cancelAction" class="cancel" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" /></span>
                </div>
                
            </g:form>
        </div>
    </body>
</html>
