package priv.dotjabber.spoj.en.challenge;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.ortools.linearsolver.*;

class Element {
    int value;
    int p;
    int x;
    int y;
    int n;

    boolean altered = true;
    boolean[] guesses;

    Element(char c, int p) {
        if(c != '.') {
            value = c - 48;
        } else {
            guesses = new boolean[9];
        }

        this.p = p;
        x = p % 9;
        y = p / 9;
        n = (y / 3) * 3 + (x / 3);
    }

    public boolean isPruned() {
        return value != 0;
    }

    public boolean isAltered() {
        return altered;
    }

    public void prune() {
        int c = 0;
        int v = 0;

        for (int i = 0; i < guesses.length; i++, c++) {
            if (guesses[i]) {
                v = i;
            }
        }

        if (c == 1) {
            value = v + 1;
            guesses = null;
            altered = true;
        }
    }

    @Override
    public String toString() {
        return value > 0 ? String.valueOf(value) : ".";
    }
}

class Sudoku {
    String setup;
    Element[] elements;

    Sudoku(String setup) {
        this.setup = setup;
        elements = IntStream.range(0, 81)
                .mapToObj(i -> new Element(setup.charAt(i), i))
                .toArray(Element[]::new);
    }

    public void guess() {
        for (Element processed : elements) {

            if (!processed.isPruned()) {
                processed.altered = false;

                for (int j = 0; j < 9; j++) {

                    // which one we will guess
                    int guess = j + 1;

                    // take a guess
                    boolean value =

                            // box check
                            Arrays.stream(elements).filter(e -> e.n == processed.n).noneMatch(e -> e.value == guess)
                            &&

                            // vertical check
                            Arrays.stream(elements).filter(e -> e.y == processed.y).noneMatch(e -> e.value == guess)
                            &&

                            // horizontal check
                            Arrays.stream(elements).filter(e -> e.x == processed.x).noneMatch(e -> e.value == guess);

                    processed.altered |= processed.guesses[j] != value;
                    processed.guesses[j] = value;
                }
            }
        }
    }


    public Element[] getHorizontal(int v) {
        return Arrays.stream(elements).filter(e -> e.x == v).filter(Element::isPruned).toArray(Element[]::new);
    }

    public int getHorizontalSum(int v) {
        return Arrays.stream(elements).filter(e -> e.x == v).mapToInt(e -> e.value).sum();
    }

    public Element[] getVertical(int v) {
        return Arrays.stream(elements).filter(e -> e.y == v).filter(Element::isPruned).toArray(Element[]::new);
    }

    public int getVerticalSum(int v) {
        return Arrays.stream(elements).filter(e -> e.y == v).mapToInt(e -> e.value).sum();
    }

    public Element[] getBox(int v) {
        return Arrays.stream(elements).filter(e -> e.n == v).filter(Element::isPruned).toArray(Element[]::new);
    }

    public int getBoxSum(int v) {
        return Arrays.stream(elements).filter(e -> e.n == v).mapToInt(e -> e.value).sum();
    }

    public boolean isValid() {
        for(int i = 0; i < 9; i++) {
            int xyn = i;

            if(
                    Arrays.stream(elements).filter(e -> e.n == xyn).filter(Element::isPruned).count() !=
                            Arrays.stream(elements).filter(e -> e.n == xyn).filter(Element::isPruned).distinct().count()
                    && Arrays.stream(elements).filter(e -> e.x == xyn).filter(Element::isPruned).count() !=
                            Arrays.stream(elements).filter(e -> e.x == xyn).filter(Element::isPruned).distinct().count()
                    && Arrays.stream(elements).filter(e -> e.y == xyn).filter(Element::isPruned).count() !=
                            Arrays.stream(elements).filter(e -> e.y == xyn).filter(Element::isPruned).distinct().count()
            ) return false;
        }

        return true;
    }

    public boolean isAltered() {
        return Arrays.stream(elements)
                .filter(e -> !e.isPruned())
                .anyMatch(Element::isAltered);
    }

    public void prune() {
        Arrays.stream(elements)
                .filter(e -> !e.isPruned())
                .forEach(Element::prune);
    }

    public String[] getBranches() {
        String[] branches = null;

        // get element that can be branched
        Element bp = Arrays.stream(elements).filter(e -> !e.isPruned()).findAny().orElse(null);
        if(bp != null) {
            branches = IntStream.range(0, 9)
                    .filter(i -> bp.guesses[i])
                    .mapToObj(i -> setup.substring(0, bp.p) + Character.forDigit(i + 1, 10) + setup.substring(bp.p + 1))
                    .toArray(String[]::new);
        }

        return branches;
    }

    public void solve() {
        while (isAltered()) {
            guess();
            prune();
        }
    }

    public boolean isSolved() {
        return Arrays.stream(elements)
                .allMatch(Element::isPruned);
    }

    @Override
    public String toString() {
        return Arrays.stream(elements)
                .map(Element::toString)
                .collect(Collectors.joining());
    }
}

class ConstraintSolver {
    public void solve(String setup) {
        Sudoku sudoku = new Sudoku(setup);
        sudoku.solve();

        System.out.println(sudoku);

        // dlatego mozemy zaladowac ja z miejsca ktore jest nam znane
        System.load(new File("lib").getAbsolutePath() + File.separator +
                System.mapLibraryName("jniortools"));

        // tworzenie solvera, w zaleznosci od zaladowanej biblioteki natywnej
        MPSolver solver = MPSolver.createSolver("SCIP_MIXED_INTEGER_PROGRAMMING");

        // tworzymy obiekty szukanych zmiennych wraz z ograniczeniami
        MPVariable[] x = new MPVariable[sudoku.elements.length];
        for (int i = 0; i < x.length; i++) {
            if(!sudoku.elements[i].isPruned()) {
                x[i] = solver.makeIntVar(1.0, 9.0, "");
            }
        }

        // tworzymy ograniczenia
        for (int i = 0; i < 9; i++) {
            Element[] v = sudoku.getVertical(i);
            Element[] h = sudoku.getHorizontal(i);
            Element[] b = sudoku.getBox(i);

            MPConstraint vc = solver.makeConstraint(0, 45 - sudoku.getVerticalSum(i), "");
            for (int j = 0; j < v.length; j++) {
                vc.setCoefficient(x[v[j].p], 1);
            }

            MPConstraint hc = solver.makeConstraint(0, 45 - sudoku.getHorizontalSum(i), "");
            for (int j = 0; j < h.length; j++) {
                hc.setCoefficient(x[h[j].p], 1);
            }

            MPConstraint bc = solver.makeConstraint(0, 45 - sudoku.getBoxSum(i), "");
            for (int j = 0; j < b.length; j++) {
                bc.setCoefficient(x[b[j].p], 1);
            }
        }

        // tworzymy funkcję celu
        MPObjective objective = solver.objective();
        for (int i = 0; i < x.length; i++) {
            if(x[i] != null) {
                objective.setCoefficient(x[i], 1);
            }
        }
        objective.setMaximization();

        // poszukaj rozwiazania
        final MPSolver.ResultStatus resultStatus = solver.solve();
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            System.out.println("have the optimal");
        }

        int[] hint = Arrays.stream(x).filter(Objects::nonNull).mapToInt(e -> e.solutionValue() > 0 ? 1 : 0).toArray();
        System.out.println(Arrays.toString(hint));

        // delete the solver
        solver.delete();
    }
}

class Solver {
    public void solve(String setup) {
        Sudoku sudoku = new Sudoku(setup);

        if(sudoku.isValid()) {
            sudoku.solve();

            if(sudoku.isSolved()){
                System.out.println(sudoku);

            } else {
                for (String branch : sudoku.getBranches()) {
                    solve(branch);
                }
            }
        }
    }
}

public class SUD {
    public static void main(String[] args) {
        Scanner inScanner = new Scanner(System.in);

        int noExamples = 0;
        if(inScanner.hasNextLine()) {
            noExamples = Integer.parseInt(inScanner.nextLine());
        }

        ConstraintSolver solver = new ConstraintSolver();
        while(noExamples-- > 0 && inScanner.hasNextLine()) {
            solver.solve(inScanner.nextLine());
        }

        inScanner.close();
    }
}
