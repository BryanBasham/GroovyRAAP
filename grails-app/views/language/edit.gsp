
<%@ page import="org.raap.domain.Language" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'language.label', default: 'Language')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${languageInstance}">
            <div class="errors">
                <g:renderErrors bean="${languageInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${languageInstance?.id}" />
                <g:hiddenField name="version" value="${languageInstance?.version}" />
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
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
