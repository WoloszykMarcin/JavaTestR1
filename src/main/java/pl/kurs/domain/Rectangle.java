package pl.kurs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.kurs.services.Shape;
import pl.kurs.util.ShapeFactory;

import java.util.Objects;

public class Rectangle extends Shape {
    @JsonProperty("length")
    private double length;
    @JsonProperty("width")
    private double width;

    public Rectangle() {
    }

    private Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public static Rectangle create(double a, double b) {
        if (a <= 0 || b <= 0)
            throw new IllegalArgumentException("length and width of rectangle must be positive");

        String cacheKey = "Rectangle:" + a + ":" + b;
        Shape shape = ShapeFactory.getInstance().getShapeFromCache(cacheKey);
        if (shape == null) {
            shape = new Rectangle(a, b);
            ShapeFactory.getInstance().putShapeInCache(cacheKey, shape);
        }
        return (Rectangle) shape;
    }

    public double getLength() {
        return length;
    }


    public double getWidth() {
        return width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * length + 2 * width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @JsonProperty("type")
    @Override
    public String getType() {
        return "rectangle";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.getLength(), getLength()) == 0 && Double.compare(rectangle.getWidth(), getWidth()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLength(), getWidth());
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + length +
                ", b=" + width +
                '}';
    }
}
