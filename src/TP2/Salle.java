package TP2;

abstract public class Salle {
	private int capacit�;
	private String nom;
	
	public Salle(int c, String n){
		capacit� = c;
		nom = new String(n);
	}
	
	public String toString(){
		return nom + " (" + capacit� + " places)";
	}
	
	public int getCapacit�(){
		return capacit�;
	}
	
	public String getNom(){
		return nom;
	}
}
