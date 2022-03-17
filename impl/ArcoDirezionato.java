package impl;

import java.util.HashSet;
import java.util.Set;

import graph.DirectedEdge;
import graph.Graph;
import graph.Vertex;

public class ArcoDirezionato implements DirectedEdge{
	
	private final double DEFAULT_EDGE_WEIGHT = 1.0;
	private Vertex partenza, arrivo;
	private double peso = DEFAULT_EDGE_WEIGHT;
	private DirectedWeightedGraphAdjListImpl grafo;
	
	public ArcoDirezionato(Vertex inizio, Vertex fine, double weight, DirectedWeightedGraphAdjListImpl g) {
		this.partenza = inizio;
		this.arrivo = fine;
		this.peso = weight;
		this.grafo = g;
	}
	
	public double getEdgeWeight() {
		return this.peso;
	}
		
	public void SetWeight(double weight) {
		this.peso = weight;
	}
	
	public Set<Vertex> getVertices() {
		Set<Vertex> vertici = new HashSet<>();
		vertici.add((Vertex) this.partenza);
		vertici.add((Vertex) this.arrivo);
		return vertici;
	}
	
	public Graph getGraph() {
		return this.grafo;
	}

	public void setGraph(DirectedWeightedGraphAdjListImpl g) {
		this.grafo = g;
	}
	
	public Vertex getSource() {
		return this.partenza;
	}
	
	public Vertex getTarget() {
		return this.arrivo;
	}
	
	public String toString() {
		System.out.println("Arco direzionato (" + this.partenza.getLabel() + ", " + this.arrivo.getLabel() + ", " + this.peso + ")");
		return ("Arco direzionato (" + this.partenza.getLabel() + ", " + this.arrivo.getLabel() + ", " + this.peso + ")");
	}
}
