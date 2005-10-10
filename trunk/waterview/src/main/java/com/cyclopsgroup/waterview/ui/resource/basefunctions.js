function dateCalendarSelected(cal, date) {
	cal.sel.value = date;
	if (cal.dateClicked)
	{
    	cal.callCloseHandler();
    }
}

function closeDateCalendar(cal) {
	cal.hide();
	_dynarch_popupCalendar = null;
}

function showDateCalendar(fieldId) {
	var el = document.getElementById(fieldId);
	if (_dynarch_popupCalendar != null)
	{
    	_dynarch_popupCalendar.hide();
  	}
	else
	{
	    var cal = new Calendar(1, null, dateCalendarSelected, closeDateCalendar);
	    _dynarch_popupCalendar = cal;
	    cal.setRange(1900, 2070);
	    cal.create();
	}
	_dynarch_popupCalendar.setDateFormat("%m/%d/%Y");
	_dynarch_popupCalendar.parseDate(el.value);
	_dynarch_popupCalendar.sel = el;
	_dynarch_popupCalendar.showAtElement(el.nextSibling, "span");
	return false;
}