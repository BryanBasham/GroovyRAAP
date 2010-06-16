
<fieldset>
    <legend>Session Notes for <g:formatDate date="${it.dateOfService}" format="dd-MMM-yyyy hh:mm a" /></legend>

    <div id="clientDIV" class="prop">
        <label for="client">Client:</label>
        <div class="value">${it.engagement?.client}</div>
    </div>
    
    <p>TEST</p>
    
    <div id="counselorDIV" class="prop">
        <label for="counselor">Counselor:</label>
        <div class="value">${it.providedBy}</div>
    </div>
    
    <div id="notesDIV" class="prop">
        <label for="notes">Session Notes:</label>
        <div class="value textArea"><p>${it.notes}</p></div>
    </div>
    
    <div id="dispApproveDIV" class="prop">
		<g:radio id="dispApproveButton" name="disposition${it.id}" value="APPROVE" />
		   <strong>Approve</strong> <br/>
		<g:radio id="dispApproveButton" name="disposition${it.id}" value="DENY" />
		   <strong>Deny</strong> <br/>
    </div>
    
</fieldset>
