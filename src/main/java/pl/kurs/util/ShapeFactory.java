package pl.kurs.util;

import pl.kurs.services.Circle;
import pl.kurs.services.Rectangle;
import pl.kurs.services.Shape;
import pl.kurs.services.Square;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
    private static ShapeFactory INSTANCE;
    private Map<String, Shape> shapeCache;

    private Shape shape;

    private ShapeFactory() {
        shapeCache = new HashMap<>();
    }

    public static ShapeFactory getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ShapeFactory();
        return INSTANCE;
    }

    public Square createSquare(double a) {
        String cacheKey = "Square: " + a;
        Shape shape = shapeCache.get(cacheKey);
        if (shape == null) {
            shape = new Square(a);
            shapeCache.put(cacheKey, shape);
        }
        return (Square) shape;
    }

    public Rectangle createRectangle(double length, double width) {
        String cacheKey = "Rectangle:" + length + ":" + width;
        Shape shape = shapeCache.get(cacheKey);
        if (shape == null) {
            shape = new Rectangle(length, width);
            shapeCache.put(cacheKey, shape);
        }
        return (Rectangle) shape;
    }

    public Circle createCircle(double radius) {
        String cacheKey = "Circle:" + radius;
        Shape shape = shapeCache.get(cacheKey);
        if (shape == null) {
            shape = new Circle(radius);
            shapeCache.put(cacheKey, shape);
        }
        return (Circle) shape;
    }
}
