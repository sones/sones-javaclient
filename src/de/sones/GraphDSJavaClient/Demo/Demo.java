package de.sones.GraphDSJavaClient.Demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import de.sones.GraphDSJavaClient.GraphDSJavaClient;
import de.sones.GraphDSJavaClient.API.Edge;
import de.sones.GraphDSJavaClient.API.IVertex;
import de.sones.GraphDSJavaClient.API.VertexGroup;
import de.sones.GraphDSJavaClient.API.VertexWeightedEdges;
import de.sones.GraphDSJavaClient.DataStructures.ObjectUUID;
import de.sones.GraphDSJavaClient.Errors.*;
import de.sones.GraphDSJavaClient.Result.QueryResult;

public class Demo {
	
	public static void generateDBTestBasis(GraphDSJavaClient myClient)
    {
		
		/**
		 * 1) Create a Vertex Type "Car" which has the following attributes:
		 * 		- Color of basic-type String
		 * 		- horse power (HP) of basic-type Integer
		 * 		- a weight of basic-type Integer
		 * 
		 * 2) Create a Vertex Type "User" which has the following attributes:
		 * 		- an UserID of basic-type Integer
		 * 		- an Edge to another User (reference) which is his best friend
		 * 		- the user's name of basic-type String
		 * 		- the user's age of basic-type Integer
		 * 		- a list of favourite numbers (oO) the list items are of basic-type Double
		 * 		
		 */
		myClient.queryXML("CREATE VERTEX Car ATTRIBUTES (String Color, Integer HP, Integer Weight)");
		myClient.queryXML("CREATE VERTEX User EXTENDS DBObject ATTRIBUTES (Integer UserID, User BestFriend, String Name, Integer Age, LIST<Integer> FavouriteNumbers, SET<User> Friends, SET<User> Enemies, Car Car) INDICES (Age)");
		
		/**
		 * 3) Add some backwardedges to the defnied vertex-types
		 * 		- "IsCarOf" itentifies a user's car
		 * 		- "IsFriendOf" identifies users who have this user as friend
		 */
		myClient.queryXML("ALTER VERTEX Car ADD BACKWARDEDGES (User.Car IsCarOf)");
		myClient.queryXML("ALTER VERTEX User ADD BACKWARDEDGES (User.Friends IsFriendOf)");        

		/**
		 * 4) Add some car data. 
		 */		
		myClient.queryXML("INSERT INTO Car VALUES (Color = 'red', HP = 75, Weight = 1000)");
		myClient.queryXML("INSERT INTO Car VALUES (Color = 'white', HP = 120, Weight = 1400)");
		myClient.queryXML("INSERT INTO Car VALUES (Color = 'black', HP = 300, Weight = 1500)");

		/**
		 * 5) Add some users
		 * 		- we use some futurama chars as an descriptive example
		 */
        myClient.queryXML("INSERT INTO User VALUES (Name = 'Fry', UserID = 12, Age = 22, Car = REF ( Color = 'red' ))");
        myClient.queryXML("INSERT INTO User VALUES (Name = 'Lila', UserID = 13, Age = 22, Car = REF ( HP = 120 ))");
        myClient.queryXML("INSERT INTO User VALUES (Name = 'Bender', UserID = 14, Age = 300, Car = REF ( Weight = 1500 ))");
        myClient.queryXML("INSERT INTO User VALUES (Name = 'Farnsworth', UserID = 15, Age = 129)");
        myClient.queryXML("INSERT INTO User VALUES (Name = 'Amy', UserID = 18, Age = 17)");
        myClient.queryXML("INSERT INTO User VALUES (Name = 'Hermes', UserID = 16)");
        myClient.queryXML("INSERT INTO User VALUES (Name = 'Zoidberg', UserID = 17)");

        /**
         * 6) Create the relations between the futurama chars
         */
        myClient.queryXML("UPDATE User SET (Friends = SETOF (UserID = 13, UserID = 14, UserID = 15)) WHERE UserID = 12");
        myClient.queryXML("UPDATE User SET (Friends = SETOF (UserID = 12, UserID = 14), Enemies = SETOF (UserID = 18)) WHERE UserID = 13");
        myClient.queryXML("UPDATE User SET (Friends = SETOF (UserID = 12)) WHERE UserID = 14");
        myClient.queryXML("UPDATE User SET (Friends = SETOF (UserID = 16)) WHERE UserID = 15");
        myClient.queryXML("UPDATE User SET (Friends = SETOF (UserID = 15)) WHERE UserID = 16");
        myClient.queryXML("UPDATE User SET (Friends = SETOF (UserID = 12, UserID = 13, UserID = 14, UserID = 15, UserID = 16)) WHERE UserID = 17");
        
        /**
         * 7) Create a weighted graph of vertex type "Tag"
         */
        myClient.queryXML("CREATE VERTEX Tag ATTRIBUTES (String Name, Integer Hits, SET<WEIGHTED(Double, DEFAULT=1, SORTED=DESC)<Tag>> RelatedTags, LIST<String> Urls)");

        /**
         * 8) Create an index on the "Name"-attribute
         */
        myClient.queryXML("CREATE INDEX IDX_Tag ON TYPE Tag ( Name )");

        /**
         * 9) Insert some weighted data
         */
        myClient.queryXML("INSERT INTO Tag VALUES (Name = 'Summer')");
        myClient.queryXML("INSERT INTO Tag VALUES (Name = 'Sun', RelatedTags = SETOF(Name = 'Summer':(0.5)))");
        myClient.queryXML("INSERT INTO Tag VALUES (Name = 'Water')");
        myClient.queryXML("INSERT INTO Tag VALUES (Name = 'Diving')");
        myClient.queryXML("INSERT INTO Tag VALUES (Name = 'Relaxing')");
    }	
	
	/**
	 * @param args
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException 
	{	
		/**
		 * init
		 */
		GraphDSJavaClient client = new GraphDSJavaClient(new URI("http://localhost:90"),
													"username", 
													"password", 
													GraphDSJavaClient.ACCEPT_TYPE_XML);
		
		//generateDBTestBasis(client);
		
		QueryResult result = null;
						
		/**
		 * 1) Select user id's and user-data (default depth 0)
		 */
		result = client.queryXML(
				"FROM User SELECT UUID, *");
		printQueryResult(result);
		
		/**
		 * 2) Select from a weighted graph
		 */
		result = client.queryXML(
				"FROM Tag SELECT * where Name = 'Sun'");
		printQueryResult(result);
		
		/**
		 * 3) Try a group statement
		 */
		result = client.queryXML(
				"FROM User u SELECT u.Age group by u.Age");
		printQueryResult(result);
	}
	
	public static void printQueryResult(QueryResult myResult)
	{
		//gql string
		System.out.println("QueryString:\t" + myResult.getQueryString());
		//result type
		System.out.println("ResultType:\t" + myResult.getResultType());
		//duration in ms
		System.out.println("Duration:\t" + myResult.getDuration());
		//warnings
		System.out.println("Warnings:");		
		for (IWarning warning : myResult.getWarnings()) 
		{
			System.out.println(String.format("\t%s : %s", new Object[] {warning.getID(), warning.getMessage()}));
		}
		//errors
		System.out.println("Errors:");
		for (IError error : myResult.getErrors()) 
		{
			System.out.println(String.format("\t%s : %s", new Object[] {error.getID(), error.getMessage()}));		
		}
		//vertices
		
		System.out.println("Vertices");
		for(IVertex vertex : myResult.getVertices())
		{
			showVertex(vertex, 1);	
		}			
	}
	
	private static void showVertex(IVertex myVertex, int depth)
	{
		String tabs = "";
		for(int i = 0; i < depth; i++) tabs += "\t";
		
		Edge tmpEdge;
		ObjectUUID tmpUUID;
		for(Map.Entry<String, Object> entry : myVertex.getAttributes().entrySet())									
		{
			if(entry.getValue() instanceof Edge)
			{
				tmpEdge = (Edge) entry.getValue();				
				System.out.println(tabs + entry.getKey() + " :");
				for(IVertex targetVertex : tmpEdge.getTargetVertices())
				{
					showVertex(targetVertex, depth + 1);
				}
			}
			else if(entry.getValue() instanceof ObjectUUID)
			{
				tmpUUID = (ObjectUUID) entry.getValue();				
				System.out.println(tabs + entry.getKey() + " : " + tmpUUID.toString());
			}
			else
			{				
				System.out.println(tabs + entry.getKey() + " : " + entry.getValue());
			}
		}
		
		if(myVertex instanceof VertexWeightedEdges)
		{			
			System.out.println(tabs + "VertexWeightedEdges:");
			System.out.println(tabs + "Weight : " + ((VertexWeightedEdges) myVertex).getWeight());
			System.out.println(tabs + "TypeName : " + ((VertexWeightedEdges) myVertex).getTypeName());					
		}
		
		if(myVertex instanceof VertexGroup)
		{						
			int count = 0;
			
			for(IVertex groupedVertex : ((VertexGroup) myVertex).getGroupedVertices())
			{
				count++;
			}
			
			System.out.println(tabs + "VertexGroup including " +  count + " vertices");
		}
	}
}
