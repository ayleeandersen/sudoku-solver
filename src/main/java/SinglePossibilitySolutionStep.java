import java.util.ArrayList;

public class SinglePossibilitySolutionStep extends SolutionStep {
    private Strategy strategy = new Strategy("SinglePossibility");
    private boolean madeMove = true;

    public void solveStep(SudokuGrid sudokuGrid) {
        this.grid = sudokuGrid.getGrid();
        while (madeMove) {
            madeMove = false;
            // Loop through grid rows for single possibility solutions
            int rowIndex = 0;
            for (ArrayList<Character> row : grid) {
                // Figure out which numbers the row needs
                ArrayList<Character> needs = new ArrayList<>();
                for (Character symbol : sudokuGrid.getSymbols()) {
                    if (!row.contains(symbol)) {
                        needs.add(symbol);
                    }
                }
                // Find the possibilities for each missing index in the row
                for (int i = 0; i < grid.size(); i++) {
                    if (row.get(i).equals('-')) {
                        findPossibilities(rowIndex, i, needs);
                    }
                }
                rowIndex++;
            }

            // Loop through grid columns for single possibility solutions
            for (int col = 0; col < grid.size(); col++) {
                ArrayList<Character> column = new ArrayList<>();
                for (int row = 0; row < grid.size(); row++) {
                    column.add(grid.get(row).get(col));
                }
                // Figure out which numbers the column needs
                ArrayList<Character> needs = new ArrayList<>();
                for (Character symbol : sudokuGrid.getSymbols()) {
                    if (!column.contains(symbol)) {
                        needs.add(symbol);
                    }
                }
                // Find the possibilities for each missing index in the column
                for (int i = 0; i < grid.size(); i++) {
                    if (column.get(i).equals('-')) {
                         findPossibilities(i, col, needs);
                    }
                }
            }

            // Loop through grid blocks for single possibility solutions
            double sqrt = Math.sqrt(grid.size());
            for (int blockRow = 0; blockRow < sqrt; blockRow++) {
                for (int blockCol = 0; blockCol < sqrt; blockCol++) {
                    ArrayList<Character> block = new ArrayList<>();
                    for (double row = sqrt * blockRow; row < sqrt * blockRow + sqrt; row++) {
                        for (double col = sqrt * blockCol; col < sqrt * blockCol + sqrt; col++) {
                            block.add(grid.get((int) row).get((int) col));
                        }
                    }
                    // Figure out which numbers the block needs
                    ArrayList<Character> needs = new ArrayList<>();
                    for (Character symbol : sudokuGrid.getSymbols()) {
                        if (!block.contains(symbol)) {
                            needs.add(symbol);
                        }
                    }
                    // Find the possibilities for each missing index in the block
                    for (int i = 0; i < grid.size(); i++) {
                        if (block.get(i).equals('-')) {
                            findPossibilities((int)(blockRow*sqrt+(int)(i/sqrt)), (int)(blockCol*sqrt+(int)(i%sqrt)), needs);
                        }
                    }
                }
            }
        }
    }

    private void findPossibilities(int row, int col, ArrayList<Character> needs) {
        ArrayList<Character> possibilities = new ArrayList<>();
        ArrayList<Character> rowList = new ArrayList<>(grid.get(row));
        ArrayList<Character> colList = new ArrayList<>();
        for (int r = 0; r < grid.size(); r++) {
            colList.add(grid.get(r).get(col));
        }
        ArrayList<Character> blockList = new ArrayList<>();
        int sqrt = (int)Math.sqrt(grid.size());
        int startRow = sqrt*(int)(row/sqrt);
        for (int rowStartIndex = startRow; rowStartIndex < startRow + sqrt; rowStartIndex++) {
            int startCol = sqrt*(int)(col/sqrt);
            for (int colStartIndex = startCol; colStartIndex < startCol + sqrt; colStartIndex++) {
                blockList.add(grid.get(rowStartIndex).get(colStartIndex));
            }
        }
        for (Character symbol : needs) {
            if (!rowList.contains(symbol) && !colList.contains(symbol) && !blockList.contains(symbol)) {
                possibilities.add(symbol);
            }
        }
        // If there is only one possibility, replace it
        if (possibilities.size() == 1) {
            grid.get(row).set(col, possibilities.get(0));
            needs.remove(possibilities.get(0));
            uses++;
            madeMove = true;
        }
    }

    public void updateStrategyStats() {
        strategy.addTime(timeMilliseconds);
        strategy.increaseUses(uses);
    }

    public String getClassName() {
        return "SinglePossibilitySolutionStep";
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
