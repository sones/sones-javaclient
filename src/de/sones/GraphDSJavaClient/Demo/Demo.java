package de.sones.GraphDSJavaClient.Demo;

import java.net.URI;
import java.net.URISyntaxException;

import de.sones.GraphDSJavaClient.GraphDSJavaClient;
import de.sones.GraphDSJavaClient.Result.QueryResult;

public class Demo {
	/**
	 * @param args
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException {	
		GraphDSJavaClient client = new GraphDSJavaClient(new URI("http://localhost:90"),
													"username", 
													"password", 
													GraphDSJavaClient.ACCEPT_TYPE_XML);
		
		QueryResult result = client.queryXML("create type user2");
		
		
		System.out.println("QueryString:\t" + result.getQueryString());
		System.out.println("ResultType:\t" + result.getResultType());
		System.out.println("Duration:\t" + result.getDuration());
	}
}
