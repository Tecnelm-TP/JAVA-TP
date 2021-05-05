package TP5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SimpleFonctionTransition {
	private ArrayList<String> nomsEtats;
	private ArrayList<Character> entree;
	private HashMap<Trans<Character>, Trans<Integer>> state;

	public SimpleFonctionTransition(String nomsEtats[], char entrees[]) {

		this.nomsEtats = new ArrayList<>(Arrays.asList(nomsEtats));

		this.entree = new ArrayList<>();

		this.state = new HashMap<>();

		for (char c : entrees)
			entree.add(c);

	}

	public void ajouterTransition(String etatOrig, char entree, String etatDest, int sortie) {
		this.state.put(new Trans<Character>(etatOrig, entree), new Trans<Integer>(etatDest, sortie));

	}

	public String getEtatSuivant(String etatOrig, char entree) throws UnidefinedTransitionException {

		Trans<Integer> tr = state.get(new Trans<Character>(etatOrig, entree));
		if (tr == null)
			throw new UnidefinedTransitionException();
		return tr.getName();
	}

	public int getSortie(String etatOrig, char entree) throws UnidefinedTransitionException {

		Trans<Integer> tr = state.get(new Trans<Character>(etatOrig, entree));
		if (tr == null)
			throw new UnidefinedTransitionException();
		return tr.getValue();
	}

	/**
	 * @return the nomsEtats
	 */
	public ArrayList<String> getNomsEtats() {
		return nomsEtats;
	}

	/**
	 * @return the entree
	 */
	public ArrayList<Character> getEntree() {
		return entree;
	}
}