public class Tron {


    //bike 1, bike 2 - next move: L,R,S
    //currentDirection
    //return the winner
    private static char[][] grid;

    private static int bike1Row = 8;
    private static int bike1Column = 2;
    private static int bike2Row = 8;
    private static int bike2Column = 14;

    private static char bike1Direction = 'R';
    private static char bike2Direction = 'L';

    private static boolean winnerOne = false;
    private static boolean winnerTwo = false;
    private static boolean gameOver = false;

    public Tron() {
        setGrid();
    }

    public static void setGrid() {
//        this.grid = grid;

        grid = new char[17][17];

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


        grid[s.row][s.col] = 'p';
        grid[p.row][p.col] = 's';
    }

    public static boolean isValidMove(int row, int column, char move, char direction, char[][] grid) {
        if (direction == 'U') {
            if (move == 'L') {
                if (grid[row][column-1] != '.') {
                    return false;
                }
            }
            else if (move == 'R') {
                if (grid[row][column+1] != '.') {
                    return false;
                }
            }
            else if (move == 'S') {
                if (grid[row-1][column] != '.') {
                    return false;
                }
            }
        }
        else if (direction == 'D') {
            if (move == 'L') {
                if (grid[row][column+1] != '.') {
                    return false;
                }
            }
            else if (move == 'R') {
                if (grid[row][column-1] != '.') {
                    return false;
                }
            }
            else if (move == 'S') {
                if (grid[row+1][column] != '.') {
                    return false;
                }
            }
        }

        else if (direction == 'L') {
            if (move == 'L') {
                if (grid[row+1][column] != '.') {
                    return false;
                }
            }
            else if (move == 'R') {
                if (grid[row-1][column] != '.') {
                    return false;
                }
            }
            else if (move == 'S') {
                if (grid[row][column-1] != '.') {
                    return false;
                }
            }
        }

        else if (direction == 'R') {
            if (move == 'L') {
                if (grid[row-1][column] != '.') {
                    return false;
                }
            }
            else if (move == 'R') {
                if (grid[row+1][column] != '.') {
                    return false;
                }
            }
            else if (move == 'S') {
                if (grid[row][column+1] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

//    public static void main(String args[]){
//        setGrid();
//        makeMove('S', 'S');
//        printGrid(grid);
//
//        makeMove('L', 'R');
//        printGrid(grid);
//
//        makeMove('S', 'S');
//        printGrid(grid);
//
//        makeMove('L', 'R');
//        printGrid(grid);
//
//        makeMove('S', 'S');
//        printGrid(grid);
//    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(" " + grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void makeMove(char firstMove, char secondMove) {
        if(isValidMove(bike1Row, bike1Column, firstMove, bike1Direction, grid)) {
            if (bike1Direction == 'U') {
                if (firstMove == 'L') {
                    grid[bike1Row][bike1Column-1] = 's';
                    bike1Column -= 1;
                    bike1Direction = 'L';
                }
                else if (firstMove == 'R') {
                    grid[bike1Row][bike1Column+1] = 's';
                    bike1Row += 1;
                    bike1Direction = 'R';
                }
                else if (firstMove == 'S') {
                    grid[bike1Row - 1][bike1Column] = 's';
                    bike1Row -= 1;
                }
            }
            else if (bike1Direction == 'D') {
                if (firstMove == 'L') {
                    grid[bike1Row][bike1Column+1] = 's';
                    bike1Column+= 1;
                    bike1Direction = 'R';
                }
                else if (firstMove == 'R') {
                    grid[bike1Row][bike1Column-1] = 's';
                    bike1Column -= 1;
                    bike1Direction = 'L';
                }
                else if (firstMove == 'S') {
                    grid[bike1Row+1][bike1Column] = 's';
                    bike1Row+=1;
                }
            }

            else if (bike1Direction == 'L') {
                if (firstMove == 'L') {
                    grid[bike1Row+1][bike1Column] = 's';
                    bike1Row += 1;
                    bike1Direction = 'D';
                }
                else if (firstMove == 'R') {
                    grid[bike1Row-1][bike1Column] = 's';
                    bike1Row -= 1;
                    bike1Direction = 'U';
                }
                else if (firstMove == 'S') {
                    grid[bike1Row][bike1Column-1] = 's';
                    bike1Column -= 1;
                }
            }
            else if (bike1Direction == 'R') {
                if (firstMove == 'L') {
                    grid[bike1Row-1][bike1Column] = 's';
                    bike1Row -= 1;
                    bike1Direction = 'U';
                }
                else if (firstMove == 'R') {
                    grid[bike1Row+1][bike1Column] = 's';
                    bike1Row += 1;
                    bike1Direction = 'D';
                }
                else if (firstMove == 'S') {
                    grid[bike1Row][bike1Column+1] = 's';
                    bike1Column += 1;
                }
            }
            //System.out.println("New Coordinates for Bike 1: " + bike1Row + ", " + bike1Column);

        } else {
            winnerTwo = true;
        }

        if(isValidMove(bike2Row, bike2Column, secondMove, bike2Direction, grid)) {

            if (bike2Direction == 'U') {
                if (secondMove == 'L') {
                    grid[bike2Row][bike2Column-1] = 'p';
                    bike2Column -= 1;
                    bike2Direction = 'L';
                }
                else if (secondMove == 'R') {
                    grid[bike2Row][bike2Column+1] = 'p';
                    bike2Row += 1;
                    bike2Direction = 'R';
                }
                else if (secondMove == 'S') {
                    grid[bike2Row - 1][bike2Column] = 'p';
                    bike2Row -= 1;
                }
            }
            else if (bike2Direction == 'D') {
                if (secondMove == 'L') {
                    grid[bike2Row][bike2Column+1] = 'p';
                    bike2Column+= 1;
                    bike2Direction = 'R';
                }
                else if (secondMove == 'R') {
                    grid[bike2Row][bike2Column-1] = 'p';
                    bike2Column -= 1;
                    bike2Direction = 'L';
                }
                else if (secondMove == 'S') {
                    grid[bike2Row+1][bike2Column] = 'p';
                    bike2Row+=1;
                }
            }

            else if (bike2Direction == 'L') {
                if (secondMove == 'L') {
                    grid[bike2Row+1][bike2Column] = 'p';
                    bike2Row += 1;
                    bike2Direction = 'D';
                }
                else if (secondMove == 'R') {
                    grid[bike2Row-1][bike2Column] = 'p';
                    bike2Row -= 1;
                    bike2Direction = 'U';
                }
                else if (secondMove == 'S') {
                    grid[bike2Row][bike2Column-1] = 'p';
                    bike2Column -= 1;
                }
            }
            else if (bike2Direction == 'R') {
                if (secondMove == 'L') {
                    grid[bike2Row-1][bike2Column] = 'p';
                    bike2Row -= 1;
                    bike2Direction = 'U';
                }
                else if (secondMove == 'R') {
                    grid[bike2Row+1][bike2Column] = 'p';
                    bike2Row += 1;
                    bike2Direction = 'D';
                }
                else if (secondMove == 'S') {
                    grid[bike2Row][bike2Column+1] = 'p';
                    bike2Column += 1;
                }
            }
            //System.out.println("New Coordinates for Bike 2: " + bike2Row + ", " + bike2Column);
        }
        else if (bike1Column != bike2Column && bike1Row != bike2Row){
            winnerOne = true;
        }
        if (bike1Column == bike2Column && bike1Row == bike2Row) {
            winnerOne = true;
            winnerTwo = true;
        }

        if (winnerOne || winnerTwo) gameOver = true;
    }

    public Genome returnWinner(Genome g1, Genome g2) {
        while (!gameOver) {
            makeMove(g1.nextMove(grid, new Bike(bike1Row, bike1Column), new Bike(bike2Row, bike2Column), bike1Direction),
                    g2.nextMove(grid, new Bike(bike2Row, bike2Column), new Bike(bike1Row, bike1Column), bike2Direction) );
        }
        if (winnerOne) return g1;
        else return g2;
    }
}


