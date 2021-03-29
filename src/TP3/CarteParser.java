package TP3;

import java.util.ArrayList;

import Parser.Parser;

import Parser.InvalidTypeException;
import Parser.NotPresentException;

public class CarteParser {
	
	static Parser parse;
	
	public static Carte getCarte(String str, Carte c)
	{
		parse = new Parser(str);
		Carte carte = c;
		ArrayList<Consommable> consommable = new ArrayList<>();

		if (c == null) {
			carte = new Carte();
		}

		for (Boisson b : getBoisson(str)) {
			carte.addBoisson(b);
			consommable.add(b);
		}
		for (PlatPrincipal b :getPlat(str)) {
			carte.addPlatPrincipal(b);
			consommable.add(b);
		}
		for (Dessert b : getDessert(str)) {
			carte.addDessert(b);
			consommable.add(b);
		}
		for (Entree b : getEntree(str)) {
			carte.addEntree(b);
			consommable.add(b);
		}

		for (Menu m : getMenu(str, consommable)) {
			carte.addMenu(m);
		}

		return carte;
		
		
	}
	
	private static ArrayList<Boisson> getBoisson(String str) {
		ArrayList<Boisson> boisson = new ArrayList<>();
		ArrayList<String> temp = null;
		int volume = -1;
		int kCal = -1;
		int glucide = -1;
		int prix = -1;

		try {
			temp = parse.getArray("Boisson", str);
		} catch (InvalidTypeException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotPresentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (temp != null)
			for (String s : temp) {
				try {
					String name;
					prix = Integer.parseInt((parse.getByID("prix", s.replace(" ", ""))));
					name = parse.getByID("name", s).replace("\"", "").trim();
					try {

						kCal = Integer.parseInt(parse.getByID("kCal", s.replace(" ", "")));
					} catch (NumberFormatException | NotPresentException e) {
						System.out.println(name + " :No cal specified");
					}
					try {

						glucide = Integer.parseInt(parse.getByID("glucide", s.replace(" ", "")));
					} catch (NumberFormatException | NotPresentException e) {
						System.out.println(name + " :No glucide specified");
					}
					try {

						volume = Integer.parseInt(parse.getByID("volume", s.replace(" ", "")));
					} catch (NumberFormatException | NotPresentException e) {
						System.out.println(name + " :Volume not specified");
					}
					boisson.add(new Boisson(volume, name, prix, kCal, glucide));
				} catch (NumberFormatException | NotPresentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		return boisson;
	}

	private static ArrayList<Entree> getEntree(String str) {
		ArrayList<Entree> entree = new ArrayList<>();
		ArrayList<String> temp = null;
		int kCal = -1;
		int glucide = -1;
		int prix = -1;

		try {
			temp = parse.getArray("Entree", str);
		} catch (InvalidTypeException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotPresentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (temp != null)
			for (String s : temp) {
				try {
					String name;
					prix = Integer.parseInt((parse.getByID("prix", s.replace(" ", ""))));
					name = parse.getByID("name", s).replace("\"", "").trim();
					try {

						kCal = Integer.parseInt(parse.getByID("kCal", s.replace(" ", "")));
					} catch (NumberFormatException | NotPresentException e) {
						System.out.println(name + " :No cal specified");
					}
					try {

						glucide = Integer.parseInt(parse.getByID("glucide", s.replace(" ", "")));
					} catch (NumberFormatException | NotPresentException e) {
						System.out.println(name + " :No glucide specified");
					}
					entree.add(new Entree(name, prix, kCal, glucide));
				} catch (NumberFormatException | NotPresentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		return entree;
	}

	private static ArrayList<PlatPrincipal> getPlat(String str) {
		ArrayList<PlatPrincipal> plat = new ArrayList<>();
		ArrayList<String> temp = null;
		int kCal = -1;
		int glucide = -1;
		int prix = -1;

		try {
			temp = parse.getArray("Plat", str);
		} catch (InvalidTypeException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotPresentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (temp != null)
			for (String s : temp) {
				try {
					String name;
					prix = Integer.parseInt((parse.getByID("prix", s.replace(" ", ""))));
					name = parse.getByID("name", s).replace("\"", "").trim();
					try {

						kCal = Integer.parseInt(parse.getByID("kCal", s.replace(" ", "")));
					} catch (NumberFormatException | NotPresentException e) {
						System.out.println(name + " :No cal specified");
					}
					try {

						glucide = Integer.parseInt(parse.getByID("glucide", s.replace(" ", "")));
					} catch (NumberFormatException | NotPresentException e) {
						System.out.println(name + " :No  glucide specified");
					}
					plat.add(new PlatPrincipal(name, prix, kCal, glucide));
				} catch (NumberFormatException | NotPresentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		return plat;
	}

	private static ArrayList<Dessert> getDessert(String str) {
		ArrayList<Dessert> dessert = new ArrayList<>();
		ArrayList<String> temp = null;
		int kCal = -1;
		int glucide = -1;
		int prix = -1;

		try {
			temp = parse.getArray("Dessert", str);
		} catch (InvalidTypeException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotPresentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (temp != null)
			for (String s : temp) {
				try {
					String name;
					prix = Integer.parseInt((parse.getByID("prix", s.replace(" ", ""))));
					name = parse.getByID("name", s).replace("\"", "").trim();
					try {

						kCal = Integer.parseInt(parse.getByID("kCal", s.replace(" ", "")));
					} catch (NumberFormatException | NotPresentException e) {
						System.out.println(name + " :No cal specified");
					}
					try {

						glucide = Integer.parseInt(parse.getByID("glucide", s.replace(" ", "")));
					} catch (NumberFormatException | NotPresentException e) {
						System.out.println(name + " :No  glucide specified");
					}
					dessert.add(new Dessert(name, prix, kCal, glucide));
				} catch (NumberFormatException | NotPresentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		return dessert;
	}

	private static ArrayList<Menu> getMenu(String str, ArrayList<Consommable> conso) {
		ArrayList<Menu> menu = new ArrayList<>();

		ArrayList<String> temp = null;
		try {
			temp = parse.getArray("Menu", str);

			for (String s : temp) {
				int prix = -1;
				try {
					prix = Integer.parseInt(parse.getByID("prix", s.replace(" ", "")));
				} catch (NumberFormatException | NotPresentException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String plat = null;
				try {
					plat = parse.getByID("plat", s).replace("\"", "");
				} catch (NotPresentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String entree = null;

				entree = parse.getByID("entree", s).replace("\"", "");

				String dessert = null;
				try {
					dessert = parse.getByID("dessert", s).replace("\"", "");
				} catch (NotPresentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String boisson = null;
				try {
					boisson = parse.getByID("boisson", s).replace("\"", "");
				} catch (NotPresentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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
		} catch (InvalidTypeException | NotPresentException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		return menu;
	}
	
	

}
