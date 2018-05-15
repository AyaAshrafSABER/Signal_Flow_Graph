package graph.construction.interfaces;

import java.util.LinkedList;

public interface ILoop {

	public String getStringLoop();

	public LinkedList<INode> getLoopNodes();

	public double getLoopGain();

}
