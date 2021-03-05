package TP2;

public class Cr�neau {
	private int ann�e;
	private int mois; // 1 � 12
	private int jour; // 1 � 31
	private int heure; // 0 � 23
	private int minute; // 0 � 59
	private int dur�e; // en minutes, maximum 210

	private Salle salle;
	private Activit� activit�;

	public Cr�neau(int a, int m, int j, int h, int min, int d, Salle s, Activit� ac) {

		ann�e = a;
		mois = m;
		jour = j;
		heure = h;
		minute = min;
		dur�e = d;
		salle = s;
		activit� = ac;

		if (!v�rifCapacit�()) {
			System.exit(1);
		}
		if (!v�rifDur�e()) {
			System.exit(1);
		}
		if (!v�rifSalle()) {
			System.exit(1);
		}
	}

	// V�rifie l'ad�quation de la salle : la salle affect�e doit �tre une des salles
	// appropri�es de l'activit�
	private boolean v�rifSalle() {
		return this.activit�.getSalles().contains(this.salle);

	}

	// V�rifie que la taille de la salle convient � la promo
	private boolean v�rifCapacit�() {
		int value = 0;
		for (Groupe g : this.activit�.getGroupes()) {
			value += g.getEffectif();
		}
		return this.salle.getCapacit�() >= value;
	}

	// V�rifie que le d�but et la fin du cr�neau sont dans la m�me journ�e, entre 8h
	// et 19h
	private boolean v�rifDur�e() {
 		return (this.heure + (this.dur�e / 60 + (this.minute + this.dur�e % 60) / 60) <= 19 && this.heure >= 8);
	}

	public Salle getSalle() {
		return salle;
	}

	public Activit� getActivit�() {
		return activit�;
	}

	public int getDur�e() {
		return dur�e;
	}

	public int getStart() {
		return this.heure * 60 + this.minute;
	}

	@Override
	public String toString() {
		return jour + "/" + mois + "/" + ann�e + " " + heure + ":" + minute + " (" + dur�e + ") : " + activit� + " "
				+ salle;
	}

	public boolean intersection(Cr�neau c) {
		Boolean ret = false;
		if (c.ann�e == this.ann�e && c.mois == this.mois && c.jour == c.jour) {
			if (!(c.getStart() + c.getDur�e() < this.getStart() || c.getStart() > this.getStart() + this.getDur�e())) {

				for (Groupe g : this.getActivit�().getGroupes()) {
					if (c.getActivit�().getGroupes().contains(g))
						ret = true;
				}

				if (this.getSalle().getNom().equals(c.getSalle().getNom()))
					ret = true;
			}

		}

		return ret;

	}

}
