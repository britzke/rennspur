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
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
"use strict";

/** @namespace */
var rs= rs || {

 // ...further classes and code

/**
 * Represents a position with longitude, latitude, time.
 */
Position : class {
    /**
     * Generates a rs.Position object. Initializes its properties.
     * 
     * @constructs
     * @param {float}
     *            Longitude of the position in degrees (-180.0 - 180.0)
     * @param {float}
     *            Latitude of the position in degrees (-90.0 - 90.0)
     * @param {number}
     *            Timestamp of this position.
     * @returns The position object.
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
         * @type {float}
         */
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
},

/**
 * Represents a rennspur map, a map for animating traces of races.
 */
Map : class {
    /**
     * Generates rs.Map object. Initalizes...
     * 
     * @lends rs.Map#
     * @constructs
     * 
     * @param {string}
     *            Id of the div-element, where the map is to reside.
     * @param {Array}
     *            An array in the form [latitude, longitude]
     * @returns The RennspurMap Object.
     */
    constructor (div = "rs-map", center = [0.0, 0.0], zoom = 16, source = "EPSG:4326", destination = "EPSG:3857") {
        this.source_ = source;
        this.destination_ = destination;
        this.center_ = center;
        this.zoom_ = zoom;
        
        var center = ol.proj.transform(center, this.source_, this.destination_);

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
        this.traceLayer_ = new ol.layer.Vector({source: this.traceSource_});

        this.map_ = new ol.Map({
            view : this.view_,
            layers : [ this.osmLayer_, this.seamarkLayer_, this.traceLayer_ ],
            target : div
        });
    }
    
    get zoom() {
        return this.zoom_;
    }
    
    get center() {
        return this.center_;
    }
    
    get traceSource() {
        return this.traceSource_;
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
    addTrace(trace) {
       var transformedTrace = [];

       for (var [index, coordinate] of trace.entries()) {
           transformedTrace[index] = ol.proj.transform(
                   coordinate,
                   this.source_,
                   this.destination_);
       }

       var traceGeometry = new ol.geom.LineString(transformedTrace);
       var traceFeature = new ol.Feature({
               geometry : new ol.geom.LineString(transformedTrace),
               style : new ol.style.Style({
                   stroke : new ol.style.Stroke({color : 'rgb(255, 0, 0)'})})
       });
       this.traceSource_.addFeature(traceFeature);
    }
}

// ...further classes and code

};