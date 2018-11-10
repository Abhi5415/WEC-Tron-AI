public class Main {
    public static void main(String[] args) {
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
        grid[s.row + 1][s.col] = 's';
        grid[s.row][s.col + 1] = 's';


        printGrid(grid);

//        System.out.println(" UP " + distanceToNearestObstacle(grid, p, 'u'));
//        System.out.println(" DOWN " + distanceToNearestObstacle(grid, p, 'd'));
//        System.out.println(" LEFT " + distanceToNearestObstacle(grid, p, 'l'));
//        System.out.println(" RIGHT " + distanceToNearestObstacle(grid, p, 'r'));
//
//        System.out.println(freeSpacesAtIndex(s, grid));
//        System.out.println(relativeY(s, p));

        // make initial generation
        Genome[] genomes = new Genome[100];

        for (int generation = 0; generation < 1000; generation++) {
            // store winners from most recent generation
            Genome[] currGeneration = new Genome[50];

            for (int i = 0; i < 100; i += 2) {
                genomes[i] = new Genome();
                genomes[i + 1] = new Genome();
                // select two genomes and assign their winner to current generation
                currGeneration[i / 2] = playGame(genomes[i], genomes[i + 1]);
            }

            // rewrite old generation
            genomes = new Genome[100];

            // copy winners to old generation
            for (int i = 0; i < 50; i++) {
                genomes[i] = currGeneration[i];
            }

            // breed two random indices of current generation
            for (int i = 50; i < 90; i++) {
                int random1 = random(0, 49);
                int random2 = random(0, 49);
                genomes[i] = new Genome(currGeneration[random1], currGeneration[random2]);
            }

            // generate 10 random breeds
            for (int i = 90; i < 100; i++) {
                genomes[i] = new Genome();
            }
        }

        System.out.println("PLAYING GAME!");
        playGame(genomes[0], genomes[1], true);
    }

    static Genome playGame(Genome g1, Genome g2) {
        Tron tron = new Tron();
        System.out.println(g1);
        System.out.println(g2);
        return tron.returnWinner(g1, g2);
    }

    static Genome playGame(Genome g1, Genome g2, boolean debug) {
        Tron tron = new Tron();
        System.out.println(g1);
        System.out.println(g2);
        return tron.returnWinner(g1, g2, debug);
    }

    static int random(int low, int high) {
        return (int) (Math.random() * (high - low)) + 1;
    }


    /*
    TODO: Determines the direction of the bike heading.
     */
    public char bikeHeading(Bike b, char[][] grid) {
        return '.';
    }

    /*
    Prints the array as a debug function.
     */
    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(" " + grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
