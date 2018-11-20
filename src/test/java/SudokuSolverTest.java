import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTest {
    @Test
    public void testConstructor() throws Exception {
        for (SolutionStep step : StrategyStepList.strategies) {
            step.getStrategy().reset();
        }
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('-','1','4','3')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','1','2')));
        grid.add(new ArrayList<>(Arrays.asList('-','2','-','4')));
        grid.add(new ArrayList<>(Arrays.asList('-','3','2','-')));
        StringBuilder gridInput = new StringBuilder("4\n" +
                "1 2 3 4\n" +
                "- 1 4 3\n" +
                "- - 1 2\n" +
                "- 2 - 4\n" +
                "- 3 2 -");
        ArrayList<Character> symbols = new ArrayList<>(List.of('1','2','3','4'));
        FileOutputStream outputStream = new FileOutputStream(new File("output_files/solverout.txt"));
        SudokuGrid sudokuGrid = new SudokuGrid(grid, gridInput, symbols);
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuGrid, outputStream);
        sudokuSolver.print();
        String result = new String(Files.readAllBytes(Paths.get("output_files/solverout.txt")));
        assertTrue(result.contains("4\n" +
                "1 2 3 4\n" +
                "- 1 4 3\n" +
                "- - 1 2\n" +
                "- 2 - 4\n" +
                "- 3 2 -\n" +
                "Solution:\n" +
                "- 1 4 3 \n" +
                "- - 1 2 \n" +
                "- 2 - 4 \n" +
                "- 3 2 - \n" +
                "\n" +
                "\n" +
                "Total time: "));
        assertTrue(result.contains("\n" +
                "Strategy                  Uses       Time           \n" +
                "OneSpotLeft               0          "));
        assertTrue(result.contains("  \n" +
                "SinglePosition            0          "));

        try {
            SudokuSolver solver = new SudokuSolver(null, null);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Sudoku solver cannot take null arguments", e.getMessage());
        }
    }

    @Test
    public void testSolve() throws Exception {
        for (SolutionStep step : StrategyStepList.strategies) {
            step.getStrategy().reset();
        }
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','3','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('2','4','-','3')));
        StringBuilder input = new StringBuilder(("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- 3 - 2\n" +
                "3 - 2 -\n" +
                "2 4 - 3"));
        ArrayList<Character> symbols = new ArrayList<>(List.of('1','2','3','4'));
        OutputStream outputStream = new FileOutputStream(new File("output_files/solverout.txt"));
        SudokuSolver sudokuSolver = new SudokuSolver(new SudokuGrid(grid, input, symbols), outputStream);
        sudokuSolver.solve();
        assertTrue(sudokuSolver.checkSolved());
        String result = new String(Files.readAllBytes(Paths.get("output_files/solverout.txt")));
        assertTrue(result.contains("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- 3 - 2\n" +
                "3 - 2 -\n" +
                "2 4 - 3\n" +
                "Solution:\n" +
                "4 2 3 1 \n" +
                "1 3 4 2 \n" +
                "3 1 2 4 \n" +
                "2 4 1 3 \n" +
                "\n" +
                "\n" +
                "Total time: 00:"));
        assertTrue(result.contains("\n" +
                "Strategy                  Uses       Time           \n" +
                "OneSpotLeft               6          "));
        assertTrue(result.contains("  \n" +
                "SinglePosition            0          "));


        for (SolutionStep step : StrategyStepList.strategies) {
            step.getStrategy().reset();
        }
        grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','-')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','-')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('2','4','-','3')));
        input = new StringBuilder(("4\n" +
                "1 2 3 4\n" +
                "- - - -\n" +
                "- - - -\n" +
                "3 - 2 -\n" +
                "2 4 - 3"));
        symbols = new ArrayList<>(List.of('1','2','3','4'));
        outputStream = new FileOutputStream(new File("output_files/solverout.txt"));
        sudokuSolver = new SudokuSolver(new SudokuGrid(grid, input, symbols), outputStream);
        try {
            sudokuSolver.solve();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        result = new String(Files.readAllBytes(Paths.get("output_files/solverout.txt")));
        assertEquals("4\n" +
                "1 2 3 4\n" +
                "- - - -\n" +
                "- - - -\n" +
                "3 - 2 -\n" +
                "2 4 - 3\n" +
                "Invalid Puzzle -- could not solve puzzle with current algorithm set\n", result);
        assertFalse(sudokuSolver.checkSolved());
    }

    @Test
    public void testCheckSolved() throws Exception {
        // Not solved
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','3','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('2','4','-','3')));
        StringBuilder input = new StringBuilder(("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- 3 - 2\n" +
                "3 - 2 -\n" +
                "2 4 - 3"));
        ArrayList<Character> symbols = new ArrayList<>(List.of('1','2','3','4'));
        OutputStream outputStream = new FileOutputStream(new File("output_files/solverout.txt"));
        SudokuSolver sudokuSolver = new SudokuSolver(new SudokuGrid(grid, input, symbols), outputStream);
        assertFalse(sudokuSolver.checkSolved());

        for (SolutionStep step : StrategyStepList.strategies) {
            step.getStrategy().reset();
        }
        // Solved
        grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','3','1')));
        grid.add(new ArrayList<>(Arrays.asList('1','3','4','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','1','2','4')));
        grid.add(new ArrayList<>(Arrays.asList('2','4','1','3')));
        input = new StringBuilder(("4\n" +
                "1 2 3 4\n" +
                "4 2 3 1\n" +
                "1 3 4 2\n" +
                "3 1 2 4\n" +
                "2 4 1 3"));
        symbols = new ArrayList<>(List.of('1','2','3','4'));
        outputStream = new FileOutputStream(new File("output_files/solverout.txt"));
        sudokuSolver = new SudokuSolver(new SudokuGrid(grid, input, symbols), outputStream);
        assertTrue(sudokuSolver.checkSolved());

        // Contains duplicates in a row/col/block
        grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','3','1')));
        grid.add(new ArrayList<>(Arrays.asList('1','3','4','3')));
        grid.add(new ArrayList<>(Arrays.asList('3','1','2','4')));
        grid.add(new ArrayList<>(Arrays.asList('2','4','1','3')));
        input = new StringBuilder(("4\n" +
                "1 2 3 4\n" +
                "4 2 3 1\n" +
                "1 3 4 3\n" +
                "3 1 2 4\n" +
                "2 4 1 3"));
        symbols = new ArrayList<>(List.of('1','2','3','4'));
        outputStream = new FileOutputStream(new File("output_files/solverout.txt"));
        sudokuSolver = new SudokuSolver(new SudokuGrid(grid, input, symbols), outputStream);
        assertFalse(sudokuSolver.checkSolved());
    }

    @Test
    public void testPrint() throws Exception {
        for (SolutionStep step : StrategyStepList.strategies) {
            step.getStrategy().reset();
        }
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','3','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('2','4','-','3')));
        StringBuilder input = new StringBuilder(("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- 3 - 2\n" +
                "3 - 2 -\n" +
                "2 4 - 3"));
        ArrayList<Character> symbols = new ArrayList<>(List.of('1','2','3','4'));
        OutputStream outputStream = new FileOutputStream(new File("output_files/solverout.txt"));
        SudokuSolver sudokuSolver = new SudokuSolver(new SudokuGrid(grid, input, symbols), outputStream);
        sudokuSolver.print();
        String result = new String(Files.readAllBytes(Paths.get("output_files/solverout.txt")));
        assertEquals("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- 3 - 2\n" +
                "3 - 2 -\n" +
                "2 4 - 3\n" +
                "Solution:\n" +
                "4 2 - 1 \n" +
                "- 3 - 2 \n" +
                "3 - 2 - \n" +
                "2 4 - 3 \n" +
                "\n" +
                "\n" +
                "Total time: 00:00:00.000\n" +
                "\n" +
                "Strategy                  Uses       Time           \n" +
                "OneSpotLeft               0          00:00:00.000   \n" +
                "SinglePosition            0          00:00:00.000   \n", result);

        outputStream = new FileOutputStream(new File("output_files/solverout.txt"));
        sudokuSolver = new SudokuSolver(new SudokuGrid(grid, input, symbols), outputStream);
        sudokuSolver.solve();
        result = new String(Files.readAllBytes(Paths.get("output_files/solverout.txt")));
        assertTrue(result.contains("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- 3 - 2\n" +
                "3 - 2 -\n" +
                "2 4 - 3\n" +
                "Solution:\n" +
                "4 2 3 1 \n" +
                "1 3 4 2 \n" +
                "3 1 2 4 \n" +
                "2 4 1 3 \n" +
                "\n" +
                "\n" +
                "Total time: "));
        assertTrue(result.contains("\n" +
                "Strategy                  Uses       Time           \n" +
                "OneSpotLeft"));
        assertTrue(result.contains("  \n" +
                "SinglePosition"));
    }
}
