package amgs.states;

// this program manages which state is currently run by the game
public class StateManager {
	
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
}
