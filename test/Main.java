package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import graph.Edge;
import graph.SearchType;
import graph.Vertex;
import impl.ArcoDirezionato;
import impl.DirectedWeightedGraphAdjListImpl;
import impl.RicercaGrafo;
import impl.Vertice;

public class Main {

	public static Scanner tastiera;
	public static DirectedWeightedGraphAdjListImpl g;
	
	public static void main(String[] args) {
		
		tastiera = new Scanner(System.in);
		DirectedWeightedGraphAdjListImpl g = new DirectedWeightedGraphAdjListImpl();
		int scelta = 0;
		
		System.out.println("1. Aggiungere un Vertice a questo Grafo");
		System.out.println("2. Aggiungere un Arco a questo Grafo");
		System.out.println("3. Controllare se un certo Vertice è contenuto in questo Grafo");
		System.out.println("4. Controllare se un certo Arco è contenuto in questo Grafo inserendo i Vertici di arrivo e partenza");
		System.out.println("5. Elencare tutti i Vertici");
		System.out.println("6. Elencare tutti gli Archi");
		System.out.println("7. Ottenere tutti gli Archi toccanti quel Vertice");
		System.out.println("8. Ottenere tutti gli Archi entranti in quel Vertice");
		System.out.println("9. Ottenere tutti gli Archi uscenti da quel Vertice");
		System.out.println("10. Ottenere il grado di quel Vertice");
		System.out.println("11. Ottenere il grado degli Archi entranti in Vertice");
		System.out.println("12. Ottenere il grado degli Archi uscenti da Vertice");
		System.out.println("13. Associare un peso ad un Arco");
		System.out.println("14. Ottenere il peso di un Arco");
		System.out.println("15. Rimuovere un Vertice da un Grafo");
		System.out.println("16. Rimuovere un Arco da un Grafo");
		System.out.println("17. Rimuovere tutti gli Archi nella collezione in input da un Grafo");			
		System.out.println("18. Rimuovere tutti i Vertici da un Grafo");
		System.out.println("19. Il Grafo è direzionato?");	
		System.out.println("20. Visita BFS");	
		System.out.println("21. Visita DFS");	
		System.out.println("22. Visita DFS_TOT");	
		System.out.println("23. Trovare i cammini minimi con Dijkstra");	
		System.out.println("24. Ordinamento Topologico");	
		System.out.println("25. Trova le CFC");	
		System.out.println("26. Controllare se un certo Arco è contenuto in questo Grafo inserendo l'Arco");
		System.out.println("27. Uscire");	
		
		while(scelta != 27) {
			
			System.out.println();
			System.out.println("GRAFO:");
			g.toString();
			System.out.println();
			System.out.println("Scegli un' azione:");
			scelta = tastiera.nextInt();

			if(scelta < 0 || scelta > 27) 
				throw new IllegalArgumentException();
				
			if(scelta == 1) {
				Vertice v = null;
				System.out.println("Inserire l' etichetta del Vertice: ");
				tastiera.nextLine();
				v = new Vertice(tastiera.nextLine(), g);
				System.out.println();
				System.out.println(g.addVertex(v));
			}
			
			if(scelta == 2) {
				Vertice partenza = null;
				Vertice arrivo = null;
				double peso;
				System.out.println("Inserisci Vertice di partenza dell' Arco: ");
				tastiera.nextLine();//per correggere l'errore in cui salta un input
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						partenza = (Vertice)v;
					}
				}
				System.out.println("Inserisci Vertice di arrivo dell' Arco: ");
				String s2 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s2)) {
						arrivo = (Vertice)v;
					}
				}
				System.out.println("Inserisci peso dell' Arco: ");
				peso = tastiera.nextInt();
				ArcoDirezionato ad = new ArcoDirezionato(partenza, arrivo, peso, g);
				System.out.println();
				System.out.println(g.addEdge(ad));
			}
			
			if(scelta == 3) {
				Vertice vertice = null;
				System.out.println("Inserire l' etichetta del Vertice: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				System.out.println();
				System.out.println(g.containsVertex(vertice));
			}
			
			if(scelta == 4) {
				Vertice partenza = null;
				Vertice arrivo = null;
				System.out.println("Inserisci Vertice di partenza dell' Arco: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				partenza = new Vertice(s1, g);
				System.out.println("Inserisci Vertice di arrivo dell' Arco: ");
				String s2 = tastiera.nextLine();
				arrivo = new Vertice(s2, g);	
				System.out.println();
				System.out.println(g.containsEdge(partenza, arrivo));
			}
			
			if(scelta == 5) {
				System.out.println();
				System.out.println(g.vertexSet());
			}
			
			if(scelta == 6) {
				System.out.println();
				System.out.println(g.edgeSet());
			}
			
			if(scelta == 7) {
				Vertice vertice = null;
				System.out.println("Inserire l' etichetta del Vertice: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				System.out.println();
				System.out.println(g.edgesOf(vertice));
			}

			if(scelta == 8){
				Vertice vertice = null;
				System.out.println("Inserire l' etichetta del Vertice: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				System.out.println();
				System.out.println(g.incomingEdgesOf(vertice));
			}
			
			if(scelta == 9){
				Vertice vertice = null;
				System.out.println("Inserire l' etichetta del Vertice: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				System.out.println();
				System.out.println(g.outgoingEdgesOf(vertice));
			}
			
			if(scelta == 10){
				Vertice vertice = null;
				System.out.println("Inserire l' etichetta del Vertice: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				System.out.println();
				System.out.println(g.degreeOf(vertice));
			}
			
			if(scelta == 11){
				Vertice vertice = null;
				System.out.println("Inserire l' etichetta del Vertice: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				System.out.println();
				System.out.println(g.inDegreeOf(vertice));
			}
			
			if(scelta == 12){
				Vertice vertice = null;
				System.out.println("Inserire l' etichetta del Vertice: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				System.out.println();
				System.out.println(g.outDegreeOf(vertice));
			}
			
			if(scelta == 13) {
				Vertice partenza = null;
				Vertice arrivo = null;
				System.out.println("Inserisci Vertice di partenza dell' Arco: ");
				tastiera.nextLine();//per correggere l'errore in cui salta un input
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						partenza = (Vertice)v;
					}
				}
				System.out.println("Inserisci Vertice di arrivo dell' Arco: ");
				String s2 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s2)) {
						arrivo = (Vertice)v;
					}
				}
				ArcoDirezionato ad = new ArcoDirezionato(partenza, arrivo, 1.0, g);
				System.out.println("Inserisci il nuovo peso: ");
				double p = tastiera.nextDouble();
				System.out.println();
				System.out.println("Vecchio peso: " + g.getEdgeWeight(ad));
				g.setEdgeWeight(ad, p);
				System.out.println("Nuovo peso: " + g.getEdgeWeight(ad));
			}
			
			if(scelta == 14) { 
				Vertice partenza = null;
				Vertice arrivo = null;
				System.out.println("Inserisci Vertice di partenza dell' Arco: ");
				tastiera.nextLine();//per correggere l'errore in cui salta un input
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						partenza = (Vertice)v;
					}
				}
				System.out.println("Inserisci Vertice di arrivo dell' Arco: ");
				String s2 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s2)) {
						arrivo = (Vertice)v;
					}
				}
				ArcoDirezionato ad = new ArcoDirezionato(partenza, arrivo, 1.0, g);
				System.out.println();
				System.out.println(g.getEdgeWeight(ad));
			}
			
			if(scelta == 15) {
				Vertice vertice = null;
				System.out.println("Inserire l' etichetta del Vertice: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				System.out.println();
				System.out.println(g.removeVertex(vertice));
			}
			
			if(scelta == 16) {
				Vertice partenza = null;
				Vertice arrivo = null;
				System.out.println("Inserisci Vertice di partenza dell' Arco: ");
				tastiera.nextLine();//per correggere l'errore in cui salta un input
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						partenza = (Vertice)v;
					}
				}
				System.out.println("Inserisci Vertice di arrivo dell' Arco: ");
				String s2 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s2)) {
						arrivo = (Vertice)v;
					}
				}
				ArcoDirezionato ad = new ArcoDirezionato(partenza, arrivo, 1.0, g);
				System.out.println();
				System.out.println(g.removeEdge(ad));
			}
			
			if(scelta == 17) {
				Set<Edge> archiRimossi = new HashSet<Edge>();
				Vertice partenza = null;
				Vertice arrivo = null;
				double peso;
				int i = 1;
				while(i == 1) {
					System.out.println("Inserisci Vertice di partenza dell' Arco: ");
					tastiera.nextLine();
					String s1 = tastiera.nextLine();
					partenza = new Vertice(s1, g);
					System.out.println("Inserisci Vertice di arrivo dell' Arco: ");
					String s2 = tastiera.nextLine();
					arrivo = new Vertice(s2, g);
					System.out.println("Inserisci peso dell' Arco: ");
					peso = tastiera.nextInt();
					ArcoDirezionato ad = new ArcoDirezionato(partenza, arrivo, peso, g);
					archiRimossi.add(ad);
					System.out.println(archiRimossi);
					System.out.println("Premere 1 per inserire un'altro arco");
					i = tastiera.nextInt();
				}
				System.out.println();
				System.out.println(g.removeAllEdges(archiRimossi));
			}
			
			if(scelta == 18) {
				System.out.println(g.removeAllVertices(g.vertexSet()));
				System.out.println();
				System.out.println("vertexset: " + g.vertexSet());
			}
			
			if(scelta == 19) {
				System.out.println();
				System.out.println(g.isDirected());
			}
			
			if(scelta == 20) {
				Vertice vertice = null;
				System.out.println("Inserire il vertice scelto come sorgente: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				RicercaGrafo r = (RicercaGrafo) g.visit(SearchType.BFS, vertice);
				System.out.println();
				System.out.println("Tempi Scoperta = " + r.getTempiScoperta());
				System.out.println("Tempi Chiusura = " + r.getTempiChiusura());
			}
				
			if(scelta == 21) {
				Vertice vertice = null;
				System.out.println("Inserire il vertice scelto come sorgente: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				RicercaGrafo r = (RicercaGrafo) g.visit(SearchType.DFS, vertice);
				System.out.println();
				System.out.println("Tempi Scoperta = " + r.getTempiScoperta());
				System.out.println("Tempi Chiusura = " + r.getTempiChiusura());
			}
			
			if(scelta == 22) {
				Vertice vertice = null;
				System.out.println("Inserire il vertice scelto come sorgente: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				RicercaGrafo r = (RicercaGrafo) g.visit(SearchType.DFS_TOT,vertice);
				System.out.println();
				System.out.println("Tempi Scoperta = " + r.getTempiScoperta());
				System.out.println("Tempi Chiusura = " + r.getTempiChiusura());
			}
			
			if(scelta == 23) {
				Vertice vertice = null;
				System.out.println("Inserire il vertice scelto come sorgente: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				for(Vertex v : g.vertexSet()) {
					if(((Vertice)v).getLabel().equals(s1)) {
						vertice = (Vertice)v;
					}
				}
				RicercaGrafo r = (RicercaGrafo) g.visit(SearchType.DIJKSTRA, vertice);
				System.out.println();
				System.out.println("Distanze Vertici = " + r.getDistanzeVertici());
			}
						
			if(scelta == 24) {
				Vertex ord[] = g.topologicalSort();
				ArrayList<Vertex> f = new ArrayList<Vertex>();
				System.out.println();
				System.out.println("Ordinamento Topologico: ");
				/*for(int j = 0; j < ord.length; j++) {
					System.out.println(ord[j]);
				}*/
				for(Vertex e : ord) {
					f.add(e);
				}
				System.out.println(f);
			}
								
			if(scelta == 25) {
				System.out.println();
				System.out.println("Le CFC sono: " + g.stronglyConnectedComponents());
			}

			if(scelta == 26) {
				Vertice partenza = null;
				Vertice arrivo = null;
				System.out.println("Inserisci Vertice di partenza dell' Arco: ");
				tastiera.nextLine();
				String s1 = tastiera.nextLine();
				partenza = new Vertice(s1, g);
				System.out.println("Inserisci Vertice di arrivo dell' Arco: ");
				String s2 = tastiera.nextLine();
				arrivo = new Vertice(s2, g);				
				ArcoDirezionato ad = new ArcoDirezionato(partenza, arrivo, 1.0, g);
				System.out.println(g.containsEdge(ad));
				System.out.println();
			}

			if(scelta == 27)
				System.exit(1);
			
		}
		System.exit(1);
	}
}
