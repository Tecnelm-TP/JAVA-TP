package TP3;


import java.util.ArrayList;

public class Commande {
	private ArrayList<Consommable> itemsCommandés;
	
	public Commande() {
		this.itemsCommandés = new ArrayList<Consommable>();
	}
	
	public void addItem(Consommable c){
		this.itemsCommandés.add(c);
	}
	
	public ArrayList<Consommable> getItemsCommandés(){
		return this.itemsCommandés;
	}

}
