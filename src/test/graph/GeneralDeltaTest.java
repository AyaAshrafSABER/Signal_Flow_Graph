package test.graph;

import graph.analysis.GeneralDelta;
import graph.analysis.LoopsFinder;
import graph.construction.SignalFlowGraph;
import graph.construction.interfaces.IPath;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GeneralDeltaTest {

    private SignalFlowGraph graph;
    private int[][] edges;
    private LoopsFinder loopsFinder;

    @Test
    public void test1() {
        edges = new int[][] {{0, 1, 5}, {0, 4, 10}, {1, 2, 10},
                {2, 3, 2}, {2, 1, -1}, {3, 2, -2},
                {3, 0, -1}, {4, 4, -1}, {4, 3, 2}};
        graph = new SignalFlowGraph(5);
        addEgdesToGraph();
        graph.checkSourceAndSinkNodes();
        loopsFinder = new LoopsFinder(graph);
        List<IPath> loops = loopsFinder.findAllSimpleCycles();
        printLoops(loops);
        double result = GeneralDelta.evaluate(loops);
        Assert.assertEquals(450.0, result, 1);
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
}
