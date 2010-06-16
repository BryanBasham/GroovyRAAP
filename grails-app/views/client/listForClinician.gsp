<html>
<head>
    <meta name="layout" content="main" />
    <title>View Active Clients for Clinician</title>
</head>
<body>
    <div class="body">
    
        <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
        </g:if>
        
        <fieldset id='services'>
            <legend>${clinician.username}'s Active Clients</legend>
            
            <table>
                <thead>
                    <tr>
                        <th>Client</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                <g:each in="${clientStatsList}" status="i" var="clientStats">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link controller="engagement" action="show" id="${clientStats.client.id}">${clientStats.client.fullName}</g:link></td>
                        <td><g:link controller="engagement" action="showSessionNotes" id="${clientStats.client.id}">View ${clientStats.numOfSessions} new session notes</g:link></td>
                        <td><g:link controller="engagement" action="showServices" id="${clientStats.client.id}">View ${clientStats.numOfServices} new services</g:link></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
	        <div class="paginateButtons">
	            <g:paginate total="${clientTotal}" />
	        </div>
	        
        </fieldset>

    </div>
</body>
</html>
