/*
 *  This file is part of Rennspur.
 *  
 *  Copyright (C) 2016  Tim Prangel, tim.prangel@gmail.com
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
 * Register a service worker
 */ 

if ('serviceWorker' in navigator) {
  navigator.serviceWorker.register('sw.js', { scope: '/rennspur/playground/gps/gps%20core%20offline/' }).then(function(reg) {
    if(reg.installing) {
      console.log('Service worker installing');
    } else if(reg.waiting) {
      console.log('Service worker installed');
    } else if(reg.active) {
      console.log('Service worker active');
    }  
  }).catch(function(error) {
    console.log('Registration failed with ' + error);
  });
}


