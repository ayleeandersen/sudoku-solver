import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {
    @Test
    public void testConstructor() {
        Time time = new Time(0);
        assertEquals("00:00:00.000", time.getTime());

        // go backwards 100 milliseconds
        time = new Time(-100);
        assertEquals("23:59:59.900", time.getTime());
    }

    @Test
    public void testGetTime() {
        Time time = new Time(10000000);
        assertEquals("02:46:40.000", time.getTime());

        time = new Time(10000007);
        assertEquals("02:46:40.007", time.getTime());

        time = new Time(-8);
        assertEquals("23:59:59.992", time.getTime());
    }
}
