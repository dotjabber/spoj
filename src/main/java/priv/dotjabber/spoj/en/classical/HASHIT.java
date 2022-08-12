package priv.dotjabber.spoj.en.classical;

import java.util.*;

/**
 * https://www.spoj.com/problems/HASHIT/
 * TODO: submit with success :)
 */
public class HASHIT {
    private static class Element {
        private int hash = -1;
        private int collision = 0;
        private final String value;

        private Element(String value) {
            this.value = value;
        }

        private int hash() {
            if(hash == -1) {
                hash = 0;
                for(int i = 0; i < value.length(); i++) {
                    hash += value.charAt(i) * (i + 1);
                }

                hash = (hash * 19) % 101;
            }

            return (hash + collision * collision + 23 * collision) % 101;
        }

        private void collide() {
            collision++;
        }

        public String toString() {
            return hash() + ":" + value;
        }
    }

    public static void add(Element elem, Element[] table) {
        if(table[elem.hash()] != null) {
            if(!table[elem.hash()].value.equals(elem.value)) {

                int tryCount = 19;
                while(--tryCount >= 0) {
                    elem.collide();

                    if(table[elem.hash()] == null) {
                        table[elem.hash()] = elem;
                        break;
                    }
                }
            }

        } else {
            table[elem.hash()] = elem;
        }
    }

    public static void del(Element elem, Element[] table) {
        if(table[elem.hash()] != null) {
            if(!table[elem.hash()].value.equals(elem.value)) {

                int tryCount = 20;
                while(--tryCount >= 0) {
                    elem.collide();

                    if(table[elem.hash()] != null) {
                        table[elem.hash()] = null;
                        break;
                    }
                }
            } else {
                table[elem.hash()] = null;
            }
        }
    }

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        int exampleCount = Integer.parseInt(consoleScanner.nextLine());
        while(exampleCount-- > 0) {
            Element[] table = new Element[101];

            int opCount = Integer.parseInt(consoleScanner.nextLine());
            while(opCount-- > 0) {
                String[] params = consoleScanner.nextLine().split(":");

                String op = params[0];
                String str = (params.length > 1) ? params[1] : "";

                Element elem = new Element(str);
                switch (op) {
                    case "ADD": add(elem, table);
                        break;
                    case "DEL": del(elem, table);
                        break;
                }
            }

            System.out.println(Arrays.stream(table).filter(Objects::nonNull).count());
            Arrays.stream(table).filter(Objects::nonNull).forEach(System.out::println);
        }

        consoleScanner.close();

    }


 }
