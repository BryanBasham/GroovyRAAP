<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'garland.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:javascript library="prototype" />
        <g:javascript library="scriptaculous" />
        <g:javascript library="application" />
        <g:layoutHead />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>
        <div id="grailsLogo" class="logo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        
        <div id="nav">
		
			<div class="navPanel">
				<div class="panelTop"></div>
				<div class="panelBody">
					<g:render controller="workflow"
						 template="/workflow/userMenu"
						 model="[]" />
				  <g:ifUserHasRole roles="ROLE_CLINICAL_SUPERVISOR">
					<g:render controller="workflow"
						 template="/workflow/clinicalSupervisorMenu"
						 model="[]" />
				  </g:ifUserHasRole>
				  <g:ifUserHasRole roles="ROLE_COUNSELOR">
					<g:render controller="workflow"
						 template="/workflow/counselorMenu"
						 model="[]" />
				  </g:ifUserHasRole>
				  <g:ifUserHasRole roles="ROLE_INTAKE_COORDINATOR">
					<g:render controller="workflow"
						 template="/workflow/intakeCoordinatorMenu"
						 model="[]" />
				  </g:ifUserHasRole>
				  <g:ifUserHasRole roles="ROLE_ADMIN">
					<g:render controller="workflow"
						 template="/workflow/adminMenu"
						 model="[]" />
				  </g:ifUserHasRole>
				</div>
				<div class="panelBtm"></div>
			</div>
		
		</div>
		
        <g:layoutBody />
    </body>
</html>