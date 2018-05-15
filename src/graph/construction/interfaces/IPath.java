package graph.construction.interfaces;

import java.util.LinkedList;

public interface IPath {

    public String getStringPath();

    public LinkedList<INode> getNodes();

    public double getGain();

}
