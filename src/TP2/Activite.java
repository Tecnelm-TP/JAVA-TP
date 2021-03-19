package TP2;

import java.util.ArrayList;

public class Activité {
	private String nom;
	private ArrayList<Groupe> groupes;
	private ArrayList<Salle> sallesAppropriées;

	public Activité(String nom) {
		this.nom = new String(nom);
		groupes = new ArrayList<Groupe>();
		sallesAppropriées = new ArrayList<Salle>();
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
		sallesAppropriées.add(s);
	}

	public ArrayList<Salle> getSalles() {
		return sallesAppropriées;
	}

	public ArrayList<Groupe> getGroupes() {
		return groupes;
	}

	public String toString() {
		return "Activité " + nom + " (" + groupes + ")";
	}
}
