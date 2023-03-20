package pl.kurs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.kurs.services.Shape;
import pl.kurs.util.ShapeFactory;

import java.util.Objects;

public class Square extends Shape {
    @JsonProperty("side")

    private double side;

    public Square() {
    }

    private Square(double side) {
        this.side = side;
    }

    public static Square create(double a) {
        if (a <= 0)
            throw new IllegalArgumentException("Side length of square must be positive");

        String cacheKey = "Square:" + a;
        Shape shape = ShapeFactory.getInstance().getShapeFromCache(cacheKey);
        if (shape == null) {
            shape = new Square(a);
            ShapeFactory.getInstance().putShapeInCache(cacheKey, shape);
        }
        return (Square) shape;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }

    @Override
    public double calculateArea() {
        return Math.pow(side, 2);
    }

    @Override
    public String getType() {
        return "square";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square = (Square) o;
        return Double.compare(square.getSide(), getSide()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSide());
    }

    @Override
    public String toString() {
        return "Square{" +
                "a=" + side +
                '}';
    }
}
