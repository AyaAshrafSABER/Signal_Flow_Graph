package graph.analysis;

import graph.construction.SignalFlowGraph;
import graph.construction.interfaces.IPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mason {

	private SignalFlowGraph mSFG;
	private LoopsFinder loopsFinder;
	private List<IPath> allLoops;
	private List<IPath> forwardPaths;
	private List<Double> smallDelta;
	private double generalDelta;
	private Map<String, String> loopsMap;
	private List<List<String>> nonTLStr;

	public Mason(SignalFlowGraph sfg) {
		this.mSFG = sfg;
		this.loopsFinder = new LoopsFinder(mSFG);
		getInformation();
	}

	private void getInformation() {
		allLoops = loopsFinder.findAllSimpleCycles();
		loopsMap = new HashMap<>();
		String loopname;
		int i = 1;
		for (IPath loop : allLoops) {
			loopname = "L" + i;
			loopsMap.put(loop.getStringPath(), loopname);
			i++;
		}
		forwardPaths = ForwardPathsFinder.getForwardPaths(mSFG);
		generalDelta = 0;
		generalDelta += GeneralDelta.evaluate(allLoops);
		smallDelta = new ArrayList<>();
		for (IPath fp : forwardPaths) {
			smallDelta.add(PathDelta.evaluate(fp, allLoops));
		}
		nonTLStr = new ArrayList<>();
        int deg = 1;
        List<IPath> nonTL = NonTouchingLoopsFinder.getNonTouchingLoops(allLoops, deg);
        while (!nonTL.isEmpty()) {
            List<String> nonStr = new ArrayList<>();
            for (IPath non : nonTL) {
                nonStr.add(loopsMap.get(non.getStringPath()));
            }
            nonTLStr.add(nonStr);
            deg++;
            nonTL = NonTouchingLoopsFinder.getNonTouchingLoops(nonTL, deg);
        }
	}

	public List<String> getAllLoops() {
		List<String> loops_str = new ArrayList<>();
		for (IPath loop : allLoops) {
			String loopDiscription = loopsMap.get(loop.getStringPath()) + " : " + loop.getStringPath() + " \tGain: "
					+ loop.getGain();
			loops_str.add(loopDiscription);
		}
		return loops_str;
	}

	public List<String> getFPs() {
		List<String> fp_str = new ArrayList<>();
		for (IPath fp : forwardPaths) {
			String fp_discription = fp.getStringPath() + " \tGain " + fp.getGain();
			fp_str.add(fp_discription);
		}
		return fp_str;
	}

	public String getGeneralDelta() {
		String ret = getGeneralDeltaExpression();
		ret += " = " + generalDelta;
		return ret;
	}

	public int getNumberOfFPs() {
		return forwardPaths.size();
	}

	public List<Double> getPathDelta() {
		return smallDelta;
	}

	public List<List<String>> getnonTouchingLoops() {
	    return nonTLStr;
	}

	public double getOATF() {
		double overall = 0;
		for (int i = 0; i < forwardPaths.size(); i++) {
			double term = forwardPaths.get(i).getGain() * Double.valueOf(smallDelta.get(i).toString());
			overall += term;
		}
		overall /= generalDelta;
		return overall;
	}

	private String getGeneralDeltaExpression() {
	    String exp = "1";
        int i;
        if (!allLoops.isEmpty()) {
        	exp += " - ( ";
			for (i = 0; i < allLoops.size() - 1; i++) {
				exp += loopsMap.get(allLoops.get(i).getStringPath());
				exp += " + ";
			}
			exp += loopsMap.get(allLoops.get(i).getStringPath()) + " )";
		}
        boolean sign = true; //positive

        int num = 2;
        for (List<String> nonT: nonTLStr) {
            if (sign) {
                exp += " + ( ";
            } else {
                exp += " - ( ";
            }
            int j;
            for (j = 0; j < nonT.size() - num; j = j + num) {
                for (int k = j; k < j + num; k++) {
                    exp += nonT.get(k);
                }
                exp += " + ";
            }
            for (int k = j; k < j + num; k++) {
                exp += nonT.get(k);
            }
            exp += " )";
            sign = !sign;
            num++;
        }
        return exp;
    }

}
