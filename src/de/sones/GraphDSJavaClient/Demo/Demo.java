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
		GraphDSJavaClient client = new GraphDSJavaClient(new URI("http://localhost:90"),
													"username", 
													"password", 
													GraphDSJavaClient.ACCEPT_TYPE_XML);
		
		QueryResult result = client.queryXML("create type user3");
		
		//gql string
		System.out.println("QueryString:\t" + result.getQueryString());
		//result type
		System.out.println("ResultType:\t" + result.getResultType());
		//duration in ms
		System.out.println("Duration:\t" + result.getDuration());
		//warnings
		System.out.println("Warnings:");		
		for (IWarning warning : result.getWarnings()) 
		{
			System.out.println(String.format("\t%s : %s", new Object[] {warning.getID(), warning.getMessage()}));
		}
		//errors
		System.out.println("Errors:");
		for (IError error : result.getErrors()) 
		{
			System.out.println(String.format("\t%s : %s", new Object[] {error.getID(), error.getMessage()}));		
		}
	}
}
