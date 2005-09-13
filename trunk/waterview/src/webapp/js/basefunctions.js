function openDatePicker(formName, fieldName, baseUrl) {
	var form = document.forms[formName];
	var field = eval('form.' + fieldName);
	if( field == null )
	{
		alert("No such date field [" + fieldName + "]");
		return;
	}
	var url = baseUrl + "&day=" + field.value;
	window.open(url, null, "width=300,height=200");
}