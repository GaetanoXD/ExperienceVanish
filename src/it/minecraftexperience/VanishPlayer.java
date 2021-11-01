package it.minecraftexperience;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VanishPlayer {
    vanish vanish;

    public VanishPlayer(vanish vanish) {
        this.vanish = vanish;
    }
// Sistema Vanish
    public void vanishPlayer(Player vanishplayer) {
        vanish.gamemodelist.put(vanishplayer, vanishplayer.getGameMode());
        vanishplayer.setGameMode(GameMode.SURVIVAL);
// Deprecato, non utilzzato.
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', vanish.getConfig().getString("quitmessage").replaceAll("%playername%", vanishplayer.getDisplayName())));

        vanish.taskidlist.put(vanishplayer, Bukkit.getScheduler().scheduleSyncRepeatingTask(vanish, () -> {
            TextComponent actionbar = new TextComponent("Sei nascosto ad altri giocatori!");
            actionbar.setColor(ChatColor.GOLD);

            vanishplayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, actionbar);
        }, 0, 20));

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != vanishplayer) {
                player.hidePlayer(vanish, vanishplayer);
            }
        }
    }
// Sistema Unvanish
    public void unvanishPlayer(Player unvanishplayer) {
        unvanishplayer.setGameMode(vanish.gamemodelist.get(unvanishplayer));
        vanish.gamemodelist.remove(unvanishplayer);
// Deprecato.
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', vanish.getConfig().getString("joinmessage").replaceAll("%playername%", unvanishplayer.getDisplayName())));

        Bukkit.getScheduler().cancelTask(vanish.taskidlist.get(unvanishplayer));
        vanish.taskidlist.remove(unvanishplayer);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != unvanishplayer) {
                player.showPlayer(vanish, unvanishplayer);
            }
        }
    }
}