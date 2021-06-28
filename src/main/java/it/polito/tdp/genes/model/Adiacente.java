package it.polito.tdp.genes.model;

public class Adiacente implements Comparable<Adiacente>{
	
	private Genes g;
	private Double peso;
	public Adiacente(Genes g, double peso) {
		super();
		this.g = g;
		this.peso = peso;
	}
	public Genes getG() {
		return g;
	}
	public void setG(Genes g) {
		this.g = g;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Adiacente o) {
		// TODO Auto-generated method stub
		return - this.peso.compareTo(o.getPeso());
	}
	
	

}
