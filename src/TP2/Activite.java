package TP2;

import java.util.ArrayList;

public class Activit� {
	private String nom;
	private ArrayList<Groupe> groupes;
	private ArrayList<Salle> sallesAppropri�es;

	public Activit�(String nom) {
		this.nom = new String(nom);
		groupes = new ArrayList<Groupe>();
		sallesAppropri�es = new ArrayList<Salle>();
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
		sallesAppropri�es.add(s);
	}

	public ArrayList<Salle> getSalles() {
		return sallesAppropri�es;
	}

	public ArrayList<Groupe> getGroupes() {
		return groupes;
	}

	public String toString() {
		return "Activit� " + nom + " (" + groupes + ")";
	}
}
