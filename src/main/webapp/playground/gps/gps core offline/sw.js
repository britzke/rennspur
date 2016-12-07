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
this.addEventListener('install', function(event) {
  event.waitUntil(
    caches.open('v1').then(function(cache) {
      return cache.addAll([
		'/rennspur/playground/gps/gps%20core%20offline/index.html',
		'/rennspur/playground/gps/gps%20core%20offline/jquery.js',
		'/rennspur/playground/gps/gps%20core%20offline/member_gps.js',
		'/rennspur/playground/gps/gps%20core%20offline/app.js'
      ]);
    })
  );
});
        /*
		 * Check if there are changes in the cached files. If yes update the
		 * cache
		 */
this.addEventListener('fetch', function(event) {
  var response;
  event.respondWith(caches.match(event.request).catch(function() {
    return fetch(event.request);
  }).then(function(r) {
    response = r;
    caches.open('v1').then(function(cache) {
      cache.put(event.request, response);
    });
    return response.clone();
  }).catch(function() {
    return caches.match('/rennspur/playground/gps/gps%20core%20offline/index.html'); // CHANGE ME
  }));
});