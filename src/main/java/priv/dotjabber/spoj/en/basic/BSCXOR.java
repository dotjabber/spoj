package priv.dotjabber.spoj.en.basic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ACEPTED
 */
public class BSCXOR {

	public static void main(String[] args) {
		Scanner consoleScanner = new Scanner(System.in);
		
		String line = consoleScanner.nextLine();
		int xor = Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).sum() % 2;
		
		System.out.println(xor);
		
		consoleScanner.close();
	}
}
