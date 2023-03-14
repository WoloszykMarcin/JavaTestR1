package pl.kurs.services;

import pl.kurs.util.ShapeFactory;

import java.util.Objects;

public class Circle implements Shape {
    private double r;

    private Circle(double r) {
        this.r = r;
    }

    public static Circle create(double r) {
        String cacheKey = "Circle:" + r;
        Shape shape = ShapeFactory.getInstance().getShapeFromCache(cacheKey);
        if (shape == null) {
            shape = new Circle(r);
            ShapeFactory.getInstance().putShapeInCache(cacheKey, shape);
        }
        return (Circle) shape;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * PI * r;
    }

    @Override
    public double calculateArea() {
        return PI * Math.pow(r, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.getR(), getR()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getR());
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + r +
                '}';
    }
}
