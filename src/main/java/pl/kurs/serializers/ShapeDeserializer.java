package pl.kurs.serializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.kurs.domain.Circle;
import pl.kurs.domain.Rectangle;
import pl.kurs.services.Shape;
import pl.kurs.domain.Square;

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
            return Circle.create(radius);
        } else if ("rectangle".equals(type)) {
            double width = jn.get("length").asDouble();
            double height = jn.get("height").asDouble();
            return Rectangle.create(width, height);
        } else if ("square".equals(type)) {
            double side = jn.get("side").asDouble();
            return Square.create(side);
        } else {
            throw new IOException("Invalid shape type: " + type);
        }
    }
}
