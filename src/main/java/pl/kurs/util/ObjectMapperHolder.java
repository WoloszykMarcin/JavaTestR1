package pl.kurs.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.kurs.serializers.ShapeDeserializer;
import pl.kurs.serializers.ShapeSerializer;
import pl.kurs.services.Shape;

import java.text.SimpleDateFormat;

public enum ObjectMapperHolder {
    INSTANCE;

    private final ObjectMapper objectMapper;

    ObjectMapperHolder() {
        this.objectMapper = create();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private static ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));

        ShapeSerializer shapeSerializer = new ShapeSerializer(Shape.class);
        SimpleModule sm1 = new SimpleModule("shape serializer");
        sm1.addSerializer(Shape.class, shapeSerializer);
        ShapeDeserializer shapeDeserializer = new ShapeDeserializer(Shape.class);
        SimpleModule sm2 = new SimpleModule("shape deserializer");
        sm2.addDeserializer(Shape.class, shapeDeserializer);
        mapper.registerModules(sm1, sm2);

        return mapper;

    }
}
