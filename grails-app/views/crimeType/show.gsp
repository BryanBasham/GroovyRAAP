
<%@ page import="org.raap.domain.CrimeType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'crimeType.label', default: 'CrimeType')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
            
                <div id="idDIV" class="prop">
                    <label for="id"><g:message code="crimeType.id.label" default="Id" /></label>
                    
                    <div class="value">${fieldValue(bean: crimeTypeInstance, field: "id")}</div>
                    
                </div>
            
                <div id="dbCodeDIV" class="prop">
                    <label for="dbCode"><g:message code="crimeType.dbCode.label" default="Db Code" /></label>
                    
                    <div class="value">${fieldValue(bean: crimeTypeInstance, field: "dbCode")}</div>
                    
                </div>
            
                <div id="nameDIV" class="prop">
                    <label for="name"><g:message code="crimeType.name.label" default="Name" /></label>
                    
                    <div class="value">${fieldValue(bean: crimeTypeInstance, field: "name")}</div>
                    
                </div>
            
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${crimeTypeInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
