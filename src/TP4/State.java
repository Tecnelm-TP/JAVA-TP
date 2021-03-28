package TP4;

public class State {
	private String name;
	
	public State(String name){
		this.name = name;
	}
	public State (State state)
	{
		this.name = state.name;
	}
	
	public String getName(){
		return this.name;
	}

	public boolean equals(State e){
		return this.name.equals(e.name);		
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
