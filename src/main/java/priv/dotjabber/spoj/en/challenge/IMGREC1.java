package priv.dotjabber.spoj.en.challenge;

import java.util.Scanner;

/**
 * https://www.spoj.com/problems/IMGREC1/
 *
 * Data:
 * 1
 *
 * 5
 *
 * 5 5
 * x...x
 * .x.x.
 * ..x..
 * .x.x.
 * x...x
 * 5 5
 * xxxxx
 * x...x
 * x...x
 * x...x
 * xxxxx
 * 6 6
 * ..x...
 * ..x...
 * xxxxxx
 * ..x...
 * ..x...
 * ......
 * 5 5
 * .xxx.
 * x...x
 * x...x
 * x...x
 * .xxx.
 * 5 5
 * .xxx.
 * .x.x.
 * .xxx.
 * .....
 * .....
 */
public class IMGREC1 {

    public static void main(String[] args) {
        Scanner inScanner = new Scanner(System.in);

        // number of test cases
        int noExamples = 0;
        if(inScanner.hasNextLine()) {
            noExamples = Integer.parseInt(inScanner.nextLine());
        }

        for(int i = 0; i < noExamples; i++) {

            // empty line
            if(inScanner.hasNextLine()) {
                inScanner.nextLine();
            }

            // number of letters in an example
            int noLetters = 0;
            if(inScanner.hasNextLine()) {
                noLetters = Integer.parseInt(inScanner.nextLine());
            }

            // empty line
            if(inScanner.hasNextLine()) {
                inScanner.nextLine();
            }

            for(int j = 0; j < noLetters; j++) {

                // image parameters
                int width = 0;
                int height = 0;
                if (inScanner.hasNextLine()) {
                    String[] params = inScanner.nextLine().split(" ");
                    height = Integer.parseInt(params[0]);
                    width = Integer.parseInt(params[1]);
                }

                // image itself
                char[][] data = new char[height][width];
                for (int h = 0; h < height; h++) {
                    String pixels = inScanner.nextLine();;

                    for (int w = 0; w < width; w++) {
                        data[h][w] = pixels.charAt(w);
                    }
                }

                if (analyze(data)) {
                    System.out.print("0");

                } else {
                    System.out.print("x");
                }
            }

            System.out.println();
        }

        inScanner.close();
    }

    private static boolean hasIt(char[][] data, int pi, int pj) {
        boolean hasUp = false;
        for(int i = pi; i >= 0; i--) {
            hasUp |= data[i][pj] == 'x';
        }

        boolean hasDown = false;
        for(int i = pi; i < data.length; i++) {
            hasDown |= data[i][pj] == 'x';
        }

        boolean hasLeft = false;
        for(int j = pj; j >= 0; j--) {
            hasLeft |= data[pi][j] == 'x';
        }

        boolean hasRight = false;
        for(int j = pj; j < data[pi].length; j++) {
            hasRight |= data[pi][j] == 'x';
        }

        int idx = 0;
        idx += hasUp ? 1 : 0;
        idx += hasDown ? 1 : 0;
        idx += hasLeft ? 1 : 0;
        idx += hasRight ? 1 : 0;

        return idx > 3 && data[pi][pj] == '.';
    }

    private static boolean analyze(char[][] data) {
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                if(hasIt(data, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }
}
