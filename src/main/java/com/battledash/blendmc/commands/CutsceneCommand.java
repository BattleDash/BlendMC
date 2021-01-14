package com.battledash.blendmc.commands;

import com.battledash.blendmc.BlendMC;
import com.battledash.blendmc.parse.BlendCameraAnimation;
import com.battledash.blendmc.parse.BlendCutscene;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Example command
 */
public class CutsceneCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            Player player = (Player) sender;
            BlendCameraAnimation parse = BlendCameraAnimation.parse(
                    new File(BlendMC.getInstance().getDataFolder().getAbsolutePath() + "/Factions.blendmc"));
            BlendCutscene cutscene = new BlendCutscene(parse,
                    new Location(Bukkit.getWorld("world"), 41.427, 185, 4.092));
            cutscene.rotateAllFrames((float) Math.toRadians(90));
            cutscene.play(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
