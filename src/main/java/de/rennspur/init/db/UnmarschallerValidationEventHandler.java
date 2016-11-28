/*
 *  This file is part of Renspur.
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
package de.rennspur.init.db;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

/**
 * EventHandler for handling validation error out of an XML file.
 * 
 * @author burghard.britzke
 */
public class UnmarschallerValidationEventHandler implements ValidationEventHandler{

	/**
	 * Handles unmarschaller validation events. Logs details to the console
	 * output.
	 * 
	 * @see javax.xml.bind.ValidationEventHandler#handleEvent(javax.xml.bind.ValidationEvent)
	 */
	public boolean handleEvent(ValidationEvent ve) {
		
		if(ve.getSeverity()==ValidationEvent.FATAL_ERROR || ve.getSeverity()==ValidationEvent.ERROR){
			ValidationEventLocator locator = ve.getLocator();
			System.out.println("Falsches Element: "+locator.getURL());
			System.out.println("Fehler: "+ve.getMessage());
			System.out.println("Fehler in der Zeile "+locator.getColumnNumber() +", an der Stelle "+locator.getLineNumber());
		}
		return true;
	}
}
