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
}
