package graph.construction.interfaces;

import java.util.ArrayList;

public interface ISignalFlowGraph {

    public boolean addEdge(int source, int destination, double gain);

    public void addNode(int position);

    public ArrayList<INode> getMatrix();
}
