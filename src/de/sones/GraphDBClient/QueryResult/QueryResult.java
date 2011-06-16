/*
* sones GraphDB Java Client Library 
* Copyright (C) 2007-2011 sones GmbH - http://www.sones.com
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
* 
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
*/
package de.sones.GraphDBClient.QueryResult;

import java.util.List;

import de.sones.GraphDBClient.Objects.Vertex;

public class QueryResult {
	/**
	 * private members
	 */
	private List<Vertex> VertexViews;
	
	private String querystring;
	
	private String querylanguage;
	
	private long duration;
	
	private String errormessage;
	
	private ResultType resulttype;
	
	/**
	 * Contains the result of the query in a parsed form
	 * @param myQueryString
	 * @param myQueryLanguage
	 * @param myResult
	 * @param myDuration
	 * @param myErrorMessage
	 * @param myVertexViewList
	 * @param myCountofVertices
	 */
	public QueryResult(String myQueryString, String myQueryLanguage, ResultType myResult, long myDuration, String myErrorMessage, List<Vertex> myVertexViewList){
		VertexViews = myVertexViewList;
		querystring = myQueryString;
		duration = myDuration;
		errormessage = myErrorMessage;
		resulttype = myResult;
		querylanguage = myQueryLanguage;
		
	}
	

	
	
	public List<Vertex> getVertexViewList() {
		return VertexViews;
	}

	public String getQueryString() {
		return querystring;
	}

	public String getQueryLanguage() {
		return querylanguage;
	}

	public long getDuration() {
		return duration;
	}

	public String getErrorMessage() {
		return errormessage;
	}

	public ResultType getResultType() {
		return resulttype;
	}	
	
}
