package com.swert.creator.command;

import com.swert.creator.Terminal;
import com.swert.creator.manager.CreatorManager;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CreatorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("creator.admin")) {
            sender.sendMessage("§cVocê não possui permissão para isso.");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUtilize: /creator give/reload <jogador>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "give":
                if (args.length == 2) {
                    val target = Bukkit.getPlayer(args[1]);
                    val creatorManager = new CreatorManager();

                    if (target == null) {
                        sender.sendMessage("§cEste jogador está offline.");
                        return false;
                    }

                    if (!(sender instanceof ConsoleCommandSender)) sender.sendMessage("§eCriador de eventos enviado para §f" + target.getName());
                    creatorManager.giveItem(target);
                }
                break;
            case "reload":
                sender.sendMessage("§b§lCRIADOR §aA configuração foi recarregada com sucesso.");
                Terminal.getPlugin().reloadConfig();
                break;
        }
        return true;
    }
}
