# sudoku-solver
The purpose of this program is to solve Sudoku puzzles. Puzzles are loaded as files. 
If the file is not formatted correctly, the solver will print the original puzzle out to a new file along with a reason why it couldn't solve the puzzle.
Currently, the solver uses two algorithms to solve the puzzles:
+ One Spot Left
+ Single Possibility

The solver alternates between the algorithms until it can no longer make a change on the board. At that point, the puzzle is "unsolveable". 
Eventually, the goal is to have a third algorithm that uses a guessing technique.

If the solver finds a solution, the original puzzle, solved puzzle, and algorithm stats are printed out to a file.

See the requirements document (HW4b.pdf) for more details as to what the program is supposed to do.
