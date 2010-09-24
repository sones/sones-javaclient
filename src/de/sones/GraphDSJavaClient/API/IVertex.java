/*
* sones GraphDB - OpenSource Graph Database - http://www.sones.com
* Copyright (C) 2007-2010 sones GmbH
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
package de.sones.GraphDSJavaClient.API;

import java.util.Map;

import de.sones.GraphDSJavaClient.DataStructures.ObjectRevisionID;
import de.sones.GraphDSJavaClient.DataStructures.ObjectUUID;

public interface IVertex 
{
	/*
	 * getter
	 */
	
	ObjectUUID 			getUUID();
	String 				getTYPE();
	String 				getEDITION();
	ObjectRevisionID 	getREVISIONID();
	String 				getComment();
	
	Map<String, Object> getAttributes();
	
	/*
	 * setter
	 */
	
	void setUUID(ObjectUUID myObjectUUID);
	void setTYPE(String myType);
	void setEDITION(String myEdition);
	void setREVISIONID(ObjectRevisionID myObjectRevisionID);
	void setComment(String myComment);
	
	/*
	 * attributes
	 */
	
	boolean hasAttribute(String myAttributeName);
	
	long getAttributeCount();
	
	/*
	 * properties
	 */
	
	boolean hasProperty(String myPropertyName);
	
	Object getProperty(String myPropertyName);	
	
	/*
	 * edges
	 */
	
	boolean hasEdge(String myEdgeName);
	
	IEdge 			getEdge(String myEdgeName);		
	
	/*
	 * neighbours
	 */
	
	IVertex 			getNeighbour(String myEdgeName);
	Iterable<IVertex> 	getNeighbours(String myEdgeName);
}
