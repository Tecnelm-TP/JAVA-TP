package TP2;

import java.util.ArrayList;
import java.util.List;

public class Planning {
	private List<Cr�neau> edt;

	public Planning() {
		edt = new ArrayList<Cr�neau>();
	}

	public String toString() {
		String res = new String();
		for (Cr�neau c : edt) {
			res = res + c + "\n";
		}
		return res;
	}

	public boolean v�rifCr�neau(Cr�neau c) {
		boolean ok = true;
		int i = 0;
		while (ok && i < edt.size()) {
			Cr�neau cour = edt.get(i++);
			ok = !c.intersection(cour);
		}
		return ok;
	}

	public void addCr�neau(Cr�neau c) {
		if (this.v�rifCr�neau(c))
			edt.add(c);
		else
			System.out.println("Cr�neau " + c + " non ins�r�");
	}

	public List<Cr�neau> planningGroupe(Groupe groupe) {

		ArrayList<Cr�neau> list = new ArrayList<>();
		
		for(Cr�neau e : edt)
		{
			if(e.getActivit�().getGroupes().contains(groupe))
				list.add(e);
		}
		return list;
	}

	public float totalHeuresGroupe(Groupe groupe) {
		
		int total = 0;
		for(Cr�neau e : this.planningGroupe(groupe))
		{
			total+= e.getDur�e();
		}
		return total/60 ;

	}

	public static void main(String[] args) {
		Planning p = new Planning();

		SalleCTD a042 = new SalleCTD(100, "A042");
		SalleCTD d030 = new SalleCTD(180, "D030");

		SalleTP b141 = new SalleTP(16, "B141", Discipline.Informatique);
		SalleCTD a048 = new SalleCTD(25, "A048");

		CM cs310cm = new CM("CM CS310");
		cs310cm.addSalle(a042);
		cs310cm.addSalle(d030);

		TP cs330tp1 = new TP("TP1 CS330",Discipline.Informatique);
		cs330tp1.addSalle(b141);

		CM cs410cm = new CM("CM CS410");
		cs410cm.addSalle(a042);
		cs410cm.addSalle(d030);
		cs410cm.addSalle(a048);

		CM cs421_422cm = new CM("CM CS421 - CS422");
		cs421_422cm.addSalle(a042);
		cs421_422cm.addSalle(d030);
		cs410cm.addSalle(a048);

		Groupe a3tp1 = new Groupe("3ATP1", 16);
		Groupe a3tp2 = new Groupe("3ATP2", 16);
		Groupe a3tp3 = new Groupe("3ATP3", 16);
		Groupe a3tp4 = new Groupe("3ATP4", 16);
		Groupe a3tp5 = new Groupe("3ATP5", 16);
		
		Groupe  p23[] = {a3tp1,a3tp2,a3tp3,a3tp4,a3tp5
				
		};
		
		Promotion a3promo = new Promotion("3A", 90,p23);
		

		Groupe a4ir = new Groupe("4AIR", 29);
		Groupe a4eis = new Groupe("4AEIS", 35);
		
		/*cs310cm.addGroupe(a3promo); 
		cs330tp1.addGroupe(a3tp1);
		Cr�neau c1 = null, c2 = null;
		c1 = new Cr�neau(2014, 1, 17, 13, 15, 105, a042, cs310cm);
		c2 = new Cr�neau(2014, 1, 17, 13, 15, 210, b141, cs330tp1);
		p.addCr�neau(c1);
		p.addCr�neau(c2);*/


		cs310cm.addGroupe(a3tp1);
		cs310cm.addGroupe(a3tp2);
		cs310cm.addGroupe(a3tp3);
		cs310cm.addGroupe(a3tp4);
		cs310cm.addGroupe(a3tp5);
		
		cs310cm.addGroupe(a3promo);

		cs330tp1.addGroupe(a3tp1);
		cs410cm.addGroupe(a4ir);
		cs421_422cm.addGroupe(a4ir);
		cs421_422cm.addGroupe(a4eis);

		Cr�neau c1 = null, c2 = null, c3 = null, c4 = null, c5 = null;
		
	

		c1 = new Cr�neau(2014, 1, 17, 13, 15, 105, a042, cs310cm);
		c2 = new Cr�neau(2014, 1, 17, 8, 00, 210, b141, cs330tp1);
		c3 = new Cr�neau(2014, 1, 17, 15, 15, 105, d030, cs410cm);
		c4 = new Cr�neau(2014, 1, 17, 10, 00, 105, a042, cs421_422cm);
		c5 = new Cr�neau(2014, 1, 17, 17, 15, 105, a042, cs310cm);

		//System.exit(1);

		p.addCr�neau(c1);
		p.addCr�neau(c2);
		p.addCr�neau(c3);
		p.addCr�neau(c4);
		p.addCr�neau(c5);

		System.out.println(p);

		System.out.println(p.planningGroupe(a3tp1) + " (" + p.totalHeuresGroupe(a3tp1) + " heures)");
		System.out.println(p.planningGroupe(a4ir));
		System.out.println(p.planningGroupe(a3tp1));

	}
}
