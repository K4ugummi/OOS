
public class Primfaktoren {
	
	static final int ZAHL = 555;
	
	public static void main(String[] args) {
		
		int zahl = ZAHL;
		boolean firstIteration = true;
		
		// Increase the value of a possible "next smallest divisor" in each
		// iteration, until
    for (int maybeDiv = 2; maybeDiv <= zahl; maybeDiv++ ) {
	      if (zahl % maybeDiv == 0) {
	      		if (firstIteration) {
	      				System.out.print(maybeDiv);
	      				firstIteration = false;
	      		}
	      		else {
	      				System.out.print("*" + maybeDiv);
	      		}
	          zahl = zahl/maybeDiv;
	          maybeDiv = 1;	// Remember increment of for loop!
	      }
    }	
    
    System.out.print("\n");
	}

}
