package impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import graph.Edge;
import graph.GraphSearchResult;
import graph.SearchType;
import graph.Vertex;

public class RicercaGrafo implements GraphSearchResult {

	private DirectedWeightedGraphAdjListImpl grafoOrientato;
	private SearchType tipo;
	private Vertex sorgente;
	private HashMap<Vertex, Vertex> predecessori;
	private HashMap<Vertex, Integer> colore;
	int bianco = 1;
	int grigio = 2;
	int nero = 3;
	protected int tempo;
	private HashMap<Vertex, Integer> tempiScoperta;//associa a ogni vertice il valore della variabile tempo nel momento in cui il vertice diventa grigio
	private HashMap<Vertex, Integer> tempiChiusura;//associa a ogni vertice il valore della variabile tempo nel momento in cui il vertice diventa nero
	protected int distanza;
	private HashMap<Vertex, Double> distanzeVertici;
	public HashSet<Collection<Vertex>> cfcs;

	public RicercaGrafo(DirectedWeightedGraphAdjListImpl grafoOrientato) {
		this.grafoOrientato = grafoOrientato;
		this.tempiScoperta = new HashMap<Vertex, Integer>();
		this.predecessori = new HashMap<Vertex, Vertex>();
		this.colore = new HashMap<Vertex, Integer>();
		this.tempiChiusura = new HashMap<Vertex, Integer>();
		this.distanzeVertici = new HashMap<Vertex, Double>();
		this.cfcs = new HashSet<Collection<Vertex>>();
		for(Iterator<Vertex> it = this.grafoOrientato.vertexSet().iterator(); it.hasNext(); ) {
			Vertex verticeCorrente = it.next();
			this.predecessori.put(verticeCorrente, null);
			this.colore.put(verticeCorrente, bianco);
			this.tempiScoperta.put(verticeCorrente, /*(int) Double.POSITIVE_INFINITY*/0);
			this.tempiChiusura.put(verticeCorrente, /*(int) Double.POSITIVE_INFINITY*/0);
			this.distanzeVertici.put(verticeCorrente, Double.POSITIVE_INFINITY);
		}
		this.tempo = 1;
		this.distanza = 0;
	}
	
	public RicercaGrafo(DirectedWeightedGraphAdjListImpl grafoOrientato, SearchType t, Vertex sorgente) {
		this.grafoOrientato = grafoOrientato;
		this.tipo = t;
		this.sorgente = sorgente;
		this.tempiScoperta = new HashMap<Vertex, Integer>();
		this.predecessori = new HashMap<Vertex, Vertex>();
		this.colore = new HashMap<Vertex, Integer>();
		this.tempiChiusura = new HashMap<Vertex, Integer>();
		this.distanzeVertici = new HashMap<Vertex, Double>();
		for(Iterator<Vertex> it = this.grafoOrientato.vertexSet().iterator(); it.hasNext(); ) {
			Vertex verticeCorrente = it.next();
			this.predecessori.put(verticeCorrente, null);
			this.colore.put(verticeCorrente, bianco);
			this.tempiScoperta.put(verticeCorrente, /*(int) Double.POSITIVE_INFINITY*/0);
			this.tempiChiusura.put(verticeCorrente, /*(int) Double.POSITIVE_INFINITY*/0);
			this.distanzeVertici.put(verticeCorrente, Double.POSITIVE_INFINITY);
		}
		this.tempo = 1;
		this.distanza = 0;
	}
	
	public Iterator<Vertex> iterator() {
		return this.iterator();
	}
	
	public SearchType getType() {
		return this.tipo;
	}

	public Vertex getSource() {
		return this.sorgente;
	}

	public double getDistance(Vertex v) throws UnsupportedOperationException, IllegalArgumentException {
		if(!this.grafoOrientato.containsVertex(v))
			throw new IllegalArgumentException("Vertice in input non presente nel grafo");
		if(this.colore.get(v) == bianco)
			return Double.POSITIVE_INFINITY;
		return this.distanzeVertici.get(v);
	}

	public Vertex getParentOf(Vertex v) throws IllegalArgumentException {
		return this.predecessori.get(v);
	}
	
	public int getStartTime(Vertex v) throws IllegalArgumentException {
		if(!this.grafoOrientato.containsVertex(v))
			throw new IllegalArgumentException("Vertice in input non contenuto in questo grafo");
		if(this.colore.get(v) == this.bianco)
			//return (int) Double.POSITIVE_INFINITY;
			return -1;
		if(v.equals(this.sorgente))
			return this.tempo;
		else
			return this.tempiScoperta.get(v);
	}

	public int getEndTime(Vertex v) throws IllegalArgumentException {
		if(!this.grafoOrientato.containsVertex(v))
			throw new IllegalArgumentException("Vertice in input non contenuto in questo grafo");
		if(this.colore.get(v) == this.bianco || this.colore.get(v) == this.grigio)
			//return (int) Double.POSITIVE_INFINITY;
			return -1;
		else
			return this.tempiChiusura.get(v);
	}
	
	public double getEdgeWeight(Vertex v1, Vertex v2) {
		if(this.grafoOrientato.containsEdge(v1, v2)) {
			for(Iterator<Edge> it1 = this.grafoOrientato.edgeSet().iterator(); it1.hasNext(); ) {
				Edge arcoCorrente = it1.next();
				if(arcoCorrente.equals(new ArcoDirezionato(v1, v2, arcoCorrente.getEdgeWeight(), this.grafoOrientato)))
					return arcoCorrente.getEdgeWeight();
			}	
		}
		if(!grafoOrientato.containsVertex(v1) || !grafoOrientato.containsVertex(v2))
			throw new IllegalArgumentException("Vertici in input non presenti nel grafo");
		else
			throw new IllegalArgumentException();
	}

	public HashMap<Vertex, Integer> getColore() {
		return this.colore;
	}

	public HashMap<Vertex, Vertex> getPredecessori() {
		return this.predecessori;
	}

	public HashMap<Vertex, Integer> getTempiScoperta() {
		return this.tempiScoperta;
	}

	public HashMap<Vertex, Integer> getTempiChiusura() {
		return this.tempiChiusura;
	}

	public HashMap<Vertex, Double> getDistanzeVertici() {
		return this.distanzeVertici;
	}
}
