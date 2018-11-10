public class Main {
    public static void main(String[] args) {
        //
        char[][] grid = new char[17][17];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = '.';
            }
        }

        for (int i = 0; i < 17; i++) {
            grid[0][i] = 'w';
            grid[i][0] = 'w';
            grid[16][i] = 'w';
            grid[i][16] = 'w';
        }

        // initial starting positions
        Bike s = new Bike(8, 14);
        Bike p = new Bike(8, 2);


        grid[s.row][s.col] = 's';
        grid[p.row][p.col] = 'p';



        printGrid(grid);

        System.out.println(" UP " + distanceToNearestObstacle(grid, p, 'u'));
        System.out.println(" DOWN " + distanceToNearestObstacle(grid, p, 'd'));
        System.out.println(" LEFT " + distanceToNearestObstacle(grid, p, 'l'));
        System.out.println(" RIGHT " + distanceToNearestObstacle(grid, p, 'r'));

    }

    /*
    Calculates the distance to the nearest obstacle (a wall or another player) given the direction
    TODO: Add current direction!
     */
    public static int distanceToNearestObstacle(char[][] state, Bike curr, char direction) {
        switch(direction) {
            case 'u':
                for (int i = curr.row - 1; i >= 0; i--) {
                    if (state[i][curr.col] != '.') {
                        return Math.abs(curr.row - i - 1);
                    }
                }
            case 'd':
                for (int i = curr.row + 1; i < state.length; i++) {
                    if (state[i][curr.col] != '.') {
                        return Math.abs(i - curr.row - 1);
                    }
                }
            case 'l':
                for (int i = curr.col - 1; i >= 0; i--) {
                    if (state[curr.row][i] != '.') {
                        return Math.abs(curr.col - i - 1);
                    }
                }
            case 'r':
                for (int i = curr.col + 1; i < state[0].length; i++) {
                    if (state[curr.row][i] != '.') {
                        return Math.abs(i - curr.col - 1);
                    }
                }
            default:
                return -1;
        }
    }

    /*
    Calculates relative x position with respect to b1. Assumes the move is valid. Takes in direction of next move.
     */
    public int relativeX(Bike b1, Bike b2, char direction) {
        return -1;
    }

    /*
    Calculates relative y position with respect to b1. Assumes the move is valid. Takes in direction of the next move.
     */
    public int relativeY(Bike b1, Bike b2, char direction) {
        return -1;
    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(" " + grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
