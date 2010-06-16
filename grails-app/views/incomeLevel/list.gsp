
<%@ page import="org.raap.domain.IncomeLevel" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'incomeLevel.label', default: 'IncomeLevel')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'incomeLevel.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="dbCode" title="${message(code: 'incomeLevel.dbCode.label', default: 'Db Code')}" />
                        
                            <g:sortableColumn property="range" title="${message(code: 'incomeLevel.range.label', default: 'Range')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${incomeLevelInstanceList}" status="i" var="incomeLevelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${incomeLevelInstance.id}">${fieldValue(bean: incomeLevelInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: incomeLevelInstance, field: "dbCode")}</td>
                        
                            <td>${fieldValue(bean: incomeLevelInstance, field: "range")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${incomeLevelInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
