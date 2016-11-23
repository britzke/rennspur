/*
 *  This file is part of Renspur.
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
var layer_mapnik;
var layer_tah;
var layer_seamark;
var marker;

/*
 * Position and zoomlevel of the map
 */
var lon = 13.34148;
var lat = 52.49603;
var zoom = 18;
				
var linkTextSkipperGuide = "Beschreibung auf SkipperGuide";
var linkTextWeatherHarbour = "Meteogramm";
var language = 'de';

/**
 * Sets the start frame.
 * 
 * @param lon
 * @param lat
 * @param zoom
 * @returns
 */
function jumpTo(lon, lat, zoom) {
	var x = Lon2Merc(lon);
	var y = Lat2Merc(lat);
	map.setCenter(new OpenLayers.LonLat(x, y), zoom);
	return false;
}

/**
 * Longitude
 * 
 * @param lon
 * @returns
 */
function Lon2Merc(lon) {
	return 20037508.34 * lon / 180;
}

/**
 * Latitude
 * 
 * @param lat
 * @returns
 */
function Lat2Merc(lat) {
	var PI = 3.14159265358979323846;
	lat = Math.log(Math.tan( (90 + lat) * PI / 360)) / (PI / 180);
	return 20037508.34 * lat / 180;
}

/**
 * Loads the map and sets the properties of the map in the window
 * 
 * @param bounds
 * @returns
 */
function getTileURL(bounds) {
	var res = this.map.getResolution();
	var x = Math.round((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
	var y = Math.round((this.maxExtent.top - bounds.top) / (res * this.tileSize.h));
	var z = this.map.getZoom();
	var limit = Math.pow(2, z);
	if (y < 0 || y >= limit) {
		return null;
	} else {
		x = ((x % limit) + limit) % limit;
		url = this.url;
		path= z + "/" + x + "/" + y + "." + this.type;
		if (url instanceof Array) {
			url = this.selectUrl(path, url);
		}
		return url+path;
	}
}

/**
 * Draws the map.
 * 
 * @returns nothing
 */
function drawmap() {
	map = new OpenLayers.Map('map', {
		projection: new OpenLayers.Projection("EPSG:900913"),
		displayProjection: new OpenLayers.Projection("EPSG:4326"),
		eventListeners: {
			"moveend": mapEventMove,
			// "zoomend": mapEventZoom
		},
		controls: [
		new OpenLayers.Control.Navigation(),
		new OpenLayers.Control.ScaleLine({topOutUnits : "nmi", bottomOutUnits: "km", topInUnits: 'nmi', bottomInUnits: 'km', maxWidth: '40'}),
		// new OpenLayers.Control.LayerSwitcher(),
		new OpenLayers.Control.MousePosition(),
		new OpenLayers.Control.PanZoomBar()],
						maxExtent:
		new OpenLayers.Bounds(-20037508.34, -20037508.34, 20037508.34, 20037508.34),
					numZoomLevels: 18,
					maxResolution: 156543,
					units: 'meters'
		});

		// Add Layers to map Mapnik seamark
		layer_mapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
		layer_seamark = new OpenLayers.Layer.TMS("Seezeichen", "http://tiles.openseamap.org/seamark/", { numZoomLevels: 18, type: 'png', getURL: getTileURL, isBaseLayer: false, displayOutsideMaxExtent: true});
		
		map.addLayers([layer_mapnik, layer_seamark]);
		jumpTo(lon, lat, zoom);
}

/**
 * Map event listener moved
 * 
 * @param event
 * @returns
 */
function mapEventMove(event) {
}
