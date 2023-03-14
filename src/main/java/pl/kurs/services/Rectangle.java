package pl.kurs.services;

import pl.kurs.util.ShapeFactory;

import java.util.Objects;

public class Rectangle implements Shape {
    private double a;
    private double b;

    private Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public static Rectangle create(double a, double b) {
        String cacheKey = "Rectangle:" + a + ":" + b;
        Shape shape = ShapeFactory.getInstance().getShapeFromCache(cacheKey);
        if (shape == null) {
            shape = new Rectangle(a, b);
            ShapeFactory.getInstance().putShapeInCache(cacheKey, shape);
        }
        return (Rectangle) shape;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * a + 2 * b;
    }

    @Override
    public double calculateArea() {
        return a * b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.getA(), getA()) == 0 && Double.compare(rectangle.getB(), getB()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getA(), getB());
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
