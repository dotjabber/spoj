package com.spojen.classical;

import java.util.Scanner;

/**
 * Przekombinowa³em, mario ma zrobiæ sekwencje od indeksu 0, nie mo¿e sobie skakaæ miêdzy
 * grzybkami...
 *
 */
public class GLJIVE {
	private static int SEQUENCE = 10;
	private static int[] POINTS = new int[SEQUENCE];

	public static void main(String[] args) {
		Scanner consoleScanner = new Scanner(System.in);
		
		for(int i = 0; i < SEQUENCE; i++) {
			POINTS[i] = Integer.parseInt(consoleScanner.nextLine());
		}
		
		int score = 0;
		int closeCall = Integer.MIN_VALUE;
		int closeCallDiff = Integer.MAX_VALUE;
		
		for(int i = 0; i < SEQUENCE; i++) {
			score += POINTS[i];
			
			int scoreDiff = Math.abs(score - 100);
			if((closeCallDiff == scoreDiff && closeCall < score) || closeCallDiff > scoreDiff) {
				closeCall = score;
				closeCallDiff = scoreDiff;
			}
		}

		System.out.println(closeCall);
		consoleScanner.close();
	}
}
