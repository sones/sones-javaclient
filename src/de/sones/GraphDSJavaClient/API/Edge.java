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
