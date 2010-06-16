<html>
<head>
    <meta name="layout" content="main" />
    <title>Evaluate Session Notes</title>
</head>
<body>
    <div class="body">
    
        <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
        </g:if>
        
		<g:each in="${sessionList}" status="i" var="session">
	      <div class="sessionNote">
	        <g:render template="displaySessionNote" bean="${session}" />
	      </div>
        </g:each>
        
    </div>
</body>
</html>
