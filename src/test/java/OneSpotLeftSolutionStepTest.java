import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class OneSpotLeftSolutionStepTest {
    @Test
    public void testSolveStep() throws Exception {
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
        FileOutputStream outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        SudokuGrid sudokuGrid = new SudokuGrid(grid, gridInput, symbols);
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuGrid, outputStream);

        OneSpotLeftSolutionStep oneSpotLeftSolutionStep = new OneSpotLeftSolutionStep();
        oneSpotLeftSolutionStep.solveStep(sudokuGrid);

        sudokuSolver.print();
        String result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertTrue(result.contains("Solution:\n" +
                "2 1 4 3 \n" +
                "3 4 1 2 \n" +
                "1 2 3 4 \n" +
                "4 3 2 1 "));

        grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','-')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','-')));
        grid.add(new ArrayList<>(Arrays.asList('-','2','-','4')));
        grid.add(new ArrayList<>(Arrays.asList('-','3','2','-')));
        gridInput = new StringBuilder("4\n" +
                "1 2 3 4\n" +
                "- - - -\n" +
                "- - - -\n" +
                "- 2 - 4\n" +
                "- 3 2 -");
        symbols = new ArrayList<>(List.of('1','2','3','4'));
        sudokuGrid = new SudokuGrid(grid, gridInput, symbols);
        oneSpotLeftSolutionStep.solveStep(sudokuGrid);
        assertEquals(0, oneSpotLeftSolutionStep.getStrategy().getUses());

        grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('2','1','4','3')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('1','-','-','4')));
        grid.add(new ArrayList<>(Arrays.asList('4','3','2','1')));
        gridInput = new StringBuilder("4\n" +
                "1 2 3 4\n" +
                "2 1 4 3\n" +
                "3 - - 2\n" +
                "1 - - 4\n" +
                "4 3 2 1");
        symbols = new ArrayList<>(List.of('1','2','3','4'));
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        sudokuGrid = new SudokuGrid(grid, gridInput, symbols);
        sudokuSolver = new SudokuSolver(sudokuGrid, outputStream);

        oneSpotLeftSolutionStep = new OneSpotLeftSolutionStep();
        oneSpotLeftSolutionStep.solveStep(sudokuGrid);

        sudokuSolver.print();
        result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertTrue(result.contains("Solution:\n" +
                "2 1 4 3 \n" +
                "3 4 1 2 \n" +
                "1 2 3 4 \n" +
                "4 3 2 1 "));
    }

    @Test
    public void testUpdateStrategyStats() {
        // Note: will be tested more thoroughly by the stats that will be checked in the SudokuSolver tests.
        OneSpotLeftSolutionStep oneSpotLeftSolutionStep = new OneSpotLeftSolutionStep();
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
        oneSpotLeftSolutionStep.runStep(new SudokuGrid(grid, gridInput, symbols));
        assertEquals(7, oneSpotLeftSolutionStep.getStrategy().getUses());
    }

    @Test
    public void testGetClassName() {
        OneSpotLeftSolutionStep oneSpotLeftSolutionStep = new OneSpotLeftSolutionStep();
        assertEquals("OneSpotLeftSolutionStep", oneSpotLeftSolutionStep.getClassName());
    }

    @Test
    public void testGetStrategy() {
        OneSpotLeftSolutionStep oneSpotLeftSolutionStep = new OneSpotLeftSolutionStep();
        Strategy test = new Strategy("OneSpotLeft");
        assertEquals(test.getName(), oneSpotLeftSolutionStep.getStrategy().getName());
        assertEquals(test.getTime(), oneSpotLeftSolutionStep.getStrategy().getTime());
        assertEquals(test.getUses(), oneSpotLeftSolutionStep.getStrategy().getUses());
    }
}
