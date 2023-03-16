package pl.kurs.services;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.domain.Circle;
import pl.kurs.domain.Rectangle;
import pl.kurs.domain.Square;
import pl.kurs.exceptions.FigureNotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        //when
        Shape figureWithTheGreatestArea = ShapeService.findFigureWithTheGreatestArea(figures);
        //then
        assertEquals(Circle.class, figureWithTheGreatestArea.getClass());
        assertEquals(50.2655, figureWithTheGreatestArea.calculateArea(), 0.0001);
    }

    @Test(expected = FigureNotFoundException.class)
    public void shouldThrowFigureNotFoundExceptionWhenListIsEmpty() throws FigureNotFoundException {
        //given
        List<Shape> list = new ArrayList<>();
        //when
        ShapeService.findFigureWithTheGreatestArea(list);
    }

    @Test(expected = FigureNotFoundException.class)
    public void shouldThrowFigureNotFoundExceptionWhenListIsNull() throws FigureNotFoundException {
        //given
        List<Shape> list = null;
        //when
        ShapeService.findFigureWithTheGreatestArea(list);
    }

    @Test
    public void shouldReturnRectangle7x5AsAFigureWithTheGreatestPerimeter() throws FigureNotFoundException {
        //when
        Shape figureWithTheGreatestPerimeter = ShapeService.findFigureWithTheGreatestPerimeter(figures, Rectangle.class);
        //then
        assertEquals(figures.get(4), figureWithTheGreatestPerimeter);
        assertEquals(24, figureWithTheGreatestPerimeter.calculatePerimeter(), 0);
    }

    @Test(expected = FigureNotFoundException.class)
    public void shouldThrowFigureNotFoundExceptionWhenNoFigureOnAList() throws FigureNotFoundException {
        //given
        List<Shape> list = new ArrayList<>();
        //when
        ShapeService.findFigureWithTheGreatestPerimeter(list, Circle.class);
    }

    @Test
    public void testExportImportShapesToJson() throws IOException {
        // given
        Shape square = Square.create(5.0);
        Shape rectangle = Rectangle.create(4.0, 5.0);
        Shape circle = Circle.create(6.0);

        List<Shape> figures = new ArrayList<>();
        figures.add(square);
        figures.add(rectangle);
        figures.add(circle);

        File file = new File("figures.json");

        // when
        ShapeService.exportShapesListToJson(figures, file);
        List<Shape> importedFigures = ShapeService.importShapesFromJson(file);

        // then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(importedFigures).hasSize(3);
        sa.assertThat(importedFigures.get(0)).isEqualTo(square);
        sa.assertThat(importedFigures.get(1)).isEqualTo(rectangle);
        sa.assertThat(importedFigures.get(2)).isEqualTo(circle);
        sa.assertAll();

        // cleanup
        Files.deleteIfExists(Paths.get("figures.json"));

    }


}