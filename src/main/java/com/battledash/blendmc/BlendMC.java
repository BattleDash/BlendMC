package com.battledash.blendmc;

import com.battledash.blendmc.commands.CutsceneCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlendMC extends JavaPlugin {

    private static BlendMC INSTANCE;

    public static BlendMC getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        getCommand("cutscene").setExecutor(new CutsceneCommand());
    }

}
