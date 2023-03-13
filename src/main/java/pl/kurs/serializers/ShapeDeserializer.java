package pl.kurs.serializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.kurs.services.Circle;
import pl.kurs.services.Rectangle;
import pl.kurs.services.Shape;
import pl.kurs.services.Square;

import java.io.IOException;

public class ShapeDeserializer extends StdDeserializer<Shape> {
    public ShapeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shape deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode jn = jsonParser.getCodec().readTree(jsonParser);

        String type = jn.get("type").asText();

        if ("circle".equals(type)) {
            double radius = jn.get("radius").asDouble();
            return new Circle(radius);
        } else if ("rectangle".equals(type)) {
            double width = jn.get("width").asDouble();
            double height = jn.get("height").asDouble();
            return new Rectangle(width, height);
        } else if ("square".equals(type)) {
            double side = jn.get("side").asDouble();
            return new Square(side);
        } else {
            throw new IOException("Invalid shape type: " + type);
        }
    }
}
