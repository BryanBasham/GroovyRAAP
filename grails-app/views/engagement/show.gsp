<html>
<head>
    <meta name="layout" content="main" />
    <title>Show Client Engagement</title>
</head>
<body>
    <div class="body">
    
        <h1>Show Client Engagement</h1>
        
        <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
        </g:if>
        
        <div class="dialog">
        
	      <g:ifUserHasRole roles="ROLE_COUNSELOR,ROLE_CLINICAL_SUPERVISOR">
	        <g:render template="/client/displayClient" bean="${engagement?.client}" />
	        
	       <g:if test="${permissions.canShowIntake}">
	        <fieldset id='intakeNotes'>
	            <legend>Intake Notes</legend>
	            
			    <div id="intakeDateDIV" class="prop">
			        <label for="intakeDate">Intake Date:</label>
			        <div class="value">${engagement.intake.intakeDate}</div>
			    </div>
	            
			    <div id="intakeCounselorDIV" class="prop">
			        <label for="intakeCounselor">Intake Date:</label>
			        <div class="value">${engagement.intake.intakeCounselor}</div>
			    </div>
	            
			    <div id="intakeCounselorDIV" class="actionLink">
	              <g:link controller="intake" action="showIntake" id="${engagement.intake.id}">View intake notes...</g:link>
			    </div>
	            
	        </fieldset>
	       </g:if>
        
	       <g:if test="${permissions.canShowSessionNotes}">
	        <fieldset id='sessionNotes'>
	            <legend>Session Notes</legend>
	            
	            <div class="actionLink">
	              <g:link controller="engagement" action="addSessionNote" id="${engagement.id}">Add new session notes...</g:link>
	            </div>
	            
	            <table>
	              <thead>
	            	<tr>
	            	  <th>Session Date</th>
	            	  <th>Counselor</th>
	            	  <th>Duration</th>
	            	</tr>
	              </thead>
	              <tbody>
	              <g:each in="${sessionNotes}" status="i" var="service">
	                <tr>
	            	  <td>${service.dateOfService}</td>
	            	  <td>${service.providedBy}</td>
	            	  <td>${service.duration}</td>
	            	</tr>
	              </g:each>
	              </tbody>
	            </table>
	            
	        </fieldset>
	       </g:if>
        
	       <g:if test="${permissions.canShowServices}">
	        <fieldset id='services'>
	            <legend>Services</legend>
	            
	            <div class="actionLink">
	              <g:link controller="engagement" action="addService" id="${engagement?.client?.id}">Add a new service...</g:link>
	            </div>
	            
	            <table>
	              <thead>
	            	<tr>
	            	  <th>Service Type</th>
	            	  <th>Service Date</th>
	            	  <th>Staff</th>
	            	  <th>Duration</th>
	            	</tr>
	              </thead>
	              <tbody>
	              <g:each in="${services}" status="i" var="service">
	                <tr>
	            	  <td>${service.type}</td>
	            	  <td>${service.dateOfService}</td>
	            	  <td>${service.providedBy}</td>
	            	  <td>${service.duration}</td>
	            	</tr>
	              </g:each>
	              </tbody>
	            </table>
	            
	        </fieldset>
	       </g:if>
        
	      </g:ifUserHasRole>
        
        </div>

    </div>
</body>
</html>
