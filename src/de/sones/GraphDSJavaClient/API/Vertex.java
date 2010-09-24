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


public class Vertex extends DBObject implements IVertex
{
	/*
	 * Constructors
	 */
	
	public Vertex()
	{		
	}
	
	public Vertex(Map<String, Object> myAttributes)
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
