package TP5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FSMIO<T1, T2> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<State> states;
	private TransitionFunction<T1, T2> tf;
	private State currentState;
	private State initialState;
	private String path;

	// Constructors
	public FSMIO(List<State> states, State init) {
		this.states = states;
		this.tf = new TransitionFunction<T1, T2>();
		this.currentState = this.initialState = init;
	}

	public FSMIO(State init) {
		this.currentState = this.initialState = init;
		this.states = new ArrayList<State>();
		this.states.add(init);
		this.tf = new TransitionFunction<T1, T2>();
	}

	public FSMIO(String path) {
		this.path = path;
	}

	@SuppressWarnings("unchecked")
	public FSMIO<T1, T2> getFSMIO() {
		ObjectInputStream ois = null;
		FSMIO<T1, T2> fsm = null;
		try {
			final FileInputStream file = new FileInputStream(path);
			ois = new ObjectInputStream(file);
			fsm = ((FSMIO<T1, T2>) ois.readObject());

		} catch (final IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

		return fsm;
	}

	public void addTransition(State orig, T1 input, T2 output, State dest) throws MissingStateExecption {

		if (this.states.contains(dest) && this.states.contains(orig)) {
			tf.addTransition(orig, input, output, dest);
		} else
			throw new MissingStateExecption();

	}

	public boolean addState(State s) {
		boolean done = false;
		if (!this.states.contains(s)) {
			this.states.add(s);
			done = true;
		}
		return done;
	}

	public void reset() {
		currentState = initialState;
	}

	public T2 doTransition(T1 input) throws UnidefinedTransitionException {

		Transition<T1, T2> nt = tf.getTransition(currentState, input);
		currentState = nt.getDest();

		return nt.getTag().getOutput();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("States: "+states.toString()+"\t"+"Entree: "+this.getEnter().toString()+"\n");
		sb.append(tf.toString());
		return  sb.toString();
	}

	/**
	 * @return the currentState
	 */
	public State getCurrentState() {
		return currentState;
	}

	/**
	 * @return the states
	 */
	public List<State> getStates() {
		return states;
	}

	public List<T1> getEnter() {
		ArrayList<T1> enter = new ArrayList<>();
		for (State s : states) {
			for (Transition<T1, T2> t : tf.getTransitions(s)) {
				if (!enter.contains(t.getTag().getInput())) {
					enter.add(t.getTag().getInput());
				}
			}
		}
		return enter;
	}

	public void saveObject(String path) {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fichier = new FileOutputStream(path);
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(this);
			oos.flush();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}