GraphDSJavaClient

Missing functionality:

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