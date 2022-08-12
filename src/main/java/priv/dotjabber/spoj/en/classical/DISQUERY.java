package priv.dotjabber.spoj.en.classical;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.spoj.com/problems/DISQUERY/
 * https://cp-algorithms.com/graph/lca.html#the-idea-of-the-algorithm
 * TODO: submit with success
 */
public class DISQUERY {
    public static class Edge {
        private final String u;
        private final String d;
        private boolean used;
        private final long length;

        public Edge(String a, String b, long length) {
            this.u = a;
            this.d = b;
            this.length = length;
        }

        public boolean has(String label) {
            return label.equals(u) || label.equals(d);
        }

        public String follow(String label) {
            return label.equals(u) ? d : u;
        }
    }

    public static class Tree {
        private final String[] checkpoints;
        private final Edge[] causers;
        private final Edge[] edges;
        private int index;

        public Tree(Edge[] edges) {
            this.edges = edges;

            checkpoints = new String[edges.length * edges.length];
            causers = new Edge[edges.length * edges.length];
            build();
        }

        private void add(String checkpoint, Edge causer) {
            checkpoints[index] = checkpoint;
            causers[index] = causer;
            index++;
        }

        private void build() {
            Arrays.stream(edges).forEach(e -> e.used = false);

            Stack<String> nodeStack = new Stack<>();
            Stack<Edge> edgeStack = new Stack<>();

            add(edges[0].u, null);
            nodeStack.push(edges[0].u);

            while(!nodeStack.isEmpty()) {
                String current = nodeStack.pop();

                List<Edge> options = Arrays.stream(edges)
                        .filter(Objects::nonNull)
                        .filter(e -> !e.used && e.has(current))
                        .collect(Collectors.toList());

                if(options.isEmpty()) {
                    if(!nodeStack.isEmpty()) {
                        add(nodeStack.peek(), edgeStack.pop());
                    }

                } else {
                    Edge causer = options.get(0);
                    causer.used = true;

                    String next = options.get(0).follow(current);

                    nodeStack.push(current);
                    nodeStack.push(next);

                    edgeStack.push(causer);
                    add(next, options.get(0));
                }
            }
        }

        public String query(String a, String b) {
            // establishing a, b positions
            int idxA = 0;
            while(!a.equals(checkpoints[idxA])) {
                idxA++;
            }

            int idxB = index - 1;
            while(!b.equals(checkpoints[idxB])) {
                idxB--;
            }

            Stack<Edge> stack = new Stack<>();
            for(int i = Math.min(idxA, idxB) + 1; i <= Math.max(idxA, idxB); i++) {
                if (stack.contains(causers[i])) {
                    stack.pop();

                } else {
                    stack.push(causers[i]);
                }
            }

            long min = Integer.MAX_VALUE;
            long max = Integer.MIN_VALUE;
            for(Edge e : new ArrayList<>(stack)) {
                if(e != null) {
                    min = Math.min(min, e.length);
                    max = Math.max(max, e.length);
                }
            }
            return min + " " + max;
        }
    }

    public static void main(String[] args) {
        Scanner inScanner = new Scanner(System.in);

        // edge count N
        int N = 0;
        if(inScanner.hasNextLine()) {
            N = Integer.parseInt(inScanner.nextLine());
        }

        // node parameters
        Edge[] edges = new Edge[N - 1];
        for(int i = 0; i < edges.length; i++) {
            String[] edge = inScanner.nextLine().split(" ");
            edges[i] = new Edge(edge[0], edge[1], Long.parseLong(edge[2]));
        }

        // build tree
        Tree tree = new Tree(edges);

        // queries count
        int K = 0;
        if(inScanner.hasNextLine()) {
            K = Integer.parseInt(inScanner.nextLine());
        }

        // queries
        StringBuilder sb = new StringBuilder();
        while(K-- > 0 && inScanner.hasNextLine()) {
            String[] cities = inScanner.nextLine().split(" ");
            sb.append(tree.query(cities[0], cities[1])).append("\n");
        }

        System.out.println(sb);
        inScanner.close();
    }
}
