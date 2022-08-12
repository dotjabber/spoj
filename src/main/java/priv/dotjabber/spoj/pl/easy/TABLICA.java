package priv.dotjabber.spoj.pl.easy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TABLICA {
	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);
		
		if(inScanner.hasNextLine()) {
			String line = inScanner.nextLine();
			
			int[] numbers = Arrays.stream(line.split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();
			
			int j = 0;
			for(int i = 0; i < numbers.length / 2; i++) {
				j = numbers.length - i - 1;
				numbers[i] = numbers[i] ^ numbers[j] ^ (numbers[j] = numbers[i]);
			}
			
			String result = Arrays.stream(numbers)
				.mapToObj(Integer::toString)
				.collect(Collectors.joining(" "));
			
			System.out.println(result);
		}

		inScanner.close();
	}
}
