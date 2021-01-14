package com.battledash.blendmc.parse;

import org.bukkit.util.Vector;

import javax.activation.UnsupportedDataTypeException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A full camera animation imported from blender, this includes the frame counts and an array of the {@link Frame}s
 *
 * @author BattleDash
 * @since 1/12/2021
 */
public class BlendCameraAnimation implements Cloneable {

    private final int startFrame;
    private final int endFrame;
    private final int totalFrames;
    private final List<Frame> frames;

    public BlendCameraAnimation(int startFrame, int endFrame, int totalFrames, List<Frame> frames) {
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.totalFrames = totalFrames;
        Vector clone = frames.get(0).getLocation().clone();
        this.frames = frames.stream().map(f -> {
            f.getLocation().subtract(clone);
            return f;
        }).collect(Collectors.toList());
    }

    public int getStartFrame() {
        return startFrame;
    }

    public int getEndFrame() {
        return endFrame;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    @Override
    public BlendCameraAnimation clone() {
        try {
            return ((BlendCameraAnimation) super.clone());
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "BlendCameraAnimation{" +
                "startFrame=" + startFrame +
                ", endFrame=" + endFrame +
                ", totalFrames=" + totalFrames +
                ", frames=" + frames +
                '}';
    }

    public static BlendCameraAnimation parse(File file) throws IOException {
        return parse(new FileInputStream(file));
    }

    public static BlendCameraAnimation parse(InputStream file) throws IOException {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))) {
            for (;;) {
                String line = reader.readLine();
                if (line == null)
                    break;
                strings.add(line);
            }
        }
        int seek = 0;
        if (!strings.get(seek++).equals("BLENDMC")) {
            throw new UnsupportedDataTypeException("This is not a BlendMC file.");
        }
        int startFrame = Integer.parseInt(strings.get(seek++));
        int endFrame = Integer.parseInt(strings.get(seek++));
        int totalFrames = Integer.parseInt(strings.get(seek++));
        // Not the cleanest thing, but works for now.
        List<Frame> frames = new ArrayList<>();
        List<float[]> locations = new ArrayList<>();
        List<float[]> rotations = new ArrayList<>();
        for (int i = 0; i < totalFrames; i++) {
            locations.add(new float[]{
                    Float.parseFloat(strings.get(seek++)),
                    Float.parseFloat(strings.get(seek++)),
                    Float.parseFloat(strings.get(seek++))
            });
            rotations.add(new float[]{
                    Float.parseFloat(strings.get(seek++)),
                    Float.parseFloat(strings.get(seek++)),
                    Float.parseFloat(strings.get(seek++))
            });
        }
        int totalMarkers = Integer.parseInt(strings.get(seek++));
        List<Marker> markers = new ArrayList<>();
        for (int i = 0; i < totalMarkers; i++) {
            markers.add(new Marker(strings.get(seek++), Integer.parseInt(strings.get(seek++))));
        }
        for (int i = 0; i < totalFrames; i++) {
            int finalI = i;
            frames.add(new Frame(locations.get(i), rotations.get(i), markers.stream().filter(m -> m.getFrame() == finalI).collect(Collectors.toList())));
        }
        return new BlendCameraAnimation(startFrame, endFrame, totalFrames, frames);
    }

}