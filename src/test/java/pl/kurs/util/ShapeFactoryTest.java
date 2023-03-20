package pl.kurs.util;

import org.junit.Before;
import org.junit.Test;
import pl.kurs.domain.Circle;
import pl.kurs.domain.Rectangle;
import pl.kurs.domain.Square;
import pl.kurs.services.Shape;

import static org.junit.Assert.*;

public class ShapeFactoryTest {

    private ShapeFactory shapeFactory;

    @Before
    public void setUp() {
        shapeFactory = ShapeFactory.getInstance();
    }

    @Test
    public void shouldCreateOnlyOneInstanceOfShapeFactory() {
        //when
        ShapeFactory shapeFactory2 = ShapeFactory.getInstance();

        //then
        assertTrue(shapeFactory == shapeFactory2);
    }

    @Test
    public void shouldReturnSquareFromCacheWhenSquareIsAlreadyInCache() {
        //given
        double a = 5;

        //when
        Square square = shapeFactory.createSquare(a);
        Square square2 = shapeFactory.createSquare(a);

        //then
        assertNotNull("Square shouldn't be null", square);
        assertSame(square, square2);
    }

    @Test
    public void shouldReturnRectangleFromCacheWhenRectangleIsAlreadyInCache() {
        //given
        double a = 5;
        double b = 6;

        //when
        Rectangle rectangle = shapeFactory.createRectangle(a, b);
        Rectangle rectangle2 = shapeFactory.createRectangle(a, b);

        //then
        assertNotNull("Rectangle should not be null", rectangle);
        assertSame(rectangle, rectangle2);
    }

    @Test
    public void shouldReturnCircleFromCacheWhenCircleIsAlreadyInCache() {
        //given
        double r = 5;

        //when
        Circle circle = shapeFactory.createCircle(r);
        Circle circle2 = shapeFactory.createCircle(r);

        //then
        assertNotNull("Circle should not be null", circle);
        assertSame(circle, circle2);
    }

    @Test
    public void shouldGetShapeFromCache() {
        //given
        String cacheKey = "Square: 5.0";
        Square square = shapeFactory.createSquare(5.0);

        //when
        Shape actualFigure = shapeFactory.getShapeFromCache(cacheKey);

        //then
        assertEquals("The returned shape should be the same as the created one", square, actualFigure);
    }

    @Test
    public void shouldPutShapeInCache() {
        //given
        String cacheKey = "Square: 5.0";
        Square square = shapeFactory.createSquare(5.0);

        //when
        Shape actualFigure = shapeFactory.getShapeFromCache(cacheKey);

        //then
        assertEquals("The shape retrieved from the cache should be the same as the one that was put in", square, actualFigure);
    }

    @Test
    public void shouldCreateCircleWhenNotInCache() {
        //given
        double r = 5.0;
        double r2 = 10.0;

        //when
        Circle circle = shapeFactory.createCircle(r);
        Circle circle2 = shapeFactory.createCircle(r2);
        Circle circle3 = shapeFactory.createCircle(r2);

        //then
        assertNotNull(circle);
        assertEquals(r, circle.getR(), 0.0);
        assertTrue(shapeFactory.getShapeFromCache("Circle:5.0") == circle);

        assertNotNull(circle2);
        assertEquals(r2, circle2.getR(), 0.0);
        assertTrue(shapeFactory.getShapeFromCache("Circle:10.0") == circle2);


        assertTrue(shapeFactory.getShapeFromCache("Circle:5.0") == circle);


        assertSame(circle2, circle3);
    }

}