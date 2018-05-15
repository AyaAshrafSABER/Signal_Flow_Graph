package test.graph;

import graph.analysis.Mason;
import graph.construction.SignalFlowGraph;
import org.junit.Test;

import java.util.List;

public class ProjectTest {

    private SignalFlowGraph graph;
    private int[][] edges;
    private Mason mason;

    @Test
    public void test1() {
        graph = new SignalFlowGraph(6);
        edges = new int[][] {{0, 1, 1}, {1, 2, 1}, {1, 3, 1}, {1, 4, 1}, {2, 1, -1}, {2, 3, 1}, {3, 2, -1}, {3, 3, -1}, {3, 4, 1}, {4, 5, 1}};
        addEgdesToGraph();
        mason = new Mason(graph);
        displayResult();
    }

    @Test
    public void test2() {
        graph = new SignalFlowGraph(5);
        edges = new int[][] {{0, 1, 1}, {0, 2, 1}, {1, 2, 1}, {2, 3, 1}, {3, 1, -1}, {3, 2, -1}, {3, 4, 1}};
        addEgdesToGraph();
        mason = new Mason(graph);
        displayResult();
    }

    @Test
    public void test3() {
        graph = new SignalFlowGraph(6);
        edges = new int[][] {{0, 1, 1}, {1, 2, 1}, {1, 3, 1},
                {2, 1, -1}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1},
                {4, 1, -1}, {4, 3, -1}};
        addEgdesToGraph();
        mason = new Mason(graph);
        displayResult();
    }

    @Test
    public void test4() {
        graph = new SignalFlowGraph(7);
        edges = new int[][] {{0, 1, 1}, {1, 2, 1}, {1, 4, 1},
                {2, 3, 1}, {3, 2, -1}, {3, 5, 1}, {4, 4, -1},
                {4, 5, 1}, {5, 3, -1}, {5, 1, -1}, {5, 6, 1}};
        addEgdesToGraph();
        mason = new Mason(graph);
        displayResult();
    }

    @Test
    public void test5() {
        graph = new SignalFlowGraph(9);
        edges = new int[][] {{0, 1, 1}, {1, 2, 1}, {2, 3, 1},
                {2, 7, 1}, {3, 4, 1}, {4, 5, 1}, {5, 6, 1},
                {6, 7, 1}, {7, 8, 1}, {7, 4, -1},
                {7, 1, -1}, {5, 2, -1}};
        addEgdesToGraph();
        mason = new Mason(graph);
        displayResult();
    }

    @Test
    public void test6() {
        graph = new SignalFlowGraph(9);
        edges = new int[][] {{0, 1, 1}, {1, 2, 1}, {2, 3, 1},
                {3, 4, 1}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1},
                {7, 8, 1}, {7, 5, -1}, {7, 1, -1}, {5, 4, -1},
                {3, 6, 1}, {5, 7, 1}, 
                {6, 2, -1}};
        addEgdesToGraph();
        mason = new Mason(graph);
        displayResult();
    }

    @Test
    public void test7() {
        graph = new SignalFlowGraph(8);
        edges = new int[][] {{0, 1, 1}, {1, 2, 1}, {2, 3, 1},
                {3, 4, 1}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1},
                {2, 1, -1}, {4, 3, -1}, {6, 5, -1}};
        addEgdesToGraph();
        mason = new Mason(graph);
        displayResult();
    }

    @Test
    public void test8() {
        graph = new SignalFlowGraph(10);
        edges = new int[][] {{0, 1, 1}, {1, 2, 1}, {2, 3, 1},
                {3, 4, 1}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1},
                {7, 8, 1}, {8, 9, 1}, {8, 7, -1}, {6, 5, -1}, {4, 3, -1},
                {2, 1, -1}};
        addEgdesToGraph();
        mason = new Mason(graph);
        displayResult();
    }
    @Test
    public void test9() {
        graph = new SignalFlowGraph(15);
        edges = new int[][] {{0, 1, 1}, {1, 2, 1}, {2, 3, 1},
                {3, 4, 1}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1},
                {7, 8, 1}, {8, 9, 1},{9,10,1},{10,11,1},{11,12,1},{12,13,1},{13,14,1},{13,12,-1},{11,10,-1}, {8, 7, -1}, {6, 5, -1}, {4, 3, -1},
                {2, 1, -1},};
        addEgdesToGraph();
        mason = new Mason(graph);
        displayResult();
    }

    private void addEgdesToGraph() {
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1], edges[i][2]);
        }
    }

    private void displayResult() {
        System.out.println(mason.getNumberOfFPs());
        for (String fp: mason.getFPs()) {
            System.out.println(fp);
        }
        for (String loop: mason.getAllLoops()) {
            System.out.println(loop);
        }
        int degree = 2;
        for (List<String> nonT: mason.getnonTouchingLoops()) {
            if (!nonT.isEmpty()) {
                System.out.println(degree + " non-touching loops:");
                for (String s: nonT) {
                    System.out.print(s + " ");
                }
                degree++;
                System.out.println();
            }
            System.out.println();
        }
        for (Double sd: mason.getPathDelta()) {
            System.out.println(sd.toString());
        }
        System.out.println(mason.getGeneralDelta());
        System.out.println(mason.getOATF());
    }
}
