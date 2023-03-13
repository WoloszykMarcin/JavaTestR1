package pl.kurs.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.exceptions.FigureNotFoundException;
import pl.kurs.util.ObjectMapperHolder;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ShapeService {
    public static Shape findFigureWithTheGreatestArea(List<Shape> list) throws FigureNotFoundException {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparing(Shape::calculateArea))
                .orElseThrow(() -> new FigureNotFoundException("Figure not found!"));
    }

    public static Shape findFigureWithTheGreatestPerimeter(List<Shape> list, Class<? extends Shape> shapeClass) throws FigureNotFoundException {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(shapeClass::isInstance)
                .max(Comparator.comparing(Shape::calculatePerimeter))
                .orElseThrow(() -> new FigureNotFoundException("Figure not found!"));
    }

    public static void exportShapesListToJson(List<Shape> list, File file) throws IOException {
        ObjectMapper objectMapper = ObjectMapperHolder.INSTANCE.getObjectMapper();
        objectMapper.writeValue(file, list);

    }

    public static List<Shape> importShapesFromJson(File file) throws IOException {
        ObjectMapper objectMapper = ObjectMapperHolder.INSTANCE.getObjectMapper();
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Shape.class));
    }
}
