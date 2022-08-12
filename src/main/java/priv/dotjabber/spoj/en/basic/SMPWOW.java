package priv.dotjabber.spoj.en.basic;

import java.util.Scanner;

/**
 * ACEPTED
 */
public class SMPWOW {

	public static void main(String[] args) {
		Scanner consoleScanner = new Scanner(System.in);
		
		String line = consoleScanner.nextLine();
		int repeat = Integer.parseInt(line);
		
		System.out.println("W" + new String(new char[repeat]).replace("\0", "o") + "w");;
		consoleScanner.close();
	}

}
