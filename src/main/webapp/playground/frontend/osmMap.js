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

/*
 * Position and zoomlevel of the map
 */

var lon = 13.34148;
var lat = 52.49603;
var zoom = 18;
				
var language = 'de';

/**
 * Draws the map.
 * 
 * @returns nothing
 */
function drawmap() {
	
	/* var earthquakeStyle = new ol.style.Style({
        image: new ol.style.Icon({
          anchor: [0.5, 0.5],
          size: [52, 52],
          offset: [52, 0],
          opacity: 1,
          scale: 0.25,
          src: 'images.png'
        })
      }); */
	
	var center = ol.proj.transform([lon, lat], 'EPSG:4326', 'EPSG:3857');
	
    var view = new ol.View({
    	center: center,
        zoom: zoom
      });
   
    var tile = new ol.layer.Tile({
    	             source: new ol.source.OSM()
    	             });
    
    map = new ol.Map({
    	view: view,
    	layers: [ tile ],
    	target: "mapdiv"
	    
	});   
	
}

/**
 * Map event listener moved
 * 
 * @param event
 * @returns
 */
function mapEventMove(event) {

}
