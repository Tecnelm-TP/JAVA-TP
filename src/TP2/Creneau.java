package TP2;

public class Créneau {
	private int année;
	private int mois; // 1 à 12
	private int jour; // 1 à 31
	private int heure; // 0 à 23
	private int minute; // 0 à 59
	private int durée; // en minutes, maximum 210

	private Salle salle;
	private Activité activité;

	public Créneau(int a, int m, int j, int h, int min, int d, Salle s, Activité ac) {

		année = a;
		mois = m;
		jour = j;
		heure = h;
		minute = min;
		durée = d;
		salle = s;
		activité = ac;

		if (!vérifCapacité()) {
			System.exit(1);
		}
		if (!vérifDurée()) {
			System.exit(1);
		}
		if (!vérifSalle()) {
			System.exit(1);
		}
	}

	// Vérifie l'adéquation de la salle : la salle affectée doit être une des salles
	// appropriées de l'activité
	private boolean vérifSalle() {
		return this.activité.getSalles().contains(this.salle);

	}

	// Vérifie que la taille de la salle convient à la promo
	private boolean vérifCapacité() {
		int value = 0;
		for (Groupe g : this.activité.getGroupes()) {
			value += g.getEffectif();
		}
		return this.salle.getCapacité() >= value;
	}

	// Vérifie que le début et la fin du créneau sont dans la même journée, entre 8h
	// et 19h
	private boolean vérifDurée() {
 		return (this.heure + (this.durée / 60 + (this.minute + this.durée % 60) / 60) <= 19 && this.heure >= 8);
	}

	public Salle getSalle() {
		return salle;
	}

	public Activité getActivité() {
		return activité;
	}

	public int getDurée() {
		return durée;
	}

	public int getStart() {
		return this.heure * 60 + this.minute;
	}

	@Override
	public String toString() {
		return jour + "/" + mois + "/" + année + " " + heure + ":" + minute + " (" + durée + ") : " + activité + " "
				+ salle;
	}

	public boolean intersection(Créneau c) {
		Boolean ret = false;
		if (c.année == this.année && c.mois == this.mois && c.jour == c.jour) {
			if (!(c.getStart() + c.getDurée() < this.getStart() || c.getStart() > this.getStart() + this.getDurée())) {

				for (Groupe g : this.getActivité().getGroupes()) {
					if (c.getActivité().getGroupes().contains(g))
						ret = true;
				}

				if (this.getSalle().getNom().equals(c.getSalle().getNom()))
					ret = true;
			}

		}

		return ret;

	}

}
