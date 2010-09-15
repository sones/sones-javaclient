package de.sones.GraphDBRESTClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import sun.misc.BASE64Encoder;

import com.sun.xml.internal.ws.util.ASCIIUtility;

import de.sones.GraphDBRESTClient.Result.QueryResult;

public class GraphDSClient 
{
	private String _RestURL = null;
	
	private String _UserName = null;
	
	private String _Password = null;
	
	private int _AcceptType = 0;

	public static final int ACCEPT_TYPE_XML = 0;
	
	public static final int ACCEPT_TYPE_JSON = 1;
	
	public static final int ACCEPT_TYPE_TEXT = 2;
	
	public GraphDSClient(URI myUri, String myUserName, String myPassword, int myDocumentType)
	{
		_RestURL = myUri.toString() + "/gql?";
		
		_UserName = myUserName;
		_Password = myPassword;		
		
		//handle argument test
		_AcceptType = myDocumentType;
	}

	public QueryResult queryXML(String myQuery)
	{			
		String resultString = request(myQuery);
		
		return new QueryResult();
	}
	
	private String request(String myQuery) 
	{
		HttpURLConnection connection = null;
		
		try
		{			
			/**
			 * Connection settings
			 */
			URL url = new URL(_RestURL + URLEncoder.encode(myQuery, "UTF-8"));
						
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setReadTimeout(10000);
			
			//accept header content type
			switch(_AcceptType)
			{
				case ACCEPT_TYPE_JSON : connection.setRequestProperty("Accept", "application/json");
										break;
				case ACCEPT_TYPE_TEXT : connection.setRequestProperty("Accept", "text/plain");
										break;
				default :				connection.setRequestProperty("Accept", "application/xml");
										break;			
			}						
											
			/**
			 * Credentials
			 */
			String loginString = _UserName + ":" + _Password;
			
			//some encoding
			BASE64Encoder encoder = new BASE64Encoder();
			String encodedLoginString = encoder.encode(ASCIIUtility.getBytes(loginString));
			
			//add login information to the request header
			connection.setRequestProperty("Authorization", "Basic " + encodedLoginString);
			
			/**
			 * Reading response
			 */
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine;
			StringBuilder result = new StringBuilder();
			
			while((inputLine = in.readLine()) != null)
			{
				result.append(inputLine);
			}
			
			in.close();
			
			return result.toString();
		} catch(Exception e)
		{
			System.out.println(e.getMessage());
			
			return null;
		}
		finally
		{		
			connection.disconnect();
		}
	}
}
