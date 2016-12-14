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

/*
 * Define which files the service worker caches.
 */

var CACHE_NAME = 'my-site-cache-v1';
var urlsToCache = [
	'/rennspur/playground/gps/gps%20core%20offline/index.html',
	'/rennspur/playground/gps/gps%20core%20offline/index2.html', // Test
	'/rennspur/webjars/jquery/3.1.1-1/jquery.js',
	'/rennspur/playground/gps/gps%20core%20offline/member_gps.js',
	'/rennspur/playground/gps/gps%20core%20offline/app.js'
	
];

self.addEventListener('install', function(event) {
  // Perform install steps
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then(function(cache) {
        console.log('Opened cache');
        return cache.addAll(urlsToCache);
      })
  );
});
        /*
		 * Check if there are changes in the cached files. If yes update the
		 * cache
		 */
self.addEventListener('fetch', function(event) {
	  var response;
	  var request = event.request;
	 if (request.method == 'POST') {
		 return; 
	 }
	  event.respondWith(
	    caches.match(event.request)
	      .then(function(response) {
	        // Cache hit - return response
	        if (response) {
	          return response;
	        }
	        var fetchRequest = event.request.clone();

	        return fetch(fetchRequest).then(
	          function(response) {
	            // Check if we received a valid response
	            if(!response || response.status !== 200 || response.type !== 'basic') {
	              return response;
	            }
	            var responseToCache = response.clone();

	            caches.open(CACHE_NAME)
	              .then(function(cache) {
	                cache.put(event.request, responseToCache);
	              });

	            return response;
	          }
	        );
	      })
	    );
	});
