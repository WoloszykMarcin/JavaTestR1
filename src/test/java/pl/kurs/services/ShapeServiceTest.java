package pl.kurs.services;

import org.junit.Before;
import org.junit.Test;
import pl.kurs.exceptions.FigureNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShapeServiceTest {

    private List<Shape> figures;

    @Before
    public void setUp() {
        figures = new ArrayList<>();
        figures.add(Circle.create(2));
        figures.add(Circle.create(4));
        figures.add(Square.create(2));
        figures.add(Square.create(7));
        figures.add(Rectangle.create(7, 5));
        figures.add(Rectangle.create(5, 4));
        figures.add(null);
    }

    @Test
    public void figureWithTheGreatestAreaShouldBeCircle() throws FigureNotFoundException {
        Shape figureWithTheGreatestArea = ShapeService.findFigureWithTheGreatestArea(figures);
        assertEquals(Circle.class, figureWithTheGreatestArea.getClass());
        assertEquals(50.2655, figureWithTheGreatestArea.calculateArea(), 0.0001);
    }

    @Test(expected = FigureNotFoundException.class)
    public void shouldThrowFigureNotFoundExceptionWhenListIsEmpty() throws FigureNotFoundException {
        List<Shape> list = new ArrayList<>();
        ShapeService.findFigureWithTheGreatestArea(list);
    }

    @Test(expected = FigureNotFoundException.class)
    public void shouldThrowFigureNotFoundExceptionWhenListIsNull() throws FigureNotFoundException {
        List<Shape> list = null;
        ShapeService.findFigureWithTheGreatestArea(list);
    }

    @Test
    public void shouldReturnRectangle7x5AsAFigureWithTheGreatestPerimeter() throws FigureNotFoundException {
        Shape figureWithTheGreatestPerimeter = ShapeService.findFigureWithTheGreatestPerimeter(figures, Rectangle.class);
        assertEquals(figures.get(4), figureWithTheGreatestPerimeter);
        assertEquals(24, figureWithTheGreatestPerimeter.calculatePerimeter(), 0);
    }

    @Test(expected = FigureNotFoundException.class)
    public void shouldThrowFigureNotFoundExceptionWhenNoFigureOnAList() throws FigureNotFoundException {
        List<Shape> list = new ArrayList<>();
        ShapeService.findFigureWithTheGreatestPerimeter(list, Circle.class);
    }




}