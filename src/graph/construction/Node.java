package graph.construction;

import graph.construction.interfaces.IEdge;
import graph.construction.interfaces.INode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

public class Node implements INode {

	private String name;
	private LinkedList<IEdge> connections;
	private Integer position;
	private float xPoint;
	private float yPoint;
	private float raduis;
	public Node (int nodeNumber) {
		this.position = nodeNumber;
		this.name = "X" + nodeNumber;
		this.connections = new LinkedList<>();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public LinkedList<IEdge> getConnections() {
		return this.connections;
	}

	@Override
	public void addConnection(INode destination, double wgt) {
		this.connections.add(new Edge(this, destination, wgt));
	}

	@Override
	public void updateName(int newNumber) {
		this.name = "X" + newNumber;
	}
	
	@Override
	public int getPostion() {
		return this.position;
	}
	@Override
	public void draw(Graphics canvas, int maxNumber) {
		Graphics2D g = (Graphics2D) canvas;
		g.setStroke(new BasicStroke(2));

		//draw R(s) node
		if (position == 0) {
			canvas.setColor(Color.BLACK);
			g.drawString("R(S)", (getxPoint() + getRaduis()/2 ) -8, getyPoint() - 5);
			canvas.setColor(Color.ORANGE);
		} 
		//draw C(s) node 
		else if (position == maxNumber-1) {
			canvas.setColor(Color.BLACK);
			g.drawString("C(S)", (getxPoint() + getRaduis()/2 ) -8, getyPoint() - 5);
			g.setColor(Color.ORANGE);
			canvas.setColor(Color.ORANGE);

		}
		else {
			canvas.setColor(Color.BLUE);
		}
		Ellipse2D.Double shape = new Ellipse2D.Double(getxPoint(), getyPoint(),getRaduis(), getRaduis());
		g.setBackground(Color.ORANGE);
		g.draw(shape);
		g.fill(shape);
		//g.setColor(Color.getHSBColor(204, 255, 255));
		canvas.setColor(Color.WHITE);
		g.drawString((this.position).toString(), getxPoint() + getRaduis()/2, getyPoint() + getRaduis()/2);
	}
	public float getxPoint() {
		return xPoint;
	}
	public float getyPoint() {
		return yPoint;
	}
	public float getRaduis() {
		return raduis;
	}
	public void setxPoint(float xPoint) {
		this.xPoint = xPoint;
	}
	
	public void setyPoint(float yPoint) {
		this.yPoint = yPoint;
	}
	
	public void setRaduis(float raduis) {
		this.raduis = raduis;
	}
	
}
