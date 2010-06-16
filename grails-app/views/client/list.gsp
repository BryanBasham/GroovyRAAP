
<%@ page import="org.raap.domain.Client" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'client.label', default: 'Client')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <th><g:message code="client.fullName.label" default="Name" /></th>
                   	    
                            <g:sortableColumn property="age" title="${message(code: 'client.age.label', default: 'Age')}" />
                        
                            <th><g:message code="client.gender.label" default="Gender" /></th>
                   	    
                            <th><g:message code="client.homePhone.label" default="Home Phone" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${clientInstanceList}" status="i" var="clientInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${clientInstance.id}">${fieldValue(bean: clientInstance, field: "fullName")}</g:link></td>
                        
                            <td>${fieldValue(bean: clientInstance, field: "age")}</td>
                        
                            <td>${fieldValue(bean: clientInstance, field: "gender")}</td>
                        
                            <td>${fieldValue(bean: clientInstance, field: "homePhone")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${clientInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
