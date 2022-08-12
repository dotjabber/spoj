package priv.dotjabber.spoj.en.basic;

import java.util.Scanner;

/**
 * ACEPTED
 */
public class ALCATRAZ1 {

	public static void main(String[] args) {
		Scanner consoleScanner = new Scanner(System.in);
		
		int exampleCount = Integer.parseInt(consoleScanner.nextLine());
		while(exampleCount-- > 0) {
			String line = consoleScanner.nextLine();

			int sum = 0;
			for(int i = 0; i < line.length(); i++) {
				sum += line.charAt(i);
			}
			
			System.out.println(sum - 48 * line.length());
		}
		
		consoleScanner.close();
	}
}
