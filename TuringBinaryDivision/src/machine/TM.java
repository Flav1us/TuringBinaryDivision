package machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TM {
	private String[][] input;
	private List<LinkedList<String>> tapes = new ArrayList<LinkedList<String>>();
	private int[] iterators;
	private List<String> alphabet;
	private List<String> inner_alphabet;
	private States states;
	private Instruction[] instructions;
	private String current_state;
	
	
	public TM(int numOfTapes, String[][] input, List<String> alphabet, States states, Instruction[] instructions) {
		assert !alphabet.contains("#") && !alphabet.contains("B"); //служебные
		this.alphabet = alphabet;
		craftInnerAlphabet(alphabet);
		this.states = states;
		this.instructions = instructions;
		this.current_state = states.start();
		this.input = input;
		this.iterators = new int[numOfTapes];
		initTapes(numOfTapes, input);
	}
	
	private void craftInnerAlphabet(List<String> alphabet) {
		this.inner_alphabet = alphabet.subList(0, alphabet.size());
		inner_alphabet.add("#"); //Начало строки
		inner_alphabet.add("B"); //Blank
	}

	private void initTapes(int numOfTapes, String[][] input) {
		for(int i = 0; i<numOfTapes; i++) {
			iterators[i]++; //указывает на первый элемент входа
			tapes.add(new LinkedList<String>());
			tapes.get(i).add("#"); //начало строки
			for(int j = 0; j < input[i].length; j++) {
				tapes.get(i).add(input[i][j]);
			}
			tapes.get(i).add("B"); //конец строки
		}
	}
	
	public List<LinkedList<String>> executeProgram() throws NoSuchInstruction {
		do {
			Optional<Instruction> todo = Arrays.stream(instructions).filter(instr -> isRequiredInstruction(instr)).findFirst();
			if (todo.isPresent()) {
				current_state = iterate(todo.get());
			}
			else { 
				throw new NoSuchInstruction(getCurrentInstruction());
			}
				
		} while(current_state != states.end());
		return tapes;
	}

	private String iterate(Instruction todo) { //выполнение инструкции
		for(int i = 0; i < iterators.length; i++) {
			tapes.get(i).set(iterators[i], todo.output_symbols[i]); //смена состояний
			switch(todo.move[i]) { //сдвиг указателейй
				case L:
					iterators[i]--;
					break;
				case R:
					iterators[i]++;
					if(iterators[i] == tapes.get(i).size()) tapes.get(i).add("B"); //самоудлинение
					break;
				case stay:
					break;
				default:
					throw new IllegalArgumentException("Move operation " + todo.move[i] + " is not supported");
			}
		}
		return todo.new_state;
	}

	private boolean isRequiredInstruction(Instruction i) { //поиск подходящей инструкции
		boolean matches = true;
		for(int j = 0; j < iterators.length; j++) {
			if (!i.input_symbols[j].equals(tapes.get(j).get(iterators[j]))) {
				matches = false;
			}
		}
		if(!i.start_state.equals(current_state)) {
			matches = false;
		}
		return matches;
	}
	
	public Instruction getCurrentInstruction() {
		String[] inp_sym = new String[iterators.length];
		for(int j = 0; j < iterators.length; j++) {
			inp_sym[j] = tapes.get(j).get(iterators[j]);
		}
		return new Instruction(current_state, inp_sym, null, null, null);
	}
}
			