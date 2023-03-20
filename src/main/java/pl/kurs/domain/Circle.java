package pl.kurs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.kurs.services.Shape;
import pl.kurs.util.ShapeFactory;

import java.util.Objects;

import static java.lang.Math.PI;

public class Circle extends Shape {
    @JsonProperty("radius")
    private double radius;

    public Circle() {
    }

    private Circle(double radius) {
        this.radius = radius;
    }

    public static Circle create(double r) {
        if (r <= 0)
            throw new IllegalArgumentException("Radius of circle must be positive");

        String cacheKey = "Circle:" + r;
        Shape shape = ShapeFactory.getInstance().getShapeFromCache(cacheKey);
        if (shape == null) {
            shape = new Circle(r);
            ShapeFactory.getInstance().putShapeInCache(cacheKey, shape);
        }
        return (Circle) shape;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * PI * radius;
    }

    @Override
    public double calculateArea() {
        return PI * Math.pow(radius, 2);
    }

    @JsonProperty("type")
    @Override
    public String getType() {
        return "circle";
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.getRadius(), getRadius()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRadius());
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + radius +
                '}';
    }
}
