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
 *  along with Rennspur.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.rennspur.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter for boolean database values "Y" and "N" to a Boolean entity
 * property.
 * 
 * @author burghard.britzke
 */
@Converter(autoApply = true)
public class BooleanYNConverter implements AttributeConverter<Boolean, String> {
	/**
	 * Converts the value true or false to the character "Y" or "N".
	 * 
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
	 */
	@Override
	public String convertToDatabaseColumn(Boolean value) {
		if (value.equals(Boolean.TRUE)) {
			return "Y";
		} else {
			return "N";
		}
	}

	/**
	 * Converts the character "Y" or "N" to true or false.
	 * 
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
	 */
	@Override
	public Boolean convertToEntityAttribute(String value) {
		return value.equals("Y");
	}

}
