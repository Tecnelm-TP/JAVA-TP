package TP3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import  Parser.Parser;

public class Carte {
	private ArrayList<Consommable> entrées;
	private ArrayList<Consommable> platsPrincipaux;
	private ArrayList<Consommable> desserts;
	private ArrayList<Consommable> boissons;

	private ArrayList<Menu> menus;
	
	public Carte createCarte(String fileName )
	{
		
		
		try {
			String path = getClass().getResource(fileName).getPath();
			BufferedReader reader= new BufferedReader(new FileReader(path));
			String line ;
			StringBuilder sb = new StringBuilder();
			while((line = reader.readLine())!=null)
			{
				sb.append(line);
			}
			 new Parser(sb.toString(),this).getcarte();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this;

	}


	public Carte() {
		entrées = new ArrayList<Consommable>();
		platsPrincipaux = new ArrayList<Consommable>();
		desserts = new ArrayList<Consommable>();
		boissons = new ArrayList<Consommable>();
		menus = new ArrayList<Menu>();
	}

	public void addEntrée(Entrée e) {
		if (verifCarte(e))
			this.entrées.add(e);
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

	public ArrayList<Consommable> getEntrées() {
		return this.entrées;
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

	// Vérifie que les plats et boissons du menu sont bien dans la carte
	private boolean verifMenu(Menu m) {
		boolean result = true;
		for (int i = 0; i < m.getItems().size() && result; i++) {
			result = this.getBoissons().contains(m.getItems().get(i))
					|| this.getDesserts().contains(m.getItems().get(i));
			result = result || this.getEntrées().contains(m.getItems().get(i))
					|| this.getPlatsPrincipaux().contains(m.getItems().get(i));
		}
		return result;
	}

	// Vérifie qu'il n'y a pas d'homonymes dans la carte
	private boolean verifCarte(Consommable c) {
		boolean result = false;

		for (int i = 0; i < this.getBoissons().size() && !result; i++) {
			result = c.getNom().equals(this.getBoissons().get(i).getNom());
		}

		for (int i = 0; i < this.getDesserts().size() && !result; i++) {
			result = c.getNom().equals(this.getDesserts().get(i).getNom());

		}

		for (int i = 0; i < this.getEntrées().size() && !result; i++) {
			result = c.getNom().equals(this.getEntrées().get(i).getNom());

		}
		for (int i = 0; i < this.getPlatsPrincipaux().size() && !result; i++) {
			result = c.getNom().equals(this.getPlatsPrincipaux().get(i).getNom());
		}
		return !result;

	}

	/*
	 * Calcule le prix de la commande. A priori, ce prix est la somme des prix des
	 * items SAUF si une partie de ces items constituent un menu; dans ce cas, le
	 * tarif menu s'applique pour ces items.
	 */
	public int calculerPrixCommande(Commande c) {
		int prix = 0;
		int plat = 0;
		int dessert = 0;
		int boisson = 0;
		int entree = 0;

		ArrayList<Consommable> consoTemp = new ArrayList<Consommable>(c.getItemsCommandés());/// cree une copie des
																								/// plats pour pouvoir
																								/// facilement effectuer
																								/// des modifications
																								/// dessus
		ArrayList<Consommable> tempmenu = new ArrayList<>();

		for (Menu menu : this.menus) { /// parcourt tous les menus
			for (Consommable conso : consoTemp) {
				if (menu.getItems().contains(conso)) { /// si un menu contient le plat
					tempmenu.add(conso);/// ajout a une liste temporaire
					if (conso instanceof Entrée) {/// compte le nombre de plat dessert etc ...
						entree++;
					} else if (conso instanceof PlatPrincipal) {
						plat++;
					} else if (conso instanceof Dessert) {
						dessert++;
					} else {
						boisson++;
					}
				}
			}
			while (entree > 0 && plat > 0 && boisson > 0 && dessert > 0) { /// cherche les plats dans le menu pour les
																			/// composer puis les retire de la liste des
																			/// plats
				entree--;
				plat--;
				boisson--;
				dessert--;
				int index = 0;
				while (!(tempmenu.get(index) instanceof Entrée))
					;
				consoTemp.remove(tempmenu.get(index));
				tempmenu.remove(index);
				index = 0;
				while (!(tempmenu.get(index) instanceof Dessert))
					;
				consoTemp.remove(tempmenu.get(index));
				tempmenu.remove(index);
				index = 0;
				while (!(tempmenu.get(index) instanceof PlatPrincipal))
					;
				consoTemp.remove(tempmenu.get(index));
				tempmenu.remove(index);
				index = 0;
				while (!(tempmenu.get(index) instanceof Boisson))
					;
				consoTemp.remove(tempmenu.get(index));
				tempmenu.remove(index);
				prix += 15;
			}
			tempmenu.clear();
			entree = 0;
			plat = 0;
			boisson = 0;
			dessert = 0;
		}
		for (Consommable conso : consoTemp) {
			prix += conso.getPrix();
		}

		return prix;
	}

	public void afficherMenu() {
		System.out.println("Liste des entrées:" + entrées);
		System.out.println("Liste des plats principaux:" + platsPrincipaux);
		System.out.println("Liste des desserts:" + desserts);
		System.out.println("Liste des boissons:" + boissons);

	}

	public void proposerMenu(int Kc, int epsilon) {
		int nbkcal = 0;
		for (Menu m : this.menus) {
			for (Consommable c : m.getItems()) {
				if (c instanceof Nutrition)
					nbkcal += ((Nutrition) c).getKcal();
			}
			if (nbkcal <= Kc + epsilon && nbkcal >= Kc - epsilon)
				System.out.println(m);
			nbkcal = 0;
		}
	}

}
