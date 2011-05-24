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


public interface IVertex extends IGraphElement {
	
	boolean hasEdge(String myEdgePropertyName);
	
	List<?> getSingleEdges();
	
	List<?> getHyperEdges();
	
	List<?> getAllEdges();
	
		
	IHyperEdge getHyperEdge(String myHyperEdgeName);
	
	ISingleEdge getSingleEdge(String mySingleEdgeName);
	
	List<IVertex> getAllNeighbours();
	
	List<IVertex> getNeighboursByEdge(String myEdgeName);
	
	BinaryProperty getBinaryPropertyByID(String myBinaryPropertyID);
	
	List<BinaryProperty> getBinaryProperties();
	

}
