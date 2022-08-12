package priv.dotjabber.spoj.en.classical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.spoj.com/problems/COMMEDIA/
 * https://www.youtube.com/watch?v=YI1WqYKHi78
 */
public class COMMEDIA {

    public static boolean isSolvable(int[] data, int m) {
        int swaps = 0;
        int empty = 0;

        // empty steps
        for(int i = 0; i < data.length - 1; i++) {
            if(data[i] == 0) {
                empty += m - 1 - i % m;
                empty += m - 1 - i % (m * m) / m;
                empty += m - 1 - i / (m * m);
            }
        }

        for(int i = 0; i < data.length - 1; i++) {
            int search = i + 1;

            if(data[i] != search) {

                // search for a good replacement (based on i)
                for (int j = i + 1; j < data.length; j++) {

                    // do the flip (with xor, no tmp needed)
                    if(data[j] == search) {

                        swaps ++;
                        data[i] = data[i] ^ data[j];
                        data[j] = data[i] ^ data[j];
                        data[i] = data[i] ^ data[j];
                    }
                }
            }
        }

        return swaps % 2 == empty % 2;
    }

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        int exampleCount = Integer.parseInt(consoleScanner.nextLine());
        while(exampleCount-- > 0) {

            int m = Integer.parseInt(consoleScanner.nextLine());
            int[] cube = new int[m * m * m];

            for(int i = 0; i < m; i++) {
                String line = consoleScanner.nextLine();

                System.arraycopy(
                        Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray(),
                        0, cube, i * m * m, m * m
                );
            }

            System.out.println(isSolvable(cube, m) ? "Puzzle can be solved." : "Puzzle is unsolvable.");
        }

        consoleScanner.close();
    }
}
