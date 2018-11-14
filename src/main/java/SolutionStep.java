import java.util.ArrayList;

public abstract class SolutionStep {
    protected long timeMilliseconds = 0;
    protected int uses = 0;
    protected ArrayList<ArrayList<Character>> grid;

    // Template method
    public final void runStep(SudokuGrid grid) {
        startTimer();
        solveStep(grid);
        endTimer();
        updateStrategyStats();
    }

    private void startTimer() {
        timeMilliseconds = System.currentTimeMillis();
    }

    abstract void solveStep(SudokuGrid grid);

    private void endTimer() {
        timeMilliseconds = System.currentTimeMillis() - timeMilliseconds;
    }

    abstract void updateStrategyStats(); //create new instance in child classes

    abstract String getClassName();

    abstract Strategy getStrategy();
}
