package TP4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SimpleFonctionTransition  {
	private ArrayList<String> nomsEtats;
	private ArrayList<Character> entree;
	private HashMap<Trans<Character>, Trans<Integer>> state;

	public SimpleFonctionTransition(String nomsEtats[], char entrees[]) {

		this.nomsEtats = new ArrayList<>(Arrays.asList(nomsEtats)) {
			@Override
			public boolean contains(Object o) {
				if (o instanceof String) {
					for (String str : this) {
						if (str.equals((String) o))
							return true;
					}
				}
				return false;
			}
		};

		this.entree = new ArrayList<>() {
			@Override
			public boolean contains(Object o) {
				if (o instanceof Character) {
					for (Character c : this) {
						if (c.equals((Character) o))
							return true;
					}
				}
				return false;
			}
		};

		this.state = new HashMap<>();

		for (char c : entrees)
			entree.add(c);

	}

	public void ajouterTransition(String etatOrig, char entree, String etatDest, int sortie) {
		this.state.put(new Trans<Character>(etatOrig, entree), new Trans<Integer>(etatDest, sortie));

	}

	public String getEtatSuivant(String etatOrig, char entree) throws ErrorParameterExeption{

			if (!this.nomsEtats.contains(etatOrig) || !this.entree.contains(entree))
				throw new ErrorParameterExeption("bad parameter");
	
			String tmp = state.get(new Trans<Character>(etatOrig, entree)).getName();
		return state.get(new Trans<Character>(etatOrig, entree)).getName();
	}

	public int getSortie(String etatOrig, char entree)  throws ErrorParameterExeption {
		
		if (!this.nomsEtats.contains(etatOrig) || !this.entree.contains(entree))
			throw new ErrorParameterExeption("bad parameter");
		return state.get(new Trans<Character>(etatOrig, entree)).getValue();
	}
}