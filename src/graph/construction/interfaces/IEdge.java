package graph.construction.interfaces;

public interface IEdge {

    public INode getSource();

    public INode getDestination();

    public double getWeight();

    public void updateWeight(double newWeight);
    
    public void draw(java.awt.Graphics canvas);

}
