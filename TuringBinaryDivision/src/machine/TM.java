package machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

public class TM {
	private List<LinkedList<String>> Tapes = new ArrayList<LinkedList<String>>();
	private List<ListIterator<String>> iterators = new ArrayList<>();
	private List<String> alphabet;
	private List<String> inner_alphabet;
	private States states;
	private Instruction[] instructions;
	private String current_state = states.start();
	
	
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
			iterators.add(Tapes.get(i).listIterator());
		}
	}
	
	public List<LinkedList<String>> executeProgram() throws NoSuchInstruction {
		Stream<Instruction> instructionStream = Arrays.stream(instructions);
		do {
			Instruction todo = instructionStream.filter(instr -> isRequiredInstruction(instr)).findFirst().get();
			if (todo.equals(null)) throw new NoSuchInstruction(todo);
			current_state = iterate(todo);
		} while(current_state != states.end());
		return Tapes;
	}

	private String iterate(Instruction todo) { //выполнение инструкции
		for(int i = 0; i < iterators.size(); i++) {
			iterators.get(i).set(todo.output_symbols[i]);
			switch(todo.move[i]) {
				case L:
					iterators.get(i).previous();
					break;
				case R:
					iterators.get(i).next();
					break;
				default:
					break;
			}
		}
		return todo.new_state;
	}

	private boolean isRequiredInstruction(Instruction i) { //поиск подходящей инструкции
		boolean matches = true;
		for(int j = 0; j < iterators.size(); j++) {
			if (!i.input_symbols[j].equals(iterators.get(j).next())) {
				matches = false;
			}
			iterators.get(j).previous(); 
		}
		if(!i.start_state.equals(current_state)) {
			matches = false;
		}
		return matches;
	}
}
			