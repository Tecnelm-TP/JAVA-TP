package TP2;

public class SalleTP extends Salle{
	
	private Discipline type;
	
	public void setType(Discipline type) {
		this.type = type;
	}

	public Discipline getType() {
		return type;
	}

	public SalleTP(int capacite, String nom, Discipline d){
		super(capacite, nom);
		type = d;
	}
	
	public String toString(){
		return "Salle TP "+ type + " " + super.toString();
	}
}
