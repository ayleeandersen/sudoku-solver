import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GridValidator {
    public static boolean isValidSize(int size) {
        return size == 4 || size == 9 || size == 16 || size == 25 || size == 36;
    }

    public static boolean hasValidSymbols(int size, ArrayList<Character> symbols) {
        if (symbols.size() != size) {
            return false;
        }
        // check for duplicates
        Set<Character> symb = new HashSet<>(symbols);
        return symb.size() == size;
    }

    public static boolean isValidSymbol(Character symbol, ArrayList<Character> symbols) {
        if (!symbols.contains(symbol) && !symbol.equals('-')) {
            return false;
        }
        return true;
    }

    public static boolean isSquare(int size, ArrayList<ArrayList<Character>> grid) {
        if (grid.size() != size) {
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
