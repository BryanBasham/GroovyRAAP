
<%@ page import="org.raap.domain.Language" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'language.label', default: 'Language')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${languageInstance}">
            <div class="errors">
                <g:renderErrors bean="${languageInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                
                    <div id="dbCodeDIV" class="prop">
                        <label for="dbCode"><g:message code="language.dbCode.label" default="Db Code" /></label>
                        <g:textField name="dbCode" maxlength="2" value="${languageInstance?.dbCode}" />
                    </div>
                
                    <div id="nameDIV" class="prop">
                        <label for="name"><g:message code="language.name.label" default="Name" /></label>
                        <g:textField name="name" value="${languageInstance?.name}" />
                    </div>
                
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
