import java.io.OutputStream;

public class SudokuSolver {
    private int totalTime = 0; // in milliseconds
    SudokuGrid grid;
    OutputStream output;

    public SudokuSolver(SudokuGrid grid, OutputStream output) {
        this.grid = grid;
        this.output = output;
    }

    public void solve() throws Exception {
        // logic for choosing which algorithm to solve
        boolean solved = false;
//        while (!solved) {
//            run "only one possibility" SolutionStep till none left
//            run "only one place" SolutionStep till none left
//            run "guess" SolutionStep recursively and restart loop
//            solved = true;
//        }
        print();
    }

    public void print() throws Exception {
        output.write(grid.getGridInput().getBytes());
        output.write("\nSolution:\n".getBytes());
        //TODO: write solution
        output.write(("\n\nTotal time: " + new Time(totalTime).getTime() + "\n\n").getBytes());

        String format = "%-25s %-10s %-15s";
        output.write(String.format(format, "Strategy", "Uses", "Time").getBytes());
        output.write("\n".getBytes());
        for (Strategy strategy : StrategyList.getStrategies()) {
            output.write(String.format(format, strategy.getName(), strategy.getUses(), strategy.getTime()).getBytes());
            output.write("\n".getBytes());
        }
    }
}
