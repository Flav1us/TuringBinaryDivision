package main;

import java.util.LinkedList;
import java.util.List;

import machine.NoSuchInstruction;
import programs.UnaryDivision;

public class Main {

	public static void main(String[] args) throws NoSuchInstruction {
		// a / b
		int a=0, b=0;
		
		try {
			a = Integer.parseInt(args[0], 2);
			b = Integer.parseInt(args[1], 2);
		} catch (NumberFormatException e1) {
			try {
				a = Integer.parseInt(args[0]);
				b = Integer.parseInt(args[1]);
			} catch (NumberFormatException e2) {
				System.out.println("Input error: required two numbers in decimal or binary form");
				System.exit(0);
			}
		}
		
		if(b==0) {
			System.out.println("Cannot divide by zero");
			System.exit(0);
		}
		
		int[] intInput = {a, b};
		
		UnaryDivision ud = new UnaryDivision(intInput);
		
		List<LinkedList<String>> res = ud.execute();
		
		int divisor = 0, remainder = 0;
		for(int i = 0; i<res.get(2).size(); i++) {
			if(res.get(2).get(i).equals("1")) divisor++;
		}
		for(int i = 0; i<res.get(2).size(); i++) {
			if(res.get(1).get(i).equals("1")) remainder++;
		}
		printAsIntegers(a, b, divisor, remainder);
		printAsBinary(a, b, divisor, remainder);
	}

	private static void printAsBinary(int a, int b, int divisor, int remainder) {
		System.out.println(bin(a) + " = " + bin(b) + " * " + bin(divisor) + " + " + bin(remainder));
	}

	private static void printAsIntegers(int a, int b, int divisor, int remainder) {
		System.out.println(a + " = " + b + " * " + divisor + " + " + remainder);
	}
	
	private static String bin(int i) {
		return Integer.toBinaryString(i);
	}

}
