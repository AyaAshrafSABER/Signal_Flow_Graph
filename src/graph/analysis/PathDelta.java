package graph.analysis;

import graph.construction.interfaces.IPath;

import java.util.ArrayList;
import java.util.List;

public class PathDelta {

    private static List<IPath> nonTouchingLoops;

    private PathDelta() {};

    public static double evaluate(IPath forwardPath, List<IPath> allLoops) {
        nonTouchingLoops = new ArrayList<>();
        findNonTouchingLoops(forwardPath, allLoops);
        return GeneralDelta.evaluate(nonTouchingLoops);
    }

    private static void findNonTouchingLoops(IPath fp, List<IPath> loops) {
        String[] parts = fp.getStringPath().split("\\s+");
        for (IPath loop: loops) {
            boolean nonTouching = true;
            for (String part: parts) {
                if (loop.getStringPath().contains(part)) {
                    nonTouching = false;
                    break;
                }
            }
            if (nonTouching) {
                nonTouchingLoops.add(loop);
            }
        }
    }
}
