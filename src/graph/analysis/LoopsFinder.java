package graph.analysis;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import graph.construction.Loop;
import graph.construction.SignalFlowGraph;
import graph.construction.interfaces.IEdge;
import graph.construction.interfaces.INode;
import graph.construction.interfaces.IPath;

/**
 * @author AyaOsman finding loops in graph using Tarjon's algorithm.
 */
public class LoopsFinder {
	

	private static List<LinkedList<INode>> result;
	private static List<IPath> loops;
	private Set<INode> visited;
	private Deque<INode> pointStack;
	private Deque<INode> markedStack;
	private Set<INode> markedSet;
	private SignalFlowGraph graph;

	public LoopsFinder(SignalFlowGraph graph) {
		reset(graph);
	}

	private void reset(SignalFlowGraph graph2) {
		this.graph = graph2;
		this.visited = new HashSet<>();
		this.pointStack = new LinkedList<>();
		this.markedStack = new LinkedList<>();
		this.markedSet = new HashSet<>();
	}

	public List<IPath> findAllSimpleCycles() {
		result = new ArrayList<>();
		for (INode node : graph.getMatrix()) {
			findAllSimpleCycles(node, node);
			visited.add(node);
			while (!markedStack.isEmpty()) {
				markedSet.remove(markedStack.pollFirst());
			}
		}
		loops = new ArrayList<>();
		for (int i = 0; i< result.size() ; i++) {
			loops.add(new Loop (result.get(i), calculateGain(result.get(i))));
		}
		
		return loops;
	}

	private double calculateGain(List<INode> list) {
		double gain = 1; 
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.get(i).getConnections().size(); j++) {
				if (list.get(i).getConnections().get(j).getDestination() == list.get(i+1)) {
					gain *= list.get(i).getConnections().get(j).getWeight();
				}
			}
		}
		return gain;
	}

	private boolean findAllSimpleCycles(INode Start, INode current) {
		boolean hasCycle = false;
		pointStack.offerFirst(current);
		markedSet.add(current);
		markedStack.offerFirst(current);
		for (IEdge w : current.getConnections()) {
			if (visited.contains(w.getDestination())) {
				continue;
			} else if (w.getDestination().equals(Start)) {
				hasCycle = true;
				pointStack.offerFirst(w.getDestination());
				LinkedList<INode> cycle = new LinkedList<>();
				Iterator<INode> itr = pointStack.descendingIterator();
				while (itr.hasNext()) {
					cycle.add(itr.next());
				}
				pointStack.pollFirst();
				result.add(cycle);
			} else if (!markedSet.contains(w.getDestination())) {
				hasCycle = findAllSimpleCycles(Start, w.getDestination()) || hasCycle;
			}
		}
		if (hasCycle) {
			while (!markedStack.peekFirst().equals(current)) {
				markedSet.remove(markedStack.pollFirst());
			}
			 markedSet.remove(markedStack.pollFirst());
		}
		pointStack.pollFirst();
		return hasCycle;
	}

}
