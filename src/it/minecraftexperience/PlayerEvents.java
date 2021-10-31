package it.minecraftexperience;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {
    vanish vanish;

    public PlayerEvents(vanish vanish) {
        this.vanish = vanish;
    }

    // Sistema Eventi di Login e Logout
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Player vanishedPlayer : vanish.gamemodelist.keySet()) {
            player.hidePlayer(vanish, vanishedPlayer);
        }
    }

    // Sistema Eventi broadcast messaggi Login Logout
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (vanish.isVanished(player)) {
            event.setQuitMessage(null); // Deprecato

            vanish.vanishPlayer.unvanishPlayer(player);
        }
    }
}