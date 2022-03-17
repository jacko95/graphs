package impl;

import java.util.HashMap;

import graph.Graph;
import graph.Vertex;

public class Vertice extends Vertex{
	
	private DirectedWeightedGraphAdjListImpl grafo;
	private HashMap<Vertice, Double> lista = new HashMap<Vertice, Double>();

	public Vertice(String label, DirectedWeightedGraphAdjListImpl g) {
		super(label);
		this.grafo = g;
	}
	
	public HashMap<Vertice, Double> getLista() {
		return this.lista;
	}
	
	public Graph getGraph() {
		return this.grafo;
	}

    public String toString() {
		System.out.println("Vertice " + this.getLabel());
    	return "Vertice " + this.getLabel();
    }
}
