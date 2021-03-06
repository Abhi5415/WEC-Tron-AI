public class Tron {


    //bike 1, bike 2 - next move: L,R,S
    //currentDirection
    //return the winner
    private static char[][] grid;

    private static int bike1Row = 8;
    private static int bike1Column = 2;
    private static int bike2Row = 8;
    private static int bike2Column = 14;

    private static char bike1Direction = 'r';
    private static char bike2Direction = 'l';

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
        if (direction == 'u') {
            if (move == 'l') {
                if (grid[row][column-1] != '.') {
                    return false;
                }
            }
            else if (move == 'r') {
                if (grid[row][column+1] != '.') {
                    return false;
                }
            }
            else if (move == 's') {
                if (grid[row-1][column] != '.') {
                    return false;
                }
            }
        }
        else if (direction == 'd') {
            if (move == 'l') {
                if (grid[row][column+1] != '.') {
                    return false;
                }
            }
            else if (move == 'r') {
                if (grid[row][column-1] != '.') {
                    return false;
                }
            }
            else if (move == 's') {
                if (grid[row+1][column] != '.') {
                    return false;
                }
            }
        }

        else if (direction == 'l') {
            if (move == 'l') {
                if (grid[row+1][column] != '.') {
                    return false;
                }
            }
            else if (move == 'r') {
                if (grid[row-1][column] != '.') {
                    return false;
                }
            }
            else if (move == 's') {
                if (grid[row][column-1] != '.') {
                    return false;
                }
            }
        }

        else if (direction == 'r') {
            if (move == 'l') {
                if (grid[row-1][column] != '.') {
                    return false;
                }
            }
            else if (move == 'r') {
                if (grid[row+1][column] != '.') {
                    return false;
                }
            }
            else if (move == 's') {
                if (grid[row][column+1] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

//    public static void main(String args[]){
//        setGrid();
//        makeMove('s', 's');
//        printGrid(grid);
//
//        makeMove('l', 'r');
//        printGrid(grid);
//
//        makeMove('s', 's');
//        printGrid(grid);
//
//        makeMove('l', 'r');
//        printGrid(grid);
//
//        makeMove('s', 's');
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
            if (bike1Direction == 'u') {
                if (firstMove == 'l') {
                    grid[bike1Row][bike1Column-1] = 's';
                    bike1Column -= 1;
                    bike1Direction = 'l';
                }
                else if (firstMove == 'r') {
                    grid[bike1Row][bike1Column+1] = 's';
                    bike1Column += 1;
                    bike1Direction = 'r';
                }
                else if (firstMove == 's') {
                    grid[bike1Row - 1][bike1Column] = 's';
                    bike1Row -= 1;
                }
            }
            else if (bike1Direction == 'd') {
                if (firstMove == 'l') {
                    grid[bike1Row][bike1Column+1] = 's';
                    bike1Column+= 1;
                    bike1Direction = 'r';
                }
                else if (firstMove == 'r') {
                    grid[bike1Row][bike1Column-1] = 's';
                    bike1Column -= 1;
                    bike1Direction = 'l';
                }
                else if (firstMove == 's') {
                    grid[bike1Row+1][bike1Column] = 's';
                    bike1Row+=1;
                }
            }

            else if (bike1Direction == 'l') {
                if (firstMove == 'l') {
                    grid[bike1Row+1][bike1Column] = 's';
                    bike1Row += 1;
                    bike1Direction = 'd';
                }
                else if (firstMove == 'r') {
                    grid[bike1Row-1][bike1Column] = 's';
                    bike1Row -= 1;
                    bike1Direction = 'u';
                }
                else if (firstMove == 's') {
                    grid[bike1Row][bike1Column-1] = 's';
                    bike1Column -= 1;
                }
            }
            else if (bike1Direction == 'r') {
                if (firstMove == 'l') {
                    grid[bike1Row-1][bike1Column] = 's';
                    bike1Row -= 1;
                    bike1Direction = 'u';
                }
                else if (firstMove == 'r') {
                    grid[bike1Row+1][bike1Column] = 's';
                    bike1Row += 1;
                    bike1Direction = 'd';
                }
                else if (firstMove == 's') {
                    grid[bike1Row][bike1Column+1] = 's';
                    bike1Column += 1;
                }
            }
            //System.out.println("New Coordinates for Bike 1: " + bike1Row + ", " + bike1Column);

        } else {
            winnerTwo = true;
        }

        if(isValidMove(bike2Row, bike2Column, secondMove, bike2Direction, grid)) {

            if (bike2Direction == 'u') {
                if (secondMove == 'l') {
                    grid[bike2Row][bike2Column-1] = 'p';
                    bike2Column -= 1;
                    bike2Direction = 'l';
                }
                else if (secondMove == 'r') {
                    grid[bike2Row][bike2Column+1] = 'p';
                    bike2Column += 1;
                    bike2Direction = 'r';
                }
                else if (secondMove == 's') {
                    grid[bike2Row - 1][bike2Column] = 'p';
                    bike2Row -= 1;
                }
            }
            else if (bike2Direction == 'd') {
                if (secondMove == 'l') {
                    grid[bike2Row][bike2Column+1] = 'p';
                    bike2Column+= 1;
                    bike2Direction = 'r';
                }
                else if (secondMove == 'r') {
                    grid[bike2Row][bike2Column-1] = 'p';
                    bike2Column -= 1;
                    bike2Direction = 'l';
                }
                else if (secondMove == 's') {
                    grid[bike2Row+1][bike2Column] = 'p';
                    bike2Row+=1;
                }
            }

            else if (bike2Direction == 'l') {
                if (secondMove == 'l') {
                    grid[bike2Row+1][bike2Column] = 'p';
                    bike2Row += 1;
                    bike2Direction = 'd';
                }
                else if (secondMove == 'r') {
                    grid[bike2Row-1][bike2Column] = 'p';
                    bike2Row -= 1;
                    bike2Direction = 'u';
                }
                else if (secondMove == 's') {
                    grid[bike2Row][bike2Column-1] = 'p';
                    bike2Column -= 1;
                }
            }
            else if (bike2Direction == 'r') {
                if (secondMove == 'l') {
                    grid[bike2Row-1][bike2Column] = 'p';
                    bike2Row -= 1;
                    bike2Direction = 'u';
                }
                else if (secondMove == 'r') {
                    grid[bike2Row+1][bike2Column] = 'p';
                    bike2Row += 1;
                    bike2Direction = 'd';   
                }
                else if (secondMove == 's') {
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
            char move1 = g1.nextMove(grid, new Bike(bike1Row, bike1Column), new Bike(bike2Row, bike2Column), bike1Direction);
            char move2 = g2.nextMove(grid, new Bike(bike2Row, bike2Column), new Bike(bike1Row, bike1Column), bike2Direction);
            makeMove(move1, move2);
        }

        if (winnerOne) return g1;
        else return g2;
    }

    public Genome returnWinner(Genome g1, Genome g2, boolean debug) {
        while (!gameOver) {
            char move1 = g1.nextMove(grid, new Bike(bike1Row, bike1Column), new Bike(bike2Row, bike2Column), bike1Direction);
            char move2 = g2.nextMove(grid, new Bike(bike2Row, bike2Column), new Bike(bike1Row, bike1Column), bike2Direction);
            printGrid(grid);
            makeMove(move1, move2);
        }

        if (winnerOne) return g1;
        else return g2;
    }
}


