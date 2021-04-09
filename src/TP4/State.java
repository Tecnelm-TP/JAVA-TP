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

	@Override
	public boolean equals(Object e){

		if(e instanceof State)
		{
			return this.name.equals(((State)e).name);
		}
		else 
		return false;		
	}
	
	public boolean equals(State e){
		return this.name.equals(e.name);
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
