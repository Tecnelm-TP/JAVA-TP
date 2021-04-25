package TP5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransitionFunction<T1, T2> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Transition<T1, T2>> transitions;

	public TransitionFunction() {
		this.transitions = new ArrayList<Transition<T1, T2>>();
	}

	public void addTransition(State orig, T1 input, T2 output, State dest) {
		this.transitions.add(new Transition<T1, T2>(orig, new Tag<T1, T2>(input, output), dest));
	}

	// Retourne la transition correspondant à l'état orig et à l'entrée input
	public Transition<T1, T2> getTransition(State orig, T1 input) throws UnidefinedTransitionException {
		Iterator<Transition<T1, T2>> iter = this.transitions.iterator();
		Transition<T1, T2> transition;
		while (iter.hasNext()) {
			transition = iter.next();
			if (transition.getOrig().equals(orig) && transition.getTag().getInput().equals(input))
				return transition;
		}
		throw new UnidefinedTransitionException();
	}

	public List<Transition<T1, T2>> getTransitions(State state) {
		ArrayList<Transition<T1, T2>> transitions = new ArrayList<>();
		Iterator<Transition<T1, T2>> iter = this.transitions.iterator();
		Transition<T1, T2> transition;

		while (iter.hasNext()) {
			transition = iter.next();
			if (transition.getOrig().equals(state))
				transitions.add(transition);
		}

		return transitions;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Transition<T1, T2> t : transitions)
		{
			sb.append(t.getOrig().toString() + "-->"+t.getDest().toString()+"\tTag= "+t.getTag().toString()+"\n");
		}
		return sb.toString();
	}

}
