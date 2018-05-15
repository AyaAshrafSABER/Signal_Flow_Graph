package test.graph;

import graph.analysis.LoopsFinder;
import graph.analysis.NonTouchingLoopsFinder;
import graph.construction.SignalFlowGraph;
import graph.construction.interfaces.IPath;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NonTouchingLoopsTest {

    private SignalFlowGraph graph;
    private int[][] edges;
    private LoopsFinder loopsFinder;
    private List<String> answer;
    private List<Double> correctGains;

    @Test
    public void test1() {
        edges = new int[][] {{0, 1, 5}, {0, 4, 10}, {1, 2, 10},
                {2, 3, 2}, {2, 1, -1}, {3, 2, -2},
                {3, 0, -1}, {4, 4, -1}, {4, 3, 2}};
        answer = new ArrayList<>();
        correctGains = new ArrayList<>();
        Double[] gainss = {100.0, 200.0, 10.0 ,4.0};
        String[] sol = {"X1 X2 X3 X4 X1", "X5 X5", "X1 X5 X4 X1", "X2 X3 X2", "X2 X3 X2", "X5 X5", "X3 X4 X3", "X5 X5"};
        for (String so: sol) {
            answer.add(so);
        }
        for (Double db: gainss) {
            correctGains.add(db);
        }
        graph = new SignalFlowGraph(5);
        addEgdesToGraph();
        graph.checkSourceAndSinkNodes();
        loopsFinder = new LoopsFinder(graph);
        List<IPath> loops = loopsFinder.findAllSimpleCycles();
        printLoops(loops);
        List<IPath> ntloops = NonTouchingLoopsFinder.getNonTouchingLoops(loops, 1);
        List<Double> gains = NonTouchingLoopsFinder.getGains();
        for (int i = 0; i < answer.size(); i++) {
            Assert.assertEquals(answer.get(i), ntloops.get(i).getStringPath());
        }
        for (int i = 0; i < correctGains.size(); i++) {
            Assert.assertEquals(correctGains.get(i), gains.get(i));
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
}
