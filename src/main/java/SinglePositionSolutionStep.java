public class SinglePositionSolutionStep extends SolutionStep {
    //TODO: test
    private Strategy strategy = new Strategy("SinglePosition");

    public void solveStep(SudokuGrid sudokuGrid) {
        this.grid = sudokuGrid.getGrid();
        //while there are still singleposition moves to make
        //if it worked, increment the uses
        // TODO: solve step
    }

    public void updateStrategyStats() {
        strategy.addTime(timeMilliseconds);
        strategy.increaseUses(uses);
    }

    public String getClassName() {
        return "SinglePositionSolutionStep";
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
