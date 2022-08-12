package priv.dotjabber.spoj.en.classical;

import java.util.Scanner;

public class PALIN {

    public static class OverflowException extends Exception {}

    public static void setup(char[] number, int index) throws OverflowException {
        if(index == -1) {
            throw new OverflowException();

        } else {
            if(number[index] == '9') {
                number[index] = '0';
                setup(number, index - 1);

            } else {
                number[index] ++;
            }
        }
    }

    public static void digest(char[] number) throws OverflowException {
        int uIndex = 0;
        int dIndex = number.length - 1;

        while(uIndex < dIndex) {
            if (dIndex - uIndex >= 1) {
                if(number[dIndex] > number[uIndex]) {
                    number[dIndex] = number[uIndex];
                    setup(number, dIndex - 1);

                } else {
                    number[dIndex] = number[uIndex];
                    uIndex++;
                    dIndex--;
                }
            }
        }
    }

    public static String next(String current) {
        char[] number = current.toCharArray();

        try {
            // break the balance
            setup(number, number.length - 1);
            digest(number);

        } catch (OverflowException ex) {
            number = String.valueOf(1 + (int) Math.pow(10, number.length)).toCharArray();
        }

        return new String(number);
    }

    public static void main(String[] args) {
        Scanner inScanner = new Scanner(System.in);

        int noExamples = 0;
        if(inScanner.hasNextLine()) {
            noExamples = Integer.parseInt(inScanner.nextLine());
        }

        while(noExamples-- > 0 && inScanner.hasNextLine()) {
            String number = inScanner.nextLine();
            System.out.println(next(number));
        }

        inScanner.close();
    }
}
