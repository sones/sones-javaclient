package de.sones.GraphDSJavaClient.API;

import java.util.HashMap;
import java.util.Map;

import de.sones.GraphDSJavaClient.DataStructures.ObjectRevisionID;
import de.sones.GraphDSJavaClient.DataStructures.ObjectUUID;

public class DBObject 
{
	/*
	 * private members
	 */
	
	/**
	 * holds all attributes of the DBObject
	 */
	protected Map<String, Object> _Attributes;
	
	/*
	 * Constructors
	 */
	
	/**
	 * default constructor for DBObject
	 */
	public DBObject()
	{
		_Attributes = new HashMap<String, Object>();
	}
	
	/*
	 * getter / setter
	 */
		
	/**
	 * Returns the ObjectUUID for that DBObject.
	 * 
	 * @return ObjectUUID or null if no uuid is assigned.
	 */
	public ObjectUUID getUUID()
	{
		Object tmp = null;
		
		if(_Attributes.get("UUID") instanceof ObjectUUID)
		{			
			return (ObjectUUID) tmp;
		}
		
		return null;
	}
	
	/**
	 * Sets the UUID of the DBObject. Existing UUID will be replaced.
	 * 
	 * @param myObjectUUID
	 */
	public void setUUID(ObjectUUID myObjectUUID)
	{
		_Attributes.put("UUID", myObjectUUID);		
	}
	
	/**
	 * Returns the TYPE for that DBObject.
	 * 
	 * @return The Object TYPE or null if no type is assigned.
	 */
	public String getTYPE()
	{
		Object tmp = null;
		
		if(_Attributes.get("TYPE") instanceof String)
		{			
			return (String) tmp;
		}
		
		return null;
	}
	
	/**
	 * Sets the TYPE of the DBObject. Existing TYPE will be replaced.
	 * 
	 * @param myType the type to be set
	 */
	public void setTYPE(String myType)
	{
		_Attributes.put("TYPE", myType);
	}
	
	/**
	 * Returns the EDITION for that DBObject.
	 * 
	 * @return The Object EDITION or null if no type is assigned.
	 */
	public String getEDITION()
	{
		Object tmp = null;
		
		if(_Attributes.get("EDITION") instanceof String)
		{			
			return (String) tmp;
		}
		
		return null;
	}
	
	/**
	 * Sets the EDITION of the DBObject. Existing EDITION will be replaced.
	 * 
	 * @param myEdition the edition to be set
	 */
	public void setEDITION(String myEdition)
	{
		_Attributes.put("EDITION", myEdition);
	}
	
	/**
	 * Returns the REVISION for that DBObject.
	 * 
	 * @return The Object REVISION or null if no revision is assigned.
	 */
	public ObjectRevisionID getREVISIONID()
	{
		Object tmp = null;
		
		if(_Attributes.get("REVISION") instanceof ObjectRevisionID)
		{			
			return (ObjectRevisionID) tmp;
		}
		
		return null;
	}
		
	/**
	 * Sets the REVISION of the DBObject. Existing REVISION will be replaced.
	 * 
	 * @param myObjectRevisionID the revision to be set
	 */
	public void setREVISIONID(ObjectRevisionID myObjectRevisionID)
	{
		_Attributes.put("REVISION", myObjectRevisionID);
	}
	
	/**
	 * Returns the Comment for that DBObject.
	 * 
	 * @return Comment or null if no comment is assigned.
	 */
	public String getComment()
	{
		Object tmp = null;
		
		if(_Attributes.get("COMMENT") instanceof String)
		{			
			return (String) tmp;
		}
		
		return null;
	}
	
	/**
	 * Sets the Comment of the DBObject. Existing Comment will be replaced.
	 * 
	 * @param myComment the comment to be set
	 */
	public void setComment(String myComment)
	{
		_Attributes.put("COMMENT", myComment);
	}
	
	/*
	 * Attributes
	 */
	
	/**
	 * Returns the number of attributes assigned to that object.
	 * 
	 * @return number of attributes
	 */
	public long getAttributeCount() 
	{
		return _Attributes.size();
	}
	
	/**
	 * Checks if attribute is existing.
	 * 
	 * @param myAttributeName the attribute to be checked
	 * 
	 * @return <tt>true</tt> if the attribute exists else <tt>false</tt>
	 */
	public boolean hasAttribute(String myAttributeName)
	{		
		return _Attributes.containsKey(myAttributeName);
	}
	
	/**
	 * Adds new attributes or replaces existing ones.
	 * 
	 * @param myAttributeName the name of the attribute
	 * @param myObject the assigned attribute value
	 */
	public void addOrUpdateAttribute(String myAttributeName, Object myObject)
	{
		_Attributes.put(myAttributeName, myObject);
	}
	
	/**
	 * Removes existing attribute.
	 * 
	 * @param myAttributeName
	 * @return <tt>true</tt> if the attribute existed, else <tt>false</tt>
	 */
	public boolean removeAttribute(String myAttributeName)
	{
		return _Attributes.remove(myAttributeName) != null;
	}
}
