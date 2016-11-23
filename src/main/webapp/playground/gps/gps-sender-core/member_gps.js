/**
 * author Konstantin Baltruschat
 *
 */

// Call saveLocation with random Parameter values to test the function while running
function clickTest() {
	saveLocation(13, 12);
}

// Initialize the JSON into the local storage once
// TODO: make sure it doesnt get called if localstorage isn't empy
function init() {
	var json = JSON
			.parse('{"key" : "","status" : [{"long" : 1,"lat" : 2,	"time" : 3},{"long" : 4,"lat" : 5,"time" : 6}]}');
	var sjson = JSON.stringify(json)
	localStorage.setItem("gps_locations", sjson);
}

// Save location into localstorage
// param lo: longitude of the location
// param la: latitude of the location
function saveLocation(lo, la) {
	var time = Date.now();
	var json = JSON.parse(JSON.stringify({
		"long" : lo,
		"lat" : la,
		"time" : time
	}));

	var slocs = localStorage.getItem("gps_locations");

	var locs = JSON.parse(slocs);
	locs.status.push(json);
	console.log(locs.status);

	localStorage.setItem("gps_locations", JSON.stringify(locs));
}

// to test functions
window.onload = function() {
	init();
	saveLocation(10, 20);
}