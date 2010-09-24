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


public class Edge extends DBObject implements IEdge 
{

	/*
	 * Private members
	 */
	private IVertex _SourceVertex;
	
	private IVertex _TargetVertex;
	
	private Iterable<IVertex> _TargetVertices;
	
	private String _EdgeTypeName;
	
	/*
	 * Constructors
	 */
	
	public Edge()
	{}
	
	public Edge(IVertex mySourceVertex, IVertex myTargetVertex, Map<String, Object> myAttributes)
	{
		_SourceVertex = mySourceVertex;
		_TargetVertex = myTargetVertex;
		
		if(myAttributes != null)
		{
			_Attributes = myAttributes;
		}
	}
	
	public Edge(IVertex mySourceVertex, Iterable<IVertex> myTargetVertices, Map<String, Object> myAttributes)
	{
		_SourceVertex 	= mySourceVertex;
		_TargetVertices = myTargetVertices;
		
		if(myAttributes != null)
		{
			_Attributes = myAttributes;
		}
	}
	
	/*
	 * Getter / Setter
	 */
	
	@Override
	public String getEdgeTypeName() 
	{
		return _EdgeTypeName;
	}

	@Override
	public IVertex getSourceVertex() 
	{
		return _SourceVertex;
	}

	@Override
	public IVertex getTargetVertex() 
	{
		return _TargetVertex;
	}

	@Override
	public Iterable<IVertex> getTargetVertices() 
	{
		return _TargetVertices;
	}
	
	@Override
	public void setEdgeTypeName(String myEdgeTypeName) 
	{
		_EdgeTypeName = myEdgeTypeName;
	}
}
