/*
* sones GraphDB(v2) - OpenSource Graph Database - http://www.sones.com
* Copyright (C) 2007-2011 sones GmbH
*
* This file is part of sones GraphDB Community Edition.
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

import java.util.List;

public class SingleEdge implements ISingleEdge {

	private String name;

	private List<Property> propertyList;
	
	private Vertex targetVertex;

	
	
	public SingleEdge(String myName, List<Property> myPropertyList,
			Vertex myTargetVertex) {
		
		this.name = myName;
		this.propertyList = myPropertyList;
		this.targetVertex = myTargetVertex;
	}

	
	public Property getPropertyByID(String myPropertyID) {
		int pos = hasProperty(myPropertyID);
		if(pos != -1){
			return propertyList.get(pos);
		}
		return null;
	}

	
	public int hasProperty(String myPropertyID) {
		for (Property property : propertyList) {
			if(property.getId().equals(myPropertyID)){
				return propertyList.indexOf(property);
			}
		}
		return -1;
	}

	
	public List<Property> getProperties() {
		return propertyList;
	}

	
	public IVertex getTargetVertex() {
		return targetVertex;
	}
		
	public String getName(){
		return name;
	}

}
