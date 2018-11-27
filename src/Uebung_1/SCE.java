package Uebung_1;

public class SCE {
    
    public static void main(String[] args) {
        System.out.println("Starting bench with SCE:");
        long startTime = System.currentTimeMillis();
        
        boolean with_sce = true || doExpensiveTest();
        System.out.println(with_sce);
        
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("With SCE: " + elapsedTime + "[ms]");
        
        System.out.println("Starting bench without SCE:");
        startTime = System.currentTimeMillis();
        
        boolean without_sce = true | doExpensiveTest();
        System.out.println(without_sce);
        
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Without SCE: " + elapsedTime + "[ms]");
    }
    
    public static boolean doExpensiveTest() {
        boolean expensiveTest = false;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            for (int j = 0; j < Integer.MAX_VALUE; j++) {
                expensiveTest = !expensiveTest;
            }
        }
        return expensiveTest;
    }
}
