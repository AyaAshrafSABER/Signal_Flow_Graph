package test.graph;

import java.util.List;

import graph.analysis.LoopsFinder;
import graph.construction.SignalFlowGraph;
import graph.construction.interfaces.ILoop;
import graph.construction.interfaces.IPath;

public class FindLoopTest {
	public static void main(String[] args) {
		SignalFlowGraph graph = new SignalFlowGraph(9);
		graph.addEdge(0, 1, 10);
		graph.addEdge(1, 4, 10);
		graph.addEdge(1, 7, 10);
		graph.addEdge(1, 6, 10);
		graph.addEdge(4, 2, 10);
		graph.addEdge(4, 3, 10);
		graph.addEdge(2, 4, 10);
		graph.addEdge(2, 7, 10);
		graph.addEdge(2, 6, 10);
		graph.addEdge(7, 8, 10);
		graph.addEdge(7, 5, 10);
		graph.addEdge(5, 2, 10);
		graph.addEdge(5, 3, 10);
		graph.addEdge(3, 7, 10);
		graph.addEdge(3, 6, 10);
		graph.addEdge(3, 4, 10);
		graph.addEdge(6, 5, 10);
		graph.addEdge(6, 8, 10);

		LoopsFinder tarjan = new LoopsFinder(graph);
		List<IPath> result = tarjan.findAllSimpleCycles();
		for (int i = 0; i< result.size(); i++) {
			System.out.println(result.get(i).getStringPath() + "  gain: " +result.get(i).getGain() );
		}
	}
}
