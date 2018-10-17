
public class Fibonacci {
    public static void main(String[] args) {
        long[] fibNumbers = new long[50];
        
        for (int curFibId = 0; curFibId < fibNumbers.length; curFibId++) {
            fibNumbers[curFibId] = fib(curFibId);
            
            System.out.println(curFibId + " -> " + fibNumbers[curFibId]);
        }
    }
    
    public static long fib(long number) {
        // Optional fib(0) == 0
        if (number == 0) {
            return 0;
        }
        else if (number == 1 || number == 2) {
            return 1;
        }
        else {
            return fib(number - 1) + fib(number - 2);
        }
    }
}
