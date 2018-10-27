import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private InputStream inputFile;
    private OutputStream outputFile;

    public FileManager(InputStream input, OutputStream output) {
        inputFile = input;
        outputFile = output;
    }

    public void loadPuzzle() throws Exception {
        Scanner sc = new Scanner(inputFile);

        if (!sc.hasNextInt()) { writeError("Size of puzzle not listed correctly"); }
        int size = sc.nextInt();
        sc.nextLine();
        if (!GridValidator.isValidSize(size)) { writeError("Size is invalid"); }

        if (!sc.hasNextLine()) { writeError("Symbols in puzzle not listed correctly"); }
        String symbolLine = sc.nextLine().replace(" ","");
        ArrayList<Character> symbols = new ArrayList<Character>();
        for (Character symbol : symbolLine.toCharArray()) {
            symbols.add(symbol);
        }
        if (!GridValidator.hasValidSymbols(size, symbols)) { writeError("Invalid list of symbols"); }

        // The first element in grid is an array of the first row of characters
        ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>();
        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            row = row.replace(" ", "");
            ArrayList<Character> letters = new ArrayList<Character>();
            for (Character c : row.toCharArray()) {
                if (!GridValidator.isValidSymbol(c, symbols)) { writeError("Puzzle contains invalid symbol"); }
                letters.add(c);
            }
            grid.add(letters);
        }
        if (!GridValidator.isSquare(size, grid)) { writeError("Puzzle is not square"); }

        SudokuGrid sudokuGrid = new SudokuGrid();
        sudokuGrid.setGrid(grid);
    }

    public void writeError(String message) throws Exception {
        outputFile.write(("Invalid Puzzle -- " + message + "\n").getBytes());
        System.exit(0);
    }
}
