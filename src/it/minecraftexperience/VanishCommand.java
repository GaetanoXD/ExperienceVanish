package it.minecraftexperience;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor, TabExecutor {
    vanish vanish;

    public VanishCommand(vanish vanish) {
        this.vanish = vanish;
    }

    // Comando vanish
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (vanish.isVanished(player)) {
                if (player.hasPermission("exp.vanish")) {
                    vanish.vanishPlayer.unvanishPlayer(player);

                    player.sendMessage("Sei ora nascosto agli altri giocatori!");
                } else {
                    sender.sendMessage("Non hai il permeso!");
                }
            } else {
                if (player.hasPermission("exp.vanish")) {
                    vanish.vanishPlayer.vanishPlayer(player);

                    player.sendMessage("Sei ora nascosto agli altri giocatori!");
                } else {
                    sender.sendMessage("Non hai il permesso!");
                }
            }
        } else {
            sender.sendMessage("Devi essere un giocatore per farlo!"); // Sistema anti-console command.
        }

        return false;
    }

    // Sistema TabComplete
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }
}