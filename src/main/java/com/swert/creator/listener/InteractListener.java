package com.swert.creator.listener;

import com.swert.creator.Terminal;
import com.swert.creator.inventory.CreatorInventory;
import com.swert.creator.manager.CreatorManager;
import lombok.val;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class InteractListener implements Listener {

    public InteractListener(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    void onInteractCreatorItem(PlayerInteractEvent event) {
        val player = event.getPlayer();
        val item = event.getItem();

        if (item == null) return;
        if (item.getType() == Material.AIR) return;
        if (!item.hasItemMeta()) return;
        if (!item.getItemMeta().hasDisplayName()) return;

        val displayName = item.getItemMeta().getDisplayName();
        val creatorInventory = new CreatorInventory();

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (displayName.equalsIgnoreCase("§eCriador de eventos")) {
                if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) event.setCancelled(true);

                player.openInventory(creatorInventory.openInventory(player));
            }
        }
    }

    @EventHandler
    void onInteractInventory(InventoryClickEvent event) {
        val player = (Player) event.getWhoClicked();
        val title = event.getInventory().getTitle();
        val creatorManager = new CreatorManager();
        val config = Terminal.getPlugin().getConfig();

        if (title.equalsIgnoreCase("§7Selecione um evento:")) {
            event.setCancelled(true);

            config.getConfigurationSection("Menu").getKeys(false).forEach(events -> {
                val position = config.getInt("Menu." + events + ".position");
                val name = ChatColor.translateAlternateColorCodes('&', config.getString("Menu." + events + ".name"));
                val command = config.getString("Menu." + events + ".command-execute");

                if (event.getSlot() == position) {
                    creatorManager.createEvent(player, command, name);
                    player.closeInventory();
                }
            });
        }
    }
}
