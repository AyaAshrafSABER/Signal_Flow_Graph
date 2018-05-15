package graph.construction;

import java.util.LinkedList;

import graph.construction.interfaces.ILoop;
import graph.construction.interfaces.INode;
import graph.construction.interfaces.IPath;

public class Loop implements IPath {
	private LinkedList<INode> nodes;
	private String sloop;
	private double loopGain;

	public Loop(LinkedList<INode> loop, double gain) {
		this.nodes = loop;
		sloop = "";
		for (INode node : loop) {
			sloop += node.getName() + " ";
		}
		sloop = sloop.trim();
		this.loopGain = gain;
	}

	@Override
	public String getStringPath() {
		return this.sloop;
	}

	@Override
	public LinkedList<INode> getNodes() {
		return this.nodes;
	}

	@Override
	public double getGain() {
		return this.loopGain;
	}




}
