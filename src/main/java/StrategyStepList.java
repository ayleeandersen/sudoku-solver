import com.sun.tools.javac.util.List;

import java.util.ArrayList;

public class StrategyStepList {
    // TODO: finish writing strategies
    // everyone should have the same reference to the strategies list. This makes sure the SolutionStep's Strategy is shared across the class.
    public static ArrayList<SolutionStep> strategies = new ArrayList<>(List.of(new SinglePositionSolutionStep(), new OneSpotLeftSolutionStep()));

    public static void addNewSolutionStep(SolutionStep solutionStep) {
        for (SolutionStep step : strategies) {
            if (step.getClassName().equals(solutionStep.getClassName())) {
                return;
            }
        }
        strategies.add(solutionStep);
    }

    public static Strategy getStrategy(String strategyName) {
        for (SolutionStep strategy : strategies) {
            if (strategy.getStrategy().getName().equals(strategyName)) {
                return strategy.getStrategy();
            }
        }
        return null;
    }

    public static void removeSolutionStep(String className) {
        for (int i = 0; i < strategies.size(); i++) {
            if (strategies.get(i).getClassName().equals(className)) {
                strategies.remove(i);
            }
        }
    }
}