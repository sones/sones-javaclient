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
	 * Returns all attributes of that DBObject
	 * 
	 * @return a map <String, Object> of all attributes
	 */
	public Map<String, Object> getAttributes()
	{
		return _Attributes;
	}
	
	
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
