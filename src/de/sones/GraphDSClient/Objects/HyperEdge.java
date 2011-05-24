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


import java.util.ArrayList;
import java.util.List;


public class HyperEdge implements IHyperEdge {

	private String name;
	
	private List<Property> propertyList;
	
	private List<BinaryProperty> binaryPropertyList;
	
	private List<ISingleEdge> containedSingleEdges;
		
	
	public HyperEdge(String myName,List<Property> myProperties, List<ISingleEdge> mySingleVertices) {
		this.name = myName;
		this.propertyList = myProperties;
		this.containedSingleEdges = mySingleVertices;
	}
	
	public List<IVertex> getTargetVertices() {
		List<IVertex> payload = new ArrayList<IVertex>(containedSingleEdges.size()); 
		for (ISingleEdge singleEdge : containedSingleEdges) {
			payload.add(singleEdge.getTargetVertex());
		}
		return payload;
	}
	
	public Property getPropertyByID(String myPropertyID) {
		int pos = hasProperty(myPropertyID);
		if(pos != -1){
			return propertyList.get(pos);
		}
		return null;
	}

	public int hasProperty(String myBinaryPropertyID) {
		for (BinaryProperty property :  binaryPropertyList) {
			if(property.getId().equals(myBinaryPropertyID)){
				return binaryPropertyList.indexOf(property);
			}
		}
		return -1;
	}

	public List<Property> getProperties() {
		return propertyList;
	}
	
	public List<ISingleEdge> getEdges() {
		return containedSingleEdges;
	}
	
	public String getName(){
		return name;
	}

	

}
