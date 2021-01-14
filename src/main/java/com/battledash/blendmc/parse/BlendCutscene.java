package com.battledash.blendmc.parse;

import com.battledash.blendmc.BlendMC;
import com.battledash.blendmc.utils.MathUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * An entire cutscene, this includes the {@link BlendCameraAnimation} and the start location of the camera.
 *
 * @author BattleDash
 * @since 1/12/2021
 */
public class BlendCutscene {

    private final BlendCameraAnimation animation;
    private Location startLocation;

    public BlendCutscene(BlendCameraAnimation animation, Location startLocation) {
        this.animation = animation.clone();
        this.startLocation = startLocation;
        this.animation.getFrames().forEach(f -> f.setLocation(f.getLocation().add(startLocation.toVector())));
    }

    public BlendCameraAnimation getAnimation() {
        return animation;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * Rotates all frames by positions around the axis (startLocation) and yaw rotation.
     * @param yaw
     */
    public void rotateAllFrames(double yaw) {
        animation.getFrames().forEach(f -> f.getRot().setX(Vector3f.transformFloat(f.getRot().x + ((float) Math.toDegrees(yaw)),
                180)));
        animation.getFrames().forEach(f -> f.setLocation(MathUtil.rotateLocXZ(f.getLocation(),
                startLocation.toVector().clone().setY(f.getLocation().getY()),
                yaw)));
    }

    public void play(Player player) {
        play(player, null);
    }

    public void play(Player player, Runnable endCallback) {
        new BukkitRunnable() {
            int frameIndex = 0;
            @Override
            public void run() {
                Frame frame = animation.getFrames().get(this.frameIndex++);
                player.teleport(new Location(startLocation.getWorld(),
                        frame.getLocation().getX(),
                        frame.getLocation().getY(),
                        frame.getLocation().getZ(),
                        frame.getRot().getX(),
                        frame.getRot().getZ()));
                if (this.frameIndex >= animation.getFrames().size()) cancel();
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                if (endCallback != null) endCallback.run();
                super.cancel();
            }
        }.runTaskTimer(BlendMC.getInstance(), 0, 1);
    }

}
