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


import java.util.List;

public class Vertex {
	

	private List<Property> propertyList;
	
	private List<BinaryProperty> binaryPropertyList;
	
	private List<EdgeTupel> eTupelList;
	
	/**
	 * Constructors
	 */
	public Vertex(List<Property> myPropertyList,List<BinaryProperty> myBinaryPropertyList, List<EdgeTupel> myETupelList){
		propertyList = myPropertyList;
		binaryPropertyList = myBinaryPropertyList;
		eTupelList = myETupelList;
	}
		
	public List<Property> getPropertyList() {
		return propertyList;
	}

	public List<BinaryProperty> getBinaryPropertyList() {
		return binaryPropertyList;
	}

	public List<EdgeTupel> getETupelList() {
		return eTupelList;
	}
	
	public Boolean hasProperties(){
		if(propertyList != null){
			if(!propertyList.isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	public int hasProperty(String myPropertyID){
		for (Property property : propertyList) {
			if(property.getId().equals(myPropertyID)){
				return propertyList.indexOf(property);
			}
		}
		return -1;
	}
	
	public Property getPropertyByID(String myPropertyID){
		int pos = hasProperty(myPropertyID);
		if(pos != -1){
			return propertyList.get(pos);
		}
		return null;
	}
	
	public Boolean hasEdgeTupels(){
		if(eTupelList != null){
			if(!eTupelList.isEmpty()){
				return true;
			}
		}
		return false;
	}

	public int hasEdgeTupel(String myEdgeTupelName){
		for (EdgeTupel edgetupel : eTupelList) {
			if(edgetupel.getName().equals(myEdgeTupelName)){
				return eTupelList.indexOf(edgetupel);
			}
		}
		return -1;
	}

	public EdgeTupel getEdgeTupelByName(String myEdgeTupelName){
		int pos = hasEdgeTupel(myEdgeTupelName);
		if(pos != -1){
			return eTupelList.get(pos);
		}
		return null;
	}

	public Boolean hasBinaryProperties(){
		if(binaryPropertyList != null){
			if(!binaryPropertyList.isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	public int hasBinaryProperty(String myBinaryPropertyID){
		for (BinaryProperty property : binaryPropertyList) {
			if(property.getId().equals(myBinaryPropertyID)){
				return binaryPropertyList.indexOf(property);
			}
		}
		return -1;
	}
	
	public BinaryProperty getBinaryPropertyByID(String myBinaryPropertyID){
		int pos = hasBinaryProperty(myBinaryPropertyID);
		if(pos != -1){
			return binaryPropertyList.get(pos);
		}
		return null;
	}
	
}
