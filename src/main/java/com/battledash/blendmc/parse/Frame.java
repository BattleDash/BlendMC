package com.battledash.blendmc.parse;

import org.bukkit.util.Vector;

import java.util.List;

/**
 *
 * A single frame of a cutscene, which include the position and rotation of the camera.
 *
 * @author BattleDash
 * @since 1/12/2021
 */
public class Frame {

    private Vector location;
    private final Vector3f rot;
    private List<Marker> markers;

    public Frame(float[] location, float[] rotation, List<Marker> markers) {
        // Rearrange Locations a bit here, blender uses a different rotation matrix (XZY)
        this.location = new Vector(
                -location[1],
                location[2],
                -location[0]
        );
        // Calculate pitch/yaw from euler angles provided by blender. x = yaw, z = pitch, y = roll (unused)
        this.rot = new Vector3f(
                Vector3f.transformFloat(90 - (float) Math.toDegrees(rotation[2]), 180),
                (float) Math.toDegrees(rotation[1]), // roll, unused (I think)
                Vector3f.transformFloat(90 - (float) Math.toDegrees(rotation[0]), 90)
        );
        this.markers = markers;
    }

    public Vector getLocation() {
        return location;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public Vector3f getRot() {
        return rot;
    }

    public List<Marker> getMarkers() {
        return markers;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "location=" + location +
                ", rot=" + rot +
                ", markers=" + markers +
                '}';
    }
}