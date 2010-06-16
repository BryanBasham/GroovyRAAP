
<%@ page import="org.raap.domain.Language" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'language.label', default: 'Language')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
            
                <div id="idDIV" class="prop">
                    <label for="id"><g:message code="language.id.label" default="Id" /></label>
                    
                    <div class="value">${fieldValue(bean: languageInstance, field: "id")}</div>
                    
                </div>
            
                <div id="dbCodeDIV" class="prop">
                    <label for="dbCode"><g:message code="language.dbCode.label" default="Db Code" /></label>
                    
                    <div class="value">${fieldValue(bean: languageInstance, field: "dbCode")}</div>
                    
                </div>
            
                <div id="nameDIV" class="prop">
                    <label for="name"><g:message code="language.name.label" default="Name" /></label>
                    
                    <div class="value">${fieldValue(bean: languageInstance, field: "name")}</div>
                    
                </div>
            
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${languageInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
