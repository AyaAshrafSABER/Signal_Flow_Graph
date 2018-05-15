package graph.construction;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;

import graph.construction.interfaces.IEdge;
import graph.construction.interfaces.INode;

public class Edge implements IEdge {

	private INode source;
	private INode destination;
	private double weight;

	public Edge(INode src, INode des, double wgt) {
		this.source = src;
		this.destination = des;
		this.weight = wgt;
	}

	@Override
	public INode getSource() {
		return this.source;
	}

	@Override
	public INode getDestination() {
		return this.destination;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void updateWeight(double newWeight) {
		this.weight = newWeight;
	}

	@Override
	public void draw(Graphics canvas) {
		int x_disp = (int) Math.abs((destination.getxPoint() - source.getxPoint()));
		int nodeRadius = (int) source.getRaduis();
		int center = (int) source.getyPoint() + nodeRadius / 2;
		Path2D.Double path;
		Graphics2D g = (Graphics2D) canvas;
		g.setStroke(new BasicStroke(1.5f));
		if (source.getPostion() - destination.getPostion() == -1) { 
			g.setColor(Color.magenta);
			g.drawLine((int) source.getxPoint() + nodeRadius, center,
					(int) destination.getxPoint() - nodeRadius / 2 + 3, center);
			// drawing arrow
			path = new Path2D.Double();
			path.moveTo(source.getxPoint() + x_disp / 2, center - 6);
			path.lineTo(source.getxPoint() + x_disp / 2, center + 6);
			path.lineTo(source.getxPoint() + x_disp / 2 + 10, center);
			g.fill(path);
			// drawing gain text
			g.setColor(Color.black);
			g.setFont(new Font("Courier New", Font.PLAIN, 16));
			g.drawString(this.getWeight() + "", source.getxPoint() + x_disp / 2, source.getyPoint());

		} else if (Math.abs(source.getPostion() - destination.getPostion()) == 0) { // self
																					// loop
			g.setColor(Color.blue);
			// create new QuadCurve2D.Float
			Ellipse2D.Double shape = new Ellipse2D.Double(source.getxPoint() - nodeRadius, source.getyPoint(), 30, 30);
			g.setBackground(Color.ORANGE);
			g.draw(shape);
			// drawing gain text
			g.setColor(Color.black);
			g.drawString(this.weight + "", source.getxPoint() - 15, source.getyPoint() + nodeRadius);

		} else if (source.getPostion() < destination.getPostion()) {
			// drawing arc
			g.setColor(Color.black);
			QuadCurve2D q = new QuadCurve2D.Float();
			// draw QuadCurve2D.Float with set coordinates
			float ctrly = source.getyPoint() - 25 * (destination.getPostion() - source.getPostion());
			float ctrlx = source.getxPoint() + x_disp / 2;
			q.setCurve(source.getxPoint() + nodeRadius / 2, source.getyPoint(), ctrlx, ctrly, destination.getxPoint(),
					destination.getyPoint());
			g.draw(q);
			// drawing arrow
			path = new Path2D.Double();
			// drawing gain text
			g.setColor(Color.black);
			g.setFont(new Font("Courier New", Font.PLAIN, 16));
			g.drawString(this.getWeight() + "", source.getxPoint() + x_disp / 2 - 12,
					center - x_disp / (source.getPostion() + destination.getPostion())
							+ 10 * (source.getPostion() - destination.getPostion()) + 15);

		} else { 
			g.setColor(Color.GREEN);
			QuadCurve2D q = new QuadCurve2D.Float();
			// draw QuadCurve2D.Float with set coordinates
			float ctrly = destination.getyPoint() - 50 * (destination.getPostion() - source.getPostion());
			float ctrlx = destination.getxPoint() + x_disp / 2;
			q.setCurve(source.getxPoint(), source.getyPoint() + nodeRadius, ctrlx, ctrly,
					destination.getxPoint() + nodeRadius, destination.getyPoint() + nodeRadius);
			g.draw(q);
			// drawing gain text
			g.setColor(Color.black);
			g.setFont(new Font("Courier New", Font.PLAIN, 16));
			g.drawString(this.getWeight() + "", destination.getxPoint() + x_disp / 2 - 12,
					center + x_disp / (source.getPostion() + destination.getPostion())
							+ 10 * (source.getPostion() - destination.getPostion()) + 15);

		}
	}
}
