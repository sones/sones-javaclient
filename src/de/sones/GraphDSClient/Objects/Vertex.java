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
import java.util.Collections;
import java.util.List;

public class Vertex implements IVertex {
	

	private List<Property> propertyList;
	
	private List<BinaryProperty> binaryPropertyList;
	
	private List<IEdge> edges;
			
	/**
	 * Constructors
	 */
	public Vertex(List<Property> myPropertyList,List<BinaryProperty> myBinaryPropertyList,
			List<IEdge> myEdges){
		this.propertyList = myPropertyList;
		this.binaryPropertyList = myBinaryPropertyList;
		this.edges = myEdges;
		
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
	
	public Boolean hasEdges(){
		if(edges != null){
			if(!edges.isEmpty()){
				return true;
			}
		}
		return false;
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
	


	
	public Property getPropertyByID(String myPropertyID) {
		int pos = hasProperty(myPropertyID);
		if(pos != -1){
			return propertyList.get(pos);
		}
		return null;
	}

	
	public List<Property> getProperties() {
		return propertyList;
	}

	
	public boolean hasEdge(String myEdgeName) {
		for (IEdge hyperedge : edges) {
			if(hyperedge.getName().equals(myEdgeName)){
				return true;
			}
		}
		return false;
	}

	
	public List<ISingleEdge> getSingleEdges() {
		List<ISingleEdge> payload = new ArrayList<ISingleEdge>(0);
		for (IEdge edge : edges) {
			if(edge instanceof SingleEdge){
				payload.add((ISingleEdge) edge);
			}
		} 
		return payload;
	}

	
	public List<IHyperEdge> getHyperEdges() {
		List<IHyperEdge> payload = new ArrayList<IHyperEdge>(0);
		for (IEdge edge : edges) {
			if(edge instanceof HyperEdge){
				payload.add((IHyperEdge) edge);
			}
		} 
		return payload;
	}

	
	public List<IEdge> getAllEdges() {
		return edges;
	}

	
	public IHyperEdge getHyperEdge(String myHyperEdgeName) {
		List<IHyperEdge> payload = getHyperEdges();
		for (IHyperEdge hyperedge : payload) {
			if(hyperedge.getName().equals(myHyperEdgeName)){
				return hyperedge;
			}
		}
		return null;
	}

	
	public ISingleEdge getSingleEdge(String mySingleEdgeName) {
		List<ISingleEdge> payload = getSingleEdges();
		for (ISingleEdge singleedge : payload) {
			if(singleedge.getName().equals(mySingleEdgeName)){
				return singleedge;
			}
		}
		return null;
	}

	
	public List<IVertex> getAllNeighbours() {
		List<IVertex> payload = new ArrayList<IVertex>(0);
		for (IEdge edge : edges) {
			if(edge instanceof SingleEdge){
				payload.add(((SingleEdge) edge).getTargetVertex());
			}
			else if(edge instanceof HyperEdge){
				Collections.copy(payload, ((HyperEdge) edge).getTargetVertices());
			}
		}
		return payload;
	}

	public BinaryProperty getBinaryPropertyByID(String myBinaryPropertyID) {
		int pos = hasBinaryProperty(myBinaryPropertyID);
		if(pos != -1){
			return binaryPropertyList.get(pos);
		}
		return null;
	}


	public List<BinaryProperty> getBinaryProperties() {
		return binaryPropertyList;
	}


	@Override
	public List<IVertex> getNeighboursByEdge(String myEdgeName) {
		List<IVertex> payload = new ArrayList<IVertex>(0);
		for (IEdge edge : edges) {
			if(edge instanceof SingleEdge){
				if(edge.getName().equals(myEdgeName)){
				payload.add(((SingleEdge) edge).getTargetVertex());	
				}
			}
			else if(edge instanceof HyperEdge){
				if(edge.getName().equals(myEdgeName)){
				Collections.copy(payload, ((HyperEdge) edge).getTargetVertices());	
				}
			}
		}
		return payload;
	}

	
	
}
