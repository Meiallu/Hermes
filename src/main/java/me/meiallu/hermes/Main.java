package me.meiallu.hermes;

import me.meiallu.hermes.util.Request;
import me.meiallu.hermes.util.SerializationType;

public class Main {

    public static void main(String[] args) {
        Request request = new Request(true);
        request.action = 12;

        String serialized = Hermes.serialize(SerializationType.JSON, request);
        Request newRequest = Hermes.deserialize(SerializationType.JSON, serialized, Request.class);

        System.out.println(serialized);
        System.out.println(newRequest.action);
    }
}