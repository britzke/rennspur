/*
 *  This file is part of Rennspur.
 *  
 *  Copyright (C) 2016  maximilian lietzmann, justin beutler, kevin ivancea
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

var map;

/*
 * Position and zoomlevel of the map
 */

var lon = 13.3425;
var lat = 52.49605;

var lon1 = 13.3435;
var lat1 = 52.49615;

var choords = [[lon, lat], [lon1, lat1]];

var zoom = 18;
/**
 * Draws the map.
 * 
 * @returns nothing
 */
function drawmap() {

	var iconFeature = new ol.Feature({
		geometry : new ol.geom.Point(ol.proj.transform([ lon, lat ],
				'EPSG:4326', 'EPSG:3857')),
	});

	var iconStyle = new ol.style.Style({
		image : new ol.style.Icon(
		({
			src : 'marker.png'
		}))
	});

	iconFeature.setStyle(iconStyle);
	// -------------------------------------------------------------------
	var iconFeature1 = new ol.Feature({
		geometry : new ol.geom.Point(ol.proj.transform([ lon1, lat1 ],
				'EPSG:4326', 'EPSG:3857'))
	});

	var iconStyle1 = new ol.style.Style({
		image : new ol.style.Icon(
		({
			src : 'marker.png'
		}))
	});

	iconFeature1.setStyle(iconStyle1);
	// -------------------------------------------------------------------
	/*var lineString = new ol.geom.LineString(choords);
	lineString.transform('EPSG:4326', 'EPSG:3875');
	
	var lineFeature = new ol.Feature({
		geometry: lineString,
		name: 'Line'
	});*/
	// -------------------------------------------------------------------

	var vectorSource = new ol.source.Vector({
		features : [ iconFeature, iconFeature1 ]
	});

	var vectorLayer = new ol.layer.Vector({
		source : vectorSource
	});

	var center = ol.proj.transform([ lon, lat ], 'EPSG:4326', 'EPSG:3857');

	var view = new ol.View({
		center : center,
		zoom : zoom
	});

	var tile = new ol.layer.Tile({
		source : new ol.source.OSM()
	});

	map = new ol.Map({
		view : view,
		renderer : 'canvas',
		layers : [ tile, vectorLayer ],
		target : "mapdiv"

	});

}