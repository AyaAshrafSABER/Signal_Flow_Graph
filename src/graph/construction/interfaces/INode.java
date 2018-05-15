package graph.construction.interfaces;

import graph.construction.interfaces.IEdge;

import java.util.LinkedList;

public interface INode {

	public String getName();

	public LinkedList<IEdge> getConnections();

	public void addConnection(INode destination, double wgt);

	public void updateName(int newNumber);

	public void draw(java.awt.Graphics canvas,int maxNumber);
	public float getxPoint();
		
	public int getPostion();
	public float getRaduis();
	
	public float getyPoint();
	public void setxPoint(float xPoint);
	
	public void setyPoint(float yPoint);
	
	public void setRaduis(float raduis);
}
