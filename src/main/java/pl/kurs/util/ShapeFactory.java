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

    public Shape getShapeFromCache(String cacheKey) {
        return shapeCache.get(cacheKey);
    }

    public void putShapeInCache(String cacheKey, Shape shape) {
        shapeCache.put(cacheKey, shape);
    }


    public Square createSquare(double a) {
        String cacheKey = "Square: " + a;
        Shape shape = shapeCache.get(cacheKey);
        if (shape == null) {
            shape = Square.create(a);
            shapeCache.put(cacheKey, shape);
        }
        return (Square) shape;
    }

    public Rectangle createRectangle(double length, double width) {
        String cacheKey = "Rectangle:" + length + ":" + width;
        Shape shape = shapeCache.get(cacheKey);
        if (shape == null) {
            shape = Rectangle.create(length, width);
            shapeCache.put(cacheKey, shape);
        }
        return (Rectangle) shape;
    }

    public Circle createCircle(double radius) {
        String cacheKey = "Circle:" + radius;
        Shape shape = shapeCache.get(cacheKey);
        if (shape == null) {
            shape = Circle.create(radius);
            shapeCache.put(cacheKey, shape);
        }
        return (Circle) shape;
    }
}
