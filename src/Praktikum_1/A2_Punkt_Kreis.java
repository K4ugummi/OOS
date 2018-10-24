/**
 * <h2>Title: Praktikum 1, Aufgabe 2 </h2>
 * <p>Description: This class contains an example of inheritance in java
 * for this we use the class Point of Uebung 2, Aufgabe 11 as a superclass.
 * KreisVererb, which means CircleInheri(ted) in german is our subclass.
 * The main function in this file is used as a unit test, because unit test
 * will be introduced in a later chapter of this Praktikum.</p>
 *
 * <p>Copyright: Stephan Schauerte</p>
 * <p>Organization: Student of the FH Aachen, FB05 </p>
 * @author Stephan Schauerte
 * @version 0.1
 */

package Praktikum_1;

/**
 * Unit tests of our inheritance example (Point -> KreisVererb)
 */
public class A2_Punkt_Kreis {
    public static void main(String[] args) {
        System.out.println("Praktikum_1::KreisVererb::Tests");
        
        Point p = new Point(1, 2);
        KreisVererb k = new KreisVererb(p);
        System.out.println(p + "\n" + k);
    }
}

/**
 * A Point in 2D space with integer coordinates. This is our superclass.
 */
class Point {
    // Private coordinates.
    int x;
    int y;
    
    /**
     * Default constructor which set the Points coordinates to (0, 0)
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }
    
    /**
     * Constructor which takes in another Point.
     */
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    /**
     * Constructor which takes in x- and y-coordinate as integers.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Getter of the location of a Point. (I'm not sure how useful it is to
     * return another point from this method, but thats how the UML diagram
     * dictates it.)
     */
    public Point getLocation() {
        return this;
    }
    
    /**
     * Setter to apply new coordinates to the Point from another Point.
     * @param p
     */
    public void setLocation(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    /**
     * Setter to apply new coordinates to the Point via the coordinates (x, y)
     * @param x
     * @param y
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Moves the point by the given delta for each axis.
     * @param dx
     * @param dy
     */
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    /**
     * Checks if a point is equal to another one.
     * @param p
     * @return
     */
    public boolean equals(Point p) {
        return this.x == p.x && this.y == p.y;
    }
    
    /**
     * Overrides the default toString() function for Object.
     */
    public String toString() {
        return "Point { x:" + this.x + " y:" + this.y + " }";
    }
}

class KreisVererb extends Point {
    float radius;
    
    public KreisVererb() {
        super();
        this.radius = 1.f;
    }
    
    public KreisVererb(Point p) {
        super(p);
        this.radius = 1.f;
    }
    
    public KreisVererb(int x, int y) {
        super(x, y);
        this.radius = 1.f;
    }
    
    public KreisVererb(Point p, float radius) {
        super(p);
        this.radius = radius;
    }
    
    public KreisVererb(int x, int y, float radius) {
        super(x, y);
        this.radius = radius;
    }
    
    public float getRadius() {
        return this.radius;
    }
    
    public void setRadius(float radius) {
        this.radius = radius;
    }
    
    public String toString() {
        return "KreisVererb { x:" + this.x 
                + " y:" + this.y 
                + " radius:" + this.radius +" }";
    }
}





