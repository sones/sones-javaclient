/*
* sones GraphDB(v2) - OpenSource Graph Database - http://www.sones.com
* Copyright (C) 2007-2011 sones GmbH
*
* This file is part of sones GraphDB OpenSource Edition.
*
* sones GraphDB OSE is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as published by
* the Free Software Foundation, version 3 of the License.
*
* sones GraphDB OSE is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public License
* along with sones GraphDB OSE. If not, see <http://www.gnu.org/licenses/>.
*/
package de.sones.GraphDSClient.Objects;

public class Property {
	private String id;
	
	private String type;
	
<<<<<<< HEAD
	private Object value;
	
	
=======
	private String value;
	
	public String getId() {
		return id;
	}

>>>>>>> f41eead1e4f4cc13df658117099c47b51186a6eb
	
	
	/**
	 * Constructors
	 */
<<<<<<< HEAD
	public Property(String myID, String myType, Object myValue){
=======
	public Property(String myID, String myType, String myValue){
>>>>>>> f41eead1e4f4cc13df658117099c47b51186a6eb
		id = myID;
		type = myType;
		value = myValue;
	}
	
	public String getType() {
		return type;
	}

<<<<<<< HEAD
	public Object getValue() {
		return value;
	}
	
	public String getId() {
		return id;
	}

=======
	public String getValue() {
		return value;
	}
>>>>>>> f41eead1e4f4cc13df658117099c47b51186a6eb
}
