package programs;

import java.util.List;

import machine.Instruction;
import machine.States;

public abstract class AbstractProgram {
//numOfTapes, testInput, alphabet, inner_alphabet, states, instrs
	int numOfTapes;
	String[][] input;
	List<String> alphabet;
	States states;
	Instruction[] instruntions;
	
}
