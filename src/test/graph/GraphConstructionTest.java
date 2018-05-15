package test.graph;

import graph.construction.SignalFlowGraph;
import graph.construction.interfaces.IEdge;
import graph.construction.interfaces.INode;
import org.junit.Test;

import java.util.ArrayList;

public class GraphConstructionTest {

    private SignalFlowGraph graph;
    private int[][] edges;
    private ArrayList<INode> matrix;

    @Test
    public void testNormalGraph() {
        System.out.println("Test normal graph:");
        edges = new int[][]{{0, 1, 3}, {1, 2, 2}, {1, 4, 5},
                            {2, 3, 7}, {3, 4, 1}, {3, 2, -1}};
        graph = new SignalFlowGraph(5);
        addEgdesToGraph();
        matrix = graph.getMatrix();
        printMatrix();
    }

    @Test
    public void testGraphWithLoops() {
        System.out.println("Test graph with loops:");
        edges = new int[][] {{0, 0, 3}, {0, 3, 7}, {1, 2, 5},
                             {2, 3, 1}, {3, 0, -10}};
        graph = new SignalFlowGraph(4);
        addEgdesToGraph();
        matrix = graph.getMatrix();
        printMatrix();
    }

    @Test
    public void testSheetProblem3WithoutSinkAndSource() {
        System.out.println("Test without sink and source nodes:");
        edges = new int[][] {{0, 1, 5}, {0, 4, 10}, {1, 2, 10},
                             {2, 3, 2}, {2, 1, -1}, {3, 2, -2},
                             {3, 0, -1}, {4, 4, -1}, {4, 3, 2}};
        graph = new SignalFlowGraph(5);
        addEgdesToGraph();
        matrix = graph.getMatrix();
        printMatrix();
        graph.checkSourceAndSinkNodes();
        matrix = graph.getMatrix();
        printMatrix();
    }

    private void addEgdesToGraph() {
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1], edges[i][2]);
        }
    }

    private void printMatrix() {
        for (INode node: matrix) {
            System.out.print(node.getName() + "\t| ");
            for (IEdge edge : node.getConnections()) {
                System.out.print(edge.getDestination().getName() + "\t" + edge.getWeight() + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }
}