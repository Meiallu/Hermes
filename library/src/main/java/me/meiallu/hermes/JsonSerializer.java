package me.meiallu.hermes;

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
                        jsonObject.add(mainField.getName(), mainField.getBoolean(object));
                        break;
                    case "char":
                        jsonObject.add(mainField.getName(), mainField.getChar(object));
                        break;
                    case "byte", "short", "int", "long", "float", "double":
                        jsonObject.add(mainField.getName(), (Number) mainField.get(object));
                        break;
                    case "boolean[]":
                        boolean[] booleanArray = (boolean[]) mainField.get(object);
                        JsonArray jsonBooleanArray = new JsonArray();

                        if (booleanArray != null)
                            for (boolean loopBoolean : booleanArray)
                                jsonBooleanArray.add(loopBoolean);

                        jsonObject.add(mainField.getName(), jsonBooleanArray);
                        break;
                    case "char[]":
                        char[] charArray = (char[]) mainField.get(object);
                        JsonArray jsonCharArray = new JsonArray();

                        if (charArray != null)
                            for (char loopChar : charArray)
                                jsonCharArray.add(loopChar);

                        jsonObject.add(mainField.getName(), jsonCharArray);
                        break;
                    case "byte[]", "short[]", "int[]", "long[]", "float[]", "double[]":
                        double[] doubleArray = (double[]) mainField.get(object);
                        JsonArray jsonDoubleArray = new JsonArray();

                        if (doubleArray != null)
                            for (double loopDouble : doubleArray)
                                jsonDoubleArray.add(loopDouble);

                        jsonObject.add(mainField.getName(), jsonDoubleArray);
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
                            }

                            jsonObject.add(mainField.getName(), jsonObjectArray);
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
            JsonObject jsonObject = new JsonObject();

            for (Field mainField : clazz.getDeclaredFields()) {
                mainField.setAccessible(true);

                switch (mainField.getType().getSimpleName()) {
                    case "boolean":
                        mainField.set(object, jsonObject.getBoolean(mainField.getName()));
                        break;
                    case "char":
                        mainField.set(object, jsonObject.getChar(mainField.getName()));
                        break;
                    case "byte":
                        mainField.set(object, jsonObject.getByte(mainField.getName()));
                        break;
                    case "short":
                        mainField.set(object, jsonObject.getShort(mainField.getName()));
                        break;
                    case "int":
                        mainField.set(object, jsonObject.getInt(mainField.getName()));
                        break;
                    case "long":
                        mainField.set(object, jsonObject.getLong(mainField.getName()));
                        break;
                    case "float":
                        mainField.set(object, jsonObject.getFloat(mainField.getName()));
                        break;
                    case "double":
                        mainField.set(object, jsonObject.getDouble(mainField.getName()));
                        break;
                    case "boolean[]":
                        JsonArray jsonBooleanArray = jsonObject.getArray(mainField.getName());
                        boolean[] booleanArray = new boolean[jsonBooleanArray.size()];

                        for (int i = 0; i < booleanArray.length; i++)
                            booleanArray[i] = jsonBooleanArray.getBoolean(i);

                        mainField.set(object, booleanArray);
                        break;
                    case "byte[]":
                        JsonArray jsonByteArray = jsonObject.getArray(mainField.getName());
                        byte[] byteArray = new byte[jsonByteArray.size()];

                        for (int i = 0; i < byteArray.length; i++)
                            byteArray[i] = jsonByteArray.getByte(i);

                        mainField.set(object, byteArray);
                        break;
                    case "short[]":
                        JsonArray jsonShortArray = jsonObject.getArray(mainField.getName());
                        short[] shortArray = new short[jsonShortArray.size()];

                        for (int i = 0; i < shortArray.length; i++)
                            shortArray[i] = jsonShortArray.getShort(i);

                        mainField.set(object, shortArray);
                        break;
                    case "int[]":
                        JsonArray jsonIntegerArray = jsonObject.getArray(mainField.getName());
                        int[] integerArray = new int[jsonIntegerArray.size()];

                        for (int i = 0; i < integerArray.length; i++)
                            integerArray[i] = jsonIntegerArray.getInt(i);

                        mainField.set(object, integerArray);
                        break;
                    case "long[]":
                        JsonArray jsonLongArray = jsonObject.getArray(mainField.getName());
                        long[] longArray = new long[jsonLongArray.size()];

                        for (int i = 0; i < longArray.length; i++)
                            longArray[i] = jsonLongArray.getLong(i);

                        mainField.set(object, longArray);
                        break;
                    case "float[]":
                        JsonArray jsonFloatArray = jsonObject.getArray(mainField.getName());
                        float[] floatArray = new float[jsonFloatArray.size()];

                        for (int i = 0; i < floatArray.length; i++)
                            floatArray[i] = jsonFloatArray.getFloat(i);

                        mainField.set(object, floatArray);
                        break;
                    case "double[]":
                        JsonArray jsonDoubleArray = jsonObject.getArray(mainField.getName());
                        double[] doubleArray = new double[jsonDoubleArray.size()];

                        for (int i = 0; i < doubleArray.length; i++)
                            doubleArray[i] = jsonDoubleArray.getDouble(i);

                        mainField.set(object, doubleArray);
                        break;
                    case "char[]":
                        JsonArray jsonCharArray = jsonObject.getArray(mainField.getName());
                        char[] charArray = new char[jsonCharArray.size()];

                        for (int i = 0; i < charArray.length; i++)
                            charArray[i] = jsonCharArray.getChar(i);

                        mainField.set(object, charArray);
                        break;
                    default:
                        if (mainField.getType().isArray()) {
                            JsonArray jsonObjectArray = jsonObject.getArray(mainField.getName());

                            if (jsonObjectArray != null) {
                                Class<?> componentType = mainField.getType().getComponentType();
                                Object[] objectArray = (Object[]) Array.newInstance(componentType, jsonObjectArray.size());

                                for (int i = 0; i < objectArray.length; i++) {
                                    Object deserializedObject = deserialize(jsonObjectArray.getObject(i).toString(), componentType);
                                    objectArray[i] = deserializedObject;
                                }

                                mainField.set(object, objectArray);
                            }
                            break;
                        } else {
                            Object subObject = deserialize(jsonObject.getString(mainField.getName()), mainField.getClass());
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