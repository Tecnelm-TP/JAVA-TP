package TP5;

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

		if(this == e)
			return true;
		if(e instanceof State)
		{
			return this.name.equals(((State)e).name);
		}
		else 
		return false;		
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
