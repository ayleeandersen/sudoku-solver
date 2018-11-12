import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {
    @Test
    public void testConstructor() {
        Strategy strategy = new Strategy("test1");
        assertEquals(0, strategy.getUses());
        assertEquals("test1", strategy.getName());
        assertEquals("00:00:00.000", strategy.getTime());
    }

    @Test
    public void testGetName() {
        Strategy strategy = new Strategy("test1");
        assertEquals("test1", strategy.getName());
        strategy = new Strategy("test2");
        assertEquals("test2", strategy.getName());
    }

    @Test
    public void testGetAndSetUses() {
        Strategy strategy = new Strategy("test1");
        assertEquals(0, strategy.getUses());
        strategy.increaseUses(10);
        assertEquals(10, strategy.getUses());
        strategy.increaseUses(-10);
        assertEquals(0, strategy.getUses());
    }

    @Test
    public void testGetAndAddTime() {
        Strategy strategy = new Strategy("test1");
        assertEquals("00:00:00.000", strategy.getTime());
        strategy.addTime(2012232430);
        assertEquals("06:57:12.430", strategy.getTime());
        strategy.addTime(2012232430);
        assertEquals("20:51:37.564", strategy.getTime());
    }
}
