package it.polito.tdp.genes.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	GenesDao dao;
	Map<String, Genes> idMap;
	SimpleWeightedGraph<Genes, DefaultEdge> grafo;
	
	public Model(){
		dao = new GenesDao();
		idMap = new LinkedHashMap<>();
	}
	
	public List<Genes> getVertici(){
		return this.dao.getVertici(idMap);
	}
	
	public List<String> getVerticiID(){
		List<String> result = new LinkedList<>();
		for(Genes g : this.dao.getVertici(idMap)) {
			result.add(g.toString());
		}
		return result;
	}
	
	public String creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultEdge.class);
		Graphs.addAllVertices(this.grafo, getVertici());
		
		List<Interaction> interazioni = dao.getArchi(idMap);
		double peso = 0;
		
		for (Interaction i : interazioni) {
			if(grafo.containsVertex(i.getG1()) && grafo.containsVertex(i.getG2())) {
				if(i.getG1().getChromosome() != i.getG2().getChromosome()) {
					peso = Math.abs(i.getCorr());
					Graphs.addEdge(grafo, i.getG1(), i.getG2(), peso);
				} else if(i.getG1().getChromosome() == i.getG2().getChromosome()) {
					peso = 2.0* Math.abs(i.getCorr());
					Graphs.addEdge(grafo, i.getG1(), i.getG2(), peso);
				}
			}
		}
		
		
		
		return String.format("#Veritici: " +grafo.vertexSet().size() +"\n" + "#Archi: " +grafo.edgeSet().size());
	}
	
	public List<String> getAdiacenti(String g){
		List<String> resultString = new LinkedList<>();
		Genes gene = idMap.get(g);
		List<Genes> result = new LinkedList<>(Graphs.neighborListOf(grafo, gene));
		List<Adiacente> adiacenti = new LinkedList<>();
		for (Genes e : result) {
			adiacenti.add(new Adiacente(e, this.grafo.getEdgeWeight(this.grafo.getEdge(gene, e))));
		}
		Collections.sort(adiacenti);
		for (Adiacente a : adiacenti) {
			resultString.add("Gene " +a.getG() + " con peso: " +a.getPeso() +"\n");
		}
		
		
		return resultString;
	}
	
	
	
}
