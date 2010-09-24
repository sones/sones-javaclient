package de.sones.GraphDSJavaClient.API;

import java.util.Map;

public class VertexWeightedEdges extends Vertex 
{
	/*
	 * private members
	 */
	
	private Object _Weight;
	
	private String _TypeName;
	
	/*
	 * constructors
	 */
	
	public VertexWeightedEdges(Map<String, Object> myAttributes, Object myWeight, String myTypeName) 
	{
		super(myAttributes);
		
		_Weight 	= myWeight;
		_TypeName 	= myTypeName;
	}

	/*
	 * getter / setter
	 */
	
	public Object getWeight() 
	{
		return _Weight;
	}

	public void setWeight(Object _Weight) 
	{
		this._Weight = _Weight;
	}

	public String getTypeName() 
	{
		return _TypeName;
	}

	public void setTypeName(String _TypeName) 
	{
		this._TypeName = _TypeName;
	}	
}

