package TP2;

abstract public class Salle {
	private int capacité;
	private String nom;
	
	public Salle(int c, String n){
		capacité = c;
		nom = new String(n);
	}
	
	public String toString(){
		return nom + " (" + capacité + " places)";
	}
	
	public int getCapacité(){
		return capacité;
	}
	
	public String getNom(){
		return nom;
	}
}
