package priv.dotjabber.spoj.en.classical;

import java.util.Scanner;
/**
1
2
3
5
8
13
21
34
55
89
 */
public class GLJIVE_ALTERN {
	private static int SEQUENCE = 10;
	private static int[] POINTS = new int[SEQUENCE];
	private static int[] SCORE = new int[SEQUENCE];
	private static int[] SCOREDIFF = new int[SEQUENCE];

	public static void main(String[] args) {
		Scanner consoleScanner = new Scanner(System.in);
		for(int i = 0; i < SEQUENCE; i++) {
			POINTS[i] = Integer.parseInt(consoleScanner.nextLine());
		}
		
		for(int i = 0; i < SEQUENCE; i++) {
			jump(i);
		}
		
		int currentScore = 0;
		int currentScoreDiff = Integer.MAX_VALUE;
		for(int i = 0; i < SEQUENCE; i++) {
			int nextScore = SCORE[i];
			int nextScoreDiff =  SCOREDIFF[i];

			if((currentScoreDiff == nextScoreDiff && currentScore < nextScore) || currentScoreDiff > nextScoreDiff) {
				currentScore = nextScore;
				currentScoreDiff = nextScoreDiff;
			}
		}
		
		System.out.println(currentScore);
		
		consoleScanner.close();
	}
	
	public static void jump(int index) {
		int currentScore = 0;
		int currentScoreDiff = Integer.MAX_VALUE;
		for(int i = index; i < SEQUENCE; i++) {
			int nextScore = currentScore + POINTS[i];
			int nextScoreDiff =  Math.abs(nextScore - 100);

			if((currentScoreDiff == nextScoreDiff && currentScore < nextScore) || currentScoreDiff > nextScoreDiff) {
				currentScore = nextScore;
				currentScoreDiff = nextScoreDiff;
			}
		}
		
		SCORE[index] = currentScore;
		SCOREDIFF[index] = currentScoreDiff;
	}
}
