/**
 * <h2>Title: Praktikum 1, Aufgabe 1 </h2>
 * <p>Description: This class contains a program that calculates the
 * Pascal's triangle up to a depth of MAX_DEPTH and prints it to the
 * default output. The special feature that is used here is that you can
 * can create triangular arrays instead of rectangular ones in Java.</p>
 *
 * <p>Copyright: Stephan Schauerte</p>
 * <p>Organization: Student of the FH Aachen, FB05 </p>
 * @author Stephan Schauerte
 * @version 0.1
 */

package Praktikum_1;

/**
 * This Program consists of just a main function which calculates the 
 * Pascal's triangle.
 */
public class A1_Pascalsches_Dreieck {

    // Constant max depth the program calculates to.
    static final int MAX_DEPTH = 9;

    public static void main(String[] args) {
        // Init the number of rows.
        int[][] dreieck = new int[MAX_DEPTH + 1][];

        for (int row = 0; row <= MAX_DEPTH; row++) {
            // Init the length of the current row.
            dreieck[row] = new int[row + 1];

            for (int col = 0; col <= row; col++) {
                // If it is the first or last number of a row.
                if (col == 0 || col == row) {
                    dreieck[row][col] = 1;
                } else {
                    dreieck[row][col] = dreieck[row - 1][col - 1]
                            + dreieck[row - 1][col];
                }
                System.out.print(dreieck[row][col] + " ");
            }
            System.out.print("\n");
        }
    }
}
