package me.eccentric_nz.adventuringtime;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AdventuringTimeCommand implements CommandExecutor {

    private final AdventuringTime plugin;
    private final TreeMap<String, Integer> achieve = new TreeMap<String, Integer>();

    public AdventuringTimeCommand(AdventuringTime plugin) {
        this.plugin = plugin;
        this.achieve.put("Beach", 0);
        this.achieve.put("Birch Forest", 0);
        this.achieve.put("Birch Forest Hills", 0);
        this.achieve.put("Cold Beach", 0);
        this.achieve.put("Cold Taiga", 0);
        this.achieve.put("Cold Taiga Hills", 0);
        this.achieve.put("Deep Ocean", 0);
        this.achieve.put("Desert", 0);
        this.achieve.put("Desert Hills", 0);
        this.achieve.put("Extreme Hills", 0);
        this.achieve.put("Extreme Hills+", 0);
        this.achieve.put("Forest", 0);
        this.achieve.put("ForestHills", 0);
        this.achieve.put("FrozenRiver", 0);
        this.achieve.put("Ice Mountains", 0);
        this.achieve.put("Ice Plains", 0);
        this.achieve.put("Jungle", 0);
        this.achieve.put("Jungle Edge", 0);
        this.achieve.put("Jungle Hills", 0);
        this.achieve.put("Mega Taiga", 0);
        this.achieve.put("Mega Taiga Hills", 0);
        this.achieve.put("Mesa", 0);
        this.achieve.put("Mesa Plateau", 0);
        this.achieve.put("Mesa Plateau F", 0);
        this.achieve.put("Mushroom Island", 0);
        this.achieve.put("MushroomIslandShore", 0);
        this.achieve.put("Ocean", 0);
        this.achieve.put("Plains", 0);
        this.achieve.put("River", 0);
        this.achieve.put("Roofed Forest", 0);
        this.achieve.put("Savanna", 0);
        this.achieve.put("Savanna Plateau", 0);
        this.achieve.put("Stone Beach", 0);
        this.achieve.put("Swampland", 0);
        this.achieve.put("Taiga", 0);
        this.achieve.put("Taiga Hills", 0);
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("adventuringtime")) {
            Player player = null;
            if (sender instanceof Player) {
                player = (Player) sender;
            }
            if (player == null) {
                sender.sendMessage(plugin.pluginName + "Command can only be used by a player!");
                return true;
            }
            // do stuff
            try {
                String file = plugin.getRoot() + player.getUniqueId().toString() + ".json";
                JSONParser parser = new JSONParser();
                String file_contents;
                try {
                    file_contents = readFile(file);
                    JSONObject json = (JSONObject) parser.parse(file_contents);
                    JSONObject stats = (JSONObject) json.get("achievement.exploreAllBiomes");
                    JSONArray biomes = (JSONArray) stats.get("progress");
                    TreeMap<String, Integer> where = new TreeMap<String, Integer>();
                    where.putAll(achieve);
                    for (Object b : biomes) {
                        if (where.containsKey(b.toString())) {
                            where.put(b.toString(), 1);
                        }
                    }
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.GOLD + "List of biomes REQUIRED for the Adventuring Time achievement:");
                        for (Map.Entry<String, Integer> map : where.entrySet()) {
                            String done = (map.getValue() == 1) ? ChatColor.GREEN + "Visited" : ChatColor.RED + "Undiscovered";
                            player.sendMessage(map.getKey() + ": " + done);
                        }
                    }
                    if (args.length > 0) {
                        if (args[0].equalsIgnoreCase("v")) {
                            player.sendMessage(ChatColor.GOLD + "You have VISITED these biomes for the Adventuring Time achievement:");
                            for (Map.Entry<String, Integer> map : where.entrySet()) {
                                if (map.getValue() == 1) {
                                    player.sendMessage(map.getKey() + ": " + ChatColor.GREEN + "Visited");
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("u")) {
                            player.sendMessage(ChatColor.GOLD + "You need to DISCOVER these biomes for the Adventuring Time achievement:");
                            for (Map.Entry<String, Integer> map : where.entrySet()) {
                                if (map.getValue() == 0) {
                                    player.sendMessage(map.getKey() + ": " + ChatColor.RED + "Undiscovered");
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("g")) {
                            // show GUI
                            ItemStack[] stacks = new AdventuringTimeGUI(where).getGUI();
                            Inventory gui = plugin.getServer().createInventory(player, 54, "§4Adventuring Time");
                            gui.setContents(stacks);
                            player.openInventory(gui);
                        }
                    }
                    where.clear();
                    return true;
                } catch (IOException ex) {
                    plugin.getServer().getLogger().log(Level.SEVERE, "Could not read stats file", ex);
                }
            } catch (ParseException ex) {
                plugin.getServer().getLogger().log(Level.SEVERE, "Could not parse JSON from stats file", ex);
            }
        }
        return false;
    }

    private String readFile(String pathname) throws IOException {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()).append(lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
}
