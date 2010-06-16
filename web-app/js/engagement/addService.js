/**
* JavaScript code for the "Add a Service" screen.
*/

var Screen = {

	/**
	 * This function retrieves a ServiceType object by it's DB id, which
	 * is the value elements of the options in the "serviceTypeDropdown"
	 * select element.
	 */
	getTypeByID: function(typeID) {
		return Screen.SERVICE_TYPES.detect(function(type) { return type.id == typeID; });
	},
	
	/**
	 * This function retrieves the default duration for a specified
	 * ServiceType (by DB id).
	 */
	getServiceDuration: function(typeID) {
		return Screen.getTypeByID(typeID).defaultDuration;
	},
	
	/** The variable holds the list of ServiceType objects from the server. */
	SERVICE_TYPES: [],

	/**
	 * The 'change' event handler for the 'serviceType' drop-down list.
	 */
	serviceTypeDropdownHandler : function(event) {
		var typeSelectEl = Event.element(event);
		var defaultDuration = Screen.getServiceDuration(typeSelectEl.getValue());
		$('duration').setValue(defaultDuration);
	}
};

document.observe("dom:loaded", function () {
	// Retrieve the list of service types
	new Ajax.Request('../getServiceTypes', {
		  onSuccess: function(response) {
				Screen.SERVICE_TYPES = response.responseJSON;
			}
		});
	
	// Register a change event listener to the type select list
	$('serviceTypeDropdown').observe('change', Screen.serviceTypeDropdownHandler);
});