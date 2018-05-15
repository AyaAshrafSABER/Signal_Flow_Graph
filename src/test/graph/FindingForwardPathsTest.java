package test.graph;

import java.util.List;

import graph.construction.interfaces.IPath;
import org.junit.Test;

import graph.analysis.ForwardPathsFinder;
import graph.construction.SignalFlowGraph;

public class FindingForwardPathsTest {

    private SignalFlowGraph graph;
    private int[][] edges;

    @Test
    public void testNormalGraph() {
        System.out.println("Test normal graph:");
        edges = new int[][]{{0, 1, 3}, {1, 2, 2}, {1, 4, 5},
                {2, 3, 7}, {3, 4, 1}, {3, 2, -1}};
        graph = new SignalFlowGraph(5);
        addEgdesToGraph();
        List<IPath> fPaths = ForwardPathsFinder.getForwardPaths(graph);
        printFPs(fPaths);
    }

    @Test
    public void testGraphWithLoops() {
        System.out.println("Test graph with loops:");
        edges = new int[][] {{0, 0, 3}, {0, 2, 7}, {1, 2, 5},
                {2, 3, 1}, {3, 0, -10}};
        graph = new SignalFlowGraph(4);
        addEgdesToGraph();
        List<IPath> fPaths = ForwardPathsFinder.getForwardPaths(graph);
        printFPs(fPaths);
    }

    private void addEgdesToGraph() {
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1], edges[i][2]);
        }
    }

    private void printFPs(List<IPath> paths) {
        System.out.println(paths.size());
        for (IPath path: paths) {
            System.out.println(path.getStringPath() + "\t" + path.getGain());
        }
    }
}
