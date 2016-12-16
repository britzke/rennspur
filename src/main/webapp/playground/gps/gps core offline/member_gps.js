/*
 *  This file is part of Rennspur.
 *  
 *  Copyright (C) 2016  Konstantin Baltruschat konstantin.baltruschat@gmail.com
 *  
 *  Rennspur is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  Rennspur is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

var send_interval;
var pos_interval;

/**
 * Clear all saved Locations from the localstorage only called after
 * successfully sending them to the server
 */
function clearPositionsArray() {
	/** Get the positions array out of the localstorage */
	var jsonString = localStorage.getItem("rennspur_gps_locations");
	var jsonObject = JSON.parse(jsonString);

	/** clear the array by initializing it as a new, empty array */
	jsonObject.positions.length = [];

	/** put the JSON object back into the localStorage */
	jsonString = JSON.stringify(jsonObject);
	localStorage.setItem("rennspur_gps_locations", jsonString);
}

/**
 * Sends all positions from the localstorage to the server
 */
function sendLocations() {
	/** use jquery to post the locations to the Rest interface */
	$.ajax({
		url : 'http://localhost:8080/rennspur/rest/gps-service',
		type : 'post',
		headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		dataType : 'json',
		data : localStorage.getItem("rennspur_gps_locations"),
		success : function(result) {
			/** in case of successful POST request clear the saved positions */
			clearPositionsArray();
		}
	});
}

/**
 * Prepare the localStorage to save positions and the hash
 */
function prepareLocalStorage() {
	// TODO: Insert hash from HTML into json string
	var url = window.location.href;
	var hash = url.split("?")[1];
	var json = '{"hash":"' + hash + '","positions" : []}';
	localStorage.setItem("rennspur_gps_locations", json);
}

/**
 * Initialize data on localstorage. Should be called once before calling other
 * functions
 */
function init() {
	/** check if localStorage is empty */
	var storageItem = localStorage.getItem("rennspur_gps_locations");
	if (storageItem == null) {
		/** nothing found, creating new */
		prepareLocalStorage();
	} else {
		/** if existing check if saved Data is the correct JSON-Format */
		var storageItemJson = JSON.parse(storageItem);
		if (storageItemJson.positions == null || storageItemJson.hash == null) {
			/**
			 * positions array or auth hash in JSON object is missing, creating
			 * new
			 */
			prepareLocalStorage();
		}
		// TODO: check if the found data is old, maybe from another event?
	}
}

/**
 * Callback for geolocation::getCurrentPosition. Saves the location into the
 * localstorage with the current system-time
 */
function geolocation_callback(position) {
	console.log("geo-callback");
	/** Get the time of the function call */
	var time = Date.now();

	/** Save positions and time as json */
	var positionJson = JSON.parse(JSON.stringify({
		"longitude" : position.coords.longitude,
		"latitude" : position.coords.latitude,
		"time" : time
	}));

	/** Get existing array of positions from the localstorage */
	var locationJsonString = localStorage.getItem("rennspur_gps_locations");

	/** Turn them into a JSON object */
	var locactionJson = JSON.parse(locationJsonString);

	/** Add the saved position into the array */
	locactionJson.positions.push(positionJson);

	/** Save the JSON object back into the localstorage as string */
	localStorage.setItem("rennspur_gps_locations", JSON
			.stringify(locactionJson));
}

/**
 * find out current gps position and save them
 */
function saveLocation() {
	console.log("save-location");
	navigator.geolocation.getCurrentPosition(geolocation_callback);
}

/**
 * onClick to test some functions
 */
function clickTest() {
	clearInterval(send_interval);
	clearInterval(pos_interval);
	if (navigator.geolocation) {
		/** Every 5000ms (5s) call sendLocations() */
		send_interval = setInterval(sendLocations, 5000);
		/** Every 1000ms (1s) save the currentPosition */
		pos_interval = setInterval(saveLocation, 1000);
	} else {
		alert("Geolocation not supported by this browser!\r\nApplication will not be able to run.")
	}
}

/**
 * To test , call init at start and then save a test-location
 */
window.onload = function() {
	init();
}
