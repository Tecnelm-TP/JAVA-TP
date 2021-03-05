package TP2;

public class TP extends Activité{
	private Discipline type;
	
	public TP(String nom,Discipline type)
	{
		super(nom);
		this.type = type;
	}
	
	public void addSalle(SalleTP s){
		if(s.getType() != this.type  || this.type == null)
		{
			System.exit(1);
		}
		else
		{
			super.addSalle(s);			
		}
	}
}
