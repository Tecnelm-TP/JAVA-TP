package TP3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Parser.Parser;

public class Carte {
	private ArrayList<Consommable> entrees;
	private ArrayList<Consommable> platsPrincipaux;
	private ArrayList<Consommable> desserts;
	private ArrayList<Consommable> boissons;

	private ArrayList<Menu> menus;

	public Carte (String fileName) {
		entrees = new ArrayList<Consommable>();
		platsPrincipaux = new ArrayList<Consommable>();/// creation des listes d'element
		desserts = new ArrayList<Consommable>();
		boissons = new ArrayList<Consommable>();
		menus = new ArrayList<Menu>();

		try {
			String path = getClass().getResource(fileName).getPath();
			BufferedReader reader = new BufferedReader(new FileReader(path)); /// ouverture du fichier
			String line;
			StringBuilder sb = new StringBuilder(); /// recuperation du contenu du fichier
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			new Parser(sb.toString(), this).getcarte(); /// creation de la carte a partir du fichier

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public Carte() {
		entrees = new ArrayList<Consommable>();
		platsPrincipaux = new ArrayList<Consommable>();/// creation des listes d'element
		desserts = new ArrayList<Consommable>();
		boissons = new ArrayList<Consommable>();
		menus = new ArrayList<Menu>();
	}

	public void addEntree(Entree e) {
		if (verifCarte(e))
			this.entrees.add(e);
	}

	public void addPlatPrincipal(PlatPrincipal p) {
		if (verifCarte(p))
			this.platsPrincipaux.add(p);
	}

	public void addDessert(Dessert d) {
		if (verifCarte(d))
			this.desserts.add(d);
	}

	public void addBoisson(Boisson b) {
		if (verifCarte(b))
			this.boissons.add(b);
	}

	public void addMenu(Menu m) {
		if (verifMenu(m)) {
			this.menus.add(m);
		}
	}

	public ArrayList<Consommable> getEntrees() {
		return this.entrees;
	}

	public ArrayList<Consommable> getPlatsPrincipaux() {
		return this.platsPrincipaux;
	}

	public ArrayList<Consommable> getDesserts() {
		return this.desserts;
	}

	public ArrayList<Consommable> getBoissons() {
		return this.boissons;
	}

	// Verifie que les plats et boissons du menu sont bien dans la carte
	private boolean verifMenu(Menu m) {
		boolean result = true;
		if (m != null) {
			for (int i = 0; i < m.getItems().size() && result; i++) {

				result = this.getBoissons().contains(m.getItems().get(i))
						|| this.getDesserts().contains(m.getItems().get(i));
				result = result || this.getEntrees().contains(m.getItems().get(i))
						|| this.getPlatsPrincipaux().contains(m.getItems().get(i));
			}
		} else
			result = false;
		return result;
	}

	// Verifie qu'il n'y a pas d'homonymes dans la carte
	private boolean verifCarte(Consommable c) {
		boolean result = false;
		if (c != null) {

			for (int i = 0; i < this.getBoissons().size() && !result; i++) {
				result = c.getNom().equalsIgnoreCase(this.getBoissons().get(i).getNom());
			}

			for (int i = 0; i < this.getDesserts().size() && !result; i++) {
				result = c.getNom().equalsIgnoreCase(this.getDesserts().get(i).getNom());

			}

			for (int i = 0; i < this.getEntrees().size() && !result; i++) {
				result = c.getNom().equalsIgnoreCase(this.getEntrees().get(i).getNom());

			}
			for (int i = 0; i < this.getPlatsPrincipaux().size() && !result; i++) {
				result = c.getNom().equalsIgnoreCase(this.getPlatsPrincipaux().get(i).getNom());
			}
		} else
			result = true;

		return !result;

	}

	/*
	 * Calcule le prix de la commande. A priori, ce prix est la somme des prix des
	 * items SAUF si une partie de ces items constituent un menu; dans ce cas, le
	 * tarif menu s'applique pour ces items.
	 */

	public int calculerPrixCommande(Commande c) {
		int prix = 0;

		if (c == null)
			return 0;
		ArrayList<Consommable> consoTemp = new ArrayList<Consommable>(c.getItemsCommandes());/// cree une copie des
																								/// plats pour pouvoir
																								/// /// facilement
																								/// effectuer
																								/// des modifications
																								/// dessus
		for (Menu menu : this.menus) { /// parcourt tous les menus
			while (consoTemp.containsAll(menu.getItems())) {
				for (Consommable conso : menu.getItems()) {
					consoTemp.remove(conso);
				}
				prix += menu.getPrix();
			}
		}
		for (Consommable conso : consoTemp) { /// ajout de tous les derniers plats hors menu
			prix += conso.getPrix();
		}
		return prix;
	}

	public void afficherMenu() {
		System.out.println("Liste des entrees:" + entrees);
		System.out.println("Liste des plats principaux:" + platsPrincipaux);
		System.out.println("Liste des desserts:" + desserts);
		System.out.println("Liste des boissons:" + boissons);

	}

	public void proposerMenu(int Kc, int epsilon) {
		int nbkcal = 0;
		for (Menu m : this.menus) {
			for (Consommable c : m.getItems()) {
				if (c instanceof Nutrition) /// si la valeur est -1 alors la valeur
					if (((Nutrition) c).getKcal() != -1)/// n'est pas renseigne
						nbkcal += ((Nutrition) c).getKcal(); /// verifie que la classe contienne bien les information
																/// calorique
			}
			if (nbkcal <= Kc + epsilon && nbkcal >= Kc - epsilon)
				System.out.println(m);
			nbkcal = 0;
		}
	}

	public ArrayList<Consommable> getAllelem() {
		ArrayList<Consommable> elem = new ArrayList<>();
		elem.addAll(platsPrincipaux);
		elem.addAll(desserts);
		elem.addAll(boissons);
		elem.addAll(entrees);
		return elem;

	}

	public Consommable getElemByName(String name) {
		Consommable ret = null;
		for (Consommable c : getAllelem()) {
			if (c.getNom().equalsIgnoreCase(name))
				ret = c;
		}
		return ret;
	}

}
