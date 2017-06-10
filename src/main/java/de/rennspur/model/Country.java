/*
 *  This file is part of Rennspur.
 *
 *  Copyright (C) 2017  burghard.britzke, bubi@charmides.in-berlin.de
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A country is a territory on earth with most political independence. A country
 * has three codes alpha-2, alpha-3 and a 3-digit numerical.
 *
 * <p>
 * The following schema fragment shows the expected content for members of this
 * class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="a2code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="a3code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="since" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})

@Entity
@Table(name = "COUNTRIES")
@NamedQueries({
		@NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c order by c.name"),
		@NamedQuery(name = "Country.findCountryByID", query = "SELECT c FROM Country c where c.id =:id"),
		@NamedQuery(name = "Country.findCountryByName", query = "SELECT c FROM Country c where c.name =:name") })

public class Country extends DistinguishableEntity {
	private static final long serialVersionUID = 1705301058L;

	@XmlElement(required = true)
	@Column(unique = true)
	private String name;

	@XmlElement(name = "a2code")
	private String a2Code;

	@XmlElement(name = "a3code")
	private String a3Code;

	@XmlElement(name = "mnacode")
	private String mnaCode;

	@XmlElement(name = "mnagroup")
	private String mnaGroup;

	private Short code;

	private Short since;

	private String note;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the a2Code
	 */
	public String getA2Code() {
		return a2Code;
	}

	/**
	 * @param a2Code
	 *            the a2Code to set
	 */
	public void setA2Code(String a2Code) {
		this.a2Code = a2Code;
	}

	/**
	 * @return the a3Code
	 */
	public String getA3Code() {
		return a3Code;
	}

	/**
	 * @param a3Code
	 *            the a3Code to set
	 */
	public void setA3Code(String a3Code) {
		this.a3Code = a3Code;
	}

	/**
	 * @return the mnaCode
	 */
	public String getMnaCode() {
		return mnaCode;
	}

	/**
	 * @param mnaCode the mnaCode to set
	 */
	public void setMnaCode(String mnaCode) {
		this.mnaCode = mnaCode;
	}

	/**
	 * @return the mnaGroup
	 */
	public String getMnaGroup() {
		return mnaGroup;
	}

	/**
	 * @param mnaGroup the mnaGroup to set
	 */
	public void setMnaGroup(String mnaGroup) {
		this.mnaGroup = mnaGroup;
	}

	/**
	 * @return the code
	 */
	public Short getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Short number) {
		this.code = number;
	}

	/**
	 * @return the since
	 */
	public Short getSince() {
		return since;
	}

	/**
	 * @param since
	 *            the since to set
	 */
	public void setSince(Short since) {
		this.since = since;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
