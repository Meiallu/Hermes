package me.meiallu.hermes.serializer;

import java.io.*;
import java.util.Base64;

public class BaseSerializer {

    public static String serialize(Object object) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

            objectStream.writeObject(object);
            objectStream.close();

            byte[] bytes = byteStream.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static Object deserialize(String string) {
        try {
            byte[] byteArray = Base64.getDecoder().decode(string);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
            ObjectInputStream objectStream = new ObjectInputStream(inputStream);

            return objectStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
