package graph.analysis;

import graph.construction.ForwardPath;
import graph.construction.SignalFlowGraph;
import graph.construction.interfaces.IEdge;
import graph.construction.interfaces.INode;
import graph.construction.interfaces.IPath;

import java.util.LinkedList;
import java.util.List;

public class ForwardPathsFinder {

    private static List<IPath> forwardPaths;
    private static String sinkNode;

    private ForwardPathsFinder() {}

    public static List<IPath> getForwardPaths(SignalFlowGraph graph) {
        forwardPaths = new LinkedList<>();
        int sinkNodeNumber = graph.getMatrix().size() - 1;
        sinkNode = "X" + sinkNodeNumber;
        LinkedList<INode> visited = new LinkedList<>();
        visited.add(graph.getMatrix().get(0));
        DFS(visited, 1);
        return forwardPaths;
    }

    private static void DFS(LinkedList<INode> visitedNodes, double gain) {
        LinkedList<IEdge> edges = visitedNodes.getLast().getConnections();

        for (IEdge edge : edges) {
            if (visitedNodes.contains(edge.getDestination())) {
                continue;
            }
            if (edge.getDestination().getName().equals(sinkNode)) {
                visitedNodes.add(edge.getDestination());
                forwardPaths.add(new ForwardPath((LinkedList<INode>)visitedNodes.clone(),
                        gain * edge.getWeight()));
                visitedNodes.removeLast();
            } else {
               visitedNodes.add(edge.getDestination());
               DFS(visitedNodes, gain * edge.getWeight());
               visitedNodes.removeLast();
            }
        }
    }
}
