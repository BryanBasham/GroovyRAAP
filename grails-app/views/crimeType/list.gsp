
<%@ page import="org.raap.domain.CrimeType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'crimeType.label', default: 'CrimeType')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'crimeType.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="dbCode" title="${message(code: 'crimeType.dbCode.label', default: 'Db Code')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'crimeType.name.label', default: 'Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${crimeTypeInstanceList}" status="i" var="crimeTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${crimeTypeInstance.id}">${fieldValue(bean: crimeTypeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: crimeTypeInstance, field: "dbCode")}</td>
                        
                            <td>${fieldValue(bean: crimeTypeInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${crimeTypeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
