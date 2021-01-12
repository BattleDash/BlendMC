package com.battledash.blendmc.commands;

import com.battledash.blendmc.BlendMC;
import com.battledash.blendmc.parse.BlendCameraAnimation;
import com.battledash.blendmc.parse.BlendCutscene;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CutsceneCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            Player player = (Player) sender;
            BlendCameraAnimation parse = BlendCameraAnimation.parse(
                    new File(BlendMC.getInstance().getDataFolder().getAbsolutePath() + "/cube.blendmc"));
            BlendCutscene hell = new BlendCutscene(parse,
                    new Location(Bukkit.getWorld("Hell"), -1.977, 42.50027, 4.916));
            hell.rotateAllFrames((float) Math.toRadians(90));
            hell.play(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
