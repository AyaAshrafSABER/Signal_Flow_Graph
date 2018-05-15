package graph.analysis;

import graph.construction.interfaces.IPath;

import java.util.*;

public class NonTouchingLoopsFinder {

    private static List<IPath> mLoops;
    private static List<IPath> nonTouchingLoops;
    private static List<Double> gains;
    private static LinkedList<IPath> visited;
    private static List<List<IPath>> allPrevLoops;

    private NonTouchingLoopsFinder(){};
    public static List<IPath> getNonTouchingLoops(List<IPath> loops, int degree) {
        mLoops = loops;
        allPrevLoops = new ArrayList<>();
        gains = new ArrayList<>();
        nonTouchingLoops = new ArrayList<>();
        visited = new LinkedList<>();
        for (int i = 0; i < loops.size(); i = i + degree) {
            for (int j = i + degree; j < loops.size(); j++) {
                checkNonTouching(j, i, degree);
            }
            visited.clear();

        }

        return nonTouchingLoops;
    }

    private static boolean checkNonTouching(int current, int toBeCompared, int degree) {
        List<IPath> prevLoops = getPrevLoops(toBeCompared, degree);
        String parts[] = mLoops.get(current).getStringPath().split("\\s+");
        boolean nonTouching = true;
        for (IPath loop: prevLoops) {
            for (String node : parts) {
                if (loop.getStringPath().contains(node)) {
                    nonTouching = false;
                    break;
                }
            }
            if (!nonTouching) {
                break;
            }
        }
        if (nonTouching
                && !visited.contains(mLoops.get(current))) {
            prevLoops.add(mLoops.get(current));
            if (!isAlreadyTaken(prevLoops, degree)) {
                double gain = 1;
                for (IPath loop : prevLoops) {
                    nonTouchingLoops.add(loop);
                    gain *= loop.getGain();
                }
               // nonTouchingLoops.add(mLoops.get(current));
               // gain *= mLoops.get(current).getGain();
                gains.add(gain);
                visited.add(mLoops.get(current));
                allPrevLoops.add(prevLoops);
            } else {
                nonTouching = false;
            }
        } else {
            nonTouching = false;
        }
        return nonTouching;
    }
        
    private static List<IPath> getPrevLoops(int toBeCompared, int degree) {
        List<IPath> prev = new ArrayList<>();
        for (int i = 0; i < degree; i++) {
            prev.add(mLoops.get(toBeCompared + i));
            //visited.add(mLoops.get(toBeCompared + i));
        }
        return prev;
    }

    public static List<Double> getGains() {
        return gains;
    }

    public static List<IPath> getNonTouchingLoops() {
        return nonTouchingLoops;
    }

    private static boolean isAlreadyTaken(List<IPath> currentPrev, int degree) {
        int i;
        for (List<IPath> list: allPrevLoops) {
            i = 0;
            for (IPath loop: currentPrev) {
                if (list.contains(loop)) {
                    i++;
                }
            }
            if (i == degree + 1) {
                return true;
            }
        }
        return false;
    }
 }
