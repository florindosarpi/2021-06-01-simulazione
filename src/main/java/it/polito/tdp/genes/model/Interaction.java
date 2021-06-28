package it.polito.tdp.genes.model;

public class Interaction {

	private Genes g1;
	private Genes g2;
	private double corr;
	public Interaction(Genes g1, Genes g2, double corr) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.corr = corr;
	}
	public Genes getG1() {
		return g1;
	}
	public void setG1(Genes g1) {
		this.g1 = g1;
	}
	public Genes getG2() {
		return g2;
	}
	public void setG2(Genes g2) {
		this.g2 = g2;
	}
	public double getCorr() {
		return corr;
	}
	public void setCorr(double corr) {
		this.corr = corr;
	}
	
	
		
}