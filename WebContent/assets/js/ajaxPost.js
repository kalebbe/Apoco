//I attempted to put all of these into one method, but it didn't seem possible
//due to the data portion. May try again later.
function ajaxPost(url, name, value, name2, name3){
	var dataObject = {};
	if(value == null){
		if(value2 == null){
			dataObject[name] = $("#" + name).val();
		}
		else{
			dataObject[name] = $("#" + value).val();
		}
	}
	else{
		dataObject[name] = value;
	}
	if(name2 != null){
		dataObject[name2] = $("#" + name2).val();
	}
	if(name3 != null){
		dataObject[name3] = $("#" + name3).val();
	}
	var ajax_control = $.ajax({
		url: '/Apoco/' + url,
		type: 'POST',
		data: dataObject
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText); //Updates the html so the new content is shown.
	});
}

function ajaxFeed(url, name, value, name2, value2){
	var dataObject = {};
	dataObject[name] = $(value).val();
	if(name2 != null){
		dataObject[name2] = $(value2).val();
	}
	var ajax_control = $.ajax({
		url: '/Apoco/' + url,
		type: 'POST',
		data: dataObject
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText);
	});
}