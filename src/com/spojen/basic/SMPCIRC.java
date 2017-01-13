package com.spojen.basic;

import java.util.Arrays;
import java.util.Scanner;

//2
//103 104 5 100 100 10
//103 104 10 100 100 10
/**
 * ACEPTED
 */
public class SMPCIRC {
	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);

		int noExamples = 0;
		if(inScanner.hasNextLine()) {
			noExamples = Integer.parseInt(inScanner.nextLine());
		}

		while(noExamples-- > 0 && inScanner.hasNextLine()) {
			
			//ax, ay, ar, bx, by, br
			// 0,  1,  2,  3,  4,  5
			String line = inScanner.nextLine();
			double[] params =  Arrays.stream(line.split(" "))
					.mapToDouble(Double::valueOf)
					.toArray();
			
			double dist = Math.sqrt(Math.pow(params[0] - params[3], 2) + (Math.pow(params[1] - params[4], 2)));
			double diff = Math.abs(params[2] - params[5]);

			// cases
			if(dist < diff) {
				System.out.println("I");
				
			} else if (dist == diff) {
				System.out.println("E");
				
			} else {
				System.out.println("O");
			}
		}
		
		inScanner.close();
	}
}
