package de.sones.GraphDSJavaClient.Demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import sun.awt.VerticalBagLayout;

import de.sones.GraphDSJavaClient.GraphDSJavaClient;
import de.sones.GraphDSJavaClient.API.Edge;
import de.sones.GraphDSJavaClient.API.IVertex;
import de.sones.GraphDSJavaClient.DataStructures.ObjectUUID;
import de.sones.GraphDSJavaClient.Errors.*;
import de.sones.GraphDSJavaClient.Result.QueryResult;

public class Demo {
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
		
		QueryResult result = null;
		
		/**
		 * demo
		 */
		
		/**
		 * 1) Create a type user with the following attributes:
		 * 
		 * - the user's name (String)
		 * - the age of the user (Integer)
		 * - the creation date (Date)
		 * - a list of it's favourite double numbers (oO) (List<Double>)
		 * - it's friends who are also users (List<User>)
		 */
//		result = client.queryXML(
//				"CREATE VERTEX User ATTRIBUTES ( String Name, Integer Age, DateTime creationDate, LIST<Double> FavouriteNumbers, User BestFriend )");		
//		printQueryResult(result);
//		
//		/**
//		 * 2) Insert some users.
//		 */
//		result = client.queryXML(
//				"INSERT INTO User VALUES (Name='Alice', Age=23, creationDate='31/12/2009 10:05', FavouriteNumbers = LISTOF(1, 2, 3.141592, 4))");
//		printQueryResult(result);
//		
//		result = client.queryXML(
//				"INSERT INTO User VALUES (Name='Bob', Age=42, creationDate='31/12/2009 10:05', FavouriteNumbers = LISTOF(1, 2.7182818, 3, 4), BestFriend = REF(Name = 'Alice'))");
//		printQueryResult(result);
		
		/**
		 * 3) Select something
		 */
		result = client.queryXML(
				"FROM User SELECT * DEPTH 1");
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
		Edge tmpEdge;
		ObjectUUID tmpUUID;
		for(Map.Entry<String, Object> entry : myVertex.getAttributes().entrySet())									
		{
			if(entry.getValue() instanceof Edge)
			{
				tmpEdge = (Edge) entry.getValue();
				for(int i = 0; i < depth; i++) System.out.print("\t");
				System.out.println(entry.getKey() + " :");
				for(IVertex targetVertex : tmpEdge.getTargetVertices())
				{
					showVertex(targetVertex, depth + 1);
				}
			}
			else if(entry.getValue() instanceof ObjectUUID)
			{
				tmpUUID = (ObjectUUID) entry.getValue();
				for(int i = 0; i < depth; i++) System.out.print("\t");
				System.out.println(entry.getKey() + " : " + tmpUUID.toString());
			}
			else
			{
				for(int i = 0; i < depth; i++) System.out.print("\t");
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
		}
	}
}
