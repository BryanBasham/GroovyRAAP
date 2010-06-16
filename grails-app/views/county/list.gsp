
<%@ page import="org.raap.domain.County" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'county.label', default: 'County')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'county.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="dbCode" title="${message(code: 'county.dbCode.label', default: 'Db Code')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'county.name.label', default: 'Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${countyInstanceList}" status="i" var="countyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${countyInstance.id}">${fieldValue(bean: countyInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: countyInstance, field: "dbCode")}</td>
                        
                            <td>${fieldValue(bean: countyInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${countyInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
