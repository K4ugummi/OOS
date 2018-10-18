// Calculate factorial of static final long NUMBER.
public class Factorial {

    static final long NUMBER = 40;

    public static void main(String[] args) {

        long factorial = 1;

        for (long currentFac = 1; currentFac <= NUMBER; currentFac++) {
            factorial = factorial * currentFac;
        }

        
        System.out.println("Factorial of " + NUMBER + " = " + factorial);
    }

} // End of class Factorial
