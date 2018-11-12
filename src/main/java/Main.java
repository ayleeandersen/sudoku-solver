import java.io.*;

public class Main {
    public static void parseCommands(String[] args) throws Exception {
        BufferedInputStream inputFile;
        BufferedOutputStream outputFile = null;
        try {
            if (args != null && args.length == 2) {
                inputFile = setInput(args[0]);
                outputFile = setOutput(args[1]);
            } else if (args != null && args.length == 1) {
                if (args[0] != null && args[0].equals("-h")) {
                    System.out.println("Your arguments must have the form '<input filename>' or '<input filename> <output filename>'");
                    return;
                }
                inputFile = setInput(args[0]);
                outputFile = new BufferedOutputStream(System.out);
            } else {
                throw new Exception("Invalid command line arguments. Run with -h for help.");
            }
            FileManager fileManager = new FileManager(inputFile, outputFile);
            fileManager.loadPuzzle();
        } finally {
            if (outputFile != null) {
                outputFile.flush();
            }
        }
    }

    private static BufferedInputStream setInput(String filename) throws Exception {
        BufferedInputStream inputFile;
        try {
            inputFile = new BufferedInputStream(new FileInputStream(new File(filename)));
        } catch (Exception e) {
            throw new Exception("Invalid Input File");
        }
        return inputFile;
    }

    private static BufferedOutputStream setOutput(String filename) throws Exception {
        BufferedOutputStream outputFile;
        try {
            outputFile = new BufferedOutputStream(new FileOutputStream(new File(filename)));
        } catch (Exception e) {
            throw new Exception("Invalid Output File");
        }
        return outputFile;
    }

    public static void main(String[] args) throws Exception {
        parseCommands(args);
    }
}
