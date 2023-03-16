package pl.kurs.util;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.domain.Circle;
import pl.kurs.domain.Rectangle;
import pl.kurs.services.Shape;
import pl.kurs.domain.Square;

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
        Rectangle rectangle = shapeFactory.createRectangle(a,b);
        Rectangle rectangle2 = shapeFactory.createRectangle(a,b);

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
    public void checkForCalculateAreaMethod() {
        //given
        Square square = shapeFactory.createSquare(5);
        Circle circle = shapeFactory.createCircle(6);
        Rectangle rectangle = shapeFactory.createRectangle(5,6);

        //when
        double squareArea = square.calculateArea();
        double circleArea = circle.calculateArea();
        double rectangleArea = rectangle.calculateArea();

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(squareArea).isEqualTo(25);
        sa.assertThat(circleArea).isEqualTo(Math.PI * Math.pow(6, 2));
        sa.assertThat(rectangleArea).isEqualTo(30);
        sa.assertAll();
    }

    @Test
    public void checkForCalculatePerimeterMethod() {
        //given
        Square square = shapeFactory.createSquare(5);
        Circle circle = shapeFactory.createCircle(6);
        Rectangle rectangle = shapeFactory.createRectangle(5,6);

        //when
        double squarePerimeter = square.calculatePerimeter();
        double circlePerimeter = circle.calculatePerimeter();
        double rectanglePerimeter = rectangle.calculatePerimeter();

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(squarePerimeter).isEqualTo(20);
        sa.assertThat(circlePerimeter).isEqualTo(2 * Math.PI * 6);
        sa.assertThat(rectanglePerimeter).isEqualTo(22);
        sa.assertAll();
    }

}