
<%@ page import="org.raap.domain.Language" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'language.label', default: 'Language')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'language.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="dbCode" title="${message(code: 'language.dbCode.label', default: 'Db Code')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'language.name.label', default: 'Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${languageInstanceList}" status="i" var="languageInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${languageInstance.id}">${fieldValue(bean: languageInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: languageInstance, field: "dbCode")}</td>
                        
                            <td>${fieldValue(bean: languageInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${languageInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
