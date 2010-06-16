
<%@ page import="org.raap.domain.IncomeLevel" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'incomeLevel.label', default: 'IncomeLevel')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${incomeLevelInstance}">
            <div class="errors">
                <g:renderErrors bean="${incomeLevelInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${incomeLevelInstance?.id}" />
                <g:hiddenField name="version" value="${incomeLevelInstance?.version}" />
                <div class="dialog">
                
                    <div id="dbCodeDIV" class="prop">
                        <label for="dbCode"><g:message code="incomeLevel.dbCode.label" default="Db Code" /></label>
                        <g:textField name="dbCode" maxlength="2" value="${incomeLevelInstance?.dbCode}" />
                    </div>
                
                    <div id="rangeDIV" class="prop">
                        <label for="range"><g:message code="incomeLevel.range.label" default="Range" /></label>
                        <g:textField name="range" value="${incomeLevelInstance?.range}" />
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
