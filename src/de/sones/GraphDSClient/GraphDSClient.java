/*
* sones GraphDB(v2) - OpenSource Graph Database - http://www.sones.com
* Copyright (C) 2007-2011 sones GmbH
*
* This file is part of sones GraphDB OpenSource Edition.
*
* sones GraphDB OSE is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as published by
* the Free Software Foundation, version 3 of the License.
*
* sones GraphDB OSE is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public License
* along with sones GraphDB OSE. If not, see <http://www.gnu.org/licenses/>.
*/
package de.sones.GraphDSClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import sun.misc.BASE64Encoder;
import com.sun.xml.internal.ws.util.ASCIIUtility;
import org.jdom.Element;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import de.sones.GraphDSClient.Objects.BinaryProperty;
import de.sones.GraphDSClient.Objects.Edge;
import de.sones.GraphDSClient.Objects.EdgeTupel;
import de.sones.GraphDSClient.Objects.Property;
import de.sones.GraphDSClient.Objects.Vertex;
import de.sones.GraphDSClient.QueryResult.QueryResult;
import de.sones.GraphDSClient.QueryResult.ResultType;


@SuppressWarnings("restriction")
public class GraphDSClient {
	
	/**
	 * private member
	 */
	private HttpURLConnection connection;
	
	private String username;
	
	private String password;
	
	private String host;
	
	private int port;
	
	private String GQLPATTERN = "/gql?";
	
	private String restUri;
	
	private Namespace sones = Namespace.getNamespace("http://sones.com/QueryResultSchema.xsd");
	
	
	/**
	 * Constructor for a GraphDS REST Client. Instantiate one per GraphDB instance.
	 * @param myHost The host to connect (e.g. 'localhost')
	 * @param myUsername The username to the GarphDB
	 * @param myPassword The password to the GraphDB
	 * @param myPort The current port of the REST interface
	 */
	public GraphDSClient(String myHost, String myUsername, String myPassword, int myPort){
		
		setHost(myHost);
		username = myUsername;
		password = myPassword;
		port = myPort;
		
		restUri = host + ":" + port + GQLPATTERN;
		
	}
	
	/**
	 * Sets the host in the correct format
	 * @param myHost
	 */
	public void setHost(String myHost){
		if(!myHost.contains("http://")){
			host = "http://" + myHost;
		}
		else{
			host = myHost;
		}
	}
	/**
	 * 
	 * @return the host used for the connection
	 */
	public String getHost(){return host;}
	
	
	
	/**
	 * 
	 * @param myGQLString
	 * @return QueryResutlt which contains all information about the result of the query
	 * @throws IOException
	 * @throws JDOMException
	 */
	public QueryResult Query(String myGQLString)throws IOException, JDOMException{
				
		//fetch xml from the database
		String responseXML = FetchGraphDBOutput(myGQLString);
		if(!"".equals(responseXML) && responseXML != null){
			
		
		Document xmlDoc = null;
		
		SAXBuilder builder = new SAXBuilder(false);
		xmlDoc =  builder.build(new StringReader(responseXML));
		 		  
		
		 
		Element result = xmlDoc.getRootElement();
		Element query = result.getChild("Query",sones);
		
		String language = query.getAttributeValue("Language");
		String querystring = query.getAttributeValue("Value");		
		ResultType resulttype = Enum.valueOf(ResultType.class, query.getAttributeValue("ResultType"));
		long duration = Long.parseLong(query.getAttributeValue("Duration"));
		long vertexcount = Long.parseLong(query.getAttributeValue("VerticesCount"));
		String errormessage = null;
		
		if(resulttype.equals(ResultType.Failed)){
			errormessage = query.getAttributeValue("Error");	
		}
		
		Element vertexviewlist = result.getChild("VertexViewList",sones);
		return new QueryResult(querystring, language, resulttype, duration, errormessage, ParseVertexViewList(vertexviewlist),vertexcount);
		}
		return null;
	}
	
	/**
	 * Receives the GraphDSREST output
	 * @param myQueryString the GQL statement
	 * @return The response string from the REST interface of the GraphDS
	 * @throws IOException
	 */
	private String FetchGraphDBOutput(String myQueryString) throws IOException{
		
		/**
		 * Connection settings
		 */
		URL url = new URL(restUri + URLEncoder.encode(myQueryString, "UTF-8"));
					
		connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setReadTimeout(10000);
		
		
		//just xml is supported to parse
		connection.setRequestProperty("Accept", "application/xml");
		
		
		 //set the used credentials
		String login = username + ":" + password;
				
		
		BASE64Encoder encoder = new BASE64Encoder();			
		String encodedlogin = encoder.encode(ASCIIUtility.getBytes(login));
		
		//add login information to the request header
		connection.setRequestProperty("Authorization", "Basic " + encodedlogin);
		String responseString = "";						
			
		//Fetch the response stream
		try{
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuilder result = new StringBuilder();	
		
		while((inputLine = in.readLine()) != null)
		{
			result.append(inputLine);
			
		}
		
		in.close();
		
		responseString = result.toString();	
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
			
		return responseString;
		
	}
	
	/**
	 * Some parser utilities
	 */
	
	/**
	 * Parser for each VertexViewList
	 * @param myElement The xml node of the VertexViewList
	 * @return List<Vertex> a list of vertices
	 */
	private List<Vertex> ParseVertexViewList(Element myVertexViewList){
		
		
		List<Element> pendingVertices = myVertexViewList.getChildren("VertexView", sones);
		List<Vertex> verticesList = new ArrayList<Vertex>(pendingVertices.size());
		for (Element element : pendingVertices) {
			verticesList.add(ParseVertex(element));
		}
		
		
		return verticesList;
	}
	/**
	 * Parser for each Vertex
	 * @param myElement The xml node of the vertex
	 * @return Vertex
	 */
	private Vertex ParseVertex(Element myElement) {
		List<Property> properties = null;
		List<BinaryProperty> binproper = null;
		List<EdgeTupel> edgetupel = null;
		
		properties = ParsePropertyList(myElement.getChild("Properties",sones));
		
		binproper = ParseBinaryPropertyList(myElement.getChild("BinaryProperties", sones));
		
		edgetupel = ParseEdgeTupelList(myElement.getChild("Edges", sones));
		
		
		return new Vertex(properties,binproper,edgetupel);
	}
	/**
	 * Parser for each EdgeTupel
	 * @param myEdgeTupel The xml node of the edge tupel
	 * @return List<EdgeTupel> a list of edge tupel
	 */
	private List<EdgeTupel> ParseEdgeTupelList(Element myEdgeTupel) {
		List<Element> pendingEdges = myEdgeTupel.getChildren("ETupleList", sones);
		List<EdgeTupel> etupellist = new ArrayList<EdgeTupel>(pendingEdges.size());
		
		
		
		for (Element edgetupel : pendingEdges) {
			String name = edgetupel.getChildText("Name",sones);
			List<Element> edge = edgetupel.getChildren("Edge", sones);
			ArrayList<Edge> edges = new ArrayList<Edge>(edge.size());
			
			for (Element element : edge) {
				Long countOfProperties = Long.parseLong(element.getChildText("CountOfProperties", sones));
				List<Property> edgeProperties = ParsePropertyList(element.getChild("Properties", sones));
				List<Vertex> targetVertices = ParseVertexViewList(element.getChild("VertexViewList", sones));
				edges.add(new Edge(targetVertices, edgeProperties,countOfProperties));
			}
			etupellist.add(new EdgeTupel(name, edges));
		}
		return etupellist;
	}
	/**
	 * Parser for each BinaryProperty
	 * @param myEdgeTupel The xml node of the binary property
	 * @return List<EdgeTupel> a list of binary property
	 */	
	private List<BinaryProperty> ParseBinaryPropertyList(Element myBinaryProperty) {
		List<Element> pendingBinProperties = myBinaryProperty.getChildren("BinaryData", sones);
		
		List<BinaryProperty> payload = new ArrayList<BinaryProperty>(pendingBinProperties.size());
		for (Element binproperty : pendingBinProperties) {
			String id = binproperty.getChildText("ID", sones);
			String content = binproperty.getChildText("Content", sones);
			payload.add(new BinaryProperty(id, content));
		}
		return payload;
	}
	/**
	 * Parser for each Property
	 * @param myEdgeTupel The xml node of the property
	 * @return List<EdgeTupel> a list of property
	 */
	private List<Property> ParsePropertyList(Element myProperties) {
		List<Element> pendingProperties = myProperties.getChildren("Property", sones);
		
		List<Property> payload  = new ArrayList<Property>(pendingProperties.size());
		for (Element property : pendingProperties) {
			String id = property.getChildText("ID", sones);
			String type = property.getChildText("Type", sones);
			String value = property.getChildText("Value", sones);
			
			payload.add(new Property(id, type, value));
		}
		return payload;
	}
	
}
