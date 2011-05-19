/*
* sones GraphDB(v2) - OpenSource Graph Database - http://www.sones.com
* Copyright (C) 2007-2011 sones GmbH
*
* This file is part of sones GraphDB Community Edition.
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
package de.sones.GraphDSClient.QueryResult;

import java.util.List;

import de.sones.GraphDSClient.Objects.Vertex;

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
