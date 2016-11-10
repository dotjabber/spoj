package priv.dotjabber.easy;

import java.util.Scanner;
import java.util.stream.LongStream;

public class FCTRL3 {
	
	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);

		int noExamples = 0;
		if(inScanner.hasNextInt()) {
			noExamples = inScanner.nextInt();
		}
		
		while(noExamples-- > 0 && inScanner.hasNextInt()) {
			long n = inScanner.nextInt();
			long factorial = LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
			
			long end = factorial % 100;
			String endStr = String.valueOf(end);
			
			if(endStr.length() > 1) {
				System.out.println(endStr.charAt(0) + " " + endStr.charAt(1));
				
			} else {
				System.out.println("0 " + endStr.charAt(0));
			}
			
		}
		
		inScanner.close();
	}
}