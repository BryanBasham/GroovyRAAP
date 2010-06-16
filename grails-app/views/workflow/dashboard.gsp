<html>
<head>
    <title>Welcome to RAAP's Client Services</title>
	<meta name="layout" content="main" />
</head>
<body>

	<div class="body">
	
        <g:if test="${flash.message}">
        	<div class="message">${flash.message}</div>
        </g:if>
            
		<g:render controller="workflow"
			 template="/workflow/introDashboard"
			 model="[]" />
			 
	  <g:ifUserHasRole roles="ROLE_CLINICAL_SUPERVISOR">
		<g:render controller="workflow"
			 template="/workflow/clinicalSupervisorDashboard"
			 model="[intakeSummary: clinicalSuper_intakeSummary,
			 		 clinicianSummaries: clinicalSuper_clinicianSummaries,
			 		 completedIntakeList: clinicalSuper_completedIntakeList]" />
	  </g:ifUserHasRole>
	  
	  <g:ifUserHasRole roles="ROLE_COUNSELOR">
		<g:render controller="workflow"
			 template="/workflow/counselorDashboard"
			 model="[activeClients: counselor_activeClients,
			 		 scheduledIntakeList: counselor_scheduledIntakeList]" />
	  </g:ifUserHasRole>
	  
	  <g:ifUserHasRole roles="ROLE_INTAKE_COORDINATOR">
		<g:render controller="workflow"
			 template="/workflow/intakeCoordinatorDashboard"
			 model="[preIntakeClients: intakeCoor_preIntakeClients,
			 		 scheduledIntakeList: intakeCoor_scheduledIntakeList]" />
	  </g:ifUserHasRole>
	  
	  <g:ifUserHasRole roles="ROLE_ADMIN">
		<g:render controller="workflow"
			 template="/workflow/adminDashboard"
			 model="[activeUsers: admin_activeUsers]" />
	  </g:ifUserHasRole>
	  
	</div>
	
</body>
</html>