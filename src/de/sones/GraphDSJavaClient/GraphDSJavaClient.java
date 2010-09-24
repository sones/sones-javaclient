package de.sones.GraphDSJavaClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import sun.misc.BASE64Encoder;

import com.sun.xml.internal.ws.util.ASCIIUtility;

import de.sones.GraphDSJavaClient.API.Edge;
import de.sones.GraphDSJavaClient.API.IVertex;
import de.sones.GraphDSJavaClient.API.Vertex;
import de.sones.GraphDSJavaClient.API.VertexGroup;
import de.sones.GraphDSJavaClient.API.VertexWeightedEdges;
import de.sones.GraphDSJavaClient.DataStructures.ObjectRevisionID;
import de.sones.GraphDSJavaClient.DataStructures.ObjectUUID;
import de.sones.GraphDSJavaClient.Errors.IError;
import de.sones.GraphDSJavaClient.Errors.IWarning;
import de.sones.GraphDSJavaClient.Errors.UnspecifiedError;
import de.sones.GraphDSJavaClient.Errors.UnspecifiedWarning;
import de.sones.GraphDSJavaClient.Result.QueryResult;
import de.sones.GraphDSJavaClient.Result.ResultType;

public class GraphDSJavaClient 
{
	private HttpURLConnection _Connection;
	
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
	
	public void disconnect()
	{
		if(_Connection != null)
		{
			_Connection.disconnect();
		}
	}
	
	public QueryResult queryXML(String myQuery) throws IOException, JDOMException
	{			
		/**
		 * retrieve the response string
		 */
		String responseString = getResponseString(myQuery);								
		
		/**
		 * parse the response-string
		 */
		Document xmlDoc = null;
										
		xmlDoc = new SAXBuilder().build(new StringReader(responseString));			
					
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
		
		List<IWarning> queryWarnings 	= null;
		List<IError> queryErrors 		= null;
		
		List<IVertex> queryVertices = null;
				
		/**
		 * 2) Meta
		 */						
		Element queryResultNode = xmlDoc.getRootElement().getChild("graphdb").getChild("queryresult");
		//query string				
		queryString = queryResultNode.getChildText("query");
		//query result
		queryResult = Enum.valueOf(ResultType.class, queryResultNode.getChildText("result"));		
		//duration
		queryDuration = Long.parseLong(queryResultNode.getChildText("duration"));			
		
		/**
		 * 3) Warnings
		 */
		Element warningsNode = queryResultNode.getChild("warnings");		
		
		if(warningsNode != null)
		{
			queryWarnings = readWarnings(warningsNode);
		}

		/**
		 * 4) Errors
		 */
		Element errorsNode = queryResultNode.getChild("errors");				 
		
		if(errorsNode != null)
		{
			queryErrors = readErrors(errorsNode);
		}
		
		/**
		 * 5) Results
		 */
		Element resultsNode = queryResultNode.getChild("results");
		
		if(resultsNode != null)
		{
			queryVertices = readVertices(resultsNode);			
		}
				
		return new QueryResult(queryVertices, queryWarnings, queryErrors, queryString, queryResult, queryDuration);
	}
	
	private String getResponseString(String myQuery) throws IOException 
	{			
		/**
		 * Connection settings
		 */
		URL url = new URL(_RestURL + URLEncoder.encode(myQuery, "UTF-8"));
					
		_Connection = (HttpURLConnection)url.openConnection();
		_Connection.setRequestMethod("GET");
		_Connection.setDoOutput(true);
		_Connection.setReadTimeout(10000);
		
		//accept header content type
		switch(_AcceptType)
		{
			case ACCEPT_TYPE_JSON : _Connection.setRequestProperty("Accept", "application/json");
									break;
			case ACCEPT_TYPE_TEXT : _Connection.setRequestProperty("Accept", "text/plain");
									break;
			default :				_Connection.setRequestProperty("Accept", "application/xml");
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
		_Connection.setRequestProperty("Authorization", "Basic " + encodedLoginString);
								
		/**
		 * Reading response
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(_Connection.getInputStream()));
		
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
	}

	private ArrayList<IWarning> readWarnings(Element myWarningsNode)
	{
		String id 						= null;
		String message 					= null;	
		Element tmpNode 				= null;
		ArrayList<IWarning> warnings 	= new ArrayList<IWarning>();
				
		for (Object warning : myWarningsNode.getChildren()) 
		{
			tmpNode = (Element) warning;
			
			//read warning ID (the attribute "node" in the warning tag)
			id = tmpNode.getAttributeValue("code");			
			if(id == null)
			{
				id = "0";
			}
			
			//read warning message (the text in the warning tag)
			message = tmpNode.getText();
			
			//add to the warnings list
			warnings.add(new UnspecifiedWarning(id, message));
		}				
		
		return warnings;
	}
	
	private ArrayList<IError> readErrors(Element myErrorsNode)
	{
		String id 						= null;
		String message 					= null;		
		Element tmpNode 				= null;
		ArrayList<IError> errors 		= new ArrayList<IError>();
		
		for (Object error : myErrorsNode.getChildren()) 
		{
			tmpNode = (Element) error;
			
			//read error ID (the attribute "node" in the error tag)
			id = tmpNode.getAttributeValue("code");			
			if(id == null)
			{
				id = "0";
			}
			
			//read error message (the text in the error tag)
			message = tmpNode.getText();
			
			//add to the warnings list
			errors.add(new UnspecifiedError(id, message));
		}			
		
		return errors;
	}

	private List<IVertex> readVertices(Element myResultsNode)
	{
		List<IVertex> vertices = new ArrayList<IVertex>();		
		
		for (Object vertex : myResultsNode.getChildren()) 
		{
			vertices.add(readVertex((Element) vertex));
		}
				
		return vertices;
	}
	
	private IVertex readVertex(Element myVertexNode)
	{						
		Element tmpNode = null;		
		Map<String, Object> payLoad = new HashMap<String, Object>();		
						
		/**
		 * attributes
		 */
		String attributeType;
		String attributeName;
		String attributeValue;
		
		for (Object attribute : myVertexNode.getChildren("attribute")) 
		{
			tmpNode = (Element) attribute;
			attributeName = tmpNode.getAttributeValue("name");
			attributeType = tmpNode.getAttributeValue("type");
			attributeValue = tmpNode.getValue();
			
			payLoad.put(attributeName, parseAttribute(attributeType, attributeValue));
		}
		
		/**
		 * edges
		 */
		Edge tmpEdge;
		String edgeName;
		
		for (Object edge : myVertexNode.getChildren("edge")) 
		{
			tmpNode = (Element) edge;
			
			edgeName = tmpNode.getAttributeValue("name");						
			tmpNode.getChild("hyperedgelabel");
			tmpNode.getChildren("vertex");
			Iterable<IVertex> targetVertices = generateEdgeContent(
					tmpNode.getChild("hyperedgelabel"),
					tmpNode.getChildren("vertex"));
			tmpEdge = new Edge(null, targetVertices, null);
			tmpEdge.setEdgeTypeName(tmpNode.getAttributeValue("type"));
			
			payLoad.put(edgeName, tmpEdge);
		}
		
		/**
		 * edgelabels
		 */
		Element edgeLabel = myVertexNode.getChild("edgelabel");

        if (edgeLabel != null)
        {            
            String edgeIdentifierElement = edgeLabel.getChild("attribute").getAttributeValue("name");
                                   
            if (edgeIdentifierElement != null)
            {
            	if("weight".equals(edgeIdentifierElement))
            	{
            		return new VertexWeightedEdges(payLoad, edgeLabel.getValue(), edgeLabel.getChild("attribute").getAttributeValue("type"));            		            		
            	} 
            	else if("group".equals(edgeIdentifierElement))
            	{
            		return new VertexGroup(payLoad, readVertices(edgeLabel.getChild("attribute")));            		            		
            	} 
            }
        }
            	            	             			
		return new Vertex(payLoad);
	}			
	
	private Iterable<IVertex> generateEdgeContent(Element myHyperEdgeElement, List<?> myTargetVerticeNodes)
	{
		List<IVertex> targetVertices = new ArrayList<IVertex>();
		
		//do some recursive work through the result graph
		for(Object targetVerticeNode : myTargetVerticeNodes)
		{
			targetVertices.add(readVertex((Element)targetVerticeNode));
		}
		
		return targetVertices;
	}
	
	private Object parseAttribute(String myAttributeType, String myAttributeValue)
	{
		if("Double".equals(myAttributeType))
		{
			return Double.parseDouble(myAttributeValue);
		} 
		else if("Int64".equals(myAttributeType))
		{
			return Long.parseLong(myAttributeValue);
		} 
		else if("Int32".equals(myAttributeType))
		{
			return Integer.parseInt(myAttributeValue);
		} 
		else if("UInt64".equals(myAttributeType))
		{
			return Long.parseLong(myAttributeValue);
		} 
		else if("DateTime".equals(myAttributeType))
		{
			DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");			
			Date date = null;
			
			try
			{
				date = formatter.parse(myAttributeValue);
			} catch(ParseException ex)
			{
				return new Date();
			}
			return date;
		} 
		else if("Boolean".equals(myAttributeType))
		{
			return Boolean.parseBoolean(myAttributeValue);
		} 
		else if("ObjectUUID".equals(myAttributeType))
		{
			return new ObjectUUID(myAttributeValue);
		} 
		else if("ObjectRevisionID".equals(myAttributeType))
		{
			return new ObjectRevisionID(myAttributeValue);
		} 		
		else
		{
			return myAttributeValue;
		}				
	}
}
