package priv.dotjabber.spoj.en.classical;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ARITH {
    private static final Pattern PATTERN = Pattern.compile("([\\-]?[0-9]+)([+\\-*])([\\-]?[0-9]+)");

    /**
     * Generic operation
     */
    private static abstract class Operator {
        protected List<Number> intermediates = new ArrayList<>();
        protected Number result;

        public abstract void execute(Number left, Number right);
    }

    /**
     * Addition
     */
    private static class Add extends Operator {
        public void execute(Number left, Number right) {
            result = new Number(left.value.add(right.value).toString());
        }

        @Override
        public String toString() {
            return "+";
        }
    }

    /**
     * Substitution
     */
    private static class Sub extends Operator {
        public void execute(Number left, Number right) {
            result = new Number(left.value.subtract(right.value).toString());
        }

        @Override
        public String toString() {
            return "-";
        }
    }

    /**
     * Multiplication
     */
    private static class Mul extends Operator {
        public void execute(Number left, Number right) {
            result = new Number(String.valueOf(left.value.multiply(right.value)));

            for(int i = 0; i < right.repr.length; i++) {
                BigInteger param = new BigInteger(String.valueOf(right.repr[i]));
                intermediates.add(new Number(left.value.multiply(param).toString(), i));
            }
        }

        @Override
        public String toString() {
            return "*";
        }
    }

    /**
     * Printing is important (number of spaces), it depends on all the
     * numbers we are trying to print
     */
    private static class Printer extends ArrayList<String> {
        private int dll; // dash line length

        @Override
        public boolean add(String s) {
            if(dll < s.length()) dll = s.length();
            return super.add(s);
        }

        public void addDash(String upper, String lower) {
            int n = Math.max(upper.length(), lower.length());
            add(String.join("", Collections.nCopies(n, "-")));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for(String line : this) {
                String prefix = String.join("", Collections.nCopies(dll - line.length(), " "));
                sb.append(prefix).append(line.trim()).append("\n");
            }

            return sb.toString();
        }
    }

    /**
     * Representation of a number
     */
    private static class Number {

        // reversed array of digits
        private int[] repr;

        // value of this number
        private final BigInteger value;

        // modificator (+ / -)
        private int modif;

        // shift (for multiplications)
        private final int shift;

        public Number(String number, int shift) {
            value = new BigInteger(number);
            this.shift = shift;

            if(number.contains("-")) modif = -1;

            // make an array out of number
            repr = Arrays.stream(number.replace("-", "").split(""))
                    .mapToInt(Integer::parseInt).toArray();

            // reverse order
            repr = IntStream.rangeClosed(1, repr.length).map(i -> repr[repr.length - i]).toArray();
        }

        public Number(String number) {
            this(number, 0);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if(modif == -1) sb.append("-");
            sb.append(IntStream.rangeClosed(1, repr.length)
                    .map(i -> repr[repr.length - i])
                    .boxed().map(String::valueOf)
                    .collect(Collectors.joining())
            );

            sb.append(String.join("", Collections.nCopies(shift, " ")));

            return sb.toString();
        }
    }

    /**
     * Calculator takes two numbers and an operator
     */
    private static class Calculator {
        private final Number left;
        private final Number right;
        private Operator operator;

        public Calculator(String left, String right, String operator) {
            this.left = new Number(left);
            this.right = new Number(right);

            switch (operator) {
                case "+": this.operator = new Add(); break;
                case "-": this.operator = new Sub(); break;
                case "*": this.operator = new Mul();
            }
        }

        public void execute() {
            operator.execute(left, right);
        }

        @Override
        public String toString() {
            Printer p = new Printer();

            p.add(left.toString());
            p.add(operator.toString() + right);

            if(operator.intermediates.size() > 1) {
                // dash
                p.addDash(operator.toString() + right, operator.intermediates.get(0).toString());

                // calculations
                operator.intermediates.forEach(n -> p.add(n.toString()));

                // dash
                p.addDash(operator.intermediates.get(operator.intermediates.size() - 1).toString(), operator.result.toString());

                // results
                p.add(operator.result.toString());

            } else {

                // dash
                p.addDash(operator.toString() + right, operator.result.toString());

                // results
                p.add(operator.result.toString());
            }

            return p.toString();
        }
    }

    public static void main(String[] args) {
        Scanner inScanner = new Scanner(System.in);

        int noExamples = 0;
        if(inScanner.hasNextLine()) {
            noExamples = Integer.parseInt(inScanner.nextLine());
        }

        while(noExamples-- > 0 && inScanner.hasNextLine()) {
            Matcher matcher = PATTERN.matcher(inScanner.nextLine());

            if(matcher.find()) {
                Calculator calculator = new Calculator(matcher.group(1), matcher.group(3), matcher.group(2));
                calculator.execute();

                System.out.println(calculator);
            }
        }

        inScanner.close();
    }
}
