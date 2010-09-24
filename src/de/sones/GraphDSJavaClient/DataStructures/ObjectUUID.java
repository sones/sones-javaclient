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
package de.sones.GraphDSJavaClient.DataStructures;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ObjectUUID
{
	/*
	 * private members
	 */
	private byte[] _UUID;
	
	/*
	 * Constructors
	 */
	/**
	 * Generates a random UUID and stores it as an UTF-8 byte array.
	 */
	public ObjectUUID()
    {        
        _UUID = java.util.UUID.randomUUID().toString().getBytes(Charset.forName("UTF-8"));        	
    }
	
	/**
	 * Stores the given UUIDString as UTF-8 byte array.
	 * 
	 * @param myUUIDString An UUID as string
	 */
	public ObjectUUID(String myUUIDString)
	{		
		_UUID = myUUIDString.getBytes(Charset.forName("UTF-8"));		
	}
	
	/*
	 * Getter / Setter
	 */
	
	/**
	 * Returns the ObjectUUID represented as a byte array.
	 * 
	 * @return byte[]
	 */	
	public byte[] getByteArray()
	{
		return _UUID;
	}
	
	@Override
	public String toString()
	{		
		try 
		{
			return new String(_UUID, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			return new String(_UUID);
		}
	}
}
