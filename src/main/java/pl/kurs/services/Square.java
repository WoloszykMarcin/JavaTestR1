package pl.kurs.services;

import pl.kurs.util.ShapeFactory;

import java.util.Objects;

public class Square implements Shape {
    private double a;

    private Square(double a) {
        this.a = a;
    }

    public static Square create(double a) {
        String cacheKey = "Square:" + a;
        Shape shape = ShapeFactory.getInstance().getShapeFromCache(cacheKey);
        if (shape == null) {
            shape = new Square(a);
            ShapeFactory.getInstance().putShapeInCache(cacheKey, shape);
        }
        return (Square) shape;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * a;
    }

    @Override
    public double calculateArea() {
        return Math.pow(a, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square = (Square) o;
        return Double.compare(square.getA(), getA()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getA());
    }

    @Override
    public String toString() {
        return "Square{" +
                "a=" + a +
                '}';
    }
}
