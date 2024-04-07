package me.meiallu.hermes;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class JsonArray extends ArrayList<JsonElement> implements JsonElement {

    public boolean add(String string) {
        return super.add(new JsonPrimitive(string));
    }

    public boolean add(Number number) {
        return super.add(new JsonPrimitive(number));
    }

    public boolean add(Boolean bool) {
        return super.add(new JsonPrimitive(bool));
    }

    public boolean add(Character character) {
        return super.add(new JsonPrimitive(character));
    }

    public JsonObject getObject(int index) {
        return (JsonObject) super.get(index);
    }

    public JsonArray getArray(int index) {
        return (JsonArray) super.get(index);
    }

    public JsonPrimitive getPrimitive(int index) {
        return (JsonPrimitive) super.get(index);
    }

    public String getString(int index) {
        return getPrimitive(index).getString();
    }

    public Byte getByte(int index) {
        return getPrimitive(index).getByte();
    }

    public Short getShort(int index) {
        return getPrimitive(index).getShort();
    }

    public Integer getInt(int index) {
        return getPrimitive(index).getInt();
    }

    public Long getLong(int index) {
        return getPrimitive(index).getLong();
    }

    public Float getFloat(int index) {
        return getPrimitive(index).getFloat();
    }

    public Double getDouble(int index) {
        return getPrimitive(index).getDouble();
    }

    public Character getChar(int index) {
        return getPrimitive(index).getChar();
    }

    public Boolean getBoolean(int index) {
        return getPrimitive(index).getBoolean();
    }

    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        writer.write('[');

        Iterator<JsonElement> iterator = super.iterator();

        while (iterator.hasNext()) {
            writer.write(iterator.next().toString());

            if (iterator.hasNext())
                writer.write(',');
        }

        writer.write(']');
        return writer.toString();
    }

    private void addValue(String valueString) {
        switch (valueString.charAt(0)) {
            case '{':
                JsonObject object = new JsonObject(valueString);
                add(object);
                break;
            case '[':
                JsonArray array = new JsonArray(valueString);
                add(array);
                break;
            default:
                JsonPrimitive primitive = JsonPrimitive.parsePrimitive(valueString);
                add(primitive);
                break;
        }
    }

    public JsonArray() {
        super();
    }

    public JsonArray(String json) {
        super();
        char[] charArray = json.substring(1, json.length() - 1).toCharArray();

        StringBuilder value = new StringBuilder();
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
                case ',':
                    if (restartOnComma) {
                        addValue(value.toString());
                        value = new StringBuilder();
                        continue;
                    }
                    break;
            }

            value.append(character);
        }

        if (!value.isEmpty())
            addValue(value.toString());
    }
}
