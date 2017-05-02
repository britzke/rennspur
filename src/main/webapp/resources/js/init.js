
var map;

JSON.useDateParser(); // parse json dates to js Date objects (see. json.date-extensions)

$(document).ready(function() {
	alert("init");
	$.getJSON("/rennspur/rest/frontend/race").done(function(data) {
		console.log(data);
		race = new rs.model.Race(data);
		console.log(race);
		map = new rs.Map("rs-map", race);
	}).fail(function() {
		console.log("xhr-error");
	});
});