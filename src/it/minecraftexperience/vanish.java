package it.minecraftexperience;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.logging.Logger;

public class vanish extends JavaPlugin {
    private Logger console;
    public final HashMap<Player, GameMode> gamemodelist = new HashMap<>();
    public final HashMap<Player, Integer> taskidlist = new HashMap<>();
    public VanishPlayer vanishPlayer;

    public void onEnable() {
        console = getLogger();
        vanishPlayer = new VanishPlayer(this);
// Comando Vanish

        PluginCommand vanish = getServer().getPluginCommand("vanish");

        if (vanish != null) {
            vanish.setExecutor(new VanishCommand(this));
            vanish.setTabCompleter(new VanishCommand(this));}
        }

       // Verifica se il giocatore è in Vanish
    public boolean isVanished(Player player) {
        return gamemodelist.containsKey(player) && taskidlist.containsKey(player);
    }



	}
