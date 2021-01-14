package com.battledash.blendmc.parse;

public class Marker {

    public String name;
    public int frame;

    public Marker(String name, int frame) {
        this.name = name;
        this.frame = frame;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "name='" + name + '\'' +
                ", frame=" + frame +
                '}';
    }

}
