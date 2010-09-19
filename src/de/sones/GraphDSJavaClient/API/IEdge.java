package de.sones.GraphDSJavaClient.API;

import de.sones.GraphDSJavaClient.DataStructures.ObjectRevisionID;
import de.sones.GraphDSJavaClient.DataStructures.ObjectUUID;

public interface IEdge 
{
	/*
	 * getter
	 */
	
	ObjectUUID 			getUUID();
	String 				getTYPE();
	String 				getEDITION();
	ObjectRevisionID 	getREVISIONID();
	String 				getComment();
	String				getEdgeTypeName();
	
	IVertex				getSourceVertex();
	IVertex				getTargetVertex();
	Iterable<IVertex>	getTargetVertices();
	
	/*
	 * setter
	 */
	
	void setUUID(ObjectUUID myObjectUUID);
	void setTYPE(String myType);
	void setEDITION(String myEdition);
	void setREVISTIONID(ObjectRevisionID myObjectRevisionID);
	void setComment(String myComment);
	void setEdgeTypeName(String myEdgeTypeName);
	
	
}
