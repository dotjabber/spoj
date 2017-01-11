package com.spojpl.easy;

import java.util.Arrays;
import java.util.Scanner;

public class ROWNANIE {
	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);
		
		while(inScanner.hasNextLine()) {
			String line = inScanner.nextLine();
			
			// get the numbers (assume that they are doubles)
			// and there are three of them
			double[] params = Arrays.stream(line.split(" "))
                    .mapToDouble(Double::valueOf)
                    .toArray();
			
			// 0(a), 1(b), 2(c)
			double a = params[0];
			double b = params[1];
			double c = params[2];
			
			double delta = b*b - 4*a*c;
			int solutions = (delta > 0) ? 2 : (delta == 0) ? 1 : 0;
			
			System.out.println(solutions);
		}
		
		inScanner.close();
	}
}
