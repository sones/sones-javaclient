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
package de.sones.GraphDSJavaClient.DataStructures;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * An ObjectRevisionID is the identifier for an object revision. 
 */
public class ObjectRevisionID 
{
	/*
	 * private members
	 */
	
	private long _TimeStamp;
	
	private ObjectUUID _ObjectUUID;	
	
	/**
	 * TODO: find a way to correctly parse the digits of the fraction part
	 */
	private static DateFormat _Formatter = new SimpleDateFormat("yyyyddMM.HHmmss.SSSSSSS", Locale.GERMANY);
	
	/*
	 * constructors
	 */
	
	public ObjectRevisionID(String myObjectRevisionID)
	{
		String revisionID_Timestamp = myObjectRevisionID.substring(0, myObjectRevisionID.indexOf('('));
		String revisionID_UUID 		= myObjectRevisionID.substring(
				revisionID_Timestamp.length() + 1,
				myObjectRevisionID.length() - 1);				
		
		try 
		{			
			_TimeStamp = _Formatter.parse(revisionID_Timestamp).getTime();
		} catch (ParseException e) {
			
		}         
		_ObjectUUID = new ObjectUUID(revisionID_UUID);		
	}

	/*
	 * getter / setter
	 */
	
	public long getTimeStamp() 
	{
		return _TimeStamp;
	}	

	public ObjectUUID getObjectUUID() 
	{
		return _ObjectUUID;
	}	
	
	@Override
	public String toString()
    {		
		return String.format("%s(%s)", _Formatter.format(new Date(_TimeStamp)), _ObjectUUID.toString());        
    }
}
