package Controller;

import java.util.ArrayList;

import graph.analysis.Mason;
import graph.construction.interfaces.IEdge;

public interface IDrawingSignalFlowGraph {
	public int getNumberOFNodes();
	
    public void refresh(java.awt.Graphics canvas);

    public void addEdge(int source,int destination,int gain);
    public void addNodes(java.awt.Graphics canvas, float xStart, float yStart, float width, float height);

    public ArrayList<IEdge> getEdges();
  
    public void undo();

    public void redo();
    public Mason displayReslut ();
}
