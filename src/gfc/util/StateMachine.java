package gfc.util;


public final class StateMachine {

	private State current_state;
	
	public void toState(State s) {
		current_state.leaveState();
		s.enterState();
	}
	
	public boolean isState(State s) {
		return( current_state == s );
	}
}
