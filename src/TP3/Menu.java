package TP3;

import java.util.ArrayList;

public class Menu {
	ArrayList<Consommable> items;
	int prix; // en cents

	public Menu(int prix, Entree e, PlatPrincipal p, Dessert d, Boisson b) {
		items = new ArrayList<>();
		this.prix = prix; /// ajout de chaque element du menu dans la liste des items et sauvegarde du
							/// prix
		items.add(e);
		items.add(p);
		items.add(d);
		items.add(b);
		if (!verifPrixMenu())
			System.exit(1);

	}

	public ArrayList<Consommable> getItems() {
		return this.items;
	}

	public int getPrix() {
		return this.prix;
	}

	@Override
	public String toString() {
		String message = "Menu compose de ";
		for (Consommable c : this.items) {
			message += c.getNom() + ", "; /// on rajoute le nom de chaque element dans la chaine de charactere
		}

		message += "au prix de " + this.prix + " euros";
		return message;
	}

	private boolean verifPrixMenu() {
		int sommePrix = 0;
		for (Consommable c : this.items) {
			sommePrix += c.getPrix();
		}
		return sommePrix >= this.prix && sommePrix > 0;
	}
}
