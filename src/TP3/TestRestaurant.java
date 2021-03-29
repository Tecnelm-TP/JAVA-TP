package TP3;

import java.util.ArrayList;

public class TestRestaurant {

	public static void main(String[] args) {
		Carte carte = new Carte("/test1.carte");
		ArrayList<Consommable> allElem = carte.getAllelem();

		Entree e = new Entree("Salade verte", 4, 100, 0);
		Entree e1 = new Entree("Salade composee", 6, 100, 0);

		PlatPrincipal p1 = new PlatPrincipal("Pizza Reine", 9, 400, 10);
		PlatPrincipal p2 = new PlatPrincipal("Pizza Margarita", 8, 400, 10);
		PlatPrincipal p3 = new PlatPrincipal("Spaghetti à la Bolognaise", 25, 500, 0);
		PlatPrincipal p4 = new PlatPrincipal("Spaghetti à la Bolognaise", 25, 500, 0);

		Dessert d = new Dessert("Tiramisu", 4, 100, 100);

		Boisson b = new Boisson(1000, "Eau", 0, 0, 0);

		Carte carte1 = new Carte();

		carte1.addBoisson(b);

		carte1.addDessert(d);

		carte1.addEntree(e);
		carte1.addEntree(e1);

		carte1.addPlatPrincipal(p1);
		carte1.addPlatPrincipal(p2);
		carte1.addPlatPrincipal(p3);
		carte1.addPlatPrincipal(p4);

		Menu m = new Menu(15, e, p1, d, b);

		carte1.addMenu(m);

		Commande c = new Commande();

		c.addItem(p2);
		c.addItem(p1);
		c.addItem(b);
		c.addItem(d);


		int prixCommande = carte1.calculerPrixCommande(c);
		System.out.println("Prix de la commande : "+prixCommande);
		carte.proposerMenu(400, 400);

		return;
	}

}
