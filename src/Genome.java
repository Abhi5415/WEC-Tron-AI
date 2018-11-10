import java.util.Arrays;

public class Genome {
    double closeFreespace;
    double closeWallDistance;
    double[] closeRelativeEnemyX; // indexes S, L, R
    double[]  closeRelativeEnemyY;
    double farFreespace;
    double farWallDistance;
    double[] farRelativeEnemyX;
    double[] farRelativeEnemyY;
    char currDirection;
    boolean didWin;

    public Genome() {       this.closeFreespace = random(-100, 100);
        this.closeWallDistance = random(-100, 100);
        this.closeRelativeEnemyX = new double[3];
        this.closeRelativeEnemyX[0] = random(-100, 100);
        this.closeRelativeEnemyX[1] = random(-100, 100);
        this.closeRelativeEnemyX[2] = random(-100, 100);
        this.closeRelativeEnemyY = new double[3];
        this.closeRelativeEnemyY[0] = random(-100, 100);
        this.closeRelativeEnemyY[1] = random(-100, 100);
        this.closeRelativeEnemyY[2] = random(-100, 100);
        this.farFreespace = random(-100, 100);
        this.farWallDistance = random(-100, 100);
        this.farRelativeEnemyX = new double[3];
        this.farRelativeEnemyX[0] = random(-100, 100);
        this.farRelativeEnemyX[1] = random(-100, 100);
        this.farRelativeEnemyX[2] = random(-100, 100);
        this.farRelativeEnemyY = new double[3];
        this.farRelativeEnemyY[0] = random(-100, 100);
        this.farRelativeEnemyY[1] = random(-100, 100);
        this.farRelativeEnemyY[2] = random(-100, 100);
        this.currDirection = 'u';
        this.didWin = false;
    }

    public Genome(Genome g1, Genome g2) {
        this.closeFreespace = random(g1.closeFreespace, g2.closeFreespace);
        this.closeWallDistance = random(g1.closeWallDistance, g2.closeWallDistance);
        this.closeRelativeEnemyX[0] = random(g1.closeRelativeEnemyX[0], g2.closeRelativeEnemyX[0]);
        this.closeRelativeEnemyX[1] = random(g1.closeRelativeEnemyX[1], g2.closeRelativeEnemyX[1]);
        this.closeRelativeEnemyX[2] = random(g1.closeRelativeEnemyX[2], g2.closeRelativeEnemyX[2]);
        this.closeRelativeEnemyY[0] = random(g1.closeRelativeEnemyY[0], g2.closeRelativeEnemyY[0]);
        this.closeRelativeEnemyY[1] = random(g1.closeRelativeEnemyY[1], g2.closeRelativeEnemyY[1]);
        this.closeRelativeEnemyY[2] = random(g1.closeRelativeEnemyY[2], g2.closeRelativeEnemyY[2]);
        this.farFreespace = random(g1.farFreespace, g2.farFreespace);
        this.farWallDistance = random(g1.farWallDistance, g2.farWallDistance);
        this.farRelativeEnemyX[0] = random(g1.farRelativeEnemyX[0], g2.farRelativeEnemyX[0]);
        this.farRelativeEnemyX[1] = random(g1.farRelativeEnemyX[1], g2.farRelativeEnemyX[1]);
        this.farRelativeEnemyX[2] = random(g1.farRelativeEnemyX[2], g2.farRelativeEnemyX[2]);
        this.farRelativeEnemyY[0] = random(g1.farRelativeEnemyY[0], g2.farRelativeEnemyY[0]);
        this.farRelativeEnemyY[1] = random(g1.farRelativeEnemyY[1], g2.farRelativeEnemyY[1]);
        this.farRelativeEnemyY[2] = random(g1.farRelativeEnemyY[2], g2.farRelativeEnemyY[2]);
    }

    public double random(double low, double high) {
        return Math.random() * (high - low) + 1;
    }

    public char nextMove(char[][] grid, Bike myPosition, Bike enemyPosition, char currentDirection) {
        double ratingS = 0;
        double ratingL = 0;
        double ratingR = 0;

        boolean isFar = Math.sqrt(Math.pow(relativeX(myPosition, enemyPosition), 2) + Math.pow(relativeY(myPosition, enemyPosition), 2)) < 5;

        char moveL = 's';
        char moveR = 's';
        Bike bikeS = new Bike(0,0);
        Bike bikeL = new Bike(0,0);
        Bike bikeR = new Bike(0,0);

        switch (currentDirection) {
            case 'u':
                moveL = 'l';
                moveR = 'r';
                bikeS = new Bike(myPosition.row - 1, myPosition.col);
                bikeL = new Bike(myPosition.row, myPosition.col - 1);
                bikeR = new Bike(myPosition.row, myPosition.col + 1);
                break;
            case 'd':
                moveL = 'r';
                moveR = 'l';
                bikeS = new Bike(myPosition.row + 1, myPosition.col);
                bikeL = new Bike(myPosition.row, myPosition.col + 1);
                bikeR = new Bike(myPosition.row, myPosition.col - 1);
                break;
            case 'r':
                moveL = 'u';
                moveR = 'd';
                bikeS = new Bike(myPosition.row, myPosition.col + 1);
                bikeL = new Bike(myPosition.row - 1, myPosition.col);
                bikeR = new Bike(myPosition.row + 1, myPosition.col);
                break;
            case 'l':
                moveL = 'd';
                moveR = 'u';
                bikeS = new Bike(myPosition.row, myPosition.col - 1);
                bikeL = new Bike(myPosition.row + 1, myPosition.col);
                bikeR = new Bike(myPosition.row - 1, myPosition.col);
                break;
        }

        if (isFar) {
            ratingS += distanceToNearestObstacle(grid, myPosition, currentDirection) * farWallDistance;
            ratingL += distanceToNearestObstacle(grid, myPosition, moveL) * farWallDistance;
            ratingR += distanceToNearestObstacle(grid, myPosition, moveR) * farWallDistance;

            ratingS += freeSpacesAtIndex(grid, bikeS) * farFreespace;
            ratingL += freeSpacesAtIndex(grid, bikeL) * farFreespace;
            ratingR += freeSpacesAtIndex(grid, bikeR) * farFreespace;

            ratingS += relativeX(bikeS, enemyPosition) * farRelativeEnemyX[0];
            ratingL += relativeX(bikeL, enemyPosition) * farRelativeEnemyX[1];
            ratingR += relativeX(bikeR, enemyPosition) * farRelativeEnemyX[2];

            ratingS += relativeY(bikeS, enemyPosition) * farRelativeEnemyY[0];
            ratingL += relativeY(bikeL, enemyPosition) * farRelativeEnemyY[1];
            ratingR += relativeY(bikeR, enemyPosition) * farRelativeEnemyY[2];
        } else {
            ratingS += distanceToNearestObstacle(grid, myPosition, currentDirection) * closeWallDistance;
            ratingL += distanceToNearestObstacle(grid, myPosition, moveL) * closeWallDistance;
            ratingR += distanceToNearestObstacle(grid, myPosition, moveR) * closeWallDistance;

            ratingS += freeSpacesAtIndex(grid, bikeS) * closeFreespace;
            ratingL += freeSpacesAtIndex(grid, bikeL) * closeFreespace;
            ratingR += freeSpacesAtIndex(grid, bikeR) * closeFreespace;

            ratingS += relativeX(bikeS, enemyPosition) * closeRelativeEnemyX[0];
            ratingL += relativeX(bikeL, enemyPosition) * closeRelativeEnemyX[1];
            ratingR += relativeX(bikeR, enemyPosition) * closeRelativeEnemyX[2];

            ratingS += relativeY(bikeS, enemyPosition) * closeRelativeEnemyY[0];
            ratingL += relativeY(bikeL, enemyPosition) * closeRelativeEnemyY[1];
            ratingR += relativeY(bikeR, enemyPosition) * closeRelativeEnemyY[2];
        }

        // return based on highest rating values
        if (ratingS >= ratingL && ratingS >= ratingR) {
            return 's';
        } else if (ratingL >= ratingS && ratingL >= ratingR) {
            return 'l';
        } else {
            return 'r';
        }
    }

    /*
Calculates the distance to the nearest obstacle (a wall or another player) given the direction.
 */
    public int distanceToNearestObstacle(char[][] state, Bike curr, char direction) {
        switch (direction) {
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
    Calculates the free spaces in a 3x3 square with the center as the Bike passed.
     */
    public int freeSpacesAtIndex(char[][] state, Bike b) {
        int x = b.row;
        int y = b.col;

        int res = 0;

        for (int r = x - 1; r < x + 2; r++) {
            for (int c = y - 1; c < y + 2; c++) {
                // validate bounds
                if (r < 0 || r >= state.length || c < 0 || c >= state.length) continue;

                if (state[r][c] == '.') res++;
            }
        }

        return res;
    }

    /*
    Calculates relative x position with respect to b1. Assumes the move is valid.
     */
    public int relativeX(Bike b1, Bike b2) {
        return b2.col - b1.col;
    }

    /*
    Calculates relative y position with respect to b1. Assumes the move is valid.
     */
    public int relativeY(Bike b1, Bike b2) {
        return b2.row - b1.row;
    }

    @Override
    public String toString() {
        return "Genome{" +
                "closeFreespace=" + closeFreespace +
                ", closeWallDistance=" + closeWallDistance +
                ", closeRelativeEnemyX=" + Arrays.toString(closeRelativeEnemyX) +
                ", closeRelativeEnemyY=" + Arrays.toString(closeRelativeEnemyY) +
                ", farFreespace=" + farFreespace +
                ", farWallDistance=" + farWallDistance +
                ", farRelativeEnemyX=" + Arrays.toString(farRelativeEnemyX) +
                ", farRelativeEnemyY=" + Arrays.toString(farRelativeEnemyY) +
                ", currDirection=" + currDirection +
                ", didWin=" + didWin +
                '}';
    }
}
