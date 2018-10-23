package Praktikum_1;

public class A2_Punkt_Kreis {
    public static void main(String[] args) {
        System.out.println("Praktikum_1::KreisVererb::Tests");
        
        Point p = new Point(1, 2);
        KreisVererb k = new KreisVererb(p);
        System.out.println(p + "\n" + k);
    }
}

class Point {
    int x;
    int y;
    
    public Point() {
        this.x = 0;
        this.y = 0;
    }
    
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point getLocation() {
        return this;
    }
    
    public void setLocation(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public boolean equals(Point p) {
        return this.x == p.x && this.y == p.y;
    }
    
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





