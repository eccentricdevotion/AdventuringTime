package me.eccentric_nz.adventuringtime;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AdventuringTime extends JavaPlugin {

    public String pluginName;
    private String root;

    @Override
    public void onDisable() {
        // TODO: Place any custom disable code here.
    }

    @Override
    public void onEnable() {
        PluginDescriptionFile pdfFile = getDescription();
        pluginName = ChatColor.GOLD + "[" + pdfFile.getName() + "]" + ChatColor.RESET + " ";
        getServer().getPluginManager().registerEvents(new AdventuringTimeListener(), this);
        getCommand("adventuringtime").setExecutor(new AdventuringTimeCommand(this));
        root = getStatsPath();
    }

    private String getStatsPath() {
        File container = getServer().getWorldContainer();
        String world = getServer().getWorlds().get(0).getName();
        String server_world = world + File.separator + "stats" + File.separator;
        root = container.getAbsolutePath() + File.separator + server_world;
        return root;
    }

    public String getPluginName() {
        return pluginName;
    }

    public String getRoot() {
        return root;
    }
}
