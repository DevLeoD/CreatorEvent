package com.swert.creator.manager;

import com.swert.creator.utils.ItemBuilder;
import com.swert.creator.utils.nms.ActionBar;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.var;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class CreatorManager {

    public void createEvent(Player player, String eventCommand, String eventType) {
        val messageActionBar = new ActionBar("§b§lCRIADOR §f" + player.getName() + " §einiciou o evento " + eventType);

        executeCommand(eventCommand);
        player.sendMessage("§eO evento " + eventType + " §efoi iniciado com sucesso.");
        player.playSound(player.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
        removeItem(player);
        Bukkit.getOnlinePlayers().forEach(messageActionBar::show);
    }

    public void giveItem(Player player) {
        val item = new ItemBuilder(Material.FIREWORK)
                .setName("§eCriador de eventos")
                .addLore("§fEste item permite que você")
                .addLore("§fpossa iniciar diversos eventos")
                .addLore("")
                .addLore("§7Clique para selecionar.")
                .setAmout(1)
                .build();

        player.getInventory().addItem(item);
    }

    private void executeCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    private void removeItem(Player player) {
        var itemHand = player.getItemInHand();

        if (itemHand.getAmount() == 1) player.setItemInHand(null);

        itemHand.setAmount(player.getItemInHand().getAmount() - 1);
    }
}
