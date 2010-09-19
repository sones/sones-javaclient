package de.sones.GraphDSJavaClient.API;

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
