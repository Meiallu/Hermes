package me.meiallu.hermes;

import me.meiallu.hermes.serializer.JsonSerializer;
import me.meiallu.hermes.util.SerializationType;

public class Hermes {

    public static String serialize(SerializationType type, Object object) {
        return (switch (type) {
            case JSON -> JsonSerializer.serialize(object);
            default -> null;
        }).toString();
    }

    public static <T> T deserialize(SerializationType type, String string, Class<T> clazz) {
        return (switch (type) {
            case JSON -> JsonSerializer.deserialize(string, clazz);
            default -> null;
        });
    }
}