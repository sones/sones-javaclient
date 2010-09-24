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
