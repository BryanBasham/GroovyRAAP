
<%@ page import="org.raap.domain.CrimeType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'crimeType.label', default: 'CrimeType')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${crimeTypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${crimeTypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                
                    <div id="dbCodeDIV" class="prop">
                        <label for="dbCode"><g:message code="crimeType.dbCode.label" default="Db Code" /></label>
                        <g:textField name="dbCode" maxlength="3" value="${crimeTypeInstance?.dbCode}" />
                    </div>
                
                    <div id="nameDIV" class="prop">
                        <label for="name"><g:message code="crimeType.name.label" default="Name" /></label>
                        <g:textField name="name" value="${crimeTypeInstance?.name}" />
                    </div>
                
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
