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
			States states,
			Instruction[] instructions)
	{
		this.alphabet = alphabet;
		this.inner_alphabet = alphabet.subList(0, alphabet.size());
		inner_alphabet.add("#"); //Начало строки
		inner_alphabet.add("B"); //Blank
		this.states = states;
		this.instructions = instructions;
		this.current_state = states.start();
		this.input = input;
		this.iterators = new int[numOfTapes];
		for(int i = 0; i<numOfTapes; i++) {
			iterators[i]++; //указывает наа первый элемент входа
			Tapes.add(new LinkedList<String>());
			Tapes.get(i).add("#"); //начало строки
			for(int j = 0; j < input[i].length; j++) {
				Tapes.get(i).add(input[i][j]);
			}
			Tapes.get(i).add("B"); //конец строки
		}
		
	}
	
	public List<LinkedList<String>> executeProgram() throws NoSuchInstruction {
		do {
			Instruction todo = Arrays.stream(instructions).filter(instr -> isRequiredInstruction(instr)).findFirst().get();
			if (todo.equals(null)) throw new NoSuchInstruction(todo);
			current_state = iterate(todo);
		} while(current_state != states.end());
		return Tapes;
	}

	private String iterate(Instruction todo) { //выполнение инструкции
		for(int i = 0; i < iterators.length; i++) {
			Tapes.get(i).set(iterators[i], todo.output_symbols[i]); //смена состояний
			switch(todo.move[i]) { //сдвиг указателейй
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
			