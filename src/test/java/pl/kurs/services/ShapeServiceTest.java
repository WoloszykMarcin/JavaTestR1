package pl.kurs.services;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.domain.Circle;
import pl.kurs.domain.Rectangle;
import pl.kurs.domain.Square;
import pl.kurs.exceptions.FigureNotFoundException;
import pl.kurs.util.ShapeFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void testExportShapesListToJson() throws IOException {
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

        // then
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        // Assert that the contents of the file match the expected JSON
        String expectedJson = "[{\"type\":\"square\",\"side\":5.0},{\"type\":\"rectangle\",\"length\":4.0,\"height\":5.0},{\"type\":\"circle\",\"radius\":6.0}]";
        String actualJson = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        assertEquals(expectedJson, actualJson);

        // cleanup
        Files.deleteIfExists(Paths.get("figures.json"));
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

    @Test
    public void checkForCalculateAreaMethod() {
        //given
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        Square square = shapeFactory.createSquare(5);
        Circle circle = shapeFactory.createCircle(6);
        Rectangle rectangle = shapeFactory.createRectangle(5, 6);

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
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        Square square = shapeFactory.createSquare(5);
        Circle circle = shapeFactory.createCircle(6);
        Rectangle rectangle = shapeFactory.createRectangle(5, 6);

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