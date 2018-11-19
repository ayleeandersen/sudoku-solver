import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void testMain() throws Exception {
        // For the sake of code coverage
        Main main = new Main();
        String[] args1 = {"input_files/onespotleft4x4.txt", "output_files/validout.txt"};
        for (SolutionStep step : StrategyStepList.strategies) {
            step.getStrategy().reset();
        }
        main.main(args1);
    }

    @Test
    public void testParseTwoCommands() throws Exception {
        for (SolutionStep step : StrategyStepList.strategies) {
            step.getStrategy().reset();
        }
        String[] args1 = {"input_files/onespotleft4x4.txt", "output_files/validout.txt"};
        Main.parseCommands(args1);

        String[] args2 = {"input_files/onespotleft4x4.txt", null};
        try {
            Main.parseCommands(args2);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Output File", e.getMessage());
        }

        String[] args3 = {null, "output_files/validout.txt"};
        try {
            Main.parseCommands(args3);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Input File", e.getMessage());
        }

        String[] args4 = {"input_files/invalid_name.txt", "output_files/validout.txt"};
        try {
            Main.parseCommands(args4);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Input File", e.getMessage());
        }

        String[] args5 = {null, null};
        try {
            Main.parseCommands(args5);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Input File", e.getMessage());
        }

        String[] args6 = {"input_files/invalid_name.txt", null};
        try {
            Main.parseCommands(args6);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Input File", e.getMessage());
        }
    }

    @Test
    public void testParseOneCommand() throws Exception {
        for (SolutionStep step : StrategyStepList.strategies) {
            step.getStrategy().reset();
        }
        String[] args1 = {"input_files/onespotleft4x4.txt"};
        System.out.println("TestParseOneCommand Output 1: ");
        Main.parseCommands(args1);

        String[] args2 = {"input_files/invalid_name.txt"};
        try {
            Main.parseCommands(args2);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Input File", e.getMessage());
        }

        String[] args3 = {null};
        try {
            Main.parseCommands(args3);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Input File", e.getMessage());
        }

        String[] args4= {""};
        try {
            Main.parseCommands(args4);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Input File", e.getMessage());
        }

        String[] args5 = {"-h"};
        System.out.println("\n\nTestParseOneCommand Output 2: ");
        Main.parseCommands(args5);
    }

    @Test
    public void testParseNoCommands() throws Exception {
        try {
            Main.parseCommands(null);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid command line arguments. Run with -h for help.", e.getMessage());
        }

        String[] args = {};
        try {
            Main.parseCommands(args);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid command line arguments. Run with -h for help.", e.getMessage());
        }
    }
}
