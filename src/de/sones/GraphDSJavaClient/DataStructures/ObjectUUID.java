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
