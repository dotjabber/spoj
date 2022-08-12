package priv.dotjabber.spoj.en.classical;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.spoj.com/problems/ONP/
 */
public class ONP {

    private static class Node extends ArrayList<Object> {
        public Node add(Node n) {
            super.add(n);
            return n;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(get(0).toString());

            for(int i = 1; i < size(); i+=2) {
                sb.append(get(i+1));
                sb.append(get(i));
            }

            return sb.toString();
        }
    }

    private static void parse(Node root, List<Character> data) {
        while (!data.isEmpty()) {
            char c = data.remove(0);

            if(c == '(') parse(root.add(new Node()), data);
            if(c != '(' && c != ')') root.add(c);
            if(c == ')') return;
        }
    }

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        int exampleCount = Integer.parseInt(consoleScanner.nextLine());
        while(exampleCount-- > 0) {
            String line = consoleScanner.nextLine();

            Node root = new Node();
            parse(root, line.chars().mapToObj(e -> (char) e).collect(Collectors.toList()));

            System.out.println(root);
        }

        consoleScanner.close();
    }
}
