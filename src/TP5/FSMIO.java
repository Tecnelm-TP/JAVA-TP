package TP5;

import java.util.ArrayList;
import java.util.List;

public class FSMIO<T1, T2> {
	private List<State> states;
	private TransitionFunction<T1, T2> tf ;
	private State currentState;	
	private State initialState;

	// Constructors
	public FSMIO(List<State> states, State init) {
		this.states = states;
		this.tf = new TransitionFunction<T1, T2>();
		this.currentState = this.initialState = init;
	}

	public FSMIO(State init){
		this.currentState = this.initialState = init;
		this.states = new ArrayList<State>();
		this.states.add(init);
		this.tf = new TransitionFunction<T1, T2>();
	}	

	public void addTransition(State orig, T1 input, T2 output, State dest) throws MissingStateExecption{
		
		if(this.states.contains(dest) && this.states.contains(orig)){
			tf.addTransition(orig, input, output, dest);
		}
		else
			throw new MissingStateExecption();
			
	}
	public boolean addState(State s){		
		boolean done = false;
		if(!this.states.contains(s)){
			this.states.add(s);
			done = true;
		}
		return done;
	}

	public void reset(){
		currentState = initialState;
	}

	public T2 doTransition(T1 input) throws UnidefinedTransitionException{
		
		Transition<T1, T2> nt = tf.getTransition(currentState, input); 
		currentState = nt.getDest();
		
		return  nt.getTag().getOutput();
	}

	public String toString(){
		return tf.toString();
	}

	/**
	 * @return the currentState
	 */
	public State getCurrentState() {
		return currentState;
	}
}