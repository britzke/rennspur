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

rs.model.Club = class {
    
};

rs.model.Event = class {
    constructor (numberOrObject) {
        if (typeof numberOrObject === "number") {
            this.number_ = numberOrObject;
        } else {
            for (let property in numberOrObject) {
                if (typeof numberOrObject[property] == "object") {
                    switch (property) {
                        case "club":
                            this[property] = new rs.model.Club(numberOrObject[property]);
                            break;
                        case "team":
                            this[property] = new rs.model.Team(numberOrObject[property]);                            
                        default:
                            this[property] = numberOrObject[property];
                    }
                }
                else {
                    this[property] = numberOrObject[property];                    
                }
            }
        }
    }

};

rs.model.Team = class {
    constructor (nameOrObject = null) {
        if (typeof nameOrObject === "string") {
            this.name_=nameOrObject;
        } else {
            for (let property in nameOrObject) {
                this[property]  = nameOrObject[property];
            }
        }
    }
};

rs.model.Race = class {
    constructor (numberOrObject) {
        if (typeof numberOrObject === "number") {
            this.number_ = numberOrObject;
        } else {
            for (let property in numberOrObject) {
                if (typeof numberOrObject[property] == "object") {
                    switch (property) {
                        case "event":
                            this[property] = new rs.model.Event(numberOrObject[property]);
                            break;
                        case "teamPositions":
                            let pos = [];
                            for (let teamPosition of numberOrObject[property]) {
                                pos[pos.length] = new rs.model.TeamPosition(teamPosition);
                            }
                            this[property] = pos;
                            break;
                        default:
                            this[property] = numberOrObject[property];
                    }
                }
                else {
                    this[property] = numberOrObject[property];
                }
            }
        }
    }
};

/**
 * Represents a position with longitude, latitude, time.
 */
rs.model.Position = class {
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
rs.model.TeamPosition = class extends rs.model.Position{
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
        this.mapListeners = [];

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
            // TODO toggle the legend
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

        rs.Legend.enableTouchScroll_(this.panel);
        
        ol.control.Control.call(this, {
            element: element,
            target: options.target
        });
};
ol.inherits(rs.Legend, ol.control.Control);

/**
 * Set the map instance the control is associated with.
 * 
 * @param {ol.Map}
 *            map The map instance.
 */
rs.Legend.prototype.setMap = function(map) {
    // Clean up listeners associated with the previous map
    for (var i = 0, key; i < this.mapListeners.length; i++) {
        this.getMap().unByKey(this.mapListeners[i]);
    }
    this.mapListeners.length = 0;
    // Wire up listeners etc. and store reference to new map
    ol.control.Control.prototype.setMap.call(this, map);
/*
 * if (map) { var this_ = this; this.mapListeners.push(map.on('pointerdown',
 * function() { this_.hidePanel(); }));
 * 
 * this.renderPanel(); }
 */
};


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

rs.Legend.prototype.addTeam = function (team) {
    this.teams_.push(team);
};

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
     * Generates rs.Map object. Initalizes...
     * 
     * @lends rs.Map#
     * @constructs
     * 
     * @param {string}
     *            Id of the div-element, where the map is to reside.
     * @param {rs.model.Race}
     *            Information about the race to display.
     * @returns The RennspurMap Object.
     */
    constructor (div = "rs-map", race = null, zoom = 16, source = "EPSG:4326", destination = "EPSG:3857") {
        this.source_ = source;
        this.destination_ = destination;
        this.race_ = race;
        this.center_ = [race.event.longitude,race.event.latitude];
        this.zoom_ = zoom;
        
        var center = ol.proj.transform(this.center_, this.source_, this.destination_);

        this.view_ = new ol.View({
            center : center,
            zoom : this.zoom_
        });

        this.osmLayer_ =  new ol.layer.Tile({
            source: new ol.source.Stamen({layer: 'watercolor'})
        });
        this.seamarkLayer_ = new ol.layer.Tile({
            source: new ol.source.OSM({
                opaque: false,
                url: 'https://tiles.openseamap.org/seamark/{z}/{x}/{y}.png'
            })
        });
        
        this.traceSource_ = new ol.source.Vector();
        this.traceLayer_ = new ol.layer.Vector({
            source: this.traceSource_,
            style: function(feature, resolution) {
                style.getText().setText(resolution < 5000 ? feature.get('name') : '');
                return style;
             }
        });

        this.legend_ = new rs.Legend({event:this.race_.event});
        
        this.map_ = new ol.Map({
            controls: ol.control.defaults().extend([
                new ol.control.FullScreen(),
                this.legend
              ]),
            view : this.view_,
            layers : [ this.osmLayer_, this.seamarkLayer_, this.traceLayer_ ],
            target : div
        });
        
        // add all teams that are currently in race
        for (let team of race.event.teams) {
            this.addTrace(team, race.teamPositions.filter(
                    teamPosition => teamPosition.team.id == team.id));
        }
    }
    
    get zoom() {
        return this.zoom_;
    }
    
    get center() {
        return this.center_;
    }
    
    get legend() {
        return this.legend_;
    }
    
    get traceSource() {
        return this.traceSource_;
    }
    
    get race() {
        return this.race_;
    }
    
    toString() {
        return `Rennspur(zoom=${this.zoom}, center=${this.center})`;
    }

    /**
     * Add a trace to the map.
     * 
     * @param {[[x,y],...]}
     *            Array of coordinate Arrays.
     */
    addTrace(team,trace) {
        let r = Math.floor(Math.random() * 64.0 + 192.0); // preverred red
        // tones
        let g = Math.floor(Math.random() * 220.0);
        let b = Math.floor(Math.random() * 220.0);
        team.color = `rgb(${r}, ${g}, ${b})`;
        this.legend.addTeam(team);
        let transformedTrace = [];

        let coordinateTrace = trace.map(
                (teamPosition) => [teamPosition.longitude,teamPosition.latitude]);
        for (let [index, coordinate] of coordinateTrace.entries()) {
            transformedTrace[index] = ol.proj.transform( coordinate,
                    this.source_, this.destination_);
        }
        let traceGeometry = new ol.geom.LineString(transformedTrace);
        let traceFeature = new ol.Feature({
            geometry : new ol.geom.LineString(transformedTrace),
            name : "test" });
          traceFeature.setStyle(
                  new ol.style.Style({ 
                      stroke : new ol.style.Stroke({color : team.color, width:3})
          }));
          this.traceSource_.addFeature(traceFeature);
    }
};


// ...further classes and code

var foo = new rs.model.TeamPosition({
    longitude:10.0,
    latitude:10.0,
    time:new Date(),
    team:{name:"GER 72"},
    race:{number:1}
});
