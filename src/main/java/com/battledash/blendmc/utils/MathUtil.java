package com.battledash.blendmc.utils;

import org.bukkit.util.Vector;

public class MathUtil {

    // Cleaned up method from https://www.spigotmc.org/threads/rotating-location-around-the-y-axis-math.429476/
    public static Vector rotateLocXZ(Vector loc, Vector axis, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        Vector v = loc.clone().subtract(axis);
        return new Vector(new Vector(cos, 0, -sin).dot(v), 0, new Vector(sin, 0, cos).dot(v)).add(axis);
    }

}
