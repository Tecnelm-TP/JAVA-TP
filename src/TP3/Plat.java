package TP3;


public class Plat implements Consommable,Nutrition{

	private String nom;
	private int prix; // en cents d'euros
	private int kCal;
	private int glucide;
	

	public Plat(String nom, int prix) {
		this.nom = nom;
		this.prix = prix;
	}


	public Plat(String nom, int prix, int kCal, int glucide) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.kCal = kCal;
		this.glucide = glucide;
	}


	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}


	@Override
	public int getPrix() {
		// TODO Auto-generated method stub
		return prix;
	}


	@Override
	public int getKcal() {
		// TODO Auto-generated method stub
		return kCal;
	}


	@Override
	public float getGlucides() {
		// TODO Auto-generated method stub
		return glucide;
	}
	
}
