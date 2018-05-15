package graph.construction;

import graph.construction.interfaces.IEdge;
import graph.construction.interfaces.INode;
import graph.construction.interfaces.ISignalFlowGraph;

import java.util.ArrayList;

public class SignalFlowGraph implements ISignalFlowGraph {

	private ArrayList<INode> matrix;
	private int sourceNodeNumber;
	private int sinkNodeNumber;

	public SignalFlowGraph(int numberOfNodes) {
		this.sourceNodeNumber = 0;
		this.sinkNodeNumber = numberOfNodes - 1;
		this.matrix = new ArrayList<>(numberOfNodes);
		for (int i = 0; i < numberOfNodes; i++) {
			matrix.add(new Node(i));
		}
	}

	@Override
	public boolean addEdge(int source, int destination, double gain) {
		try {
			checkValidation(source, destination, gain);
			if (gain == 0) {
				return false;
			}
			matrix.get(source).addConnection(matrix.get(destination), gain);
			return true; // to be drawn.
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public void addNode(int position) {
		if (position > matrix.size() || position < 0) {
			throw new IllegalArgumentException("Invalid node position.");
		}
		if (position == matrix.size()) {
			matrix.add(new Node(position));
		} else {
			matrix.add(position, new Node(position));
			for (int i = position + 1; i < matrix.size(); i++) {
				matrix.get(i).updateName(i);
			}
		}
		sinkNodeNumber++;
	}

	@Override
	public ArrayList<INode> getMatrix() {
		return this.matrix;
	}

	private void checkValidation(int source, int destination, double gain) throws IllegalArgumentException {
		if (source >= matrix.size() || source < 0) {
			throw new IllegalArgumentException("Invalid source node number!");
		}
		if (destination >= matrix.size() || destination < 0) {
			throw new IllegalArgumentException("Invalid destination node number!");
		}
		
	}

	// To be called when user says he has finished.
	public void checkSourceAndSinkNodes() {
		// Check sink node.
		if (!this.matrix.get(sinkNodeNumber).getConnections().isEmpty()) {
			addNode(matrix.size());
			this.addEdge(sinkNodeNumber - 1, sinkNodeNumber, 1);
		}

		// Check source node.
		boolean foundOnce = false;
		for (INode node : matrix) {
			for (IEdge edge : node.getConnections()) {
				if (edge.getDestination().getName().equals("X0")) {
					foundOnce = true;
					addNode(0);
					addEdge(sourceNodeNumber, sourceNodeNumber + 1, 1);
					break;
				}
			}
			if (foundOnce) {
				break;
			}
		}
	}
}
