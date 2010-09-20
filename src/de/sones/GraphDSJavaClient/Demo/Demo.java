package de.sones.GraphDSJavaClient.Demo;

import java.net.URI;
import java.net.URISyntaxException;

import de.sones.GraphDSJavaClient.GraphDSJavaClient;
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
//				"CREATE TYPE User ATTRIBUTES ( String Name, Integer Age, DateTime creationDate, LIST<Double> FavouriteNumbers, SET<User> Friends )");		
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
//				"INSERT INTO User VALUES (Name='Bob', Age=42, creationDate='31/12/2009 10:05', FavouriteNumbers = LISTOF(1, 2.7182818, 3, 4))");
//		printQueryResult(result);
		
		/**
		 * 3) Select something
		 */
		result = client.queryXML(
				"FROM User SELECT *");
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
	}
}
