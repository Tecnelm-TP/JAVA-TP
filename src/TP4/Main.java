package TP4;



public class Main {

	public static void main(String args[]) {
		String nomEtats[] = new String[3];
		char entrees[] = new char[2];
		int out = -1;

		nomEtats[0] = "s1";
		nomEtats[1] = "s2";
		nomEtats[2] = "s3";
		entrees[0] = 'a';
		entrees[1] = 'b';
		SimpleFSMIO auto = new SimpleFSMIO(nomEtats, "s1", entrees);
		try {
			auto.ajouterTransition("s1", 'a', "s1", 0);
		} catch (ErrorParameterExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			auto.ajouterTransition("s1", 'b', "s3", 0);
		} catch (ErrorParameterExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			auto.ajouterTransition("s2", 'a', "s1", 0);
		} catch (ErrorParameterExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			auto.ajouterTransition("s2", 'b', "s2", 1);
		} catch (ErrorParameterExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			auto.ajouterTransition("s3", 'a', "s2", 1);
		} catch (ErrorParameterExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			auto.ajouterTransition("s3", 'b', "s3", 1);
		} catch (ErrorParameterExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sequence = "abaabbaaa";
		System.out.println("Réalisation de la séquence :"+sequence);


		System.out.println("Debut de la séquence");
		for (char c : sequence.toCharArray()) {
			System.out.println("lettre :"+c);
			System.out.println("FSM1");
			try {	
					out = auto.faireTransition(c);
					System.out.println("Etat courant:\t" + auto.getEtatCourant() + "\tsortie:\t" + out);
			} catch (ErrorParameterExeption|UnidefinedTransitionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

	}
}
