package machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

public class TM {
	private String[][] input;
	private List<LinkedList<String>> Tapes = new ArrayList<LinkedList<String>>();
	private int[] iterators;
	private List<String> alphabet;
	private List<String> inner_alphabet;
	private States states;
	private Instruction[] instructions;
	private String current_state;
	
	
	public TM(
			int numOfTapes,
			String[][] input,
			List<String> alphabet,
			List<String> inner_alphabet,
			States states,
			Instruction[] instructions)
	{
		this.alphabet = alphabet;
		this.inner_alphabet = inner_alphabet;
		this.states = states;
		this.instructions = instructions;
		this.current_state = states.start();
		this.input = input;
		this.iterators = new int[numOfTapes];
		for(int i = 0; i<numOfTapes; i++) {
			Tapes.add(new LinkedList<String>());
			for(int j = 0; j < input[i].length; j++) {
				Tapes.get(i).add(input[i][j]);
			}
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
		for(int i = 0; i < iterators.length; i++) {
			Tapes.get(i).set(iterators[i], todo.output_symbols[i]);
			switch(todo.move[i]) {
				case L:
					iterators[i]--;
					break;
				case R:
					iterators[i]++;
					break;
				default:
					break;
			}
		}
		return todo.new_state;
	}

	private boolean isRequiredInstruction(Instruction i) { //поиск подходящей инструкции
		boolean matches = true;
		for(int j = 0; j < iterators.length; j++) {
			if (!i.input_symbols[j].equals(Tapes.get(j).get(iterators[j]))) {
				matches = false;
			}
		}
		if(!i.start_state.equals(current_state)) {
			matches = false;
		}
		return matches;
	}
}
			