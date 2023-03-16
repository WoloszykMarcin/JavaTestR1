package pl.kurs.services;

import pl.kurs.util.ShapeFactory;

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

    @Override
    public double calculatePerimeter() {
        return 2 * PI * r;
    }

    @Override
    public double calculateArea() {
        return PI * Math.pow(r, 2);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + r +
                '}';
    }
}
