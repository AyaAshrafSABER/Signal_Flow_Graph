package Controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import graph.analysis.Mason;
import graph.construction.SignalFlowGraph;
import graph.construction.interfaces.IEdge;
import graph.construction.interfaces.INode;

public class DrawingSignalFlowGraph implements IDrawingSignalFlowGraph {
	private int numberOfNodes;
	private float distanceRation;
	private SignalFlowGraph myGraph;
	private ArrayList<IEdge> edges;

	public DrawingSignalFlowGraph(int numOfNodes) {
		this.numberOfNodes = numOfNodes;
		edges = new ArrayList<IEdge>();
		myGraph = new SignalFlowGraph(numOfNodes);
	}


	public int getNumberOFNodes() {
		return numberOfNodes;
	}

	@Override
	public void refresh(Graphics canvas) {
		for (int i = 0; i < edges.size(); i++) {
			edges.get(i).draw(canvas);
		}
	}

	@Override
	public void addEdge(int source, int destination, int gain) {
		if (!IsOldEdge(source, destination, gain)) {
			myGraph.addEdge(source, destination, gain);
			LinkedList<IEdge> souceEdges = myGraph.getMatrix().get(source).getConnections();
			for (int i = 0; i < souceEdges.size(); i++) {
				if (souceEdges.get(i).getDestination().getPostion() == destination) {
					edges.add(souceEdges.get(i));
				}
			}
		}

	}

	private boolean IsOldEdge(int source, int destination, int gain) {
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getSource().getPostion() == source
					&& edges.get(i).getDestination().getPostion() == destination) {
				gain += edges.get(i).getWeight();
				edges.get(i).updateWeight(gain);
				System.out.println(gain);
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<IEdge> getEdges() {
		return this.edges;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNodes(Graphics canvas, float xStart, float yStart, float width, float height) {
		distanceRation = width / numberOfNodes;
		float raduis = 20;
		float xPoint = xStart + raduis;
		float yPoint = yStart + height / 2 - 50;
		for (int i = 0; i < numberOfNodes; i++) {
			INode node = myGraph.getMatrix().get(i);
			node.setRaduis(raduis);
			node.setxPoint(xPoint);
			node.setyPoint(yPoint);
			node.draw(canvas, numberOfNodes);
			xPoint += distanceRation;

		}
	}
	public Mason displayReslut () {
		Mason mason = new Mason(myGraph);
		return mason;
		
	}

}
