/*
 *  This file is part of Rennspur.
 *  
 *  Copyright (C) 2016  Konstantin Baltruschat
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
 *  along with Rennspur.  If not, see <http://www.gnu.org/licenses/>.
 */

var race;
var selected;

function geolocation_error_callback(){
	switch (error.code) {
	case 1: // PERMISSION_DENIED
		alert("Permission to access location denied.");
		stop();
		break;
	case 2: // POSITION_UNAVAILABLE
		alert("Location unaviable!\rHas your device position support?")
		stop();
		break;
	case 3: // TIMEOUT
		console.log("geolocation_timeout after 10.000ms");
	}
}

function geolocation_success_callback(position) {
		
	race.waypoints[selected].waypointPositions.push({
		"longitude" : position.coords.longitude,
		"latitude" : position.coords.latitude,
		"time" : Date.now()
	});
	
	$.ajax({
		url : `${location.origin}/rennspur/rest/gps-service/waypoint`,
		type : 'post',
		headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		dataType : 'json',
		data : JSON.stringify(race.waypoints[selected])
	});
}

function send(){
	navigator.geolocation.getCurrentPosition(geolocation_success_callback, geolocation_error_callback, {timeout:10000});
}

/*
 * When the user clicks on the button, toggle between hiding and showing the
 * dropdown content
 */
function showDropdown() {
    document.getElementById("myDropdown").classList.toggle("show");
}

function select() {
	// document.getElementById("select-button").innerHTML =
	// document.getElementById("first").innerHTML;
	document.getElementById("myDropdown").classList.toggle("show");
}


function handleWaypointData(result){
	for(var i = 0; result.waypoints[i] != null; i++){
		document.getElementById("myDropdown").innerHTML += "<a onclick=\"select()\" id=\"selection:" + i + "\">" + result.waypoints[i].name  + "</a>"
	}
	race = result;
}

window.onload = function(){
	$.ajax({
		url : `${location.origin}/rennspur/rest/frontend/race`,
		type : 'get',
		headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		success : function(result) {
			handleWaypointData(result);
		},
		error : function(error){
			alert("check your connection");
		}
	});
}

// Close the dropdown if the user clicks outside of it //TODO: NOT WORKING
window.onclick = function(event) {
	if(event.target.id.split(":")[0] == "selection"){
		selected = parseInt(event.target.id.split(":")[1]);
	} else if (event.target.id != "select-button") {
		var dropdowns = document.getElementsByClassName("dropdown-content");
		var i;
		for (i = 0; i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show'); 
			} 
		}
	}
}