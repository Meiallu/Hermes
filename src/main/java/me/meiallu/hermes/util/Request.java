package me.meiallu.hermes.util;

public class Request {

    private final byte myByte;
    private final float aLong;
    private final char[] randomAssArray2;
    public boolean bool = false;
    public int action;
    public char myCharWow;
    public boolean[] randomAssArray;
    public Request[] randomAssArray3;

    public Request(boolean recreate) {
        this.action = 3;
        this.myByte = (byte) 32;
        this.myCharWow = 'a';
        this.aLong = 3.000032f;
        this.randomAssArray = new boolean[]{true, true, false, false, true};
        this.randomAssArray2 = new char[]{'a', 'z', 'f', '4'};

        if (recreate)
            this.randomAssArray3 = new Request[]{new Request(false), new Request(false)};
    }
}
