package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private Graph<Airport,DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO dao=new ExtFlightDelaysDAO();
	List<Airport> aereoporti=dao.loadAllAirports();
	Map<Integer,Airport> mappaAereoporti=new HashMap<Integer,Airport>();
	String s="";


	public void creaGrafo(Integer media) {
		if(mappaAereoporti==null || mappaAereoporti.size()==0) {
			for(Airport a:aereoporti)
				mappaAereoporti.put(a.getId(), a);
		}
		grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, aereoporti);
		List<MediaVoli> archi=dao.aggiungiVertici(media);
		for(MediaVoli m: archi) {
			Airport aereoporto_p=this.mappaAereoporti.get(m.getId_partenza());
			Airport aereoporto_a=this.mappaAereoporti.get(m.getId_arrivo());
			if(verifica(aereoporto_p,aereoporto_a)==false) {
				Graphs.addEdge(this.grafo, aereoporto_p, aereoporto_a, m.getMedia());
				s=s+aereoporto_p.getAirportName()+" - "+aereoporto_a.getAirportName()+" : "+m.getMedia()+"\n";}
		}
		
		
	}
	
	public Integer numeroVertici() {
		return grafo.vertexSet().size();
	}
	
	public Integer numeroArchi() {
		return grafo.edgeSet().size();
	}
	
	public String result() {
		if(s!="")
			return s;
			else
				return null;
	}
	
	private boolean verifica(Airport p, Airport a) {
		return grafo.containsEdge(a,p);
	}
}
