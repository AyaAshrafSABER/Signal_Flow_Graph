package graph.analysis;

import graph.construction.interfaces.IPath;

import java.util.ArrayList;
import java.util.List;

public class GeneralDelta {

    private static List<IPath> allLoops;

    private GeneralDelta(){}; //singleton (only one delta for the whole graph)

    public static double evaluate(List<IPath> loops) {
        double term1 = 0;
        for (IPath loop: loops) {
            term1 += loop.getGain();
        }
        int sign = 1;
        double restTerms = 0;
        allLoops = loops;
        List<List<IPath>> terms = new ArrayList<>();
        terms.add(loops);
        int degree = 1; //two non-touched loops.
        List<IPath> term = NonTouchingLoopsFinder.getNonTouchingLoops(loops, degree);
        while (!term.isEmpty()) {
            terms.add(term);
            degree++;
            Double g = 0.0;
            List<Double> ga = NonTouchingLoopsFinder.getGains();
            for (Double gain: ga) {
                g += gain;
            }
            restTerms += sign * Double.valueOf(g.toString());
            sign *= -1;
            term = NonTouchingLoopsFinder.getNonTouchingLoops(term, degree);
        }
        double result = 1 - term1 + restTerms;
        return result;
    }


}
