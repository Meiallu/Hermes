package me.meiallu.hermes;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class JsonObject implements JsonElement {

    private final HashMap<String, JsonElement> elements;

    public JsonElement add(String key, JsonElement element) {
        return elements.put(key, element);
    }

    public JsonElement add(String key, String string) {
        return elements.put(key, new JsonPrimitive(string));
    }

    public JsonElement add(String key, Number number) {
        return elements.put(key, new JsonPrimitive(number));
    }

    public JsonElement add(String key, Boolean bool) {
        return elements.put(key, new JsonPrimitive(bool));
    }

    public JsonElement add(String key, Character character) {
        return elements.put(key, new JsonPrimitive(character));
    }

    public JsonElement remove(String key) {
        return elements.remove(key);
    }

    public JsonObject getObject(String key) {
        return (JsonObject) elements.get(key);
    }

    public JsonArray getArray(String key) {
        return (JsonArray) elements.get(key);
    }

    public JsonPrimitive getPrimitive(String key) {
        return (JsonPrimitive) elements.get(key);
    }

    public String getString(String key) {
        return getPrimitive(key).getString();
    }

    public Byte getByte(String key) {
        return getPrimitive(key).getByte();
    }

    public Short getShort(String key) {
        return getPrimitive(key).getShort();
    }

    public Integer getInt(String key) {
        return getPrimitive(key).getInt();
    }

    public Long getLong(String key) {
        return getPrimitive(key).getLong();
    }

    public Float getFloat(String key) {
        return getPrimitive(key).getFloat();
    }

    public Double getDouble(String key) {
        return getPrimitive(key).getDouble();
    }

    public Character getChar(String key) {
        return getPrimitive(key).getChar();
    }

    public Boolean getBoolean(String key) {
        return getPrimitive(key).getBoolean();
    }

    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        writer.write('{');

        Iterator<String> iterator = elements.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            JsonElement element = elements.get(key);

            writer.write('"');
            writer.write(key);
            writer.write("\":");
            writer.write(element.toString());

            if (iterator.hasNext())
                writer.write(',');
        }

        writer.write('}');
        return writer.toString();
    }

    private void addValue(StringBuilder key, StringBuilder value) {
        String keyString = key.toString();
        String valueString = value.toString();

        switch (valueString.charAt(0)) {
            case '{':
                JsonObject object = new JsonObject(valueString);
                add(keyString, object);
                break;
            case '[':
                JsonArray array = new JsonArray(valueString);
                add(keyString, array);
                break;
            default:
                JsonPrimitive primitive = JsonPrimitive.parsePrimitive(valueString);
                add(keyString, primitive);
                break;
        }
    }

    public JsonObject() {
        this.elements = new HashMap<>();
    }

    public JsonObject(String json) {
        this.elements = new HashMap<>();
        char[] charArray = json.substring(1, json.length() - 1).toCharArray();

        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();

        boolean isKey = true;
        boolean reading = false;

        boolean restartOnComma = true;
        int lockNumber = 0;
        char lockChar = 0;

        for (char character : charArray) {
            switch (character) {
                case '[':
                    if (restartOnComma) {
                        lockChar = '[';
                        restartOnComma = false;
                    }

                    if (lockChar == '[')
                        lockNumber++;
                    break;
                case ']':
                    if (!restartOnComma && lockChar == '[')
                        lockNumber--;
                    if (lockNumber == 0)
                        restartOnComma = true;
                    break;
                case '{':
                    if (restartOnComma) {
                        lockChar = '{';
                        restartOnComma = false;
                    }

                    if (lockChar == '{')
                        lockNumber++;
                    break;
                case '}':
                    if (!restartOnComma && lockChar == '{')
                        lockNumber--;
                    if (lockNumber == 0)
                        restartOnComma = true;
                    break;
                case '"':
                    if (!reading) {
                        reading = true;
                        isKey = true;
                        continue;
                    } else if (isKey) {
                        reading = false;
                        isKey = false;
                        continue;
                    }
                    break;
                case ':':
                    if (!reading) {
                        reading = true;
                        continue;
                    }
                    break;
                case ',':
                    if (reading && restartOnComma) {
                        reading = false;
                        addValue(key, value);

                        key = new StringBuilder();
                        value = new StringBuilder();
                        continue;
                    }
                    break;
            }

            if (reading) {
                if (isKey)
                    key.append(character);
                else
                    value.append(character);
            }
        }

        if (!value.isEmpty())
            addValue(key, value);
    }
}
