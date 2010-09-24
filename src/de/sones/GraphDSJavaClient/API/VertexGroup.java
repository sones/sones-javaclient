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
