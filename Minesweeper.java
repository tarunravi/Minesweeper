import java.util.Arrays;
import java.util.Scanner;

public class Minesweeper {

    private static final int MINE = 9;

    public static void main(String[] args) {

        // Enter size grid N x N
        // place N bombs randomly in the grid - check for repeats
        // In each remaining space, count the number of bombs
        // print grid

        int gridSize = getGridSize();
        int[][] grid = makeGrid(gridSize);

        placeMines(gridSize, grid);

        countAllSurroundingMines(grid);
        printGrid(grid);
    }

    public Minesweeper() {}

    /**
     * Prompts to enter the size of the grid. Ensures the grid size > 4.
     *
     * @return the size of the grid
     */
    public static int getGridSize() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Grid Size?");
        int size = sc.nextInt();
        sc.close();
        return size;
    }

    /**
     * Creates and returns a 2D array of <code>size</code> x <code>size</code>
     *
     * @param size the number of rows and columns in the 2D array
     * @return a square 2D array of <code>size</code> x <code>size</code>
     */
    public static int[][] makeGrid(int size) {
        int[][] myArray = new int[size][size];
        // System.out.println(Arrays.deepToString(myArray));
        return myArray;
    }

    /**
     * Randomly places n mines in the 2D array, <code>grid</code>, where n is equal to <code>size
     * </code>
     *
     * @param size the number of mines to place in the <code>grid</code>
     * @param grid the 2D array
     */
    public static void placeMines(int size, int[][] grid) {
        for (int i = 0; i < size; i++) {
            int randomRow = (int) (Math.random() * size);
            int randomCol = (int) (Math.random() * size);
            if (grid[randomRow][randomCol] != 9) {
                grid[randomRow][randomCol] = 9;
            } else {
                i--;
            }
        }
    }

    /**
     * Counts each element in the 2D array, <code>grid</code> for the number of mines surrounding
     * the element
     *
     * @param grid the 2D array
     */
    public static void countAllSurroundingMines(int[][] grid) {
        int amountOfMines = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                if (grid[r][c] != 9) {
                    if (r != 0 && grid[r - 1][c] == 9) amountOfMines++;
                    if (r != grid.length - 1 && grid[r + 1][c] == 9) amountOfMines++;
                    if (c != 0 && grid[r][c - 1] == 9) amountOfMines++;
                    if (c != grid.length - 1 && grid[r][c + 1] == 9) amountOfMines++;
                    grid[r][c] = amountOfMines;
                    amountOfMines = 0;
                }
            }
        }
    }

    /**
     * Counts the number of mines surrounding the element at the given <code>row</code> and <code>
     * col
     * </code>
     *
     * @param r the row location
     * @param c the column location
     * @param grid the 2D array
     */
    public int countSurroundingMines(int r, int c, int[][] grid) {
        int amountOfMines = 0;
        if (r != 0 && grid[r - 1][c] == 9) amountOfMines++;
        if (r != grid.length - 1 && grid[r + 1][c] == 9) amountOfMines++;
        if (c != 0 && grid[r][c - 1] == 9) amountOfMines++;
        if (c != grid.length - 1 && grid[r][c + 1] == 9) amountOfMines++;
        return amountOfMines;
    }

    /**
     * Prints the 2D array of integers
     *
     * @param grid the 2D array
     */
    public static void printGrid(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }
}
