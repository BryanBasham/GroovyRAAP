/**
* JavaScript code for the "Intake Disposition" fieldset.
*/

var IntakeDisp = {
	/**
	 * The 'change' event handler for the 'disposition' radio buttons.
	 */
	changeDisposition : function(event) {
		var button = Event.element(event);
		switch (button.getValue()) {
			case "APPROVE": {
				$('individualCounselor').disabled = false;
				$('individualCounselor').focus();
				break;
			}
			case "SEND_BACK":
			case "REFER": {
				$('individualCounselor').disabled = true;
				$('individualCounselor').setValue('null');
				break;
			}
		}
	},
	
	/**
	 * This function verifies the user has entered all necessary data.
	 */
	verifyDisposition: function(event) {
		
	}
};

document.observe("dom:loaded", function () {
	$('dispApproveButton').observe('change', IntakeDisp.changeDisposition);
	$('dispSendBackButton').observe('change', IntakeDisp.changeDisposition);
	$('dispReferralButton').observe('change', IntakeDisp.changeDisposition);
});