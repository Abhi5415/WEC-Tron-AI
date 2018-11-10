public class Matrix implements  Constants {
    int[][] grid;
    int rows;
    int cols;

    public Matrix(int rows, int cols, Bike initialBike1, Bike initialBike2) {
        grid = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;

        grid[initialBike1.y][initialBike1.x] = 1;
        grid[initialBike2.y][initialBike2.y] = 2;

    }

    void debug() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf(" %d ", grid[i][j]);
            }
            System.out.println();
        }
    }
}
