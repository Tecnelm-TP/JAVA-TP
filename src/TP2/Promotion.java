package TP2;

import java.util.ArrayList;
import java.util.Arrays;

public class Promotion extends Groupe {

	private ArrayList<Groupe> sousgroupe;

	public Promotion(String n, int e) {
		super(n, e);
		this.sousgroupe = new ArrayList<Groupe>();

	}

	public Promotion(String n, int e, Groupe[]sousgroupe) {
		this(n, e);
		addGroupe(sousgroupe);
	}

	public void addGroupe(Groupe g) {
		this.sousgroupe.add(g);
	}

	public void addGroupe (Groupe[] groupeList)
	{
		this.sousgroupe.addAll(Arrays.asList(groupeList));
	}

	public ArrayList<Groupe> getSousgroupe() {
		return sousgroupe;
	}

	public void setSousgroupe(ArrayList<Groupe> sousgroupe) {
		this.sousgroupe = sousgroupe;
	}
	
	public void removeGroupes(Groupe[] groupes)
	{
		for(Groupe e : groupes)
		{
			this.removeGroupe(e);
		}
	}
	public void removeGroupe(Groupe g)
	{
			this.sousgroupe.remove(g);
	}

}
