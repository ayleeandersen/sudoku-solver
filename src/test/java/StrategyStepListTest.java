import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyStepListTest {
    @Test
    public void testAddStrategy() {
        StrategyStepList strategyStepList = new StrategyStepList();
        int size = strategyStepList.strategies.size();
        SolutionStep strategy = new SinglePossibilitySolutionStep();

        // make sure it doesn't add duplicates
        strategyStepList.addNewSolutionStep(strategy);
        assertEquals(size, strategyStepList.strategies.size());

        strategyStepList.removeSolutionStep("SinglePossibilitySolutionStep");
        assertEquals(size-1, strategyStepList.strategies.size());

        strategyStepList.addNewSolutionStep(strategy);
        assertEquals(size, strategyStepList.strategies.size());
        assertEquals(strategy, strategyStepList.strategies.get(strategyStepList.strategies.size()-1));
    }

    @Test
    public void testRemoveStrategies() {
        int size = StrategyStepList.strategies.size();
        ArrayList<SolutionStep> strategyList = new ArrayList<>(StrategyStepList.strategies);

        assertEquals(size, strategyList.size());

        StrategyStepList.removeSolutionStep("OneSpotLeftSolutionStep");

        strategyList.remove(0);

        assertEquals(size-1, StrategyStepList.strategies.size());
        assertEquals(strategyList, StrategyStepList.strategies);

        StrategyStepList.addNewSolutionStep(new OneSpotLeftSolutionStep());
    }

    @Test
    public void testGetStrategy() {
        assertEquals("SinglePossibility", StrategyStepList.getStrategy("SinglePossibility").getName());
        assertEquals("OneSpotLeft", StrategyStepList.getStrategy("OneSpotLeft").getName());

        assertEquals(null, StrategyStepList.getStrategy("TEST"));
    }
}
