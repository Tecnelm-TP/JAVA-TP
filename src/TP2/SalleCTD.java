package TP2;

public class SalleCTD extends Salle{
	
	public SalleCTD(int capacit�, String nom){
		super(capacit�, nom);
	}
	
	public String toString(){
		return "Salle cours-TD "+ super.toString();
	}
}
