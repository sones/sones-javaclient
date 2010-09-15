package de.sones.GraphDBRESTClient.Result;

import java.util.List;

import de.sones.GraphDBRESTClient.Errors.IError;


public class QueryResult 
{
	
	/**
	 * private members
	 */
	private ResultType _ResultType;
	
	private List<IError> _Errors;
	
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
	
}
