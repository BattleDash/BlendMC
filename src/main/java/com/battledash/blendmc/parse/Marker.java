package com.battledash.blendmc.parse;

public class Marker {

    private final String name;
    private final int frame;

    public Marker(String name, int frame) {
        this.name = name;
        this.frame = frame;
    }

    public String getName() {
        return name;
    }

    public int getFrame() {
        return frame;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "name='" + name + '\'' +
                ", frame=" + frame +
                '}';
    }

}
