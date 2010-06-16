<h2>Intake Coordinator</h2>
<p>
This dashboard will help you automate the Intake phoneline.
</p>

<%-- START: The list of clients awaiting intakes to be scheduled. --%>
<div class="list clientList">
	<h3>Awaiting Intakes</h3>
    <table>
        <thead>
            <tr>
               <th>Client</th>
               <th>Age</th>
               <th>Phone Number</th>
               <th></th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${preIntakeClients}" status="i" var="intake">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            
                <td><g:link controller="client" action="show" id="${intake.client.id}">${intake.client.fullName}</g:link></td>
            
                <td>${intake.client.age}</td>
            
                <td>${intake.client.homePhone}</td>
            
                <td><g:link controller="intake" action="editIntakeSchedule" id="${intake.id}">schedule intake</g:link></td>
            
            </tr>
        </g:each>
        </tbody>
    </table>
</div>            
<%-- END: The list of scheduled intakes. --%>

<%-- START: The list of scheduled intakes. --%>
<div class="list clientList">
	<h3>All Scheduled Intakes</h3>
    <table>
        <thead>
            <tr>
               <th>Client</th>
               <th>Age</th>
               <th>Intake Counselor</th>
               <th>Intake Date</th>
               <th>Missed Appts</th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${scheduledIntakeList}" status="i" var="intake">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            
                <td><g:link controller="client" action="show" id="${intake.client.id}">${intake.client.fullName}</g:link></td>
            
                <td>${intake.client.age}</td>
            
                <td>${intake.intakeCounselor}</td>
            
                <td><g:formatDate date="${intake.intakeDate}" format="dd-MMM-yyyy hh:mm a" /></td>
            
                <td>${intake.appointmentMisses}</td>
            
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<%-- END: The list of scheduled intakes. --%>
