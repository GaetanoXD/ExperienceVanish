package it.minecraftexperience;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import net.minecraft.core.BlockPosition;
import net.minecraft.world.level.block.Block;

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

                    player.sendMessage("Sei tornato visibile.");
                    if(player.hasPotionEffect(PotionEffectType.NIGHT_VISION)){
                    	player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    	
                        
                    	}
                    
                } else {
                    sender.sendMessage("Non hai il permeso!");
                }
            } else {
                if (player.hasPermission("exp.vanish")) {
                    vanish.vanishPlayer.vanishPlayer(player);

                    player.sendMessage("Sei ora nascosto agli altri giocatori!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 1)); // Niht Vision
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