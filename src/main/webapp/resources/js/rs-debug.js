/*
 *  This file is part of Renspur.
 *
 *  Copyright (C) 2016  maximilian lietzmann,
 *                      burghard.britzke bubi@charmides.in-berlin.de
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
"use strict";

/** @namespace */
var rs= rs || {};
/** @namespace */
rs.model = {};

/**
 * A club can organize events and members of competitor teams can belong to a
 * club.
 *
 * @class
 */
rs.model.Club = class Club{
    /**
	 * @constructs
	 * @param {object}
	 *            properties An object with the properties to initialize.
	 * @return {rs.model.Club} The initialized club object.
	 */
    constructor (properties) {
        for (let property in properties) {
            this[property] = properties[property];
        }
    }
    get id() {
        return this.id_;
    }
    set id(id) {
        this.id_ = id;
    }

    get dsvNumber() {
        return this.dsvNumber_;
    }

    set dsvNumber(dsvNumber) {
        this.dsvNumber_ = dsvNumber;
    }

    get abbreviation() {
        return this.abbreviation_;
    }

    set abbreviation(abbreviation) {
        this.abbreviation_ = abbreviation;
    }
    get name() {
        return this.name_;
    }

    set name(name) {
        this.name_ = name;
    }

    get url() {
        return this.url_;
    }

    set url(url) {
        this.url_ = url;
    }
};

/**
 * A group of races which form the event.
 *
 * @class
 */
rs.model.Event = class Event{
    /**
	 * @constructs
	 * @param {object}
	 *            properties An object with the properties to initialize.
	 * @return {rs.model.Event} The initialized event object.
	 */
    constructor (properties) {
        for (let property in properties) {
            switch (property) {
                case "club":
                    this[property] = new rs.model.Club(properties[property]);
                    break;
                case "teams":
                    let teams = [];
                    for (let team of properties[property]) {
                        teams[teams.length] = new rs.model.Team(team);
                    }
                    this[property] = teams;
                case "races":
                    let races = [];
                    for (let race of properties[property]) {
                        races[race.length] = new rs.model.Race(race);
                    }
                    this[property] = races;
                default:
                    this[property] = properties[property];
            }
        }
    }
    get id() {
        return this.id_;
    }
    set id(id) {
        this.id_ = id;
    }

    get club() {
        return this.club_;
    }
    set club(club) {
        this.club_ = club;
    }
    get team() {
        return this.team_;
    }
    set team(team) {
        this.team_ = team;
    }
};

/**
 * A waypoint is a mark, which all competitors must pass during a race. this
 * mark has a position, which may change.
 */
rs.model.Waypoint = class Waypoint{
    /**
	 *
	 */
    constructor (properties = null) {
        for (let property in properties) {
            switch (property) {
            case "waypointPositions":
                let waypointPositions = [];
                for (let waypointPosition of properties[property]) {
                    waypointPositions[waypointPositions.length] = new rs.model.WaypointPosition(waypointPosition);
                }
                this[property] = waypointPositions;
                break;
            default:
                this[property]  = properties[property];
            }
        }
    }

    get id() {
        return this.id_;
    }

    set id(id) {
        this.id_ = id;
    }

    get name() {
        return name_;
    }
    set name(name) {
        this.name_ = name;
    }
}
/**
 * A group of members who form one competitor team - the team.
 */
rs.model.Team = class Team{
    constructor (properties = null) {
        for (let property in properties) {
           this[property]  = properties[property];
        }
    }
    get id() {
        return this.id_;
    }

    set id(id) {
        this.id_ = id;
    }

    get name() {
        return name_;
    }
    set name(name) {
        this.name_ = name;
    }
    get country() {
        return this.country_;
    }
    set country(country) {
        this.country_ = country;
    }
    get handycapFactor () {
        return this.handycapFactor_;
    }
    set handicapFactor (handicapFactor) {
        this.handicapFactor_ = handicapFactor;
    }
    get club() {
        return this.club_;
    }
    set club(club) {
        this.club_ = club;
    }
};

/**
 * A race with its relations to events and teamPositions.
 */
rs.model.Race = class Race {
    constructor (properties) {
        for (let property in properties) {
            switch (property) {
                case "event":
                    this[property] = new rs.model.Event(properties[property]);
                    break;
                case "teamPositions":
                    let pos = [];
                    for (let teamPosition of properties[property]) {
                        pos[pos.length] = new rs.model.TeamPosition(teamPosition);
                    }
                    this[property] = pos;
                    break;
                case "waypoints":
                    let wpos = [];
                    for (let waypoint of properties[property]) {
                        wpos[wpos.length] = new rs.model.Waypoint(waypoint);
                    }
                    this[property] = wpos;
                    break;
                default:
                    this[property] = properties[property];
             }
         }
     }

    get id() {
        return this.id_;
    }
    set id(id) {
        this.id_ = id;
    }
};

/**
 * Represents a position with longitude, latitude, time.
 */
rs.model.Position = class Position{
    /**
	 * Generates a rs.Position object. Initializes its properties.
	 *
	 * @constructs
	 * @param {float}
	 *            Longitude of the position in degrees (-180.0 -180.0)
	 * @param {float}
	 *            Latitude of the position in degrees (-90.0 - 90.0)
	 * @param {number}
	 *            Timestamp of this position.
	 * @returns {Position} The position object.
	 */
    constructor (latitude = 0.0, longitude = 0.0, time=0) {
        /**
		 * @private
		 * @type {float}
		 */
        this.longitude_ = longitude;
        /**
		 * @private
		 * @type {float}
		 */
         this.latitude_ = latitude;
         /**
			 * @private
			 * @type {Date}
			 */
          this.time_ = time;

    }

    get longitude() {
        return this.longitude_;
    }
    set longitude(longitude) {
        this.longitude_ = longitude;
    }

    get latitude() {
        return this.latitude_;
    }
    set latitude(latitude) {
        this.latitude_ = latitude;
    }

    get time () {
        return this.time_;
    }
    set time(time) {
        this.time_ = time;
    }

    /**
	 * Get the coordinate of the positon.
	 *
	 * @returns [longitude , latitude]
	 */
     get coordinate()  {
         return [this.longitude_,this.latitude_];
     }
};

/**
 * Represents a TeamPosition, a position of a team in a race.
 */
rs.model.TeamPosition = class TeamPosition extends rs.model.Position{
    /**
	 * Constructs a new TeamPosition either out of an object or out of
	 * longitude, latitude, and time.
	 *
	 * @constructs
	 * @param {number|object}
	 *            A longitude or an Object from which the properties ar taken.
	 *            When it is an object, further attributes are ignored.
	 * @param {number}
	 *            A latitude. In case that the first parameter is an object,
	 *            this parameter is ignored.
	 * @param {Date}
	 *            The time, when the position is taken. If the first parameter
	 *            is an object, this parameter is ignored.
	 */
    constructor (longitudeOrObject = 0.0, latitude = 0.0, time = null, team = null, race = null) {
        if (typeof longitudeOrObject === "number" ) {
            super(longitudeOrObject, latitude,time);
            this.team_= new rs.model.Team(team);
            this.race_= new rs.model.Race(race);
        } else {
            super(0.0,0.0,null);
            for (let property in longitudeOrObject) {
                this[property] = longitudeOrObject[property];
            }
        }
    }
    get team() {
        return this.team_;
    }
    set team(team) {
        this.team_=team;
    }

    get race() {
        return this.race_;
    }
    set race(race) {
        this.race_=race;
    }
};

/**
 * Represents a WaypointPosition, a position of a mark in a race, which all
 * competitors must pass.
 */
rs.model.WaypointPosition = class WaypointPosition extends rs.model.Position{
    /**
	 * Constructs a new WaypointPosition either out of an object or out of
	 * longitude, latitude, and time.
	 *
	 * @constructs
	 * @param {number|object}
	 *            A longitude or an Object from which the properties ar taken.
	 *            When it is an object, further attributes are ignored.
	 * @param {number}
	 *            A latitude. In case that the first parameter is an object,
	 *            this parameter is ignored.
	 * @param {Date}
	 *            The time, when the position is taken. If the first parameter
	 *            is an object, this parameter is ignored.
	 */
    constructor (longitudeOrObject = 0.0, latitude = 0.0, time = null, race = null) {
        if (typeof longitudeOrObject === "number" ) {
            super(longitudeOrObject, latitude,time);
            this.race_= new rs.model.Race(race);
        } else {
            super(0.0,0.0,null);
            for (let property in longitudeOrObject) {
                this[property] = longitudeOrObject[property];
            }
        }
    }

    get name() {
        return this.name_;
    }
    set name(name) {
        this.name_=name;
    }
    get race() {
        return this.race_;
    }
    set race(race) {
        this.race_=race;
    }
};

/**
 * Represents a control for the legend. Inspired by
 * https://github.com/walkermatt/ol3-layerswitcher
 *
 * @extends {ol.control.Control}
 */
rs.Legend = function (opt_options) {
    /**
	 *
	 * @constructor
	 * @param {Object=}
	 *            opt_options Control options.
	 */
// constructor (opt_options) {
        let options = opt_options || {};

        this.event_ = options.event ? options.event : {};
        this.teams_ = [];

        this.hiddenClassName = 'ol-unselectable ol-control toggle-legend';
        if (rs.Legend.isTouchDevice_()) {
            this.hiddenClassName += ' touch';
        }
        this.shownClassName = this.hiddenClassName + ' shown';

        var element = document.createElement('div');
        element.className = this.hiddenClassName;

        let button = document.createElement('button');
        button.innerHTML = 'L';

        var that = this;
        var toggleLegend = function(e) {
            e = e || window.event;
            that.togglePanel();
            e.preventDefault();
        };

        button.addEventListener('click', toggleLegend, false);
        button.addEventListener('touchstart', toggleLegend, false);

        element.appendChild(button);
        this.panel = document.createElement('div');
        this.panel.className = 'panel';
        element.appendChild(this.panel);
        this.panel.addEventListener('click', toggleLegend, false);
        this.panel.addEventListener('touchstart', toggleLegend, false);

        rs.Legend.enableTouchScroll_(this.panel);

        ol.control.Control.call(this, {
            element: element,
            target: options.target
        });
};
ol.inherits(rs.Legend, ol.control.Control);

/**
 * Toggles, whether the panel is visible or not.
 */
rs.Legend.prototype.togglePanel = function() {
    if (this.element.className != this.shownClassName) {
        this.element.className = this.shownClassName;
        this.renderPanel();
    }
    else {
        this.element.className = this.hiddenClassName;
    }
};

/**
 * Re-draw the layer panel to represent the current state of the layers.
 */
rs.Legend.prototype.renderPanel = function() {

    while(this.panel.firstChild) {
        this.panel.removeChild(this.panel.firstChild);
    }

    let h1 = document.createElement("h1");
    h1.className = "legend-header";
    h1.appendChild(document.createTextNode(this.event_.name));
    let ul = document.createElement('ul');
    ul.className = "legend-teams-list";
    for (let team of this.teams_) {
        let li = document.createElement('li');
        li.style.backgroundColor = team.color;
        li.appendChild(document.createTextNode(team.name));
        ul.appendChild(li);
    }
    this.panel.appendChild(h1);
    this.panel.appendChild(ul);
};

/**
 * Add a team to the map.
 *
 * @param {rs.model.Team}
 *            team The team to add.
 */
rs.Legend.prototype.addTeam = function (team) {
    this.teams_.push(team);
};

/**
 * Get the teams.
 *
 * @returns {Array.Team}
 */
rs.Legend.prototype.getTeams = function () {
    return this.teams_;
}
/**
 * @private
 * @desc Apply workaround to enable scrolling of overflowing content within an
 *       element. Adapted from https://gist.github.com/chrismbarr/4107472
 */
rs.Legend.enableTouchScroll_ = function(elm) {
   if(rs.Legend.isTouchDevice_()){
       var scrollStartPos = 0;
       elm.addEventListener("touchstart", function(event) {
           scrollStartPos = this.scrollTop + event.touches[0].pageY;
       }, false);
       elm.addEventListener("touchmove", function(event) {
           this.scrollTop = scrollStartPos - event.touches[0].pageY;
       }, false);
   }
};

/**
 * @private
 * @desc Determine if the current browser supports touch events. Adapted from
 *       https://gist.github.com/chrismbarr/4107472
 */
rs.Legend.isTouchDevice_ = function() {
    try {
        document.createEvent("TouchEvent");
        return true;
    } catch(e) {
        return false;
    }
};

/**
 * Represents a rennspur map, a map for animating traces of races.
 */
rs.Map = class {
    /**
	 * Generates the rs.map object. Initializes every writable property, given
	 * with the properties object.
	 *
	 * @lends rs.Map#
	 * @constructs
	 * @returns The RennspurMap Object.
	 */
    constructor (properties) {
        this.source_ = "EPSG:4326";
        this.destination_ = "EPSG:3857";
        this.race_ = null;
        this.rotation_ = 0.0;
        this.resolution_ = 17.0;
        this.zoom_ = 16;
        this.div_ = "rs-map";
        this.center_ = [0,0];
        this.theme_ = "terrain";
        this.userTheme_ = "false";

        for (let property in properties) {
            this[property] = properties[property];
        }

        if (this.resolution_ == 0.0) {
            this.resolution_ = 18;
        }

        $( document ).ready(function() {
        		$(".ol-zoom-out").parent().prepend($("#mapStyleList"));
            	$("#mapStyleList").mouseover(function() {
            		$("#mapStyleList").children().first().hide();
            	});
            	$("#mapStyleList").mouseleave(function() {
            		$("#mapStyleList").children().first().text("T");
            	});
            	if(this.userTheme_ == "true"){
            		document.getElementById("mapStyleList").onchange = function() {
                		changeMap(this.value);
                		return false
                	};
            	}
        });

        function changeMap(style) {
	    	$("#mapStyleList").children().text("T");
	    	localStorage.setItem("mapStyle", style);
	    	location.reload();
	    }

	    function removeSelect() {
	    	$("#mapStyleList").style.display = none;
	    }

        if(this.userTheme_ == "false"){
            $("#mapStyleList").remove();
            console.log("option removed");
        }
        if(this.userTheme_ == "true"){
            this.theme_ = localStorage.getItem("mapStyle");
            console.log("option kept");
        }

        if (this.race_ != null) {
            this.center_ = [this.race.event.longitude,this.race.event.latitude];
        }
        this.imageSource_ = new ol.source.Vector();
        this.imageLayer_ = new ol.layer.Vector({
        	source: this.imageSource_});

        this.waypointStyle_ = new ol.style.Style({
        	image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
                src: "/rennspur/playground/frontend/waypoint.png",
                size: [250,250],
                scale: 0.25,
                anchor: [84,9],
                anchorOrigin: "bottom-left",
                anchorXUnits: "pixels",
                anchorYUnits: "pixels"
              }))
        });

        rs.map = this;
        var center = ol.proj.transform(this.center_, this.source_, this.destination_);

        this.view_ = new ol.View({
            center : center,
            zoom : this.zoom_,
            resolution : this.resolution_,
            rotation : this.rotation_
        });

        this.osmLayer_ =  new ol.layer.Tile({
            source: new ol.source.Stamen({layer: this.theme_})
        });
        this.seamarkLayer_ = new ol.layer.Tile({
            source: new ol.source.OSM({
                opaque: false,
                url: 'https://tiles.openseamap.org/seamark/{z}/{x}/{y}.png'
            })
        });

        this.traceSource_ = new ol.source.Vector();
        this.traceLayer_ = new ol.layer.Vector({
            source: this.traceSource_});

        this.map_ = new ol.Map({
        	controls: ol.control.defaults().extend([
                new ol.control.FullScreen(),
                new ol.control.Rotate()
            ]),
            interactions: ol.interaction.defaults().extend([
                new ol.interaction.DragRotateAndZoom()
            ]),
            view : this.view_,
            layers : [ this.osmLayer_, this.seamarkLayer_, this.traceLayer_, this.imageLayer_ ],
            target : this.div_
        });

        if (this.race_ != null) {
            this.legend_ = new rs.Legend({event:this.race_.event});
            if (this.legend_ != null) {
                this.map_.addControl( this.legend_);
            }

            for (let waypoint of race.waypoints){
                this.addWaypoint(waypoint);
            };

            // add all teams that are currently in race
            for (let team of race.event.teams) {
                this.addTeam(team);
            }
            var timer = setInterval(rs.Map.updateTraces, 1000);
        }
    }

    get zoom() {
        return this.zoom_;
    }

    get center() {
        return this.center_;
    }

    set center(center) {
        this.center_ = center;
    }

    get legend() {
        return this.legend_;
    }

    get map() {
        return this.map_;
    }

    set resolution(resolution) {
        return this.resolution_ = resolution;
    }

    set rotation(rotation) {
        return this.rotation_ = rotation;
    }

    set theme (theme) {
        this.theme_ = theme;
    }
    get traceSource() {
        return this.traceSource_;
    }

    get race() {
        return this.race_;
    }

    set race(race) {
        this.race_ = race;
    }

    toString() {
        return `rs.Map(zoom=${this.zoom}, center=${this.center})`;
    }

    /**
	 * Transforms an array of TeamPositions to a transformed array of
	 * coordinates. Applies the transform into the projection to each
	 * coordinate.
	 *
	 * @param {Array.TeamPosition}
	 *            An array of TeamPositions.
	 * @returns {Array.Array.number} An array of coordinates, transformed into
	 *          the projection of the map.
	 * @private
	 */
    coordinateTrace_(trace) {
        let transformedTrace = [];

        let coordinates = trace.map(
                (teamPosition) => [teamPosition.longitude,teamPosition.latitude]);
        for (let [index, coordinate] of coordinates.entries()) {
            transformedTrace[index] = ol.proj.transform( coordinate,
                    this.source_, this.destination_);
        }
        return transformedTrace;
    }

    /**
	 * Add a trace for a team to the map.
	 *
	 * @param {[[x,y],...]}
	 *            Array of coordinate Arrays.
	 */
    addTeam(team) {
        let r = Math.floor(Math.random() * 128.0 + 128.0); // preverred red
        // tones
        let g = Math.floor(Math.random() * 220.0);
        let b = Math.floor(Math.random() * 220.0);
        team.color = `rgb(${r}, ${g}, ${b})`;
        this.legend.addTeam(team);
        let emptyTrace = [];
        let traceGeometry = new ol.geom.LineString(emptyTrace);
        let line = new ol.geom.LineString(emptyTrace);
        let traceFeature = new ol.Feature({
            geometry : line});
          traceFeature.setStyle(
                  new ol.style.Style({
                      stroke : new ol.style.Stroke({color : team.color, width:3})
          }));
          traceFeature.setId(`${team.id}.trace`);
          this.traceSource_.addFeature(traceFeature);
    }
    /**
	 * add waypoints and for every waypoint all of it's waypointPositions to the
	 * map.
	 *
	 * @param {[[x,y],...]}
	 *            Array of coordinate Arrays.
	 */
    addWaypoint(waypoint) {
        if (waypoint.waypointPositions != null) {
            let waypointCoordinates = rs.map.coordinateTrace_(waypoint.waypointPositions);

            for (let waypointCoordinate of waypointCoordinates) {
                let waypointFeature = new ol.Feature({
                    geometry: new ol.geom.Point(waypointCoordinates[0])
                });
                waypointFeature.setStyle(this.waypointStyle_);
                this.imageSource_.addFeature(waypointFeature);
            }
        } else {
            console.log ("Waypoint "+waypoint.name+" without a position.")
        }
    }

    /**
	 * Updates the Traces from the backend service. This routine is to be called
	 * periodically.
	 */
    static updateTraces() {
        for (let team of rs.map.race.event.teams) {  // for each team
            let xhr = $.getJSON("/rennspur/rest/frontend/update/"+team.id);
            xhr.teamId= team.id;    // pass team id to function via xhr
            xhr.done(function( data, status, xhr ) {
                let teamPositions = [];
                for (let item of data) {
                    let teamPosition = new rs.model.TeamPosition(item);
                    teamPositions.push(teamPosition);
                }
                let team = xhr.teamId;  // get the team id out of the xhr
                if (team) {
                    let feature = rs.map.traceSource.getFeatureById(`${team}.trace`);
                    let line = feature.getGeometry();
                    let transformedTrace = rs.map.coordinateTrace_(teamPositions);
                    line.setCoordinates(transformedTrace);
                }
            })
            .fail(function () {
                console.log("xhr-error");
            });
        }
    }
 };

// ...further classes and code
