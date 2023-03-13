package pl.kurs;

import pl.kurs.exceptions.FigureNotFoundException;
import pl.kurs.services.*;
import pl.kurs.util.ShapeFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws FigureNotFoundException, IOException {
        Square square = Square.create(2);
        Square square2 = Square.create(2);
        System.out.println(square == square2);

        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        Square sq1 = shapeFactory.createSquare(10);
        Square sq2 = shapeFactory.createSquare(10);
        System.out.println(sq1 == sq2);

        Rectangle rec1 = shapeFactory.createRectangle(10,5);
        Rectangle rec2 = shapeFactory.createRectangle(10,5);
        Rectangle rec3 = shapeFactory.createRectangle(14,5);

        Circle circle = shapeFactory.createCircle(10);
        Circle circle2 = shapeFactory.createCircle(14);

        List<Shape> figureList = List.of(sq1, sq2, rec1, rec2, rec3, circle, circle2);


        Shape figureWithTheGreatestPerimeter = ShapeService.findFigureWithTheGreatestPerimeter(figureList, Square.class);
        System.out.println("figureWithTheGreatestPerimeter = " + figureWithTheGreatestPerimeter.toString());

        Shape figureWithTheGreatestArea = ShapeService.findFigureWithTheGreatestArea(figureList);
        System.out.println("figureWithTheGreatestArea = " + figureWithTheGreatestArea);

        ShapeService.exportShapesListToJson(figureList, new File("src/main/resources/figureList.json"));

        List<Shape> shapes = ShapeService.importShapesFromJson(new File("src/main/resources/figureList.json"));
        System.out.println(shapes);

    }
}
