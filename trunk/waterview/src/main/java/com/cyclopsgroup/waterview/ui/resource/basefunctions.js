//Calendar related functions

function dateCalendarSelected(cal, date)
{
	cal.sel.value = date;
	if (cal.dateClicked)
	{
    	cal.callCloseHandler();
    }
}

function closeDateCalendar(cal)
{
	cal.hide();
	_dynarch_popupCalendar = null;
}

function showDateCalendar(fieldId)
{
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

//AJAX related functions
var _XMLHttpRequest;
if(window.XMLHttpRequest)
{
	_XMLHttpRequest = new XMLHttpRequest();
}
else if(window.ActiveXObject)
{
	_XMLHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
}

function fillDynaContent(tagId, url)
{
	if(_XMLHttpRequest == null)
	{
		alert("Your browser " + navigator.userAgent + " is not supported. Try other browser please.");
		return;
	}
	var el = document.getElementById(tagId);
	if(el == null)
	{
		return;
	}
    _XMLHttpRequest.open("GET", url, false);
    _XMLHttpRequest.send("");
    if(_XMLHttpRequest.status == "200")
	{
		el.innerHTML = _XMLHttpRequest.responseText;
	}
	else
	{
		alert("Something is wrong " + _XMLHttpRequest.response.Text);
	}
}

function clearDynaContent(tagId)
{
	var el = document.getElementById(tagId);
	if(el)
	{
		el.innerHTML = "";
	}
}	