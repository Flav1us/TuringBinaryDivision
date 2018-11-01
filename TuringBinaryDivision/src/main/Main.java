package main;

import java.util.LinkedList;
import java.util.List;

import machine.Instruction;
import machine.Instruction.Move;
import machine.States;
import machine.TM;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numOfTapes = 3;
		List<String> alphabet = new LinkedList<>();
		alphabet.add("0");
		alphabet.add("1");
		List<String> inner_alphabet = alphabet.subList(0, alphabet.size());
		inner_alphabet.add(" ");
		alphabet.add(" ");
		String[] stat = {"q0", "q1"};
		States states = new States(stat, "q0", "q1");
		Instruction[] instrs = new Instruction[1];
		instrs[0] = new Instruction("q0", new String[] {"0", "0", "0"}, "q1", new String[] {"1", "1", "1"}, Move.stay); 
		
			
		TM m = new TM(numOfTapes, alphabet, inner_alphabet, states, instrs);

	}

}
