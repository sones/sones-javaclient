package de.sones.GraphDSJavaClient.API;

import java.util.Map;

public class VertexGroup extends Vertex
{
	/*
	 * private members
	 */
	
	private Iterable<IVertex> _GroupedVertices;
	
	/*
	 * constructors
	 */
	
	public VertexGroup(Map<String, Object> myAttributes, Iterable<IVertex> myGroupedVertices)
	{
		super(myAttributes);
		
		_GroupedVertices = myGroupedVertices;
	}	
	
	/*
	 * getter / setter
	 */
	
	public Iterable<IVertex> getGroupedVertices()
	{
		return _GroupedVertices;
	}
	
	protected void setGroupedVertices(Iterable<IVertex> myGroupedVertices)
	{
		_GroupedVertices = myGroupedVertices;		
	}
}
