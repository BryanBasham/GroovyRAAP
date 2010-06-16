<h2>Clinical Supervisor</h2>
<p>
This dashboard will help assist you with Clinical Supervisor duties.
</p>

<%-- START: The list of supervised clinicians. --%>
<div class="list clientList">
	<h3>Clinical Staff</h3>
    <table>
        <thead>
            <tr>
               <th>Clinician</th>
               <th>Number of Clients</th>
               <th></th>
            </tr>
        </thead>
        <tbody>
          <g:each in="${clinicianSummaries}" status="i" var="summary">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>${summary.user}</td>
                <td>${summary.numOfActiveClients}</td>
                <td><g:link controller="client" action="listForClinician" id="${summary.user.id}">view clients</g:link></td>
            </tr>
          </g:each>
        </tbody>
    </table>
</div>            
<%-- END: The list of supervised clinicians. --%>

<%-- START: The list of completed intakes. --%>
<div class="list clientList">
	<h3>All Completed Intakes</h3>
    <table>
        <thead>
            <tr>
               <th>Client</th>
               <th>Age</th>
               <th>Intake Counselor</th>
               <th>Intake Date</th>
               <th></th>
            </tr>
        </thead>
        <tbody>
          <g:each in="${completedIntakeList}" status="i" var="intake">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td><g:link controller="client" action="show" id="${intake.client.id}">${intake.client.fullName}</g:link></td>
                <td>${intake.client.age}</td>
                <td>${intake.intakeCounselor}</td>
                <td><g:formatDate date="${intake.intakeDate}" format="dd-MMM-yyyy hh:mm a" /></td>
                <td><g:link controller="intake" action="showIntake" id="${intake.client.id}">view intake notes</g:link></td>
            </tr>
          </g:each>
        </tbody>
    </table>
</div>
<%-- END: The list of completed intakes. --%>

<%-- START: The list of intakes by state. --%>
<div class="list clientList">
	<h3>Clinical Intakes</h3>
    <table>
        <thead>
            <tr>
               <th>State</th>
               <th>Number of Intakes</th>
            </tr>
        </thead>
        <tbody>
          <g:each in="${intakeSummary}" status="i" var="summary">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>${summary[0]}</td>
                <td>${summary[1]}</td>
            </tr>
          </g:each>
        </tbody>
    </table>
</div>            
<%-- END: The list of intakes by state. --%>
