package de.sones.GraphDSJavaClient.API;

import java.util.HashMap;


public class Vertex extends DBObject implements IVertex
{
	/*
	 * Constructors
	 */
	
	public Vertex()
	{		
	}
	
	public Vertex(HashMap<String, Object> myAttributes)
	{
		if(myAttributes != null)
		{
			_Attributes = myAttributes;
		}
	}

	/*
	 * Properties	
	 */
	
	@Override
	public boolean hasProperty(String myPropertyName) 
	{
		Object property = getProperty(myPropertyName);
		
		if(property == null)
		{
			return false;
		}
		//this is bad..
		if(property instanceof Vertex)
		{
			return false;
		}
		//..and this one too
		if(property instanceof Iterable)
		{
			return false;
		}
		
		return true;
	}

	@Override
	public Object getProperty(String myPropertyName) 
	{			
		return _Attributes.get(myPropertyName);
	}

	/*
	 * Edges 
	 */	
	
	@Override
	public boolean hasEdge(String myEdgeName) 
	{
		return getEdge(myEdgeName) != null;
	}

	@Override
	public IEdge getEdge(String myEdgeName) 
	{
		return (Edge) getProperty(myEdgeName);
	}	

	/*
	 * Neighbours
	 */
	
	@Override
	public IVertex getNeighbour(String myEdgeName) 
	{		
		Iterable<IVertex> targetVertices = getNeighbours(myEdgeName);
		
		if(targetVertices != null)
		{
			for (IVertex iVertex : targetVertices) 
			{
				return iVertex; //first
			}
		}
        
        return null;
	}

	@Override
	public Iterable<IVertex> getNeighbours(String myEdgeName) 
	{
		Object tmp = null;

        if((tmp = _Attributes.get(myEdgeName)) instanceof Edge)
        {
        	return ((Edge) tmp).getTargetVertices();        	    		 
        }
        return null;
	}
	

	
}
