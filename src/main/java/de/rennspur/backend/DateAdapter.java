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
 *  @author leon.schlender
 */
//Credits to Blaise Doughan from https://stackoverflow.com/questions/2519432/jaxb-unmarshal-timestamp

package de.rennspur.backend;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *  Formats the given date into a date/time string
     *  @param Date 
     *  @return the given date as String
     */
    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }

    /**
     * Parses the given Date as new Date
     * @param String
     * @return the given Date-String as Date Object
     */
    @Override
    public Date unmarshal(String v) throws Exception {
        return new Date(Long.parseLong(v));
    }

}