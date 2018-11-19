/**
 * <h2>Title: Praktikum 1, Aufgabe 1 </h2>
 * <p>Description: This file contains a program that calculates the
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
 * This Program consists of just a main function which calculates the Pascal's
 * triangle.
 */
public class A1_Pascalsches_Dreieck {

    // Constant max depth the program calculates the Pascal's triangle to.
    static final int MAX_DEPTH = 9;

    public static void main(String[] args) {
        // Init the number of rows.
        int[][] dreieck = new int[MAX_DEPTH + 1][];

        // Calculate each row of a Pascal's triangle, where row represents
        // the current depth.
        for (int row = 0; row <= MAX_DEPTH; row++) {
            // Init the length of the current row.
            dreieck[row] = new int[row + 1];
            dreieck[row][0] = 1;
            dreieck[row][row] = 1;

            for (int col = 1; col < row; col++) {
                dreieck[row][col] = dreieck[row - 1][col - 1]
                        + dreieck[row - 1][col];
                System.out.print(dreieck[row][col] + " ");
            }
            System.out.print("\n");
        }
    }
}
