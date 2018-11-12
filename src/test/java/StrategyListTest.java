import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyListTest {
    @Test
    public void testAddStrategy() {
        StrategyList strategies = new StrategyList();
        ArrayList<Strategy> strategyList = strategies.getStrategies();

        Strategy strategy = new Strategy("TEST");
        strategies.addNewStrategy(strategy);

        assertEquals(strategyList.size()+1, strategies.getStrategies().size());

        strategyList.add(strategy);
        assertEquals(strategyList, strategies.getStrategies());

        // make sure it doesn't add duplicates
        strategies.addNewStrategy(strategy);
        assertEquals(strategyList, strategies.getStrategies());

        strategies.removeStrategy("TEST");
    }

    @Test
    public void testGetStrategies() {
        ArrayList<Strategy> strategyList = StrategyList.getStrategies();

        Strategy strategy = new Strategy("TEST");
        Strategy strategy2 = new Strategy("TEST2");
        Strategy strategy3 = new Strategy("TEST3");

        StrategyList.addNewStrategy(strategy);
        StrategyList.addNewStrategy(strategy2);
        StrategyList.addNewStrategy(strategy3);

        strategyList.add(strategy);
        strategyList.add(strategy2);
        strategyList.add(strategy3);

        assertEquals(strategyList, StrategyList.getStrategies());

        StrategyList.removeStrategy("TEST");
        StrategyList.removeStrategy("TEST2");
        StrategyList.removeStrategy("TEST3");

        assertEquals(2, StrategyList.getStrategies().size());
    }

    @Test
    public void testRemoveStrategies() {
        int size = StrategyList.getStrategies().size();
        ArrayList<Strategy> strategyList = StrategyList.getStrategies();

        Strategy strategy = new Strategy("TEST");
        Strategy strategy2 = new Strategy("TEST2");
        Strategy strategy3 = new Strategy("TEST3");

        StrategyList.addNewStrategy(strategy);
        StrategyList.addNewStrategy(strategy2);
        StrategyList.addNewStrategy(strategy3);

        assertEquals(size+3, StrategyList.getStrategies().size());

        StrategyList.removeStrategy("TEST2");

        strategyList.add(strategy);
        strategyList.add(strategy3);

        assertEquals(size+2, StrategyList.getStrategies().size());
        assertEquals(strategyList, StrategyList.getStrategies());

        StrategyList.removeStrategy("TEST");
        StrategyList.removeStrategy("TEST3");

        assertEquals(size, StrategyList.getStrategies().size());
    }
}
