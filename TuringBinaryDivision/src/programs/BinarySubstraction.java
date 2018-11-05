package programs;

import machine.Instruction;
import machine.NoSuchInstruction;
import machine.Instruction.Move;
import machine.States;
import machine.TM;

import static util.Util.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinarySubstraction {
	
	int a, b;
	
	Move L = Move.L;
	Move S = Move.stay;
	Move R = Move.R;
	
	ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("0", "1"));
	States states = new States(new String[] {"q0", "q1", "end"}, "q0", "end");
	int numOfTapes = 2;
	
	Instruction[] instructions = {
			new Instruction("q0", str("0","0"), "q0", str("0", "0"), new Move[] {R, R}),
			new Instruction("q0", str("0","1"), "q1", str("1", "1"), new Move[] {R, R}),
			new Instruction("q0", str("1","0"), "q0", str("1", "0"), new Move[] {R, R}),
			new Instruction("q0", str("1","1"), "q0", str("0", "1"), new Move[] {R, R}),
			new Instruction("q1", str("0","0"), "q1", str("1", "0"), new Move[] {R, R}),
			new Instruction("q1", str("0","1"), "q1", str("0", "1"), new Move[] {R, R}),
			new Instruction("q1", str("1","0"), "q0", str("0", "0"), new Move[] {R, R}),
			new Instruction("q1", str("1","1"), "q1", str("0", "1"), new Move[] {R, R}),
			new Instruction("q0", str("B","B"), "end", str("B", "B"), new Move[] {S, S}),
			new Instruction("q0", str("0","B"), "end", str("B", "B"), new Move[] {S, S}),
			new Instruction("q0", str("1","B"), "end", str("B", "B"), new Move[] {S, S})
	};
	
	public BinarySubstraction(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public String[][] formatInput(int a_int, int b_int) {
		assert a_int >= b_int;
		String[] a = splitCharacters(a_int); 
		String[] b = splitCharacters(b_int);
		String[][] inp = new String[2][a.length];
		for(int i = 0; i<a.length; i++) { //little-endian
			inp[0][i] = a[a.length-i-1];
		}
		for(int i = 0; i<b.length; i++) {
			inp[1][i] = b[b.length-i-1];
		}
		for(int i = b.length; i<a.length; i++) {
			inp[1][i] = "0";
		}
		return inp;
	}

	private String[] splitCharacters(int a_int) { //https://stackoverflow.com/questions/2297347/splitting-a-string-at-every-n-th-character
		String[] a = Integer.toBinaryString(a_int).split("(?<=\\G.)");
		return a;
	}
	
	public List<LinkedList<String>> execute() throws NoSuchInstruction {
		String[][] input = formatInput(a, b);
		TM tm = new TM(numOfTapes, input, alphabet, states, instructions);
		return tm.executeProgram();
	}
}

/* big-endian, делимое сдвинуто вправо -- привычная IRL запись
new Instruction("q0", str("0","0"), "q0", str("0", "0"), new Move[] {L, L}),
new Instruction("q0", str("0","1"), "q1", str("1", "1"), new Move[] {L, L}),
new Instruction("q0", str("1","0"), "q0", str("1", "0"), new Move[] {L, L}),
new Instruction("q0", str("1","1"), "q0", str("0", "1"), new Move[] {L, L}),
new Instruction("q1", str("0","0"), "q1", str("1", "0"), new Move[] {L, L}),
new Instruction("q1", str("0","1"), "q1", str("0", "1"), new Move[] {L, L}),
new Instruction("q1", str("1","0"), "q0", str("0", "0"), new Move[] {L, L}),
new Instruction("q1", str("1","1"), "q1", str("0", "1"), new Move[] {L, L}),
new Instruction("q0", str("#","#"), "end", str("#", "#"), new Move[] {S, S}),
*/