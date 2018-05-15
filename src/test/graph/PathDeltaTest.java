package test.graph;

import graph.analysis.ForwardPathsFinder;
import graph.analysis.LoopsFinder;
import graph.analysis.PathDelta;
import graph.construction.SignalFlowGraph;
import graph.construction.interfaces.IPath;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PathDeltaTest {

    private SignalFlowGraph graph;
    private int[][] edges;
    private LoopsFinder loopsFinder;

    @Test
    public void test1() {
        double[] deltas = new double[] {15.0};
        edges = new int[][] {{0, 1, 5}, {0, 4, 10}, {1, 2, 10},
                {2, 3, 2}, {2, 1, -1}, {3, 2, -2},
                {3, 0, -1}, {4, 4, -1}, {4, 3, 2}};
        graph = new SignalFlowGraph(5);
        addEgdesToGraph();
        graph.checkSourceAndSinkNodes();
        loopsFinder = new LoopsFinder(graph);
        List<IPath> forwardPaths = ForwardPathsFinder.getForwardPaths(graph);
        List<IPath> loops = loopsFinder.findAllSimpleCycles();
        printLoops(loops);
        printFPs(forwardPaths);
        int i = 0;
        for (IPath fp: forwardPaths) {
            Assert.assertEquals(deltas[i], PathDelta.evaluate(fp, loops), 1);
            i++;
        }
    }

    private void addEgdesToGraph() {
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1], edges[i][2]);
        }
    }

    private void printLoops(List<IPath> loops) {
        int i = 1;
        for (IPath loo: loops) {
            System.out.println("Loop #" + i + " : " + loo.getStringPath() + " " + loo.getGain());
            i++;
        }
        System.out.println();
    }

    private void printFPs(List<IPath> paths) {
        System.out.println(paths.size());
        for (IPath path: paths) {
            System.out.println(path.getStringPath() + "\t" + path.getGain());
        }
    }
}
