package de.sones.GraphDSClient.Result;

import java.util.HashMap;

public class DBObjectReadout 
{
	private HashMap<String, Object> _Attributes;

	public DBObjectReadout()
	{
		_Attributes = new HashMap<String, Object>();
	}
	
	public DBObjectReadout(HashMap<String, Object> myAttributes)
	{
		_Attributes = myAttributes;
	}
	
	public HashMap<String, Object> getAttributes() 
	{
		return _Attributes;
	}
	
	public Object getAttribute(String myAttribute)
	{
		return _Attributes.get(myAttribute);
	}	
}
