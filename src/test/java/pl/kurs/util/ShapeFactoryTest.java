package pl.kurs.util;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.services.Circle;
import pl.kurs.services.Rectangle;
import pl.kurs.services.Square;

import static org.junit.Assert.*;

public class ShapeFactoryTest {

    private ShapeFactory shapeFactory;

    @Before
    public void setUp() {
        shapeFactory = ShapeFactory.getInstance();
    }

    @Test
    public void shouldReturnSquareFromCacheWhenSquareIsAlreadyInCache() {
        //given
        double a = 5;
        String cacheKey = "Square: " + a;

        //when
        Square square = shapeFactory.createSquare(a);
        Square square2 = shapeFactory.createSquare(a);

        //then
        assertNotNull(square);
        Assertions.assertThat(square == square2);
    }

    @Test
    public void shouldReturnRectangleFromCacheWhenRectangleIsAlreadyInCache() {
        //given
        double a = 5;
        double b = 6;
        String cacheKey = "Rectangle: " + a;

        //when
        Rectangle rectangle = shapeFactory.createRectangle(a,b);
        Rectangle rectangle2 = shapeFactory.createRectangle(a,b);

        //then
        assertNotNull(rectangle);
        Assertions.assertThat(rectangle == rectangle2);
    }

    @Test
    public void shouldReturnCircleFromCacheWhenCircleIsAlreadyInCache() {
        //given
        double r = 5;
        String cacheKey = "Circle: " + r;

        //when
        Circle circle = shapeFactory.createCircle(r);
        Circle circle2 = shapeFactory.createCircle(r);

        //then
        assertNotNull(circle);
        Assertions.assertThat(circle == circle2);
    }

}