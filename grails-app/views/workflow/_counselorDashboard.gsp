<h2>Counseling</h2>
<p>
This dashboard will help you navigate the Client record system.
</p>

<%-- START: The list of active clients. --%>
<div id="activeClients" class="list clientList">
	<h3>Active Clients</h3>
    <table>
        <thead>
            <tr>
               <th>Client</th>
               <th>Age</th>
               <th>Phone Number</th>
               <th class='actionLink'>&nbsp;</th>
               <th class='actionLink'>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${activeClients}" status="i" var="intake">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td><g:link controller="engagement" action="show" id="${intake.client.id}">${intake.client.fullName}</g:link></td>
                <td>${intake.client.age}</td>
                <td>${intake.client.homePhone}</td>
                <td><g:link controller="engagement" action="addSessionNote" id="${intake.client.id}">Add session notes</g:link></td>
                <td><g:link controller="engagement" action="addService" id="${intake.client.id}">Add a service</g:link></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<%-- END: The list of scheduled intakes. --%>

<%-- START: The list of scheduled intakes. --%>
<div class="list clientList">
	<h3>Scheduled Intakes</h3>
    <table>
        <thead>
            <tr>
               <th>Client</th>
               <th>Age</th>
               <th>Intake Counselor</th>
               <th>Intake Date</th>
               <th>Missed Appts</th>
               <th></th>
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
                <td><g:link controller="intake" action="editIntake" id="${intake.id}">edit intake</g:link></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<%-- END: The list of scheduled intakes. --%>
