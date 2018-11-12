import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private InputStream inputFile;
    private OutputStream outputFile;
    private StringBuilder gridOut = new StringBuilder();

    public FileManager(InputStream input, OutputStream output) throws Exception {
        if (input == null || output == null) {
            throw new Exception("FileManager cannot take a null Input/Output Stream");
        }
        inputFile = input;
        outputFile = output;
    }

    public void loadPuzzle() throws Exception {
        Scanner init = new Scanner(inputFile);
        while (init.hasNextLine()) {
            gridOut.append(init.nextLine() + "\n");
        }
        init.close();
        Scanner sc = new Scanner(new ByteArrayInputStream(gridOut.toString().getBytes()));

        if (!sc.hasNextInt()) { writeError("Size of puzzle not listed correctly"); }
        int size = sc.nextInt();
        sc.nextLine();
        if (!GridValidator.isValidSize(size)) { writeError("Size is invalid"); }

        if (!sc.hasNextLine()) { writeError("Symbols in puzzle not listed correctly"); }
        String symbolLine = sc.nextLine();
        symbolLine = symbolLine.replace(" ","");
        ArrayList<Character> symbols = new ArrayList<>();
        for (Character symbol : symbolLine.toCharArray()) {
            symbols.add(symbol);
        }
        if (!GridValidator.hasValidSymbols(size, symbols)) { writeError("Invalid list of symbols"); }

        // The first element in grid is an array of the first row of characters
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            row = row.replace(" ", "");
            ArrayList<Character> letters = new ArrayList<>();
            for (Character c : row.toCharArray()) {
                if (!GridValidator.isValidSymbol(c, symbols)) { writeError("Puzzle contains invalid symbol"); }
                letters.add(c);
            }
            grid.add(letters);
        }
        if (!GridValidator.isSquare(size, grid)) { writeError("Puzzle is not square"); }

        SudokuSolver sudokuSolver = new SudokuSolver(new SudokuGrid(grid, gridOut, symbols), outputFile);
        sudokuSolver.solve();
    }

    public void writeError(String message) throws Exception {
        outputFile.write((gridOut.toString()).getBytes());
        outputFile.write(("Invalid Puzzle -- " + message + "\n").getBytes());
        throw new Exception("Invalid Puzzle -- see output for details");
    }
}
