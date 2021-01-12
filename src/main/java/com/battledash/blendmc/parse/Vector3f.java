package com.battledash.blendmc.parse;

/**
 * Mostly copied from Vector3f in NMS code, but with some slight modifications.
 *
 * @author BattleDash
 * @since 1/12/2021
 */
public class Vector3f {
    protected float x;
    protected float y;
    protected float z;

    public Vector3f(float x, float y, float z) {
        this.x = !Float.isInfinite(x) && !Float.isNaN(x) ? x % 360.0F : 0.0F;
        this.y = !Float.isInfinite(y) && !Float.isNaN(y) ? y % 360.0F : 0.0F;
        this.z = !Float.isInfinite(z) && !Float.isNaN(z) ? z % 360.0F : 0.0F;
    }

    public void setX(float x) {
        this.x = !Float.isInfinite(x) && !Float.isNaN(x) ? x % 360.0F : 0.0F;
    }

    public void setY(float y) {
        this.y = !Float.isInfinite(y) && !Float.isNaN(y) ? y % 360.0F : 0.0F;
    }

    public void setZ(float z) {
        this.z = !Float.isInfinite(z) && !Float.isNaN(z) ? z % 360.0F : 0.0F;
    }

    public boolean equals(Object compareAgainst) {
        if (!(compareAgainst instanceof Vector3f)) {
            return false;
        } else {
            Vector3f vecOther = (Vector3f)compareAgainst;
            return this.x == vecOther.x && this.y == vecOther.y && this.z == vecOther.z;
        }
    }

    public static float transformFloat(float f, float bound) {
        if (f > bound) {
            return transformFloat(-bound + (f - bound), bound);
        }
        if (f < -bound) {
            return transformFloat(bound + (f + bound), bound);
        }
        return f;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public String toString() {
        return "Vector3f{" +
                x + "," +
                y + "," +
                z + "}";
    }
}