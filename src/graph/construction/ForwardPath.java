package graph.construction;

import graph.construction.interfaces.INode;
import graph.construction.interfaces.IPath;

import java.util.ArrayList;
import java.util.LinkedList;

public class ForwardPath implements IPath {

    private LinkedList<INode> nodes;
    private String sPath;
    private double pathGain;

    public ForwardPath(LinkedList<INode> path, double gain) {
        this.nodes = path;
        sPath = "";
        for (INode node: path) {
            sPath += node.getName() + " ";
        }
        sPath = sPath.trim();
        this.pathGain = gain;
    }

    @Override
    public String getStringPath() {
        return sPath;
    }

    @Override
    public LinkedList<INode> getNodes() {
        return this.nodes;
    }

    @Override
    public double getGain() {
        return this.pathGain;
    }
}
