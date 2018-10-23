package Uebung_1;
// Calculate prime factors for a hard coded static final int NUMBER.
public class Primefactors {

    static final long NUMBER = 555;

    public static void main(String[] args) {

        long number = NUMBER;
        boolean firstIteration = true;

        // Increase the value of a possible "next smallest divisor" in each
        // iteration, until
        for (long maybeDiv = 2; maybeDiv <= number; maybeDiv++) {
            if (number % maybeDiv == 0) {
                // The first factor does not need a '*' in front.
                if (firstIteration) {
                    System.out.print(maybeDiv);
                    firstIteration = false;
                } else {
                    System.out.print("*" + maybeDiv);
                }

                // Divide the current number by its smallest divisor.
                number = number / maybeDiv;
                maybeDiv = 1; // Remember increment of for loop!
            }
        }

        System.out.print("\n");
    }

} // End of class Primefactors
