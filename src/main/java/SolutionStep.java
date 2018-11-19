import java.util.ArrayList;

public abstract class SolutionStep {
    protected long timeMilliseconds = 0;
    protected int uses = 0;
    protected ArrayList<ArrayList<Character>> grid;

    // Template method
    public final void runStep(SudokuGrid grid) {
        resetVariables();
        startTimer();
        solveStep(grid);
        endTimer();
        updateStrategyStats();
    }

    private void resetVariables() {
        timeMilliseconds = 0;
        uses = 0;
    }

    private void startTimer() {
        timeMilliseconds = System.currentTimeMillis();
    }

    abstract void solveStep(SudokuGrid grid);

    private void endTimer() {
        timeMilliseconds = System.currentTimeMillis() - timeMilliseconds;
    }

    abstract void updateStrategyStats();

    abstract String getClassName();

    abstract Strategy getStrategy();
}
