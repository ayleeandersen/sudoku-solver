import java.util.*;
import java.util.ArrayList;
import java.util.ArrayList;

public class GridValidator {
    public static boolean isValidSize(int size) {
        return size == 4 || size == 9 || size == 16 || size == 25 || size == 36;
    }

    public static boolean hasValidSymbols(int size, ArrayList<Character> symbols) {
        if (symbols == null || symbols.size() != size) {
            return false;
        }
        // check for duplicates
        Set<Character> symb = new HashSet<>(symbols);
        return symb.size() == size;
    }

    public static boolean isValidSymbol(Character symbol, ArrayList<Character> symbols) {
        if (symbol == null || symbols == null || (!symbols.contains(symbol) && !symbol.equals('-'))) {
            return false;
        }
        return true;
    }

    public static boolean isSquare(int size, ArrayList<ArrayList<Character>> grid) {
        if (grid == null || grid.size() != size) {
            return false;
        }
        for (ArrayList<Character> row : grid) {
            if (row.size() != size) {
                return false;
            }
        }
        return true;
    }

    public static boolean doesNotContainDuplicates(ArrayList<ArrayList<Character>> grid) {
        // check for duplicates in a row
        for (ArrayList<Character> row : grid) {
            int dashes = Collections.frequency(row, '-');
            Set<Character> symb = new HashSet<>(row);
            symb.remove('-');
            if (symb.size() != row.size()-dashes) {
                return false;
            }
        }
        // check for duplicates in a column
        for (int col = 0; col < grid.size(); col++) {
            ArrayList<Character> column = new ArrayList<>();
            for (int row = 0; row < grid.size(); row++) {
                column.add(grid.get(row).get(col));
            }
            int dashes = Collections.frequency(column, '-');
            Set<Character> symb = new HashSet<>(column);
            symb.remove('-');
            if (symb.size() != column.size()-dashes) {
                return false;
            }
        }
        // check for duplicates in a block
        double sqrt = Math.sqrt(grid.size());
        for (int blockRow = 0; blockRow < sqrt; blockRow++) {
            for (int blockCol = 0; blockCol < sqrt; blockCol++) {
                ArrayList<Character> block = new ArrayList<>();
                for (double row = sqrt*blockRow; row < sqrt*blockRow+sqrt; row++) {
                    for (double col = sqrt*blockCol; col < sqrt*blockCol+sqrt; col++) {
                        block.add(grid.get((int)row).get((int)col));
                    }
                }
                int dashes = Collections.frequency(block, '-');
                Set<Character> symb = new HashSet<>(block);
                symb.remove('-');
                if (symb.size() != block.size()-dashes) {
                    return false;
                }
            }
        }
        return true;
    }
}
