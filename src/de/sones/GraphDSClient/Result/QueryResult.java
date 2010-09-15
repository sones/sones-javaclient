package de.sones.GraphDSClient.Result;

import java.util.List;

import de.sones.GraphDSClient.Errors.IError;
import de.sones.GraphDSClient.Errors.IWarning;


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

	public String getQueryString() {
		return _QueryString;
	}	

	public long getDuration() {
		return _Duration;
	}

	/**
	 * Constructors
	 */
	public QueryResult(List<IError> myErrors, List<IWarning> myWarnings, String myQueryString, ResultType myResultType, long myDuration)
	{
		_Errors = myErrors;
		_Warnings = myWarnings;
		
		_QueryString = myQueryString;
		_ResultType = myResultType;
		_Duration = myDuration;	
	}
}
