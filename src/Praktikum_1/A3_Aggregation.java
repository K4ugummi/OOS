/**
 * <h2>Title: Praktikum 1, Aufgabe 3 </h2>
 * <p>Description: This class contains an example of aggregation in java.
 * To show this we use the class Point of Uebung 2, Aufgabe 11 as a member of
 * two new classes Rechteck and KreisAgg. Those two are subclasses
 * of an abstract class Geometry which lets us implement the function
 * flaechenInhalt() for each. In the main function we demonstrate that it is
 * possible to add Rechteck and KreisAgg to the same array and calculate the
 * sum of each flaechenInhalt().</p>
 *
 * <p>Copyright: Stephan Schauerte</p>
 * <p>Organization: Student of the FH Aachen, FB05 </p>
 * @author Stephan Schauerte
 * @version 0.1
 */

package Praktikum_1;

public class A3_Aggregation {
    public static void main(String[] args) {
        Geometrie[] geo = new Geometrie[4];
        geo[0] = new Rechteck(0, 0, 1, 1); // A = 1.0f;
        geo[1] = new Rechteck(1, 1, 2, 2); // A = 1.0f;
        // Give this circles a nice radius to get a good sum of A
        geo[2] = new KreisAgg(0, 0, 1.0f / (float) Math.sqrt(Math.PI));
        geo[3] = new KreisAgg(1, 1, 1.0f / (float) Math.sqrt(Math.PI));

        float gesamtFlaeche = 0.0f;
        for (int i = 0; i < geo.length; i++) {
            gesamtFlaeche += geo[i].flaechenInhalt();
        }

        System.out.println("Der Flächeninhalt insgesamt beträgt "
                + gesamtFlaeche + " [Einheiten]");
    }
}

/**
 * Abstract class Geometry lets us implement the function flaechenInhalt for
 * different subclasses.
 * 
 * @author stephan
 */
abstract class Geometrie {
    abstract float flaechenInhalt();
}

/**
 * This class represents a Rechteck from just two points. We do not have any
 * error checking here, like: min > max and trust the user (Hope this works).
 * 
 * @author Stephan Schauerte
 */
class Rechteck extends Geometrie {
    Point min;

    Point max;

    public Rechteck() {
        this.min = new Point();
        this.max = new Point();
    }

    public Rechteck(Point min, Point max) {
        this.min = new Point(min);
        this.max = new Point(max);
    }

    public Rechteck(int minX, int minY, int maxX, int maxY) {
        this.min = new Point(minX, minY);
        this.max = new Point(maxX, maxY);
    }

    public boolean equals(Object other) {
        assert (other instanceof Rechteck);
        return this.min == ((Rechteck) other).min
                && this.max == ((Rechteck) other).max;
    }

    public Rechteck clone() {
        return new Rechteck(this.min, this.max);
    }

    public String toString() {
        return "Rechteck { min:" + this.min.toString() + " max:"
                + this.max.toString() + " }";
    }

    public float flaechenInhalt() {
        return (float) ((this.max.x - this.min.x) * (this.max.y - this.min.y));
    }
}

class KreisAgg extends Geometrie {
    Point center;

    float radius;

    /**
     * Many constructors...
     */
    public KreisAgg() {
        this.center = new Point();
        this.radius = 1.0f;
    }

    public KreisAgg(Point center) {
        this.center = new Point(center);
        this.radius = 1.0f;
    }

    public KreisAgg(Point center, float radius) {
        this.center = new Point(center);
        this.radius = radius;
    }

    public KreisAgg(int x, int y, float radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }

    public KreisAgg(float radius) {
        this.center = new Point();
        this.radius = radius;
    }

    public boolean equals(Object other) {
        assert (other instanceof KreisAgg);
        return this.center == ((KreisAgg) other).center
                && this.radius == ((KreisAgg) other).radius;
    }

    public KreisAgg clone() {
        return new KreisAgg(this.center, this.radius);
    }

    public String toString() {
        return "KreisAgg { center:" + this.center.toString() + " radius:"
                + this.radius + " }";
    }

    public float flaechenInhalt() {
        return (float) (this.radius * this.radius * Math.PI);
    }
}
