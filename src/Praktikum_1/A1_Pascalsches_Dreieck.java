package Praktikum_1;

public class A1_Pascalsches_Dreieck {
    
    static final int MAX_DEPTH = 9;
    
    public static void main(String[] args) {
        int[][] dreieck = new int[MAX_DEPTH + 1][];  
        
        for (int i = 0; i <= MAX_DEPTH; i++) {
            dreieck[i] = new int[i+1];
            
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    dreieck[i][j] = 1;
                }
                else {
                    dreieck[i][j] = dreieck[i-1][j-1] + dreieck[i-1][j];
                }
                System.out.print(dreieck[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
