package TP2;

import java.util.ArrayList;

public class Activite {
	private String nom;
	private ArrayList<Groupe> groupes;
	private ArrayList<Salle> sallesAppropriees;

	public Activite(String nom) {
		this.nom = new String(nom);
		groupes = new ArrayList<Groupe>();
		sallesAppropriees = new ArrayList<Salle>();
	}

	public void addGroupe(Groupe groupe) {
		groupes.add(groupe);
	}
	
	public void addGroupe(Promotion promo)
	{
		groupes.addAll(promo.getSousgroupe());
	}

	public void setGroupes(Promotion promo) {
		this.groupes = promo.getSousgroupe();
	}

	protected void addSalle(Salle s) {
		sallesAppropriees.add(s);
	}

	public ArrayList<Salle> getSalles() {
		return sallesAppropriees;
	}

	public ArrayList<Groupe> getGroupes() {
		return groupes;
	}

	public String toString() {
		return "Activite " + nom + " (" + groupes + ")";
	}
}
