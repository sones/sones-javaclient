package de.sones.demo;

import java.net.URI;
import java.net.URISyntaxException;

import de.sones.GraphDBRESTClient.GraphDSClient;
import de.sones.GraphDBRESTClient.Result.QueryResult;

public class Demo {
	/**
	 * @param args
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException {	
		GraphDSClient client = new GraphDSClient(new URI("http://localhost:90"),
													"username", 
													"password", 
													GraphDSClient.ACCEPT_TYPE_XML);
		
		QueryResult resultString = client.queryXML("create type user");
		
		System.out.println(resultString);
		
	}

}
