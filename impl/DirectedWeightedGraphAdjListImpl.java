package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;


import graph.*;

public class DirectedWeightedGraphAdjListImpl implements Graph {
	
	public static Scanner tastiera;
	private ArrayList<Vertice> grafo;
	private int bianco = 1;
	private int grigio = 2;
	private int nero = 3;

	public DirectedWeightedGraphAdjListImpl() {	
		tastiera = new Scanner(System.in);
		grafo = new ArrayList<Vertice>();
	}
	
	public ArrayList<Vertice> getGrafo() {
		return this.grafo;
	}
	
	public Iterator<Vertex> iterator() {
		return this.vertexSet().iterator();
	}

	public Edge getEdge(Vertex sourceVertex, Vertex targetVertex) {	
		for(Iterator<Vertice> it = this.getGrafo().iterator(); it.hasNext(); ) {
			Vertice corrente = it.next();
			if(corrente.equals(sourceVertex)) {
				if(((Vertice)corrente).getLista().containsKey((Vertice)targetVertex)){
					return new ArcoDirezionato(((Vertice)sourceVertex), (Vertice)targetVertex, ((Vertice)sourceVertex).getLista().get((Vertice)targetVertex), this);
				}
			}		
		}		
		return null;
	}

	public Edge addEdge(Vertex sourceVertex, Vertex targetVertex) {
		if(!this.containsVertex(sourceVertex) || !this.containsVertex(targetVertex)) 
			throw new IllegalArgumentException("I vertici in input non sono presenti in questo grafo");
		if(sourceVertex == null || targetVertex == null)
			throw new NullPointerException("Uno dei due vertici in input è null");
		if(this.containsVertex(sourceVertex) && this.containsVertex(targetVertex)) {
			ArcoDirezionato a = new ArcoDirezionato((Vertice)sourceVertex, (Vertice)targetVertex, 1.0, this);
			for(Vertice v : this.grafo) {
				if(v.equals((Vertice)sourceVertex)) {
					((Vertice)sourceVertex).getLista().put((Vertice)targetVertex, 1.0);
				}
			}
			return a;
		}
		return null;
	}
	
	public boolean addEdge(Edge e) {
		if(e instanceof ArcoDirezionato) {
			ArcoDirezionato ed = ((ArcoDirezionato)e);
			Vertex a = ((Vertice)ed.getSource());
			Vertice b = ((Vertice)ed.getTarget());		
			if(!(this.containsVertex(a)) || !(this.containsVertex(b))) 
				throw new IllegalArgumentException("I vertici in input non sono presenti in questo grafo");
			if(a == null || b == null)
				throw new NullPointerException("Uno dei due vertici in input è null");
			if(this.containsVertex(a) && this.containsVertex(b)) {
				((Vertice)a).getLista().put((Vertice)b, ((ArcoDirezionato)e).getEdgeWeight());
				return true;
			}
		}
		if(!(e instanceof DirectedEdge))
			throw new ClassCastException("L'arco in input non è un arco direzionato");
		return false;
	}

	public boolean addVertex(Vertex v) {
		if(this.containsVertex(v))
			return false;
		if(v == null)
			throw new NullPointerException("Vertice in input nullo");
		Vertice nuovo = (Vertice)v;
		this.grafo.add(nuovo);
		return true;
	}

	public boolean containsEdge(Vertex sourceVertex, Vertex targetVertex) {
		for(Vertice v : this.grafo) {
			if(((Vertice)sourceVertex).equals(v)) {
				for(Vertice v1 : v.getLista().keySet()) {
					if(((Vertice)targetVertex).equals(v1)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean containsEdge(Edge e) {
		if(e instanceof ArcoDirezionato) {
			ArcoDirezionato ad = (ArcoDirezionato)e;
			for(Vertice v : this.grafo) {
				if(((Vertice)ad.getSource()).equals(v)) {
					for(Vertice v1 : v.getLista().keySet()) {
						if(((Vertice)ad.getTarget()).equals(v1)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean containsVertex(Vertex v) {
		for(Vertice vertice : this.grafo) {
			if(((Vertex)vertice).equals(v))
				return true;
		}
		return false;
	}
	
	public Set<Edge> edgeSet() {
		Set<Edge> a = new HashSet<Edge>();
		for(Vertice v : this.grafo) {
			for(Vertice v1 : v.getLista().keySet()) {
				ArcoDirezionato nuovo = new ArcoDirezionato(v, v1, v.getLista().get(v1), this);
				a.add((ArcoDirezionato)nuovo);
			}
		}
		return a;
	}

	public int degreeOf(Vertex vertex) {
		return inDegreeOf(vertex) + outDegreeOf(vertex);
	}

	public Set<Edge> edgesOf(Vertex vertex) {
		if(vertex == null)
			throw new NullPointerException("Vertice in input nullo");
		if(!this.containsVertex(vertex))
			throw new IllegalArgumentException("Vertice non presente nel grafo");	
		Set<Edge> setArchi = new HashSet<>();
		for(Iterator<Edge> it = this.edgeSet().iterator(); it.hasNext(); ) {
			ArcoDirezionato corrente = (ArcoDirezionato) it.next();
			if(corrente.getSource().equals(vertex) || corrente.getTarget().equals(vertex)) {
				setArchi.add(corrente);
			}
		}
		return setArchi;
	}

	public int inDegreeOf(Vertex vertex) {
		if(vertex == null)
			throw new NullPointerException("Vertice in input nullo");
		if(!this.containsVertex(vertex))
			throw new IllegalArgumentException("Vertice non presente nel grafo");
		int c = 0;
		for(Iterator<Edge> it = this.edgeSet().iterator(); it.hasNext(); ) {
			ArcoDirezionato valoreCorrente = (ArcoDirezionato) it.next();
			if(valoreCorrente.getTarget().equals((Vertice)vertex)) 
				c++;	
		}
		return c;
	}

	public Set<Edge> incomingEdgesOf(Vertex vertex) {
		if(vertex == null)
			throw new NullPointerException("Vertice in input nullo");
		if(!this.containsVertex(vertex))
			throw new IllegalArgumentException("Vertice non presente nel grafo");
		Set<Edge> setArchi = new HashSet<>();
		for(Iterator<Edge> it = this.edgeSet().iterator(); it.hasNext(); ) {
			ArcoDirezionato valoreCorrente = (ArcoDirezionato) it.next();
			if(valoreCorrente.getTarget().equals(vertex)) {
				setArchi.add(valoreCorrente);
			}
		}
		return setArchi;
	}

	public int outDegreeOf(Vertex vertex) {
		if(vertex == null)
			throw new NullPointerException("Vertice in input nullo");
		if(!this.containsVertex(vertex))
			throw new IllegalArgumentException("Vertice non presente nel grafo");
		int c = 0;
		for(Iterator<Edge> it = this.edgeSet().iterator(); it.hasNext(); ) {
			ArcoDirezionato valoreCorrente = (ArcoDirezionato) it.next();
			if(valoreCorrente.getSource().equals((Vertice)vertex)) 
				c++;	
		}
		return c;
	}
	
	public Set<Edge> outgoingEdgesOf(Vertex vertex) {
		if(vertex == null)
			throw new NullPointerException("Vertice in input nullo");
		if(!this.containsVertex(vertex))
			throw new IllegalArgumentException("Vertice non presente nel grafo");
		Set<Edge> setArchi = new HashSet<>();
		for(Iterator<Edge> it = this.edgeSet().iterator(); it.hasNext(); ) {
			ArcoDirezionato valoreCorrente = (ArcoDirezionato) it.next();
			if(valoreCorrente.getSource().equals(vertex)) {
				setArchi.add(valoreCorrente);
			}
		}
		return setArchi;
	}

	public boolean removeAllEdges(Collection<? extends Edge> edges) {
		if(edges == null)
			throw new NullPointerException("Collezione in input nulla");
		int c = 0;
		for(Edge e : edges) {
			if(e instanceof ArcoDirezionato) {
				if(this.removeEdge(((ArcoDirezionato)e).getSource(), ((ArcoDirezionato)e).getTarget()) != null)
					c++;
			}
		}
		if(c > 0)
			return true;
		return false;
	}

	public Set<Edge> removeAllEdges(Vertex sourceVertex, Vertex targetVertex) {
		Set<Edge> archiRimossi = new HashSet<Edge>();
		for(Vertice v : this.grafo) {
			if(v.equals((Vertice)sourceVertex) && v.getLista().containsKey((Vertice)targetVertex)) {
				archiRimossi.add(new ArcoDirezionato(v, (Vertice)targetVertex, v.getLista().get(targetVertex), this));
				v.getLista().remove((Vertice)targetVertex);
			}
		}
		return archiRimossi;
	}

	public boolean removeAllVertices(Collection<? extends Vertex> vertices) {
		if(vertices == null)
			throw new NullPointerException("Collezione in input nulla");
		if(this.grafo.removeAll(vertices) == true)
			return true;
		return false;
	}

	public Edge removeEdge(Vertex sourceVertex, Vertex targetVertex) {
		for(Vertice v : this.grafo) {
			if(((Vertice)sourceVertex).equals(v)) {
				for(Vertice v1 : v.getLista().keySet()) {
					if(v1.equals((Vertice)targetVertex)) {
						ArcoDirezionato a = new ArcoDirezionato(v, v1, v.getLista().get(v1), this); 
						v.getLista().remove(v1);
						return a;
					}
				}
			}
		}
		return null;
	}

	public boolean removeEdge(Edge e) {
		ArcoDirezionato ed = (ArcoDirezionato) e;
		Vertice a = (Vertice)ed.getSource();
		Vertice b = (Vertice)ed.getTarget();	
		for(Vertice v : this.grafo) {
			if(((Vertice)a).equals(v)) {
				for(Vertice v1 : v.getLista().keySet()) {
					if(v1.equals((Vertice)b)) {
						v.getLista().remove(v1);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean removeVertex(Vertex v) {
		if(v != null) {
			if(this.grafo.contains((Vertice)v)) {
				int c = 0;
				for(int i = 0; i < this.grafo.size(); i++) {
					Vertice vertice = this.grafo.get(i);
					if(vertice.equals((Vertice)v)) {
						this.grafo.remove(vertice);
						c++;
					}
					if(vertice.getLista().containsKey((Vertice)v))
						vertice.getLista().remove((Vertice)v);
				}
				if(c > 0)
					return true;
			}
		}
		return false;
	}

	public Set<Vertex> vertexSet() {
		Set<Vertex> c = new HashSet<>();
		for(Vertice v : this.grafo)
			c.add(v);
		return c;
	}

	public double getEdgeWeight(Edge e) {
		if(e == null)
			throw new NullPointerException("Arco in input nullo");
		if(!this.containsEdge(e))
			throw new UnsupportedOperationException("Il grafo non contiene l'Arco in input");
		if(e instanceof ArcoDirezionato) {
			Vertice p = (Vertice)((ArcoDirezionato)e).getSource();
			Vertice a = (Vertice)((ArcoDirezionato)e).getTarget();	
			for(Vertice v : this.grafo) {
				if(p.equals(v)) {
					return v.getLista().get(a);
				}
			}
		}
		return 1.0;
	}

	public void setEdgeWeight(Edge e, double weight) {
		if(e == null)
			throw new NullPointerException("Arco in input nullo");
		if(!(this instanceof DirectedWeightedGraphAdjListImpl))
			throw new UnsupportedOperationException("Il grafo non supporta gli archi pesati");	
		if(!(e instanceof DirectedEdge))
				throw new IllegalArgumentException("Arco non diretto");
		if(e instanceof ArcoDirezionato) {
			((ArcoDirezionato)e).SetWeight(weight);
			for(Vertice v : this.grafo) {
				if(((ArcoDirezionato)e).getSource().equals(v)) {
					v.getLista().put((Vertice)((ArcoDirezionato)e).getTarget(), weight);
				}
			}
		}
	}

	public boolean isDirected() {
		return true;
	}
	
	public String toString() {
		return "Vertici" + this.vertexSet() + " e con archi" + this.edgeSet();
	}

	public int getNumVertici() {
		return grafo.size();
	}

	RicercaGrafo dfs(DirectedWeightedGraphAdjListImpl g, Vertex source, SearchType t, int cfcss, ArrayList<Vertice> liord) {
		HashSet<Collection<Vertex>> cfcs = new HashSet<Collection<Vertex>>();
		Collection<Vertex> cfc = new HashSet<Vertex>();
		RicercaGrafo r = null;
		if(t == SearchType.DFS)
			r = new RicercaGrafo(g, SearchType.DFS, source);
		if(t == SearchType.DFS_TOT)
			r = new RicercaGrafo(g, SearchType.DFS_TOT, source);
		Stack<Vertex> d = new Stack<Vertex>();
		Vertex u;
		int i;
		int dd = 0;
		r.getColore().put(source, grigio);
		r.getTempiScoperta().put(source, r.tempo);
		d.push(source);
		if(cfcss == 1) {
			//rimuovo da liord il vertice sorgente che è quello con tempo di fine visita maggiore nella visita precedente
			liord.remove(source);
		}
		while(!d.isEmpty()) {//rimani nel ciclo finchè tutti i vertici non sono neri
			i = 0;
			u = d.peek();
			if(cfcss == 1) {
				//prende tutti i vertici ordinatamente in liord
				for(int j = 0; j < liord.size(); j++) {
					Vertice primo = liord.get(j);
					for(Iterator<Edge> it = g.outgoingEdgesOf(u).iterator(); it.hasNext() && i == 0; ) {
						ArcoDirezionato a = (ArcoDirezionato) it.next();
						Vertice v1 = null;
						if(a.getSource().equals(u)) {
							v1 = (Vertice) a.getTarget();
							if(v1.equals(primo)) {
								if(r.getColore().get(v1) == bianco) {
									i = 1;
									r.tempo++;
									//color [v] <- gray
									r.getColore().put(v1, grigio);
									r.getTempiScoperta().put(v1, r.tempo);
									//pigreco[v] <- u
									r.getPredecessori().put(v1, u);
									//push(D,v)
									d.push(v1);
									liord.remove(primo);
								}
							}
						}
					}
				}
			}
			if(cfcss == 0) {
				for(Vertice v1 : ((Vertice)u).getLista().keySet()) {				
					//if esiste v bianco
					if(r.getColore().get(v1) == bianco && i == 0) {
						i = 1;
						r.tempo++;
						//color [v] <- gray
						r.getColore().put(v1, grigio);
						r.getTempiScoperta().put(v1, r.tempo);
						//pigreco[v] <- u
						r.getPredecessori().put(v1, u);
						//push(D,v)
						d.push(v1);
					}
				}
			}
			if(i == 0){
				r.tempo++;
				r.getColore().put(u, nero);
				r.getTempiChiusura().put(u, r.tempo);
				//pop(D)
				d.pop();
				if(cfcss == 1) {
					//mette i nodi nella cfc
					cfc.add(u);
				}
			}
			//per fare la DFS_TOT
			if(t == SearchType.DFS_TOT) {
				if(d.isEmpty()) {
					if(cfcss == 1){
						//aggiungere la CFC alla soluzione
						cfcs.add(cfc);
						cfc = new HashSet<>(); //cfc punta ad un nuovo oggetto hashset
					}
					for(Vertice v2 : g.grafo) {
						if(r.getColore().get(v2) == bianco && dd == 0) {//prende un vertice bianco a caso e riprende la visita
							r.tempo++;
							d.push(v2);
							r.getColore().put(v2, grigio);
							r.getTempiScoperta().put(v2, r.tempo);
							//pigreco[v] <- u
							r.getPredecessori().put(v2, null);
							//ne deve aggiungere solo uno bianco
							dd = 1;
						}
					}
					dd = 0;
				}
			}
		}
		r.cfcs = cfcs;
		return r;
	}
	
	public GraphSearchResult visit(SearchType type, Vertex source)
			throws UnsupportedOperationException, IllegalArgumentException {
		
		if(type == SearchType.BFS) {
			RicercaGrafo r = new RicercaGrafo(this, SearchType.BFS, source);
			//D <- empty_queue()
			LinkedBlockingQueue<Vertex> d = new LinkedBlockingQueue<>();
			Vertex u;
			//color [s] <- gray
			r.getColore().replace(source, grigio);
			r.getTempiScoperta().put(source, r.tempo);
			r.getTempiScoperta().replace(source, r.tempo);
			//enqueue(D,s)
			d.add(source);
			//while NotEmpty(D) do begin
			while(!d.isEmpty()) {
				//u <- head(D)
				u = d.peek();
				for(Iterator<Edge> it2 = this.edgeSet().iterator(); it2.hasNext(); ) {//Dobbiamo vedere se tra tutti gli archi ci sono archi che toccano u
					ArcoDirezionato arcoCorrente = (ArcoDirezionato) it2.next();
					//if esiste v adj ad u
					if(arcoCorrente.getSource().equals(u)) {
						Vertice v = ((Vertice)arcoCorrente.getTarget());
						//if esiste v bianco
						if(r.getColore().get(v) == r.bianco) {
							//color [v] <- gray
							r.getColore().replace(v, r.grigio);
							r.tempo++;//non presente nello pseudocodice
							r.getTempiScoperta().replace(v, r.tempo);//non presente nello pseudocodice
							//pigreco[v] <- u
							r.getPredecessori().replace(v, u);
							//enqueue(D,v)
							d.add(v);
						}	
					}
				}
				for(Iterator<Vertex> it = this.vertexSet().iterator(); it.hasNext(); ) {
					Vertex vertex = it.next();
					if(vertex.equals(u)) {
						//color [u] <- black
						r.getColore().replace(u, nero);
						r.tempo++;
						r.getTempiChiusura().replace(vertex, r.tempo);
						//dequeue(D)
						try {
							d.take();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return r;
		}
		if(type == SearchType.DFS) {
			return dfs(this, source, SearchType.DFS, 0, null);
		}
		//prende un vertice bianco a caso e riprende la visita
		if(type == SearchType.DFS_TOT) {
			return dfs(this, source, SearchType.DFS_TOT, 0, null);
		}
		//le distanze vanno aggiornate con i pesi degli archi
		if(type == SearchType.DIJKSTRA) {
			RicercaGrafo r = new RicercaGrafo(this, SearchType.DIJKSTRA, source);
			TreeMap<Integer, Vertex> tree = new TreeMap<Integer, Vertex>();
			Vertex u;
			int distanza = 0;
			//color [s] <- gray
			r.getColore().put(source, grigio);
			//d[s] <- 0
			r.getDistanzeVertici().put(source, (double) distanza);
			tree.put(0, source);
			//while NotEmpty(D)
			while(!tree.isEmpty()) {
				//u <- dequeue_min(D)
				u = tree.get(tree.firstKey());//prendo la chiave con valore inferiore
				tree.remove(tree.firstKey());//la rimuovo
				//S <- S U {u}//aggiungo u all’albero definitivamente
				//for ogni v adj ad u then
				for(Iterator<Vertice> it = ((Vertice)u).getLista().keySet().iterator(); it.hasNext(); ) {
					Vertex v = it.next();
					//if color[v] != black
					if(r.getColore().get(v) != nero) {
						//if color[v] = white 
						if(r.getColore().get(v) == bianco) {
							//color [v] <- gray
							r.getColore().put(v, grigio);
							//enqueue(D,v,d[v])
							for(Iterator<Edge> rr = this.edgeSet().iterator(); rr.hasNext(); ) {
								ArcoDirezionato ar = (ArcoDirezionato) rr.next();
								if(ar.getSource().equals(u) && ar.getTarget().equals(v)) {
									//if d[v] > d[u] + W(u,v)
									if(r.getDistanzeVertici().get(v) > (r.getDistanzeVertici().get(u) + this.getEdgeWeight(this.getEdge(u, v)))) {
										//pigreco[v] <- u
										r.getPredecessori().put(v, u);
										//d[v] <- d[u] + W(u,v)
										r.getDistanzeVertici().put(v, r.getDistanzeVertici().get(u) + this.getEdgeWeight(this.getEdge(u, v)));
										//decrease_key(D,v,d[v])
										tree.put((int) r.getDistance(v), v);
									}
								}
							}
						}
					}
				}
				r.getColore().put(u, nero);
			}
			return r;
		}
		return null;
	}
	
	public boolean isDAG() {
		if(isCyclic())
			return false;
		return true;
	}
	
	public boolean isCyclic() {
		RicercaGrafo r =  new RicercaGrafo(this);
		for(Iterator<Vertex> it1 = r.getColore().keySet().iterator(); it1.hasNext(); ) {
			Vertex chiaveCorrente = it1.next();
			if(r.getColore().get(chiaveCorrente) == bianco && visitaRicCiclo(r, this, chiaveCorrente))
				return true;
		}
		return false;
	}
	
	private boolean visitaRicCiclo(RicercaGrafo r, DirectedWeightedGraphAdjListImpl g, Vertex u) {	
		r.getColore().put(u, this.grigio);
		//for ogni v adiacente ad u
		for(Iterator<Vertice> it1 = ((Vertice)u).getLista().keySet().iterator(); it1.hasNext(); ) {
			Vertice v = it1.next();
			//if esiste v bianco
			if(r.getColore().get(v) == bianco) {
				r.getPredecessori().put(v, u);
				if(visitaRicCiclo(r, g, v))
					return true;
			}
			if(r.getColore().get(v) == grigio) {
				return true;
			}
		}		
		r.getColore().put(u, this.nero);
		return false;
	}

	public Vertex[] topologicalSort() {
		if(!this.isDAG())
			throw new UnsupportedOperationException("Il grafo su cui si sta tentando di fare l'ordinamento topologico non è un DAG");
		int n = this.grafo.size();
		Vertex ord[] = new Vertex[n]; 
		int i = 0;
		RicercaGrafo r1 = new RicercaGrafo(this);
		Stack<Vertex> s = new Stack<Vertex>();
		HashMap<Integer, Vertex> cc = new HashMap<Integer, Vertex>();
		s.addAll(this.vertexSet());
		r1 = (RicercaGrafo) this.visit(SearchType.DFS_TOT, s.peek());
		int g;
		//scambia il valore con la chiave per tutti gli elementi dell'hashmap
		for(Iterator<Vertex> it1 = r1.getTempiChiusura().keySet().iterator(); it1.hasNext(); ) {
			Vertex ul = it1.next();
			g = r1.getTempiChiusura().get(ul);
			cc.put(g, ul);
		}
		//ordiniamo le chiavi in ordine decrescente
		TreeMap<Integer, Vertex> o = new TreeMap<Integer, Vertex>(Collections.reverseOrder());
		o.putAll(cc);	
		//mettiamo nell'array da ritornare i vertici in ordine da quello 
		//con tempo di fine maggiore in prima posizione fino a quello con tempo minore in ultima
		for(Iterator<Vertex> it = o.values().iterator(); it.hasNext(); ) {//Dobbiamo vedere se tra tutti gli archi ci sono archi che toccano u
			Vertex w = it.next();
			ord[i] = w;
			i++;
		}
		return ord;
	}

	public Collection<Collection<Vertex>> stronglyConnectedComponents() {
		if(this instanceof DirectedWeightedGraphAdjListImpl) {
			ArrayList<Vertice> liord = new ArrayList<Vertice>();
			/*1. Visita G con l’algoritmo VISITA_TUTTI_I_VERTICI-DFS e costruisci
			una lista dei vertici in ordine decrescente dei tempi di fine visita*/
			int n = this.grafo.size();
			Vertex ord[] = new Vertex[n]; 
			int i = 0;			
			Stack<Vertex> s = new Stack<Vertex>();
			s.addAll(this.vertexSet());
			RicercaGrafo r1 = (RicercaGrafo) this.visit(SearchType.DFS_TOT, s.peek());
			//scambia il valore con la chiave per tutti gli elementi dell'hashmap
			int g;
			HashMap<Integer, Vertex> TcV = new HashMap<Integer, Vertex>();
			for(Iterator<Vertex> it1 = r1.getTempiChiusura().keySet().iterator(); it1.hasNext(); ) {
				Vertex ul = it1.next();
				g = r1.getTempiChiusura().get(ul);
				TcV.put(g, ul);
			}
			//ordiniamo le chiavi (i tempi di fine chiusura) in ordine decrescente
			TreeMap<Integer, Vertex> TcVO = new TreeMap<Integer, Vertex>(Collections.reverseOrder());
			TcVO.putAll(TcV);				
			//mettiamo nell'array da ritornare i vertici in ordine da quello 
			//con tempo di fine maggiore in prima posizione fino a quello con tempo minore in ultima
			for(Iterator<Vertex> it = TcVO.values().iterator(); it.hasNext(); ) {//Dobbiamo vedere se tra tutti gli archi ci sono archi che toccano u
				Vertex w = it.next();
				ord[i] = w;
				liord.add((Vertice)w);
				i++;
			}
			//2. Costruisci GT costruisco nuovo grafo
			for(Edge e : this.edgeSet()) {
				ArcoDirezionato vecchio = (ArcoDirezionato) e;
				Vertex v1 = vecchio.getSource();
				Vertex v2 = vecchio.getTarget();
				this.grafo.add((Vertice) v2);
				((Vertice)v2).getLista().put((Vertice)v1, e.getEdgeWeight());
				this.grafo.remove((Vertice) v2);
				((Vertice)v1).getLista().remove((Vertice)v2, e.getEdgeWeight());
			}
			/*3. Visita GT con l’algoritmo VISITA_TUTTI_I_VERTICI-DFS
			considerando, nel ciclo principale dell’algoritmo, i vertici
			nell’ordine trovato al passo 1.*/
			Vertice source = liord.get(0);			
			RicercaGrafo rr = dfs(this, source, SearchType.DFS_TOT, 1, liord);
			return rr.cfcs;
		
		}
		else
			throw new UnsupportedOperationException("Grafo non direzionato"); 
	}

	public String toStringSCC() {
		System.out.println(this.stronglyConnectedComponents());
		return this.stronglyConnectedComponents().toString();
	}

}