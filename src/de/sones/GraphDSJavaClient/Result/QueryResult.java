package de.sones.GraphDSJavaClient.Result;

import java.util.List;

import de.sones.GraphDSJavaClient.API.IVertex;
import de.sones.GraphDSJavaClient.Errors.IError;
import de.sones.GraphDSJavaClient.Errors.IWarning;


public class QueryResult 
{
	
	/**
	 * private members
	 */
	private ResultType _ResultType;
	
	private String _QueryString;
	
	private long _Duration;
	
	private List<IError> _Errors;
	
	private List<IWarning> _Warnings;
	
	private List<IVertex> _Vertices;
	
	/**
	 * getter / setter
	 */
	public ResultType getResultType()
	{
		return _ResultType;
	}
	
	public List<IError> getErrors()
	{
		return _Errors;
	}
	
	public List<IWarning> getWarnings()
	{
		return _Warnings;
	}

	public String getQueryString() 
	{
		return _QueryString;
	}	

	public long getDuration() 
	{
		return _Duration;
	}
	
	public List<IVertex> getVertices()
	{
		return _Vertices;
	}
	
	public void setVertices(List<IVertex> myVertices)
	{
		_Vertices = myVertices;
	}

	/**
	 * Constructors
	 */
	public QueryResult(List<IVertex> myVertices, List<IWarning> myWarnings, List<IError> myErrors, String myQueryString, ResultType myResultType, long myDuration)
	{
		_Vertices 		= myVertices;
		
		_Errors 		= myErrors;
		_Warnings 		= myWarnings;
		
		_QueryString 	= myQueryString;
		_ResultType 	= myResultType;
		_Duration 		= myDuration;	
	}
}
