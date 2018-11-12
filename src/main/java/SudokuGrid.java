import java.util.ArrayList;

public class SudokuGrid {
    private ArrayList<ArrayList<Character>> grid;
    private StringBuilder gridInput;
    private ArrayList<Character> symbols;

    public SudokuGrid(ArrayList<ArrayList<Character>> grid, StringBuilder gridInput, ArrayList<Character> symbols) {
        this.grid = grid;
        this.gridInput = gridInput;
        this.symbols = symbols;
    }

    public ArrayList<ArrayList<Character>> getGrid() {
        // Shallow copy
        ArrayList<ArrayList<Character>> gridCopy = new ArrayList<>(grid);
        return gridCopy;
    }

    public String getGridInput() {
        return gridInput.toString();
    }

    public ArrayList<Character> getSymbols() {
        // return shallow copy of symbols
        return new ArrayList<>(symbols);
    }
}