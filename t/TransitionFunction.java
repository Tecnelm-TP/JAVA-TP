package TP4;

import java.util.ArrayList;
import java.util.List;

public class TransitionFunction <T1, T2>{
	private List<Transition<T1, T2>> transitions;

	public TransitionFunction( ) {
		this.transitions = new ArrayList<Transition<T1, T2>>();
	}

	public void addTransition(State orig, T1 input, T2 output, State dest){
		this.transitions.add(new Transition<T1, T2>(orig,
                        new Tag<T1, T2>(input, output), dest));
	}
	
	// Retourne la transition correspondant à l'état orig et à l'entrée input
	public Transition<T1, T2> getTransition(State orig, T1 input) throws UnidefinedTransitionException{
		//Iterator iter = this.transitions.iterator();
		for(Transition<T1, T2> transition : this.transitions)
		{
			if(transition.getOrig().equals(orig) && transition.getTag().getInput().equals(input))
				return transition;
		}
		throw new UnidefinedTransitionException();
	}
	public List<Transition<T1, T2>> getTransitions(State state)
	{
		ArrayList<Transition<T1, T2>> transitions = new ArrayList<>();
		
		for(Transition<T1, T2> transition : this.transitions)
		{
			if(transition.getOrig().equals(state) )
				transitions.add(transition);
		}
		return transitions;
	}
}
