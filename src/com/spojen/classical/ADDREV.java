package com.spojen.classical;

import java.util.Scanner;

public class ADDREV {
	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);

		int noExamples = 0;
		if(inScanner.hasNextLine()) {
			noExamples = Integer.parseInt(inScanner.nextLine());
		}

		while(noExamples-- > 0 && inScanner.hasNextLine()) {
			String[] part = inScanner.nextLine().split(" ");
			
			int a = Integer.parseInt(new StringBuilder(part[0]).reverse().toString());
			int b = Integer.parseInt(new StringBuilder(part[1]).reverse().toString());
			int sum = Integer.parseInt(new StringBuilder(String.valueOf(a+b)).reverse().toString());
			
			System.out.println(sum);
			
		}
		
		inScanner.close();
	}
}
