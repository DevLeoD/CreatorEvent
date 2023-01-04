package com.swert.creator;

import com.swert.creator.command.CreatorCommand;
import com.swert.creator.listener.InteractListener;
import lombok.Getter;
import lombok.val;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Terminal extends JavaPlugin {

    @Getter
    private static Terminal plugin;

    @Override
    public void onEnable() {
        plugin = this;
        val file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) saveDefaultConfig();

        register();

        getLogger().info("Plugin enabled");
    }

    private void register() {
        new InteractListener(this);

        getCommand("creator").setExecutor(new CreatorCommand());
    }
}
