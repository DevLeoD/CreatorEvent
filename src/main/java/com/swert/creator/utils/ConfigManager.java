package com.swert.creator.utils;

import com.swert.creator.Terminal;
import org.bukkit.ChatColor;

public class ConfigManager {

    public static String getString(String path, boolean message) {
        if (!message) {
            return Terminal.getPlugin().getConfig().getString(path);
        }
        return ChatColor.translateAlternateColorCodes('&', Terminal.getPlugin().getConfig().getString(path));
    }

    public static boolean getBoolean(String path) {
        return Terminal.getPlugin().getConfig().getBoolean(path);
    }

    public static int getInt(String path) {
        return Terminal.getPlugin().getConfig().getInt(path);
    }
}
