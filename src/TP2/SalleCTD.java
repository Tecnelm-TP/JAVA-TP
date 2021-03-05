package TP2;

public class SalleCTD extends Salle{
	
	public SalleCTD(int capacité, String nom){
		super(capacité, nom);
	}
	
	public String toString(){
		return "Salle cours-TD "+ super.toString();
	}
}
