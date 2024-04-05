package me.meiallu.hermes.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class JsonSerializer {

    public static JsonObject serialize(Object object) {
        try {
            JsonObject jsonObject = new JsonObject();

            for (Field mainField : object.getClass().getDeclaredFields()) {
                mainField.setAccessible(true);

                switch (mainField.getType().getSimpleName()) {
                    case "boolean":
                        jsonObject.addProperty(mainField.getName(), mainField.getBoolean(object));
                        break;
                    case "char":
                        jsonObject.addProperty(mainField.getName(), mainField.getChar(object));
                        break;
                    case "byte", "short", "int", "long", "float", "double":
                        jsonObject.addProperty(mainField.getName(), (Number) mainField.get(object));
                        break;
                    case "boolean[]":
                        boolean[] booleanArray = (boolean[]) mainField.get(object);
                        JsonArray jsonBooleanArray = new JsonArray();

                        if (booleanArray != null) {
                            for (boolean loopBoolean : booleanArray)
                                jsonBooleanArray.add(loopBoolean);

                            jsonObject.add(mainField.getName(), jsonBooleanArray);
                        }
                        break;
                    case "char[]":
                        char[] charArray = (char[]) mainField.get(object);
                        JsonArray jsonCharArray = new JsonArray();

                        if (charArray != null) {
                            for (char loopChar : charArray)
                                jsonCharArray.add(loopChar);

                            jsonObject.add(mainField.getName(), jsonCharArray);
                        }
                        break;
                    case "byte[]", "short[]", "int[]", "long[]", "float[]", "double[]":
                        double[] doubleArray = (double[]) mainField.get(object);
                        JsonArray jsonDoubleArray = new JsonArray();

                        if (doubleArray != null) {
                            for (double loopDouble : doubleArray)
                                jsonDoubleArray.add(loopDouble);

                            jsonObject.add(mainField.getName(), jsonDoubleArray);
                        }
                        break;
                    default:
                        if (mainField.getType().isArray()) {
                            Object[] objectArray = (Object[]) mainField.get(object);
                            JsonArray jsonObjectArray = new JsonArray();

                            if (objectArray != null && objectArray[0] != null) {
                                for (Object loopObject : objectArray) {
                                    JsonObject loopJsonObject = serialize(loopObject);
                                    jsonObjectArray.add(loopJsonObject);
                                }

                                jsonObject.add(mainField.getName(), jsonObjectArray);
                            }
                        } else {
                            JsonObject subObject = serialize(mainField.get(object));
                            jsonObject.add(mainField.getName(), subObject);
                        }
                        break;
                }
            }

            return jsonObject;
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static <T> T deserialize(String string, Class<T> clazz) {
        try {
            T object = new ObjenesisStd().newInstance(clazz);
            JsonObject jsonObject = JsonParser.parseString(string).getAsJsonObject();

            for (Field mainField : clazz.getDeclaredFields()) {
                mainField.setAccessible(true);

                switch (mainField.getType().getSimpleName()) {
                    case "boolean":
                        mainField.set(object, jsonObject.get(mainField.getName()).getAsBoolean());
                        break;
                    case "char":
                        mainField.set(object, jsonObject.get(mainField.getName()).getAsCharacter());
                        break;
                    case "byte":
                        mainField.set(object, jsonObject.get(mainField.getName()).getAsByte());
                        break;
                    case "short":
                        mainField.set(object, jsonObject.get(mainField.getName()).getAsShort());
                        break;
                    case "int":
                        mainField.set(object, jsonObject.get(mainField.getName()).getAsInt());
                        break;
                    case "long":
                        mainField.set(object, jsonObject.get(mainField.getName()).getAsLong());
                        break;
                    case "float":
                        mainField.set(object, jsonObject.get(mainField.getName()).getAsFloat());
                        break;
                    case "double":
                        mainField.set(object, jsonObject.get(mainField.getName()).getAsDouble());
                        break;
                    case "boolean[]":
                        JsonArray jsonBooleanArray = jsonObject.get(mainField.getName()).getAsJsonArray();
                        boolean[] booleanArray = new boolean[jsonBooleanArray.size()];

                        for (int i = 0; i < booleanArray.length; i++)
                            booleanArray[i] = jsonBooleanArray.get(i).getAsBoolean();

                        mainField.set(object, booleanArray);
                        break;
                    case "byte[]":
                        JsonArray jsonByteArray = jsonObject.get(mainField.getName()).getAsJsonArray();
                        byte[] byteArray = new byte[jsonByteArray.size()];

                        for (int i = 0; i < byteArray.length; i++)
                            byteArray[i] = jsonByteArray.get(i).getAsByte();

                        mainField.set(object, byteArray);
                        break;
                    case "short[]":
                        JsonArray jsonShortArray = jsonObject.get(mainField.getName()).getAsJsonArray();
                        short[] shortArray = new short[jsonShortArray.size()];

                        for (int i = 0; i < shortArray.length; i++)
                            shortArray[i] = jsonShortArray.get(i).getAsShort();

                        mainField.set(object, shortArray);
                        break;
                    case "int[]":
                        JsonArray jsonIntegerArray = jsonObject.get(mainField.getName()).getAsJsonArray();
                        int[] integerArray = new int[jsonIntegerArray.size()];

                        for (int i = 0; i < integerArray.length; i++)
                            integerArray[i] = jsonIntegerArray.get(i).getAsInt();

                        mainField.set(object, integerArray);
                        break;
                    case "long[]":
                        JsonArray jsonLongArray = jsonObject.get(mainField.getName()).getAsJsonArray();
                        long[] longArray = new long[jsonLongArray.size()];

                        for (int i = 0; i < longArray.length; i++)
                            longArray[i] = jsonLongArray.get(i).getAsLong();

                        mainField.set(object, longArray);
                        break;
                    case "float[]":
                        JsonArray jsonFloatArray = jsonObject.get(mainField.getName()).getAsJsonArray();
                        float[] floatArray = new float[jsonFloatArray.size()];

                        for (int i = 0; i < floatArray.length; i++)
                            floatArray[i] = jsonFloatArray.get(i).getAsFloat();

                        mainField.set(object, floatArray);
                        break;
                    case "double[]":
                        JsonArray jsonDoubleArray = jsonObject.get(mainField.getName()).getAsJsonArray();
                        double[] doubleArray = new double[jsonDoubleArray.size()];

                        for (int i = 0; i < doubleArray.length; i++)
                            doubleArray[i] = jsonDoubleArray.get(i).getAsDouble();

                        mainField.set(object, doubleArray);
                        break;
                    case "char[]":
                        JsonArray jsonCharArray = jsonObject.get(mainField.getName()).getAsJsonArray();
                        char[] charArray = new char[jsonCharArray.size()];

                        for (int i = 0; i < charArray.length; i++)
                            charArray[i] = jsonCharArray.get(i).getAsCharacter();

                        mainField.set(object, charArray);
                        break;
                    default:
                        if (mainField.getType().isArray()) {
                            JsonElement jsonObjectField = jsonObject.get(mainField.getName());

                            if (jsonObjectField != null) {
                                JsonArray jsonObjectArray = jsonObjectField.getAsJsonArray();
                                Class<?> componentType = mainField.getType().getComponentType();

                                Object[] objectArray = (Object[]) Array.newInstance(componentType, jsonObjectArray.size());

                                for (int i = 0; i < objectArray.length; i++) {
                                    Object deserializedObject = deserialize(jsonObjectArray.get(i).getAsJsonObject().toString(), componentType);
                                    objectArray[i] = deserializedObject;
                                }

                                mainField.set(object, objectArray);
                            }
                            break;
                        } else {
                            Object subObject = deserialize(jsonObject.get(mainField.getName()).getAsString(), mainField.getClass());
                            mainField.set(object, subObject);
                        }
                        break;
                }
            }

            return object;
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }
}