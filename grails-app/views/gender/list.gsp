
<%@ page import="org.raap.domain.Gender" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gender.label', default: 'Gender')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'gender.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="dbCode" title="${message(code: 'gender.dbCode.label', default: 'Db Code')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'gender.name.label', default: 'Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${genderInstanceList}" status="i" var="genderInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${genderInstance.id}">${fieldValue(bean: genderInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: genderInstance, field: "dbCode")}</td>
                        
                            <td>${fieldValue(bean: genderInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${genderInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
