
package graph;

import java.util.*;

/**
 * The root interface in the graph hierarchy.
 *
 */
public interface Graph extends Cloneable, Iterable<Vertex> {

	/**
	 * Returns an edge connecting source vertex to target vertex if such vertices
	 * and such edge exist in this graph. Otherwise returns <code>
	 * null</code>. If any of the specified vertices is <code>null</code> returns
	 * <code>null</code>
	 *
	 *
	 * @param sourceVertex
	 *            source vertex of the edge.
	 * @param targetVertex
	 *            target vertex of the edge.
	 *
	 * @return an edge connecting source vertex to target vertex.
	 */
	Edge getEdge(Vertex sourceVertex, Vertex targetVertex);

	/**
	 * Creates a new edge in this graph, going from the source vertex to the target
	 * vertex, and returns the created edge. Some graphs do not allow
	 * edge-multiplicity. In such cases, if the graph already contains an edge from
	 * the specified source to the specified target, than this method does not
	 * change the graph and returns <code>null</code>.
	 *
	 * <p>
	 * The source and target vertices must already be contained in this graph. If
	 * they are not found in graph IllegalArgumentException is thrown.
	 * </p>
	 *
	 *
	 * @param sourceVertex
	 *            source vertex of the edge.
	 * @param targetVertex
	 *            target vertex of the edge.
	 *
	 * @return The newly created edge if added to the graph, otherwise <code>
	 * null</code>.
	 *
	 * @throws IllegalArgumentException
	 *             if source or target vertices are not found in the graph.
	 * @throws NullPointerException
	 *             if any of the specified vertices is <code>
	 * null</code>.
	 *
	 */
	Edge addEdge(Vertex sourceVertex, Vertex targetVertex);

	/**
	 * Adds the specified edge to this graph, going from the source vertex to the
	 * target vertex. More formally, adds the specified edge, <code>
	 * e</code>, to this graph if this graph contains no edge <code>e2</code> such
	 * that <code>e2.equals(e)</code>. If this graph already contains such an edge,
	 * the call leaves this graph unchanged and returns <tt>false</tt>. Some graphs
	 * do not allow edge-multiplicity. In such cases, if the graph already contains
	 * an edge from the specified source to the specified target, than this method
	 * does not change the graph and returns <code>
	 * false</code>. If the edge was added to the graph, returns <code>
	 * true</code>.
	 *
	 * <p>
	 * The source and target vertices must already be contained in this graph. If
	 * they are not found in graph IllegalArgumentException is thrown.
	 * </p>
	 *
	 * @param e
	 *            edge to be added to this graph.
	 *
	 * @return <tt>true</tt> if this graph did not already contain the specified
	 *         edge.
	 *
	 * @throws IllegalArgumentException
	 *             if source or target vertices are not found in the graph.
	 * @throws ClassCastException
	 *             if the specified edge is not assignment compatible with the class
	 *             of edges produced by the edge factory of this graph.
	 * @throws NullPointerException
	 *             if any of the vertices belonging to the edge is <code>
	 * null</code>.
	 *
	 */
	boolean addEdge(Edge e);

	/**
	 * Adds the specified vertex to this graph if not already present. More
	 * formally, adds the specified vertex, <code>v</code>, to this graph if this
	 * graph contains no vertex <code>u</code> such that <code>
	 * u.equals(v)</code>. If this graph already contains such vertex, the call
	 * leaves this graph unchanged and returns <tt>false</tt>. In combination with
	 * the restriction on constructors, this ensures that graphs never contain
	 * duplicate vertices.
	 *
	 * @param v
	 *            vertex to be added to this graph.
	 *
	 * @return <tt>true</tt> if this graph did not already contain the specified
	 *         vertex.
	 *
	 * @throws NullPointerException
	 *             if the specified vertex is <code>
	 * null</code>.
	 */
	boolean addVertex(Vertex v);

	/**
	 * Returns <tt>true</tt> if and only if this graph contains an edge going from
	 * the source vertex to the target vertex. If any of the specified vertices does
	 * not exist in the graph, or if is <code>
	 * null</code>, returns <code>false</code>.
	 *
	 * @param sourceVertex
	 *            source vertex of the edge.
	 * @param targetVertex
	 *            target vertex of the edge.
	 *
	 * @return <tt>true</tt> if this graph contains the specified edge.
	 */
	boolean containsEdge(Vertex sourceVertex, Vertex targetVertex);

	/**
	 * Returns <tt>true</tt> if this graph contains the specified edge. More
	 * formally, returns <tt>true</tt> if and only if this graph contains an edge
	 * <code>e2</code> such that <code>e.equals(e2)</code>. If the specified edge is
	 * <code>null</code> returns <code>false</code>.
	 *
	 * @param e
	 *            edge whose presence in this graph is to be tested.
	 *
	 * @return <tt>true</tt> if this graph contains the specified edge.
	 */
	boolean containsEdge(Edge e);

	/**
	 * Returns <tt>true</tt> if this graph contains the specified vertex. More
	 * formally, returns <tt>true</tt> if and only if this graph contains a vertex
	 * <code>u</code> such that <code>u.equals(v)</code>. If the specified vertex is
	 * <code>null</code> returns <code>false</code>.
	 *
	 * @param v
	 *            vertex whose presence in this graph is to be tested.
	 *
	 * @return <tt>true</tt> if this graph contains the specified vertex.
	 */
	boolean containsVertex(Vertex v);

	/**
	 * Returns a set of the edges contained in this graph.
	 *
	 *
	 * @return a set of the edges contained in this graph.
	 */
	Set<Edge> edgeSet();

	/**
	 * Returns the degree of the specified vertex.
	 * 
	 * <p>
	 * A degree of a vertex in an undirected graph is the number of edges touching
	 * that vertex. Edges with same source and target vertices (self-loops) are
	 * counted twice.
	 * 
	 * <p>
	 * In directed graphs this method returns the sum of the "in degree" and the
	 * "out degree".
	 *
	 * @param vertex
	 *            vertex whose degree is to be calculated.
	 * @return the degree of the specified vertex.
	 */
	int degreeOf(Vertex vertex);

	/**
	 * Returns a set of all edges touching the specified vertex. If no edges are
	 * touching the specified vertex returns an empty set.
	 *
	 * @param vertex
	 *            the vertex for which a set of touching edges is to be returned.
	 * @return a set of all edges touching the specified vertex.
	 *
	 * @throws IllegalArgumentException
	 *             if vertex is not found in the graph.
	 * @throws NullPointerException
	 *             if vertex is <code>null</code>.
	 */
	Set<Edge> edgesOf(Vertex vertex);

	/**
	 * Returns the "in degree" of the specified vertex.
	 * 
	 * <p>
	 * The "in degree" of a vertex in a directed graph is the number of inward
	 * directed edges from that vertex.
	 * 
	 * <p>
	 * In the case of undirected graphs this method returns the number of edges
	 * touching the vertex. Edges with same source and target vertices (self-loops)
	 * are counted twice.
	 *
	 * @param vertex
	 *            vertex whose degree is to be calculated.
	 *            
	 *            
	 * @throws IllegalArgumentException
	 *             if vertex is not found in the graph.
	 * @throws NullPointerException
	 *             if vertex is <code>null</code>.
	 *             
	 * @return the degree of the specified vertex.
	 */
	int inDegreeOf(Vertex vertex);

	/**
	 * Returns a set of all edges incoming into the specified vertex.
	 *
	 * <p>
	 *
	 * @param vertex
	 *            the vertex for which the list of incoming edges to be returned.
	 * @return a set of all edges incoming into the specified vertex.
	 * 
	 * @throws IllegalArgumentException
	 *             if vertex is not found in the graph.
	 * @throws NullPointerException
	 *             if vertex is <code>null</code>.
	 */
	Set<Edge> incomingEdgesOf(Vertex vertex) throws IllegalArgumentException,NullPointerException;

	/**
	 * Returns the "out degree" of the specified vertex.
	 * 
	 * <p>
	 * The "out degree" of a vertex in a directed graph is the number of outward
	 * directed edges from that vertex.
	 * 
	 * <p>
	 * Edges with same source and target vertices (self-loops) are counted twice.
	 *
	 * @param vertex
	 *            vertex whose degree is to be calculated.
	 *            
	 * @throws IllegalArgumentException
	 *             if vertex is not found in the graph.
	 * @throws NullPointerException
	 *             if vertex is <code>null</code>.
	 *             
	 * @return the degree of the specified vertex.
	 */
	int outDegreeOf(Vertex vertex);

	/**
	 * Returns a set of all edges outgoing from the specified vertex.
	 * 
	 * <p>
	 *
	 * @param vertex
	 *            the vertex for which the list of outgoing edges to be returned.
	 *            
	 * @throws IllegalArgumentException
	 *             if vertex is not found in the graph.
	 * @throws NullPointerException
	 *             if vertex is <code>null</code>.
	 *             
	 * @return a set of all edges outgoing from the specified vertex.
	 */
	Set<Edge> outgoingEdgesOf(Vertex vertex);

	/**
	 * Removes all the edges in this graph that are also contained in the specified
	 * edge collection. After this call returns, this graph will contain no edges in
	 * common with the specified edges. This method will invoke the
	 * {@link #removeEdge(Object)} method.
	 *
	 * @param edges
	 *            edges to be removed from this graph.
	 *
	 * @return <tt>true</tt> if this graph changed as a result of the call
	 *
	 * @throws NullPointerException
	 *             if the specified edge collection is <tt>
	 * null</tt>.
	 *
	 * @see #removeEdge(Object)
	 * @see #containsEdge(Object)
	 */
	boolean removeAllEdges(Collection<? extends Edge> edges);

	boolean isDirected();

	/**
	 * Removes all the edges going from the specified source vertex to the specified
	 * target vertex, and returns a set of all removed edges. Returns
	 * <code>null</code> if any of the specified vertices does not exist in the
	 * graph. If both vertices exist but no edge is found, returns an empty set.
	 *
	 * @param sourceVertex
	 *            source vertex of the edge.
	 * @param targetVertex
	 *            target vertex of the edge.
	 *
	 * @return the removed edges, or <code>null</code> if either vertex is not part
	 *         of graph
	 */
	Set<Edge> removeAllEdges(Vertex sourceVertex, Vertex targetVertex);

	/**
	 * Removes all the vertices in this graph that are also contained in the
	 * specified vertex collection. After this call returns, this graph will contain
	 * no vertices in common with the specified vertices.
	 *
	 * @param vertices
	 *            vertices to be removed from this graph.
	 *
	 * @return <tt>true</tt> if this graph changed as a result of the call
	 *
	 * @throws NullPointerException
	 *             if the specified vertex collection is <tt>
	 * null</tt>.
	 *
	 * @see #removeVertex(Object)
	 * @see #containsVertex(Object)
	 */
	boolean removeAllVertices(Collection<? extends Vertex> vertices);

	/**
	 * Removes an edge going from source vertex to target vertex, if such vertices
	 * and such edge exist in this graph. Returns the edge if removed or
	 * <code>null</code> otherwise.
	 *
	 * @param sourceVertex
	 *            source vertex of the edge.
	 * @param targetVertex
	 *            target vertex of the edge.
	 *
	 * @return The removed edge, or <code>null</code> if no edge removed.
	 */
	Edge removeEdge(Vertex sourceVertex, Vertex targetVertex);

	/**
	 * Removes the specified edge from the graph. Removes the specified edge from
	 * this graph if it is present. More formally, removes an edge <code>
	 * e2</code> such that <code>e2.equals(e)</code>, if the graph contains such
	 * edge. Returns <tt>true</tt> if the graph contained the specified edge. (The
	 * graph will not contain the specified edge once the call returns).
	 *
	 * <p>
	 * If the specified edge is <code>null</code> returns <code>
	 * false</code>.
	 * </p>
	 *
	 * @param e
	 *            edge to be removed from this graph, if present.
	 *
	 * @return <code>true</code> if and only if the graph contained the specified
	 *         edge.
	 */
	boolean removeEdge(Edge e);

	/**
	 * Removes the specified vertex from this graph including all its touching edges
	 * if present. More formally, if the graph contains a vertex <code>
	 * u</code> such that <code>u.equals(v)</code>, the call removes all edges that
	 * touch <code>u</code> and then removes <code>u</code> itself. If no such
	 * <code>u</code> is found, the call leaves the graph unchanged. Returns
	 * <tt>true</tt> if the graph contained the specified vertex. (The graph will
	 * not contain the specified vertex once the call returns).
	 *
	 * <p>
	 * If the specified vertex is <code>null</code> returns <code>
	 * false</code>.
	 * </p>
	 *
	 * @param v
	 *            vertex to be removed from this graph, if present.
	 *
	 * @return <code>true</code> if the graph contained the specified vertex;
	 *         <code>false</code> otherwise.
	 */
	boolean removeVertex(Vertex v);

	/**
	 * Returns a set of the vertices contained in this graph.
	 *
	 *
	 * @return a set view of the vertices contained in this graph.
	 */
	Set<Vertex> vertexSet();

	/**
	 * The default weight for an edge.
	 */
	double DEFAULT_EDGE_WEIGHT = 1.0;

	/**
	 * Returns the weight assigned to a given edge. Unweighted graphs return 1.0 (as
	 * defined by {@link #DEFAULT_EDGE_WEIGHT}), allowing weighted-graph algorithms
	 * to apply to them when meaningful.
	 *
	 * @param e
	 *            edge of interest
	 *            
	 * @throws IllegalArgumentException
	 *             if edge is not contained in the graph.
	 * @throws NullPointerException
	 *             if edge is <code>null</code>.
	 *             
	 * @return edge weight
	 */
	double getEdgeWeight(Edge e);

	/**
	 * Assigns a weight to an edge.
	 *
	 * @param e
	 *            edge on which to set weight
	 * @param weight
	 *            new weight for edge
	 * @throws UnsupportedOperationException
	 *             if the graph does not support weights            
	 * @throws IllegalArgumentException
	 *             if edge is not contained in the graph.
	 * @throws NullPointerException
	 *             if edge is <code>null</code>.
	 */
	void setEdgeWeight(Edge e, double weight);

	/**
	 * Compares this graph to the specified object. The result is true if and only if 
	 * the argument is not null and is a Graph object that represents the same sets of
	 * vertices (where vertices are compared using labels) and edges.
	 * 
	 * @param o The object to compare this Graph against
	 * 
	 * @return true if the given object represents a graph equivalent to this graph, false otherwise
	 */
	@Override
	boolean equals(Object o);

	/**
	 * Returns a string representation of this graph. 
	 * 
	 * @return a string representation of this graph
	 */
	@Override
	public String toString();
	
	/**
	 * Performs a visit (of type <code>type</code>) over the current graph, starting from the source vertex.
	 * DA IMPLEMENTARE (per pratico 2): solo BFS e DFS_TOT.
	 * DA IMPLEMENTARE (per pratico 3): Dijkstra.
	 * HINT: si consideri l'implementazione di una visita generica che prenda in input un oggetto "frangia", 
	 * con frangia diversa a seconda della visita (come visto a lezione).
	 * 
	 * @param type the search type.
	 * @param source the source vertex.
	 * 
	 * @throws UnsupportedOperationException if the visit cannot be performed on the current graph 
	 * (e.g., a Dijkstra visit on an unweighted graph).
	 * @throws IllegalArgumentException if source does not belong to the graph
	 * 
	 * @return a GraphSearchResult representing the result of the visit performed.
	 * 
	 */
	public GraphSearchResult visit(SearchType type, Vertex source) throws UnsupportedOperationException,IllegalArgumentException;
	
	/**
	 * Check whether the current graph contains cycles or not.
	 * @return true if the current graph contains cycles, false otherwise.
	 */
	public boolean isCyclic();
	
	/**
	 * Check whether the current graph is a directed acyclic graph (DAG) or not.
	 * @return true if the current graph is a DAG, false otherwise.
	 */
	public boolean isDAG();
	
	/**
	 * If the current graph is a DAG, it returns a topological sort of this graph. 
	 * 
	 * @throws UnsupportedOperationException if the current graph is not a DAG.
	 * 
	 * @return a topological sort of this graph. 
	 */
	public Vertex[] topologicalSort();
	
	/**
	 * If the current graph is directed, this method returns the strongly connected components
	 * of the graph. Otherwise, the method throws an UnsupportedOperationException.
	 * 
	 * @throws UnsupportedOperationException if the current graph is not directed.
	 * 
	 * @return a collection of collections of vertices representing the strongly connected components of the graph.
	 */
	public Collection<Collection<Vertex>> stronglyConnectedComponents();
	
	/**
	 * Returns a string representing the strongly connected components of the graph.
	 * For instance: {{1,2,3},{4,6},{5}}
	 * 
	 * @throws UnsupportedOperationException if the current graph is not directed.
	 * 
	 * @return a string representing the strongly connected components of the graph.
	 */
	public String toStringSCC();

}

// End Graph.java
