package Praktikum_1;

public class A2_Punkt_Kreis {
    public static void main(String[] args) {
        System.out.println("It works!");
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
        return "Point { x: " + this.x + " y: " + this.y + " }";
    }
}

class KreisVererb extends Point {
    float radius;
    
    public KreisVererb() {
        super();
        this.radius = 1.f;
    }
}





