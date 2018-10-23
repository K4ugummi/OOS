package Praktikum_1;

public class A3_Aggregation {
    public static void main(String[] args) {
        Geometrie[] geo = new Geometrie[4];
        geo[0] = new Rechteck(0, 0, 1, 1);
        geo[1] = new Rechteck(1, 1, 2, 2);
        geo[2] = new KreisAgg(0, 0, 1.0f / (float)Math.sqrt(Math.PI));
        geo[3] = new KreisAgg(1, 1, 1.0f / (float)Math.sqrt(Math.PI));

        float gesamtFlaeche = 0.0f;
        for (int i = 0; i < geo.length; i++) {
            gesamtFlaeche += geo[i].flaechenInhalt();
        }

        System.out.println("Der Flächeninhalt insgesamt beträgt "
                + gesamtFlaeche + " [Einheiten]");
    }
}

abstract class Geometrie {
    abstract float flaechenInhalt();
}

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

    public boolean equals(Rechteck other) {
        return this.min == other.min && this.max == other.max;
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

    public boolean equals(KreisAgg other) {
        return this.center == other.center && this.radius == other.radius;
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
