package Parser;

import java.util.ArrayList;

import TP3.Boisson;
import TP3.Carte;
import TP3.Consommable;
import TP3.Dessert;
import TP3.Entree;
import TP3.Menu;
import TP3.PlatPrincipal;

public class Parser {

	private Carte carte;
	private String file;
	private int index;

	public Parser(String file, Carte carte) {
		this.carte = carte;
		this.file = file;
	}

	public Carte getcarte() {
		ArrayList<Consommable> consommable = new ArrayList<>();

		if (carte == null) {
			carte = new Carte();
		}

		for (Boisson b : getBoisson(file)) {
			carte.addBoisson(b);
			consommable.add(b);
		}
		for (PlatPrincipal b : getPlat(file)) {
			carte.addPlatPrincipal(b);
			consommable.add(b);
		}
		for (Dessert b : getDessert(file)) {
			carte.addDessert(b);
			consommable.add(b);
		}
		for (Entree b : getEntree(file)) {
			carte.addEntree(b);
			consommable.add(b);
		}

		for (Menu m : getMenu(file, consommable)) {
			carte.addMenu(m);
		}

		return carte;
	}

	private ArrayList<Boisson> getBoisson(String str) {
		ArrayList<Boisson> boisson = new ArrayList<>();
		Categorie strcp = getTable("Boisson", file);
		ArrayList<String> temp = splitItem(strcp.getContent());
		for (String s : temp) {
			int volume = -1;
			int kCal = -1;
			int glucide = -1;

			try {

				kCal = Integer.parseInt(getByID("kCal", s.replace(" ", "")));
			} catch (Exception e) {
				System.out.println("no cal, glucide specified");
			}
			try {

				glucide = Integer.parseInt(getByID("glucide", s.replace(" ", "")));
			} catch (Exception e) {
				System.out.println("no cal, glucide specified");
			}
			try {

				volume = Integer.parseInt(getByID("volume", s.replace(" ", "")));
			} catch (Exception e) {
				System.out.println("no cal, glucide specified");
			}

			int prix = Integer.parseInt(getByID("prix", s.replace(" ", "")));
			String name = getByID("name", s).replace("\"", "");

			boisson.add(new Boisson(volume, name, prix, kCal, glucide));
		}

		return boisson;
	}

	private ArrayList<Entree> getEntree(String str) {
		ArrayList<Entree> entree = new ArrayList<>();
		Categorie strcp = getTable("Entree", file);
		ArrayList<String> temp = splitItem(strcp.getContent());
		for (String s : temp) {

			int kCal = -1;
			int glucide = -1;
			try {

				kCal = Integer.parseInt(getByID("kCal", s.replace(" ", "")));
			} catch (Exception e) {
				System.out.println("no cal, glucide specified");
			}
			try {

				glucide = Integer.parseInt(getByID("glucide", s.replace(" ", "")));
			} catch (Exception e) {
				System.out.println("no cal, glucide specified");
			}

			int prix = Integer.parseInt(getByID("prix", s.replace(" ", "")));
			String name = getByID("name", s).replace("\"", "");

			entree.add(new Entree(name, prix, kCal, glucide));
		}
		return entree;
	}

	private ArrayList<PlatPrincipal> getPlat(String str) {
		ArrayList<PlatPrincipal> plat = new ArrayList<>();
		Categorie strcp = getTable("Plat", file);
		ArrayList<String> temp = splitItem(strcp.getContent());
		for (String s : temp) {
			int kCal = -1;
			int glucide = -1;
			try {

				kCal = Integer.parseInt(getByID("kCal", s.replace(" ", "")));
				glucide = Integer.parseInt(getByID("glucide", s.replace(" ", "")));
			} catch (Exception e) {
				System.out.println("no cal, glucide specified");
			}
			int prix = Integer.parseInt(getByID("prix", s.replace(" ", "")));
			String name = getByID("name", s).replace("\"", "");

			plat.add(new PlatPrincipal(name, prix, kCal, glucide));
		}
		return plat;
	}

	private ArrayList<Dessert> getDessert(String str) {
		ArrayList<Dessert> dessert = new ArrayList<>();
		Categorie strcp = getTable("Dessert", file);
		ArrayList<String> temp = splitItem(strcp.getContent());
		for (String s : temp) {
			int kCal = -1;
			int glucide = -1;
			try {

				kCal = Integer.parseInt(getByID("kCal", s.replace(" ", "")));
			} catch (Exception e) {
				System.out.println("no cal, glucide specified");
			}
			try {

				glucide = Integer.parseInt(getByID("glucide", s.replace(" ", "")));
			} catch (Exception e) {
				System.out.println("no cal, glucide specified");
			}
			int prix = Integer.parseInt(getByID("prix", s.replace(" ", "")));
			String name = getByID("name", s).replace("\"", "");

			dessert.add(new Dessert(name, prix, kCal, glucide));
		}
		return dessert;
	}

	private ArrayList<Menu> getMenu(String str, ArrayList<Consommable> conso) {
		ArrayList<Menu> menu = new ArrayList<>();

		Categorie strcp = getTable("Menu", file);
		ArrayList<String> temp = splitItem(strcp.getContent());

		for (String s : temp) {
			int prix = Integer.parseInt(getByID("prix", s.replace(" ", "")));
			String plat = getByID("plat", s).replace("\"", "");
			String entree = getByID("entree", s).replace("\"", "");
			String dessert = getByID("dessert", s).replace("\"", "");
			String boisson = getByID("boisson", s).replace("\"", "");

			PlatPrincipal p = null;
			Dessert d = null;
			Entree e = null;
			Boisson b = null;

			for (Consommable c : conso) {
				if (c.getNom().equals(plat)) {
					p = (PlatPrincipal) c;
				}
				if (c.getNom().equals(entree)) {
					e = (Entree) c;
				}
				if (c.getNom().equals(dessert)) {
					d = (Dessert) c;
				}
				if (c.getNom().equals(boisson)) {
					b = (Boisson) c;
				}

			}
			menu.add(new Menu(prix, e, p, d, b));

		}
		return menu;
	}

	private Categorie getTable(String name, String str) {

		Categorie cat = null;
		String categorie;
		index = 0;

		for (index = 0; index < str.length() && cat == null; index++) {
			switch (str.charAt(index)) {
			case '\"':

				categorie = getName(str);
				if (categorie.equals(name)) {
					cat = parseCategorie(name, str);
				}
				break;
			case ':':
				// goNextChar(",", str);
				goNextCat(str);
				break;
			}

		}

		return cat;

	}

	private String getName(String str) {
		StringBuilder sb = new StringBuilder();
		index++;
		while (str.charAt(index) != '"') {
			sb.append(str.charAt(index));
			index++;
		}
		return sb.toString();
	}

	private void goNextChar(String c, String str) {
		char[] stopchar = c.toCharArray();
		Boolean stop = false;
		while (!stop && index + 1 < str.length()) {
			index++;
			for (char t : stopchar) {
				stop = stop || (!stop && (str.charAt(index) == t)) ? true : false;
			}

		}
		if (!stop) {
			index++;
		}

	}

	private void goNextCat(String str) {
		ArrayList<Character> braquet = new ArrayList<>();
		goNextChar("[", str);
		Boolean stop = false;
		Character b2 = '[';
		do {
			switch (str.charAt(index)) {
			case ']':
				braquet.remove(b2);
				break;
			case '[':
				braquet.add(b2);
				break;
			default:
				break;
			}
			stop = braquet.size() == 0 && (str.charAt(index + 1) == '\"');
			if (!stop)
				index++;

		} while (!stop && index + 1 < str.length());

	}

	private Categorie parseCategorie(String name, String str) {
		StringBuilder sb = new StringBuilder();

		ArrayList<Character> braquet = new ArrayList<>();
		goNextChar("[{", str);
		Character b1 = '{';
		Character b2 = '[';

		do {
			switch (str.charAt(index)) {
			case ']':
				braquet.remove(b2);
				break;
			case '[':
				braquet.add(b2);
				break;
			case '{':
				braquet.add(b1);
				break;
			case '}':
				braquet.remove(b1);
				break;
			default:
				break;
			}
			sb.append(str.charAt(index));
			index++;

		} while (braquet.size() != 0);
		return new Categorie(name, sb.toString().trim());
	}

	private ArrayList<String> splitItem(String str) {
		ArrayList<String> items = new ArrayList<>();
		index = 0;
		StringBuilder sb = new StringBuilder();
		ArrayList<Character> braquet = new ArrayList<>();
		goNextChar("{", str);
		Character b1 = '{';

		while (index < str.length()) {
			do {
				switch (str.charAt(index)) {
				case '{':
					braquet.add(b1);
					break;
				case '}':
					braquet.remove(b1);
					break;
				default:
					break;
				}
				sb.append(str.charAt(index));
				index++;

			} while (braquet.size() != 0);
			items.add(sb.toString());
			sb.setLength(0);
			goNextChar("{", str);
		}

		return items;
	}

	private String getByID(String key, String str) {
		String ret = null;
		StringBuilder sb = new StringBuilder();
		for (index = 0; index < str.length() && ret == null; index++) {
			switch (str.charAt(index)) {
			case '\"':
				if (getName(str).equals(key)) {
					goNextChar(":", str);
					index++;
					while (!(str.charAt(index) == '}' || str.charAt(index) == ',') && index < str.length()) {
						sb.append(str.charAt(index));
						index++;
					}
					ret = (sb.toString());
				}
				break;
			case ':':
				goNextChar(",", str);
				break;
			}

		}

		return ret;
	}
}
