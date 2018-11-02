package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import machine.Instruction;
import machine.Instruction.Move;
import machine.NoSuchInstruction;
import machine.States;
import machine.TM;
import programs.UnaryDivision;

public class Main {

	public static void main(String[] args) throws NoSuchInstruction {
		int[] intInput = {11, 5};
		
		UnaryDivision ud = new UnaryDivision(intInput); 
		List<LinkedList<String>> res = ud.execute();
		for(LinkedList<String> l : res) {
			for(String s : l) {
				System.out.print(s + "\t");
			} System.out.println();
		}
	}

	private static void test0() {
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
		instrs[0] = new Instruction("q0", new String[] {"0", "0", "0"}, "q1", new String[] {"1", "1", "1"}, new Move[] {Move.stay, Move.stay, Move.stay}); 
		String[][] testInput = {
				{"0", "0", "0"},
				{"0", "0", "0"},
				{"0", "0", "0"}
		};
	}

}
