import java.io.OutputStream;
import java.util.ArrayList;

public class SudokuSolver {
    // TODO: TEST and update UML
    private long totalTime = 0; // in milliseconds
    private SudokuGrid grid;
    private OutputStream output;

    public SudokuSolver(SudokuGrid sudokuGrid, OutputStream output) {
        this.grid = sudokuGrid;
        this.output = output;
    }

    public void solve() throws Exception {
        totalTime = System.currentTimeMillis();
        //TODO: remove and replace with a check for if none of them work we know it doesn't work
        long start = System.currentTimeMillis();
        while (!checkSolved()) {
            if (System.currentTimeMillis() - start > 30) {
                break;
            }
            for (SolutionStep step : StrategyStepList.strategies) {
                step.runStep(grid);
            }
//            run "only one place" SolutionStep
//            run "guess" SolutionStep recursively and restart loop
        }
        totalTime = (System.currentTimeMillis() - totalTime);
        print();
    }

    public boolean checkSolved() {
        for (ArrayList<Character> row : grid.getGrid()) {
            for (int col = 0; col < grid.getGrid().size(); col++) {
                if (row.get(col) == '-') {
                    return false;
                }
            }
        }
        if (!GridValidator.doesNotContainDuplicates(grid.getGrid())) {
            return false;
        }
        return true;
    }

    public void print() throws Exception {
        output.write(grid.getGridInput().getBytes());
        output.write("\nSolution:\n".getBytes());
        for (ArrayList<Character> row : grid.getGrid()) {
            for (int col = 0; col < grid.getGrid().size(); col++) {
                output.write((row.get(col) + " ").getBytes());
            }
            output.write("\n".getBytes());
        }
        output.write(("\n\nTotal time: " + new Time(totalTime).getTime() + "\n\n").getBytes());
        String format = "%-25s %-10s %-15s";
        output.write(String.format(format, "Strategy", "Uses", "Time").getBytes());
        output.write("\n".getBytes());
        for (SolutionStep solutionStep : StrategyStepList.strategies) {
            output.write(String.format(format, solutionStep.getStrategy().getName(), solutionStep.getStrategy().getUses(), solutionStep.getStrategy().getTime()).getBytes());
            output.write("\n".getBytes());
        }
    }
}
