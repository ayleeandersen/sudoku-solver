import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GridValidatorTest {
    @Test
    public void testIsValidSize() {
        GridValidator gridValidator = new GridValidator();
        assertTrue(gridValidator.isValidSize(4));
        assertTrue(gridValidator.isValidSize(9));
        assertTrue(gridValidator.isValidSize(25));
        assertTrue(gridValidator.isValidSize(36));

        assertFalse(GridValidator.isValidSize(-10));
        assertFalse(GridValidator.isValidSize(0));
        assertFalse(GridValidator.isValidSize(1));
        assertFalse(GridValidator.isValidSize(100));
    }

    @Test
    public void testHasValidSymbols() {
        int size = 4;
        ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('A','B','C','D'));
        assertTrue(GridValidator.hasValidSymbols(size, symbols));

        symbols = new ArrayList<>(Arrays.asList('1','2','3'));
        assertFalse(GridValidator.hasValidSymbols(size, symbols));

        symbols = new ArrayList<>(Arrays.asList('1','2','3','3'));
        assertFalse(GridValidator.hasValidSymbols(size, symbols));

        assertFalse(GridValidator.hasValidSymbols(size, null));
    }

    @Test
    public void testIsValidSymbol() {
        ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('A','B','C','D'));
        assertTrue(GridValidator.isValidSymbol('A', symbols));
        assertTrue(GridValidator.isValidSymbol('D', symbols));
        assertFalse(GridValidator.isValidSymbol('E', symbols));
        assertFalse(GridValidator.isValidSymbol(' ', symbols));
        assertFalse(GridValidator.isValidSymbol(null, symbols));
        assertFalse(GridValidator.isValidSymbol('A', null));
        assertFalse(GridValidator.isValidSymbol(null, null));
    }

    @Test
    public void testIsSquare() {
        int size = 4;
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('-','4','-','3')));
        assertTrue(GridValidator.isSquare(size, grid));

        grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        assertFalse(GridValidator.isSquare(size, grid));

        grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1','4')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','2','1')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-','2')));
        grid.add(new ArrayList<>(Arrays.asList('-','4','-','3','-')));
        assertFalse(GridValidator.isSquare(size, grid));

        grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('4','2','-','1')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','-','2','1')));
        grid.add(new ArrayList<>(Arrays.asList('3','-','2','-')));
        grid.add(new ArrayList<>(Arrays.asList('-','4','-','3')));
        assertFalse(GridValidator.isSquare(size, grid));
    }
}
