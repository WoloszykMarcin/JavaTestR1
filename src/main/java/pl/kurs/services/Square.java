package pl.kurs.services;

import pl.kurs.util.ShapeFactory;

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
    public String toString() {
        return "Square{" +
                "a=" + a +
                '}';
    }
}
