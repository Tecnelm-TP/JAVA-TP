package TP3;

public class Boisson implements Consommable, Nutrition {

	private int volume; // en centilitres
	private String nom;
	private int prix; // en cents d'euros
	private int kCal;
	private int glucide;
	

	public Boisson(int volume, String nom, int prix) {		
		this(-1,nom,prix,-1,-1);
	}

	public Boisson(String nom, int prix, int volume) {
		this(volume,nom,prix,-1,-1);
	}

	public Boisson(int volume, String nom, int prix, int kCal, int glucide) {
		this.volume = volume;
		this.nom = nom;
		this.prix = prix;
		this.kCal = kCal;
		this.glucide = glucide;
	}

	public int getVolume() {
		return volume;
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
