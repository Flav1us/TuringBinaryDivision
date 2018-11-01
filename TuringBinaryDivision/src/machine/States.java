package machine;

import java.util.Arrays;

public class States {
	String[] arr;
	String start_state;
	String end_state;
	
	public States(String[] all_states, String start_state, String end_state) {
		assert Arrays.asList(all_states).contains(start_state);
		assert Arrays.asList(all_states).contains(end_state);
		this.arr = all_states;
		this.start_state = start_state;
		this.end_state = end_state;
	}
	
	public String end() {
		return end_state;
	}
	
	public String start() {
		return start_state;
	}
}
