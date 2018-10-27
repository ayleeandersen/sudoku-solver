import java.io.*;

public class Main {
    private static void parseCommands(String[] args) throws Exception {
        InputStream inputFile;
        OutputStream outputFile;
        if (args.length == 2) {
            inputFile = setInput(args[0]);
            outputFile = setOutput(args[1]);
        } else if (args.length == 1) {
            if (args[0].equals("-h")) {
                System.out.println("Your arguments must have the form '<input filename>' or '<input filename> <output filename>'");
                System.exit(0);
            }
            inputFile = setInput(args[0]);
            outputFile = System.out;
        } else {
            throw new Exception("Invalid command line arguments. Run with -h for help.");
        }
        FileManager fileManager = new FileManager(inputFile, outputFile);
        fileManager.loadPuzzle();
    }

    private static InputStream setInput(String filename) throws Exception {
        InputStream inputFile;
        try {
            inputFile = new FileInputStream(new File(filename));
        } catch (Exception e) {
            throw new Exception("Invalid Input File");
        }
        return inputFile;
    }

    private static OutputStream setOutput(String filename) throws Exception {
        OutputStream outputFile;
        try {
            outputFile = new FileOutputStream(new File(filename));
        } catch (Exception e) {
            throw new Exception("Invalid Output File");
        }
        return outputFile;
    }

    public static void main(String[] args) throws Exception {
        parseCommands(args);
    }
}
