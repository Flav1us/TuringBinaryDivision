package programs;

import static util.Util.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import machine.Instruction;
import machine.Instruction.Move;
import machine.NoSuchInstruction;
import machine.States;
import machine.TM;

public class UnaryDivision{
	
	Move R = Move.R;
	Move L = Move.L;
	Move S = Move.stay;
	
	int numOfTapes = 3;
	ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("1"));
	States states = new States(new String[] {"q0","q1","q2", "q3", "end"}, "q0", "end");
	
	Instruction[] instructions;
	
	int[] input;
	
	
	public UnaryDivision(int[] input) {
		this.input = input;
		this.instructions = new Instruction[] {
				//import static util.Util.str
			new Instruction("q0", str("1", "1", "B"), "q0", str("B", "1", "B"), new Move[] {R, R, S}),
			new Instruction("q0", str("1", "B", "B"), "q1", str("1", "B", "1"), new Move[] {S, L, R}),
			new Instruction("q1", str("1", "1", "B"), "q1", str("1", "1", "B"), new Move[] {S, L, S}),
			new Instruction("q1", str("1", "#", "B"), "q0", str("1", "#", "B"), new Move[] {S, R, S}),
			
			new Instruction("q0", str("B", "B", "B"), "q3", str("B", "B", "1"), new Move[] {S, L, R}),
			new Instruction("q3", str("B", "1", "B"), "q3", str("B", "B", "B"), new Move[] {S, L, S}),
			new Instruction("q3", str("B", "#", "B"), "end", str("B", "#", "B"), new Move[] {S, R, S}),
			
			new Instruction("q0", str("B", "1", "B"), "q2", str("B", "B", "B"), new Move[] {S, R, S}),
			new Instruction("q2", str("B", "1", "B"), "q2", str("B", "B", "B"), new Move[] {S, R, S}),
			new Instruction("q2", str("B", "B", "B"), "end", str("B", "B", "B"), new Move[] {S, S, S}),
		};
	}
	
	public List<LinkedList<String>> execute() throws NoSuchInstruction {
		String[][] inp = formatInput();
		for(int i = 0; i<inp[2].length; i++) inp[2][i] = "B";
		TM tm = new TM(numOfTapes, inp, alphabet, states, instructions);
		return tm.executeProgram();
	}

	private String[][] formatInput() {
		String[][] inp = new String[3][Math.max(input[0], input[1])];
		for(int i = 0; i<2; i++) {
			for(int j = 0; j<inp[i].length; j++) {
				inp[i][j] = j < input[i] ? "1" : "B";
			}
		}
		inp[2] = new String[Math.max(input[0], input[1])]; //тут будет записан делитель
		return inp;
	}
	
}
