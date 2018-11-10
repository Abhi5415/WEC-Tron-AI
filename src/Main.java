public class Main implements Constants {
    public static void main(String[] args) {
        Bike s = new Bike(BIKE_1_X, BIKE_1_Y);
        Bike p = new Bike(BIKE_2_X,BIKE_2_Y);


        Matrix m = new Matrix(MATRIX_ROWS, MATRIX_COLS, s, p);

        m.debug();
    }
}
