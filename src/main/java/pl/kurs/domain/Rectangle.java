package pl.kurs.domain;

import pl.kurs.services.Shape;
import pl.kurs.util.ShapeFactory;

public class Rectangle implements Shape {
    private double a;
    private double b;

    private Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public static Rectangle create(double a, double b) {
        if (a <= 0 || b <= 0)
            throw new IllegalArgumentException("height and width of rectangle must be positive");

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


    public double getB() {
        return b;
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
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
