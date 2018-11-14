import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    @Test
    public void testConstructor() throws Exception {
        InputStream inputStream = new FileInputStream(new File("input_files/valid4x4.txt"));
        OutputStream outputStream = new FileOutputStream(new File("output_files/validout.txt"));
        new FileManager(inputStream, outputStream);

        new FileManager(System.in, System.out);

        outputStream = new FileOutputStream(new File("output_files/validout.txt"));
        try {
            new FileManager(null, outputStream);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("FileManager cannot take a null Input/Output Stream", e.getMessage());
        }

        try {
            new FileManager(null, null);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("FileManager cannot take a null Input/Output Stream", e.getMessage());
        }

        inputStream = new FileInputStream(new File("input_files/valid4x4.txt"));
        try {
            new FileManager(inputStream, null);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("FileManager cannot take a null Input/Output Stream", e.getMessage());
        }
    }

    @Test
    public void testLoadPuzzle() throws Exception {
        // Valid puzzle
        InputStream inputStream = new ByteArrayInputStream(("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3").getBytes());
        OutputStream outputStream = new FileOutputStream(new File("output_files/testout.txt"));
        FileManager fileManager = new FileManager(inputStream, outputStream);
        fileManager.loadPuzzle();

        // No size
        inputStream = new ByteArrayInputStream(("\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3").getBytes());
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        fileManager = new FileManager(inputStream, outputStream);
        try {
            fileManager.loadPuzzle();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        String result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3\n" +
                "Invalid Puzzle -- Size is invalid\n", result);

        // Nothing
        inputStream = new ByteArrayInputStream(("").getBytes());
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        fileManager = new FileManager(inputStream, outputStream);
        try {
            fileManager.loadPuzzle();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("Invalid Puzzle -- Size of puzzle not listed correctly\n", result);

        // Bad size
        inputStream = new ByteArrayInputStream(("5\n" +
                "3 3 3 3\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3").getBytes());
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        fileManager = new FileManager(inputStream, outputStream);
        try {
            fileManager.loadPuzzle();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("5\n" +
                "3 3 3 3\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3\n" +
                "Invalid Puzzle -- Size is invalid\n", result);

        // Symbols not listed correctly
        inputStream = new ByteArrayInputStream(("4\n").getBytes());
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        fileManager = new FileManager(inputStream, outputStream);
        try {
            fileManager.loadPuzzle();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("4\nInvalid Puzzle -- Symbols in puzzle not listed correctly\n", result);

        // Symbols invalid
        inputStream = new ByteArrayInputStream(("4\n" +
                "1 2 3 3\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3").getBytes());
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        fileManager = new FileManager(inputStream, outputStream);
        try {
            fileManager.loadPuzzle();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("4\n" +
                "1 2 3 3\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3\n" +
                "Invalid Puzzle -- Invalid list of symbols\n", result);

        // Contains invalid symbol
        inputStream = new ByteArrayInputStream(("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- 5 - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3").getBytes());
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        fileManager = new FileManager(inputStream, outputStream);
        try {
            fileManager.loadPuzzle();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- 5 - 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3\n" +
                "Invalid Puzzle -- Puzzle contains invalid symbol\n", result);

        // Not square
        inputStream = new ByteArrayInputStream(("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2 3\n" +
                "3 - 2 -\n" +
                "- 4 - 3").getBytes());
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        fileManager = new FileManager(inputStream, outputStream);
        try {
            fileManager.loadPuzzle();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2 3\n" +
                "3 - 2 -\n" +
                "- 4 - 3\n" +
                "Invalid Puzzle -- Puzzle is not square\n", result);

        // Not square 2
        inputStream = new ByteArrayInputStream(("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n").getBytes());
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        fileManager = new FileManager(inputStream, outputStream);
        try {
            fileManager.loadPuzzle();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - - 2\n" +
                "3 - 2 -\n" +
                "Invalid Puzzle -- Puzzle is not square\n", result);

        // Duplicates in a block
        inputStream = new ByteArrayInputStream(("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - 1 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3").getBytes());
        outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        fileManager = new FileManager(inputStream, outputStream);
        try {
            fileManager.loadPuzzle();
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("4\n" +
                "1 2 3 4\n" +
                "4 2 - 1\n" +
                "- - 1 2\n" +
                "3 - 2 -\n" +
                "- 4 - 3\n" +
                "Invalid Puzzle -- Puzzle contains duplicate symbols in a row, column, or block\n", result);
    }

    @Test
    public void testWriteError() throws Exception {
        String message = "Message this";
        OutputStream outputStream = new FileOutputStream(new File("output_files/errorout.txt"));
        FileManager fileManager = new FileManager(System.in, outputStream);
        try {
            fileManager.writeError(message);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals("Invalid Puzzle -- see output for details", e.getMessage());
        }
        String result = new String(Files.readAllBytes(Paths.get("output_files/errorout.txt")));
        assertEquals("Invalid Puzzle -- Message this\n", result);
    }
}
