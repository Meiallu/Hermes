package me.meiallu.hermes;

public class JsonPrimitive implements JsonElement {

    private final Object object;

    public static JsonPrimitive parsePrimitive(String value) {
        try {
            Number number = Double.parseDouble(value);
            return new JsonPrimitive(number);
        } catch (NumberFormatException ignored) {
            return switch (value) {
                case "true" -> new JsonPrimitive(true);
                case "false" -> new JsonPrimitive(false);
                default -> new JsonPrimitive(value.substring(1, value.length() - 1));
            };
        }
    }

    public String getString() {
        return (String) object;
    }

    public Byte getByte() {
        return (Byte) object;
    }

    public Short getShort() {
        return (Short) object;
    }

    public Integer getInt() {
        return (Integer) object;
    }

    public Long getLong() {
        return (Long) object;
    }

    public Float getFloat() {
        return (Float) object;
    }

    public Double getDouble() {
        return (Double) object;
    }

    public Character getChar() {
        return getString().charAt(0);
    }

    public Boolean getBoolean() {
        return (Boolean) object;
    }

    @Override
    public String toString() {
        if (object instanceof String)
            return "\"" + object + "\"";

        return object.toString();
    }

    public JsonPrimitive(String string) {
        this.object = string;
    }

    public JsonPrimitive(Number number) {
        this.object = number;
    }

    public JsonPrimitive(Character character) {
        this.object = character.toString();
    }

    public JsonPrimitive(Boolean bool) {
        this.object = bool;
    }
}
