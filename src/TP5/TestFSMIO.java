package TP5;

import java.util.ArrayList;
import java.util.List;

public class TestFSMIO {
	public static void main(String[] args) {
		String nomEtats[] = new String[3];
		char entrees[] = new char[2];
		int out = -1;
		String strOut = "";

		nomEtats[0] = "s1";
		nomEtats[1] = "s2";
		nomEtats[2] = "s3";
		entrees[0] = 'a';
		entrees[1] = 'b';

		String sequence = "abbaabbaaa";
		System.out.println("Réalisation de la séquence :" + sequence);

		List<State> states = new ArrayList<>();
		for (String str : nomEtats) {
			states.add(new State(str));
		}
		FSMIO<Character, Integer> fsm = new FSMIO<>(states, states.get(0));

		try {
			fsm.addState(states.get(0));
			fsm.addState(states.get(1));
			fsm.addState(states.get(2));

			fsm.addTransition(states.get(0), 'a', 0, states.get(0));
			fsm.addTransition(states.get(0), 'b', 0, states.get(2));
			fsm.addTransition(states.get(1), 'a', 0, states.get(0));
			fsm.addTransition(states.get(1), 'b', 1, states.get(1));
			fsm.addTransition(states.get(2), 'a', 1, states.get(1));
			fsm.addTransition(states.get(2), 'b', 1, states.get(2));
		} catch (MissingStateExecption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fsm.saveObject("./res/FSM.fsm");

		FSMIO<Character, Integer> fsm2 = new FSMIO<Character, Integer>("./res/FSM.fsm").getFSMIO();

		System.out.println("Debut de la séquence");
		for (char c : sequence.toCharArray()) {
			System.out.println("lettre :" + c);
			try {
				System.out.println("FSM1 ");
				out = fsm.doTransition(c);
				System.out.println("Etat courant:\t" + fsm.getCurrentState().toString() + "\tsortie:\t" + out);
			} catch (UnidefinedTransitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				System.out.println("FSM2 ");
				out = fsm2.doTransition(c);
				System.out.println("Etat courant:\t" + fsm2.getCurrentState().toString() + "\tsortie:\t" + out);
			} catch (UnidefinedTransitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
