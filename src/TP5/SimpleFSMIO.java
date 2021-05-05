package TP5;

public class SimpleFSMIO {
	private SimpleFonctionTransition t;
	private String etatInit;
	private String etatCourant;
	private String etats[];
	private char entrees[];

	public SimpleFSMIO(String etats[], String etatInit, char entrees[]) {
		t = new SimpleFonctionTransition(etats, entrees);
		this.etats = etats;
		this.entrees = entrees;
		this.etatCourant = this.etatInit = etatInit;
	}

	public void ajouterTransition(String etatOrig, char valEntree, String etatDest, int sortie)
			throws ErrorParameterExeption {
		boolean etatOrigValide = false;
		boolean etatDestValide = false;
		boolean entreeValide = false;

		for (int ie = 0; ie < this.etats.length && (!etatOrigValide || !etatDestValide); ie++) {
			if (etatOrig.equals(this.etats[ie]))
				etatOrigValide = true;
			if (etatDest.equals(this.etats[ie]))
				etatDestValide = true;
		}

		for (int i = 0; i < this.entrees.length && !entreeValide; i++) {
			entreeValide = (valEntree == this.entrees[i]);
		}

		if (etatOrigValide && etatDestValide && entreeValide) {
			this.t.ajouterTransition(etatOrig, valEntree, etatDest, sortie);
		} else
			throw new ErrorParameterExeption();
	}

	public int faireTransition(char val) throws ErrorParameterExeption, UnidefinedTransitionException {

		int sortie = -1;
		if (!t.getEntree().contains(val))
			throw new ErrorParameterExeption();

		sortie = this.t.getSortie(etatCourant, val);
		this.etatCourant = this.t.getEtatSuivant(etatCourant, val);

		return sortie;
	}

	public void reset() {
		this.etatCourant = this.etatInit;
	}

	public String getEtatCourant() {
		return this.etatCourant;
	}

	public String getEtatSuivant(String s, char e) throws ErrorParameterExeption, UnidefinedTransitionException {
		String es = "";

		if (!t.getNomsEtats().contains(s) || !t.getEntree().contains(e))
			throw new ErrorParameterExeption();

		es = t.getEtatSuivant(s, e);

		return (es);
	}

	public int getSortie(String s, char e) throws ErrorParameterExeption, UnidefinedTransitionException {
		int es = 0;
		if (!t.getNomsEtats().contains(s) || !t.getEntree().contains(e))
			throw new ErrorParameterExeption();
		es = t.getSortie(s, e);

		return es;
	}
}
