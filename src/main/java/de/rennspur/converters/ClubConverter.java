/*
 *  This file is part of Renspur.
 *
 *  Copyright (C) 2017  burghard.britzke bubi@charmides.in-berlin.de
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
package de.rennspur.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import de.rennspur.beans.ClubBean;
import de.rennspur.model.Club;

// TODO Maybe with JSF 2.3 and the ability to inject beans into converters
// this should not be a bean but a FacesConverter
// @FacesConverter(forClass = Club.class, value = "clubConverter")
/**
 * The ClubConverter can convert a club to its internal and external
 * representation.
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@Named
@RequestScoped
public class ClubConverter implements Converter {

	@Inject
	private ClubBean clubBean;

	/**
	 * Get the club object from its external representation. The object data is
	 * retrieved from the database out of the id, which is the external
	 * representation of the club.
	 *
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String submittedValue) {
		if (submittedValue.trim().equals("")) {
			return null;
		} else {
			try {
				int number = Integer.parseInt(submittedValue);

				for (Club club : clubBean.getClubs()) {
					if (club.getId() == number) {
						return club;
					}
				}
				return null;
			} catch (NumberFormatException exception) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Conversion Error", "Not a valid club"));
			}
		}
	}

	/**
	 * Get the external representation out of a club object. The external
	 * representation is the id of the club.
	 *
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Club) value).getId());
		}
	}
}
