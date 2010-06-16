<h2>System Administor</h2>
<p>
This dashboard will help you navigate the Client record system.
</p>

<%-- START: The list of active users. --%>
<div id="activeUsers" class="list clientList">
	<h3>Active Users</h3>
    <table>
        <thead>
            <tr>
               <th>User</th>
               <th>Supervisor</th>
               <th></th>
               <th></th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${activeUsers}" status="i" var="user">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            
                <td><g:link controller="user" action="edit" id="${user.id}">${user.userRealName}</g:link></td>
            
                <td>${user.supervisor?.userRealName}</td>
            
                <td><a href="#">Reset password</a></td>
            
                <td><a href="#">Inactivate user</a></td>
            
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<%-- END: The list of active users. --%>
