package priv.dotjabber.spoj.en.classical;

import java.util.Scanner;

/**
 * https://www.spoj.com/problems/SAMER08F/
 */
public class SAMER08F {
    private static int feynmann(int i) {
        return (i == 1) ? 1 : i * i + feynmann(i - 1);
    }

    public static void main(String[] args) {
        Scanner inScanner = new Scanner(System.in);

        while(inScanner.hasNextLine()) {
            int size = Integer.parseInt(inScanner.nextLine());

            if(size == 0) break;
            System.out.println(feynmann(size));
        }

        inScanner.close();
    }
}
