package de.sones.GraphDSJavaClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import sun.misc.BASE64Encoder;

import com.sun.xml.internal.ws.util.ASCIIUtility;

import de.sones.GraphDSJavaClient.Errors.IError;
import de.sones.GraphDSJavaClient.Errors.IWarning;
import de.sones.GraphDSJavaClient.Errors.UnspecifiedError;
import de.sones.GraphDSJavaClient.Errors.UnspecifiedWarning;
import de.sones.GraphDSJavaClient.Result.QueryResult;
import de.sones.GraphDSJavaClient.Result.ResultType;

public class GraphDSJavaClient 
{
	private String _RestURL = null;
	
	private String _UserName = null;
	
	private String _Password = null;
	
	private int _AcceptType = 0;

	public static final int ACCEPT_TYPE_XML = 0;
	
	public static final int ACCEPT_TYPE_JSON = 1;
	
	public static final int ACCEPT_TYPE_TEXT = 2;
	
	public GraphDSJavaClient(URI myUri, String myUserName, String myPassword, int myDocumentType)
	{
		_RestURL = myUri.toString() + "/gql?";
		
		_UserName = myUserName;
		_Password = myPassword;		
		
		//handle argument test
		_AcceptType = myDocumentType;
	}

	public QueryResult queryXML(String myQuery)
	{			
		String responseString = getResponseString(myQuery);
		
		System.out.println(responseString);
		
		/**
		 * parse the response-string
		 */
		Document xmlDoc = null;
		
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();			
			xmlDoc = builder.parse(new InputSource(new StringReader(responseString)));
		} catch(IOException ioEx)
		{
			
		} catch(ParserConfigurationException pcEx)
		{
			
		}
		catch(SAXException saxEx)
		{
			
		}
		
		if(xmlDoc == null)
		{
			return null;
		}
		
		/**
		 * Build query result.
		 * 
		 * 1) Data
		 */
		String queryString 		= null;
		ResultType queryResult 	= null;
		long queryDuration		= 0L;
		
		ArrayList<IWarning> queryWarnings 	= null;
		ArrayList<IError> queryErrors 		= null;
				
		/**
		 * 2) Meta
		 */
		NodeList queryResultChildNodes = xmlDoc.getElementsByTagName("queryresult").item(0).getChildNodes();
		//query string				
		queryString = queryResultChildNodes.item(0).getFirstChild().getNodeValue();
		//query result
		queryResult = Enum.valueOf(ResultType.class, queryResultChildNodes.item(1).getFirstChild().getNodeValue());		
		//duration
		queryDuration = Long.parseLong(queryResultChildNodes.item(2).getFirstChild().getNodeValue());			
		
		/**
		 * 3) Warnings
		 */
		NodeList warningsNode = xmlDoc.getElementsByTagName("warnings").item(0).getChildNodes();
		
		if(warningsNode != null)
		{
			queryWarnings = readWarnings(warningsNode);
		}

		/**
		 * 4) Errors
		 */
		NodeList errorsNode = xmlDoc.getElementsByTagName("errors").item(0).getChildNodes();				 
		
		if(errorsNode != null)
		{
			queryErrors = readErrors(errorsNode);
		}
				
		return new QueryResult(queryWarnings, queryErrors, queryString, queryResult, queryDuration);
	}
	
	private String getResponseString(String myQuery) 
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
						
			String responseString = result.toString();
			
			//HACK remove Byte Order Mark (BOM) (http://de.wikipedia.org/wiki/Byte_Order_Mark)
			responseString = responseString.substring(responseString.indexOf('<'), responseString.length());
			
			return responseString;
			
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

	private ArrayList<IWarning> readWarnings(NodeList myWarnings)
	{
		String id 						= null;
		String message 					= null;
		NamedNodeMap tmpNodeMap 		= null;
		Node tmpNode 				 	= null;
		ArrayList<IWarning> warnings 	= new ArrayList<IWarning>();
		
		for (int i = 0; i < myWarnings.getLength(); i++) 
		{		
			tmpNode = myWarnings.item(i);
			//read warning ID (the attribute "node" in the warning tag)
			if((tmpNodeMap = tmpNode.getAttributes()) != null)
			{
				id = (tmpNodeMap.getNamedItem("code") != null) ? tmpNodeMap.getNamedItem("code").getNodeValue() : "0";
			}
			//read warning message (the text in the warning tag)
			message = tmpNode.getFirstChild().getNodeValue();
			
			//add to the warnings list
			warnings.add(new UnspecifiedWarning(id, message));
		}
		
		return warnings;
	}
	
	private ArrayList<IError> readErrors(NodeList myErrors)
	{
		String id 						= null;
		String message 					= null;
		NamedNodeMap tmpNodeMap 		= null;
		Node tmpNode 				 	= null;
		ArrayList<IError> errors 		= new ArrayList<IError>();
		
		for (int i = 0; i < myErrors.getLength(); i++) 
		{		
			tmpNode = myErrors.item(i);
			//read warning ID (the attribute "node" in the warning tag)
			if((tmpNodeMap = tmpNode.getAttributes()) != null)
			{
				id = (tmpNodeMap.getNamedItem("code") != null) ? tmpNodeMap.getNamedItem("code").getNodeValue() : "0";
			}
			//read warning message (the text in the warning tag)
			message = tmpNode.getFirstChild().getNodeValue();
			
			//add to the warnings list
			errors.add(new UnspecifiedError(id, message));
		}
		
		return errors;
	}
}
