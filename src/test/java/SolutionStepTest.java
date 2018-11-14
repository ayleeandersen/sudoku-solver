import com.sun.tools.javac.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.verify;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

public class SolutionStepTest {
    // Rest of SolutionStep tested more thoroughly in subclasses
    @Mock
    private SolutionStep solutionStep;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRunStep() throws Exception {
        solutionStep = Mockito.mock(SolutionStep.class);

        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList('-','1','4','3')));
        grid.add(new ArrayList<>(Arrays.asList('-','-','1','2')));
        grid.add(new ArrayList<>(Arrays.asList('-','2','-','4')));
        grid.add(new ArrayList<>(Arrays.asList('-','3','2','-')));
        StringBuilder gridInput = new StringBuilder("4\n" +
                "1 2 3 4\n" +
                "- 1 4 3\n" +
                "- - 1 2\n" +
                "- 2 - 4\n" +
                "- 3 2 -");
        ArrayList<Character> symbols = new ArrayList<>(List.of('1','2','3','4'));
        SudokuGrid sudokuGrid = new SudokuGrid(grid, gridInput, symbols);

        solutionStep.runStep(sudokuGrid);
        verify(solutionStep).solveStep(sudokuGrid);
        verify(solutionStep).updateStrategyStats();
    }
}
