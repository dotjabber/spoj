package priv.dotjabber.spoj.en.classical;

import java.util.*;

/**
 * https://www.spoj.com/problems/SBANK/
 */
public class SBANK {

    // binary tree
    private static class Node {
        private final String id;
        private int count;

        private Node lower;
        private Node higher;

        private Node(String id) {
            this.id = id;
            this.count = 1;
        }

        public void add(Node n) {
            int c = n.id.compareTo(id);
            if(c == 0) {
                count++;

            } else if(c < 0) {
                if(lower == null) lower = n;
                else lower.add(n);

            } else {
                if(higher == null) higher = n;
                else higher.add(n);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if(lower != null) sb.append(lower);
            sb.append(id).append(" ").append(count).append("\n");
            if(higher != null) sb.append(higher);

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        int testCount = Integer.parseInt(consoleScanner.nextLine());
        while(testCount-- > 0) {

            Node node = null;

            int elementCount = Integer.parseInt(consoleScanner.nextLine());
            while(elementCount-- > 0) {
                String line = consoleScanner.nextLine();

                if(node == null) {
                    node = new Node(line);

                } else {
                    node.add(new Node(line));
                }
            }

            // empty line
            if(testCount > 0) {
                consoleScanner.nextLine();
            }

            System.out.println(node);
        }

        consoleScanner.close();
    }
}
