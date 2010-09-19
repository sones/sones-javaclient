package de.sones.GraphDSJavaClient.API;

import java.util.HashMap;

import de.sones.GraphDSJavaClient.DataStructures.ObjectUUID;

public class DBObject 
{
	/*
	 * private members
	 */
	
	/**
	 * holds all attributes of the DBObject
	 */
	private HashMap<String, Object> _Attributes;
	
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
		
		if((tmp = _Attributes.get("UUID")) != null)
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
