package machine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TM {
	private List<LinkedList<String>> Tapes = new ArrayList<LinkedList<String>>();
	private List<ListIterator<String>> Iterators = new ArrayList<>();
	private List<String> alphabet;
	private List<String> inner_alphabet;
	private States states;
	private Instruction[] instructions;
	
	
	public TM(
			int numOfTapes,
			List<String> alphabet,
			List<String> inner_alphabet,
			States states,
			Instruction[] instructions)
	{
		this.alphabet = alphabet;
		this.inner_alphabet = inner_alphabet;
		this.states = states;
		this.instructions = instructions;
		for(int i = 0; i<numOfTapes; i++) {
			Tapes.add(new LinkedList<String>());
			Iterators.add(Tapes.get(i).listIterator());
		}
	}
	
	private void set(int tapeIndex, String element) {
		assert alphabet.contains(element);
		getIterator(tapeIndex).set(element);
	}
	
	private ListIterator<String> getIterator(int tapeIndex) {
		return Iterators.get(tapeIndex);
	}
	
	public List<LinkedList<String>> executeProgram() {
		//String current_state =
		//TODO loop
		return Tapes;
	}
}
			