import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuGridTest {
    @Test
    public void testConstructor() {
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('-','4','-','3')));
        StringBuilder gridInput = new StringBuilder("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3");
        ArrayList<Character> symbols = new ArrayList<>(List.of('1','2','3','4'));
        SudokuGrid sudokuGrid = new SudokuGrid(grid, gridInput, symbols);
        assertEquals(new Character('4'), (sudokuGrid.getGrid().get(0)).get(0));
        assertEquals(new Character('-'), (sudokuGrid.getGrid().get(3)).get(2));
        assertEquals(new Character('2'), (sudokuGrid.getGrid().get(2)).get(2));
        assertEquals(gridInput.toString(), sudokuGrid.getGridInput());
    }

    @Test
    public void testGetGrid() {
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('-','4','-','3')));
        StringBuilder gridInput = new StringBuilder("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3");
        ArrayList<Character> symbols = new ArrayList<>(List.of('1','2','3','4'));
        SudokuGrid sudokuGrid = new SudokuGrid(grid, gridInput, symbols);
        assertEquals(grid, sudokuGrid.getGrid());
    }

    @Test
    public void testGetGridInput() {
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('-','4','-','3')));
        StringBuilder gridInput = new StringBuilder("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3");
        ArrayList<Character> symbols = new ArrayList<>(List.of('1','2','3','4'));
        SudokuGrid sudokuGrid = new SudokuGrid(grid, gridInput, symbols);
        assertEquals("4\n1 2 3 4\n4 2 - 1\n- - - 2\n3 - 2 -\n- 4 - 3", sudokuGrid.getGridInput());
    }

    @Test
    public void testGetSymbols() {
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('-','4','-','3')));
        StringBuilder gridInput = new StringBuilder("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3");
        ArrayList<Character> symbols = new ArrayList<>(List.of('1','2','3','4'));
        SudokuGrid sudokuGrid = new SudokuGrid(grid, gridInput, symbols);
        assertEquals(symbols, sudokuGrid.getSymbols());
        assertEquals(symbols.get(0), sudokuGrid.getSymbols().get(0));
    }
}
