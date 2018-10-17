
public class Matrices {

    static final long MATRIX_WIDTH = 2;
    static final long MATRIX_HEIGHT = 2;
    
    public static void main(String[] args) {
        long[][] a = new long[2][2];
        long[][] b = new long[2][2];
        
        for (int j = 0; j < MATRIX_HEIGHT; j++) {
            for (int i = 0; i < MATRIX_WIDTH; i++) {
                // This depends on row-major or column-major i and j.
                // It is not clear from the task.
                a[i][j] = 10 * i + j;
                b[i][j] = i + 10 * j;
                //a[i][j] = 10 * i + j;
                //b[i][j] = i + 10 * j;
            }
        }

        System.out.println("Matrix a:");
        printMatrix(a);
        System.out.println("Matrix b:");
        printMatrix(b);
        
        System.out.println("Matrix mul a*b:");
        printMatrix(matrixMult(a,b));
    }
    
    static long[][] matrixMult(long[][] a, long[][] b) {
        if (a[0].length != b.length) {
            assert(false);
        }
        int columnLength = a.length;
        int rowLength = a[0].length;
        long[][] result = new long[columnLength][rowLength];
        
        for (int j = 0; j < columnLength; j++) {
            System.out.println("j:" + j);
            
            for (int i = 0; i < rowLength; i++) {
                System.out.println("i: " + i);
                int sum = 0;
                for (int sumIdx = 0; sumIdx < rowLength; sumIdx++) {
                    sum += a[j][sumIdx] * b[sumIdx][i];
                }
                result[j][i] = sum;
            }
        }
        
        return result;
    }
    
    static void printMatrix(long[][] matrix) {
        // Calculate how many digits the longest number of matrix has.
        int maxDigits = 0;
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix[j].length; i++) {
                int curDigits = String.valueOf(matrix[j][i]).length();
                
                if (maxDigits < curDigits) {
                    maxDigits = curDigits;
                }
            }
        }
        
        // Print the matrix with brackets.
        for (int j = 0; j < matrix.length; j++) {
            // Print matrix left bracket.
            if (j == 0) {
                System.out.print("┌");
            }
            else if (j == matrix.length) {
                System.out.print("│");
            }
            else {
                System.out.print("└");
            }
            
            // Print row values.
            for (int i = 0; i < matrix[j].length; i++) {
                printNumber(matrix[j][i], maxDigits);
            }
            
            // Print matrix right bracket.
            if (j == 0) {
                System.out.println(" ┐");
            }
            else if (j == matrix.length) {
                System.out.println(" │");
            }
            else {
                System.out.println(" ┘");
            }   
        }
    }
    
    static void printNumber(long number, int maxDigits) {
        int numberDigits = String.valueOf(number).length();
        int emptyDigits = maxDigits - numberDigits;
        for (int emptyCntr = 0; emptyCntr <= emptyDigits; emptyCntr++) {
            System.out.print(" ");
        }
        System.out.print(number);
    }
}
