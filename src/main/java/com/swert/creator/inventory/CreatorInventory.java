package com.swert.creator.inventory;

import com.swert.creator.Terminal;
import com.swert.creator.utils.ItemBuilder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.var;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CreatorInventory {

    public Inventory openInventory(Player player) {
        val config = Terminal.getPlugin().getConfig();
        val inventory = Bukkit.createInventory(null, config.getInt("Inventory.size") * 9, "§7Selecione um evento:");

        config.getConfigurationSection("Menu").getKeys(false).forEach(events -> {
            val name = config.getString("Menu." + events + ".name");
            val material = config.getString("Menu." + events + ".material");
            val position = config.getInt("Menu." + events + ".position");
            var lores = config.getStringList("Menu." + events + ".lore");

            lores = lores.stream().map(lore -> ChatColor.translateAlternateColorCodes('&', lore))
                    .collect(Collectors.toList());

            val event = new ItemBuilder(Material.matchMaterial(material))
                    .setName(name)
                    .addLore(lores)
                    .addFlag(ItemFlag.HIDE_ATTRIBUTES)
                    .build();

            inventory.setItem(position, event);
        });

        return inventory;
    }
}
