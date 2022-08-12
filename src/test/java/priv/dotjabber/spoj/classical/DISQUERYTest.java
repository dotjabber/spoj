package priv.dotjabber.spoj.classical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static priv.dotjabber.spoj.en.classical.DISQUERY.Tree;
import static priv.dotjabber.spoj.en.classical.DISQUERY.Edge;

public class DISQUERYTest {

    @Test
    public void spojTestOne() {
        Edge[] edges = {
                new Edge("2", "3", 100),
                new Edge("4", "3", 200),
                new Edge("1", "5", 150),
                new Edge("1", "3", 50)
        };

        Tree tree = new Tree(edges);

        Assertions.assertEquals("100 200", tree.query("2", "4"));
        Assertions.assertEquals("50 150", tree.query("3", "5"));
        Assertions.assertEquals("50 100", tree.query("1", "2"));
    }

    @Test
    public void spojTestTwo() {
        Edge[] edges = {
                new Edge("3", "6", 4),
                new Edge("1", "7", 1),
                new Edge("1", "3", 2),
                new Edge("1", "2", 6),
                new Edge("2", "5", 4),
                new Edge("2", "4", 4)
        };

        Tree tree = new Tree(edges);

        Assertions.assertEquals("2 6", tree.query("6", "4"));
        Assertions.assertEquals("1 4", tree.query("7", "6"));
        Assertions.assertEquals("6 6", tree.query("1", "2"));
        Assertions.assertEquals("2 2", tree.query("1", "3"));
        Assertions.assertEquals("2 6", tree.query("3", "5"));
    }

    @Test
    public void spojTestOverflow() {
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < 5000; i++) {
            edges.add(new Edge(String.valueOf(i), String.valueOf(i+1), 1));
        }

        Tree tree = new Tree(edges.toArray(Edge[]::new));
        for(int i = 0; i < 4999; i++) {
            Assertions.assertEquals("1 1", tree.query(String.valueOf(0), String.valueOf(i + 1)));
        }
    }
}
