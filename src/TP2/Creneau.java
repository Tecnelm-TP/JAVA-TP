package TP2;

public class Creneau {
	private int annee;
	private int mois; // 1 a 12
	private int jour; // 1 a 31
	private int heure; // 0 a 23
	private int minute; // 0 a 59
	private int duree; // en minutes, maximum 210

	private Salle salle;
	private Activite activite;

	public Creneau(int a, int m, int j, int h, int min, int d, Salle s, Activite ac) {

		annee = a;
		mois = m;
		jour = j;
		heure = h;
		minute = min;
		duree = d;
		salle = s;
		activite = ac;

		if (!verifCapacite()) {
			System.exit(1);
		}
		if (!verifDuree()) {
			System.exit(1);
		}
		if (!verifSalle()) {
			System.exit(1);
		}
	}

	// Verifie l'adequation de la salle : la salle affectee doit être une des salles
	// appropriees de l'activite
	private boolean verifSalle() {
		return this.activite.getSalles().contains(this.salle);

	}

	// Verifie que la taille de la salle convient a la promo
	private boolean verifCapacite() {
		int value = 0;
		for (Groupe g : this.activite.getGroupes()) {
			value += g.getEffectif();
		}
		return this.salle.getCapacite() >= value;
	}

	// Verifie que le debut et la fin du creneau sont dans la même journee, entre 8h
	// et 19h
	private boolean verifDuree() {
 		return ((this.heure*60 + this.duree +this.minute <= 19*60 && this.heure >= 8) && this.duree <=210 && this.duree > 0);
	}

	public Salle getSalle() {
		return salle;
	}

	public Activite getActivite() {
		return activite;
	}

	public int getDuree() {
		return duree;
	}

	public int getStart() {
		return this.heure * 60 + this.minute;
	}

	@Override
	public String toString() {
		return jour + "/" + mois + "/" + annee + " " + heure + ":" + minute + " (" + duree + ") : " + activite + " "
				+ salle;
	}

	public boolean intersection(Creneau c) {
		Boolean ret = false;
		if (c.annee == this.annee && c.mois == this.mois && c.jour == c.jour) {
			if (!(c.getStart() + c.getDuree() < this.getStart() || c.getStart() > this.getStart() + this.getDuree())) {

				for (Groupe g : this.getActivite().getGroupes()) {
					if (c.getActivite().getGroupes().contains(g))
						ret = true;
				}

				if (this.getSalle().getNom().equals(c.getSalle().getNom()))
					ret = true;
			}
		}
		return ret;
	}

}
