sones GraphDSJavaClient

Simple java client library to talk with a sones GraphDB - enterprise graph database management system.

Documentation:

For API Documentation see Javadoc in doc folder.
For GQL Syntax see documentation at sones Developer-Wiki (http://developers.sones.de/)

History:

2010-09-24 (initial release) by s1ck (Martin Junghanns)

	Features
		- simple connection to a graphDB REST service based on given URI and credentials
		- possibility to easily send GQL-Queries to the service
		- parsing methods to create a QueryResult out of the XML-Response (using JDOM (rocks))
		- API to handle vertices, edges and some result meta data
		- some demo-queries to see how it works atm
		
	Missing functionality
		General
			- BasicType UInt64 not supported, appropriate values are parsed as Int64 (Long)

		IVertex
			- anonymous functions
			- traverse methods
			- link/unlink
		
		GraphDSClient
			- QueryResultActions
			
		ObjectRevisionID
			- The pattern doesn't exactly parse the timestamp (GraphDB uses C# DateTime.ParseExact()-Method with 7 most significant digits of seconds fraction)

