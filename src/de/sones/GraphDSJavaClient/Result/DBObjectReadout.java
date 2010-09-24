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

import java.util.HashMap;

public class DBObjectReadout 
{
	private HashMap<String, Object> _Attributes;

	public DBObjectReadout()
	{
		_Attributes = new HashMap<String, Object>();
	}
	
	public DBObjectReadout(HashMap<String, Object> myAttributes)
	{
		_Attributes = myAttributes;
	}
	
	public HashMap<String, Object> getAttributes() 
	{
		return _Attributes;
	}
	
	public Object getAttribute(String myAttribute)
	{
		return _Attributes.get(myAttribute);
	}	
}
