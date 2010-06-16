
<fieldset id='disposition'>
    <legend>Intake Disposition</legend>

	<div id='dispApprove'>
		<g:radio id="dispApproveButton" name="disposition" value="APPROVE" />
		   <strong>Approve for services</strong> <br/>
        <div id="individualCounselorDIV" class="prop">
            <label for="individualCounselor"><g:message code="client.individualCounselor.label" default="Individual Counselor" /></label>
            <g:select id="individualCounselor" disabled="true" name="individualCounselor.id" from="${counselorsList}" optionKey="id" value="${intake?.engagement.individualCounselor?.id}" noSelection="['null': 'Assign an individual counselor...']" />
        </div>
	</div>

	<div id='dispSendBack'>
		<g:radio id="dispSendBackButton" name="disposition" value="SEND_BACK" />
		   <strong>Send back to Intake Counselor</strong> <br/>
	</div>

	<div id='dispReferr'>
		<g:radio id="dispReferralButton" name="disposition" value="REFER" />
		   <strong>Refer Client</strong> <br/>
	</div>

	<div id="commentDIV" class="prop">
		<label for="comment" class="textArea">Additional Notes</label>
        <g:textArea id="dispositionComment" name="dispositionComment" value="${intake?.engagement.dispositionComment}" rows="3" />
	</div>

</fieldset>
