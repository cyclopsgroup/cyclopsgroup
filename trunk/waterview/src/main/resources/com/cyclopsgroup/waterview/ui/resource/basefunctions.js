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
function createHttpRequest()
{
	if(window.XMLHttpRequest)
	{
		return new XMLHttpRequest();
	}
	else if(window.ActiveXObject)
	{
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	else
	{
		alert("Your browser is not supported");
	}
}

function fetchExternalContent(url)
{
	var req = createHttpRequest();
    req.open("GET", url, false);
    req.send("");
    if(req.status == "200")
	{
		return req.responseText;
	}
	return req.statusText;
}

function fillDynaContent(tagId, url)
{
	var el = document.getElementById(tagId);
	if(el == null)
	{
		return;
	}

	var text = fetchExternalContent(url);

	el.innerHTML = text;
}

function clearDynaContent(tagId)
{
	var el = document.getElementById(tagId);
	if(el)
	{
		el.innerHTML = "";
	}
}

//Form related
// Convert a list of strings into a 'get' query string
function _makeSearchString() {
   var args = _makeSearchString.arguments;
   var searchString = "";
   var pair;
   for (var i = 0; i < args.length; i++) {
      pair = escape(args[i++]) + "=";
      pair += escape(args[i]);
      searchString += pair + "&";
   }
   return searchString.substring(0, searchString.length - 1);
}


// Create a 'get' query string with the data from a given form
function _createFormData(form) {
   var formData = '';
   var element;

   // For each form element, extract the name and value
   for (var i = 0; i < form.elements.length; i++) {
      element = form.elements[i];
      if (element.type == "text" || element.type == "password" || element.type == "textarea") formData += "'" + element.name + "', '" + escape(element.value) + "', ";
      else if (element.type.indexOf("select") != -1) {
         for (var j = 0; j < element.options.length; j++) {
            if (element.options[j].selected == true) formData += "'" + element.name + "', '" + element.options[element.selectedIndex].value + "', ";
         }
      }
      else if (element.type == "checkbox" && element.checked) formData += "'" + element.name + "', '" + element.value + "', ";
      else if (element.type == "radio" && element.checked == true) formData += "'" + element.name + "', '" + element.value + "', ";
   }
   // Feed strings to makeSearchString() to do 'get' query string conversion
   return (eval("_makeSearchString(" + formData.substring(0, formData.length - 2) + ")"));
}

function validateForm(form, formId)
{
	var query = _createFormData(form) + "&form_id=" + escape(formId)+ "&page_layout_id=layout.rawview";
	var url = pageBaseUrl + "/!show!/waterview/pub/FormValidation.jm";

	var req = createHttpRequest();
	req.open("POST", url, false);
	req.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	//req.overrideMimeType('text/xml');
    req.send(query);
    var successful = false;
    var errorMessage = "";
    var focused = false;
    if(req.status == "200")
	{
		var text = req.responseText;
		successful = true;
		var results = text.split('|');
		for(var i = 0; i < results.length; i++)
		{
			var fieldResult = results[i];
			if(fieldResult == "")
			{
				continue;
			}

			var infos = fieldResult.split(':');
			var fieldName = infos[0];
			var fieldTitle = infos[1];
			var msg = infos[2];
			var field = eval("form." + fieldName);

			if(msg == "ok")
			{
				if(field)
				{
					field.className = "waterviewFormInput";
					field.title = "";
				}
			}
			else
			{
				errorMessage += fieldTitle + ": " + msg + "\n";
				successful = false;
				if(field)
				{
					field.className = "waterviewFormInvalidInput";
					field.title = msg;
					if( !focused )
					{
						focused = true;
						field.select();
						field.focus();
					}
				}
			}
		}
	}
	if(!successful)
	{
		alert(errorMessage);
	}
	return successful;
}

function submitForm(form, url, validation)
{
	if( validation )
	{
		if( form.force_validation )
		{
			form.force_validation.value = "true";
		}
		if( !validateForm(form, form.form_id.value))
		{
			return false;
		}
	}
	for(var i = 0; i < form.elements.length; i++)
	{
		var element = form.elements[i];
		if(element.type == "submit" || element.type == "image" || element.type == "button")
		{
			element.disabled = true;
		}
	}
	form.action = url;
	form.submit();
	return false;
}

var _waterviewCurrentTabName = new Array();

function setTabName(tabId, tabName)
{
	_waterviewCurrentTabName[ tabId ] = tabName;
}

function changeTab(tabId, tabName, url)
{
	previousName = _waterviewCurrentTabName[ tabId ];
	var previousTd = document.getElementById("tabtd" + tabId +"_" + previousName);
	previousTd.className = "waterviewUnselectedTab";
	var currentTd = document.getElementById("tabtd" + tabId +"_" + tabName);
	setTabName(tabId, tabName);
	currentTd.className = "waterviewSelectedTab";

	var actionUrl = pageBaseUrl + "/!do!/waterview/ChangeSessionAttribute/!show!/waterview/pub/Empty.jelly?attribute_name="
			+ tabId + "&attribute_value=" + tabName;
	//alert(actionUrl);
	fetchExternalContent( actionUrl );

	var furl = url;
	if(url.indexOf('?') == -1)
	{
		furl = url + "?page_layout_id=layout.rawview";
	}
	else
	{
		furl = url + "&page_layout_id=layout.rawview";
	}
	fillDynaContent("tab" + tabId, furl);
	return false;
}