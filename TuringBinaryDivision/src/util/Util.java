package util;

import java.util.LinkedList;
import java.util.List;

public class Util {

	public static String[] str(String ... s) { //для сокращения записи, str("s1", "s2", "s3") == new String[] {"s1", "s2", "s3"}
		return s;
	}

	public static void printOutput(List<LinkedList<String>> res) {
		for(int i = 0; i < res.size(); i++) {
			for(int j = 0; j < res.get(i).size(); j++) {
				System.out.print(res.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

}
