import java.util.ArrayList;
import java.util.Collections;

public class OneSpotLeftSolutionStep extends SolutionStep {
    private Strategy strategy = new Strategy("OneSpotLeft");
    private boolean madeMove = true;

    public void solveStep(SudokuGrid sudokuGrid) {
        this.madeMove = true;
        this.grid = sudokuGrid.getGrid();
        // loop through till no moves are made
        while (madeMove) {
            madeMove = false;
            // Look through grid rows for one left
            for (ArrayList<Character> row : grid) {
                if (Collections.frequency(row, '-') == 1) {
                    // figure out what it should be and replace it
                    for (Character symbol : sudokuGrid.getSymbols()) {
                        if (!row.contains(symbol)) {
                            row.set(row.indexOf('-'), symbol);
                            uses++;
                            madeMove = true;
                            break;
                        }
                    }
                }
            }
            // Look through grid columns for one left
            for (int col = 0; col < grid.size(); col++) {
                ArrayList<Character> column = new ArrayList<>();
                for (int row = 0; row < grid.size(); row++) {
                    column.add(grid.get(row).get(col));
                }
                if (Collections.frequency(column, '-') == 1) {
                    for (Character symbol : sudokuGrid.getSymbols()) {
                        if (!column.contains(symbol)) {
                            grid.get(column.indexOf('-')).set(col, symbol);
                            uses++;
                            madeMove = true;
                            break;
                        }
                    }
                }
            }
            // Look through blocks for one left
            double sqrt = Math.sqrt(grid.size());
            for (int blockRow = 0; blockRow < sqrt; blockRow++) {
                for (int blockCol = 0; blockCol < sqrt; blockCol++) {
                    int i = -100;
                    int j = -100;
                    boolean oneDash = false;
                    ArrayList<Character> block = new ArrayList<>();
                    for (double row = sqrt * blockRow; row < sqrt * blockRow + sqrt; row++) {
                        for (double col = sqrt * blockCol; col < sqrt * blockCol + sqrt; col++) {
                            block.add(grid.get((int) row).get((int) col));
                            if (grid.get((int) row).get((int) col) == '-') {
                                if (row != -100 && col != 100) {
                                    oneDash = false;
                                } else {
                                    i = (int) row;
                                    j = (int) col;
                                    oneDash = true;
                                }
                            }
                        }
                    }
                    if (oneDash) {
                        for (Character symbol : sudokuGrid.getSymbols()) {
                            if (!block.contains(symbol)) {
                                grid.get(i).set(j, symbol);
                                uses++;
                                madeMove = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateStrategyStats() {
        strategy.addTime(timeMilliseconds);
        strategy.increaseUses(uses);
    }

    public String getClassName() {
        return "OneSpotLeftSolutionStep";
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
