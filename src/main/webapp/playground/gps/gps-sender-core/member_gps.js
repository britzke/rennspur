/*
 *  This file is part of Rennspur.
 *  
 *  Copyright (C) 2016  burghard.britzke, bubi@charmides.in-berlin.de
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

/**
 * Clear all saved Locations from the localstorage
 * only called after successfully sending them to the server
 */
function clearLocalStorage(){
	/** clear status array in json */
}



/**
 * Sends all positions from the localstorage to the server
 */
function sendLocations(){
	/** Use POST to send the positions as json string to server */
}

/**
 * Prepare the localStorage to save positions and the key
 */
function prepareLocalStorage(){
	//TODO: Insert key from HTML into json string
	var json = '{"key":"","status" : []}';
	localStorage.setItem("rennspur_gps_locations", json);
}

/**
 * Initialize data on localstorage.
 * Should be called once before calling other functions
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
		if(storageItemJson.status == null || storageItemJson.key == null){
			/** status array or auth key in JSON object is missing, creating new */
			prepareLocalStorage();
		}
		//TODO: check if the found data is old, maybe from another event?
		/** everything is fine, keeping old localStorage */
		
	}
}

/**
 * Saves the location into the localstorage with the current system-time
 * 
 * @param {number} lo [lo = 51.3256] - longitude of the location
 * @param {number} la [la = 40.9034] - latitude of the location
 */
function saveLocation(lo, la) {
	/** Get the time of the function call */
	var time = Date.now();

	/** Save positions and time as json */
	var positionJson = JSON.parse(JSON.stringify({
		"long" : lo,
		"lat" : la,
		"time" : time
	}));

	/** Get existing array of positions from the localstorage */
	var locationJsonString = localStorage.getItem("rennspur_gps_locations");

	/** Turn them into a JSON object */
	var locactionJson = JSON.parse(locationJsonString);

	/** Add the saved position into the array */
	locactionJson.status.push(positionJson);

	/** Save the JSON object back into the localstorage as string */
	localStorage.setItem("rennspur_gps_locations", JSON.stringify(locactionJson));
}

/**
 * To test , call init at start and then save a test-location
 */
window.onload = function() {
	init();
	saveLocation(10, 20);
}