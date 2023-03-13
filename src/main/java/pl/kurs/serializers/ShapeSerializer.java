package pl.kurs.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.kurs.services.Circle;
import pl.kurs.services.Rectangle;
import pl.kurs.services.Shape;
import pl.kurs.services.Square;

import java.io.IOException;

public class ShapeSerializer extends StdSerializer<Shape> {

    public ShapeSerializer(Class<Shape> t) {
        super(t);
    }

    @Override
    public void serialize(Shape shape, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", shape.getClass().getSimpleName().toLowerCase());

        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            jsonGenerator.writeNumberField("radius", circle.getR());
        } else if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            jsonGenerator.writeNumberField("width", rectangle.getA());
            jsonGenerator.writeNumberField("height", rectangle.getB());
        } else if (shape instanceof Square) {
            Square square = (Square) shape;
            jsonGenerator.writeNumberField("side", square.getA());
        }

        jsonGenerator.writeEndObject();
    }
}
