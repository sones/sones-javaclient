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
package de.sones.GraphDSClient.Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import de.sones.GraphDSClient.GraphDSClient;
import de.sones.GraphDSClient.Objects.BinaryProperty;
<<<<<<< HEAD
import de.sones.GraphDSClient.Objects.IHyperEdge;
import de.sones.GraphDSClient.Objects.ISingleEdge;
=======
import de.sones.GraphDSClient.Objects.Edge;
import de.sones.GraphDSClient.Objects.EdgeTupel;
>>>>>>> f41eead1e4f4cc13df658117099c47b51186a6eb
import de.sones.GraphDSClient.Objects.Property;
import de.sones.GraphDSClient.Objects.Vertex;
import de.sones.GraphDSClient.QueryResult.QueryResult;

public class GraphDSClientDemo {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, Exception {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		GraphDSClient client  = new GraphDSClient("localhost","test","test",9975);
		while(true){
			System.out.print("Type in the GQL: ");
			String gql = console.readLine();
			if(gql != ""){
			QueryResult result = client.Query(gql);	
			printQueryResult(result);
			}
					
		}
		
		
		
		
	}
	/**
	 * Prints a QueryResult
	 * @param myResult QueryResult to print
	 */
	public static void printQueryResult(QueryResult myResult){
		
		System.out.println("The current QueryResult contains the following data:");
		System.out.println(myResult.getQueryLanguage() + ": " + myResult.getQueryString());
		System.out.println("Duartion: " + myResult.getDuration());
		System.out.println("ResultType: " + myResult.getResultType().toString());
		System.out.println("Error: " + myResult.getErrorMessage());
<<<<<<< HEAD
=======
		System.out.println("Count of fetched Vertices: " + myResult.getCountOfVertices());
>>>>>>> f41eead1e4f4cc13df658117099c47b51186a6eb
		System.out.println("The fetched Vertices are:\n");
		
		List<Vertex> vertexviewlist = myResult.getVertexViewList();
		
		for (Vertex vertex : vertexviewlist) {
			printvertex(vertex, vertexviewlist.indexOf(vertex),0);
		}
		
	}
	/**
	 * Prints a vertex with all the attributes and edges
	 * @param myVertex The Vertex to print
	 * @param vertexnumber The number of the vertex in the current context
	 * @param depth The depth of the tabs
	 */
	private static void printvertex(Vertex myVertex, long vertexnumber, int depth){
		String tabs = "";
		for (int i = 0; i < depth; i++) {
			tabs += "\t";
		}
		
		System.out.println(tabs + "--"+ " Vertex " + Long.toString(vertexnumber));
		tabs = "";
		for (int i = 0; i < depth + 1; i++) {
			tabs += "\t";
		}
		System.out.println(tabs + "\\");
		System.out.println(tabs + " |- Properties are:");
<<<<<<< HEAD
		List<Property> properties = myVertex.getProperties();
=======
		List<Property> properties = myVertex.getPropertyList();
>>>>>>> f41eead1e4f4cc13df658117099c47b51186a6eb
		for (Property property : properties) {
			System.out.println(tabs + " |   *" + property.getId() + ": " + property.getValue());
		}
		System.out.println(tabs + " |");
<<<<<<< HEAD
		List<BinaryProperty> binproper = myVertex.getBinaryProperties();
		System.out.println(tabs + " |- BinaryProperties are:");
		for (BinaryProperty binaryProperty : binproper) {
			System.out.println(tabs + " |   *" + binaryProperty.getId() + ": " + binaryProperty.getContent());
		}
		System.out.println(tabs + " |");
		List<IHyperEdge> edges = myVertex.getHyperEdges();
		System.out.println(tabs + " |- Edges are:");
		
		for (IHyperEdge edge : edges) {
			System.out.println(tabs + "     ->" + edge.getName()+":");


			properties = edge.getProperties();
			for (Property property : properties) {
				System.out.println(tabs + "       \\   " + property.getId() + ": " + property.getValue());
			}
			List<ISingleEdge> singleedges = edge.getEdges();
			for (ISingleEdge singleedge : singleedges) {
				printvertex((Vertex) singleedge.getTargetVertex(),(long)edges.indexOf(edge),depth + 2);
			}
		}
		List<ISingleEdge> sedges = myVertex.getSingleEdges(); 
		for (ISingleEdge sedge : sedges) {
			System.out.println(tabs + "  ->" + sedge.getName()+":");
			printvertex((Vertex) sedge.getTargetVertex(),sedges.indexOf(sedge),depth + 2);
		}
		
=======
		List<BinaryProperty> binproper = myVertex.getBinaryPropertyList();
		System.out.println(tabs + " |- BinaryProperties are:");
		for (BinaryProperty binaryProperty : binproper) {
			System.out.println(tabs + "|   *" + binaryProperty.getId() + ": " + binaryProperty.getContent());
		}
		System.out.println(tabs + " |");
		List<EdgeTupel> edgetupels = myVertex.getETupelList();
		System.out.println(tabs + " |- EdgeTupels are:");
		for (EdgeTupel edgeTupel : edgetupels) {
			System.out.println(tabs + "  ->" + edgeTupel.getName()+":");
			List<Edge> edges = edgeTupel.getEdges();
			for (Edge edge : edges) {
				List<Vertex> targetvertex = edge.getTargetVertices();
				for (Vertex vertex : targetvertex) {
					printvertex(vertex,targetvertex.indexOf(vertex),depth +2);
				}
			}
		}
>>>>>>> f41eead1e4f4cc13df658117099c47b51186a6eb
		System.out.println("\n");
		
		
		
				
		
	}

}
