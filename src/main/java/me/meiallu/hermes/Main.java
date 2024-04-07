package me.meiallu.hermes;

public class Main {

    public static void main(String[] args) {
        String serialized = JsonSerializer.serialize(new Request(true)).toString();
        JsonObject object = new JsonObject(serialized);

        System.out.println(object);
    }
}