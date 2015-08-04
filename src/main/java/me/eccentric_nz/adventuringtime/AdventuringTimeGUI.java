/*
 *  Copyright 2015 eccentric_nz.
 */
package me.eccentric_nz.adventuringtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author eccentric_nz
 */
public class AdventuringTimeGUI {

    private final TreeMap<String, Integer> biomes;
    private final List<Material> blocks = new ArrayList<Material>();
    private final List<Byte> bytes = new ArrayList<Byte>();
    private final ItemStack[] gui;

    public AdventuringTimeGUI(TreeMap<String, Integer> biomes) {
        this.biomes = biomes;
        this.blocks.add(Material.SAND);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.LOG);
        this.bytes.add((byte) 2);
        this.blocks.add(Material.WOOD);
        this.bytes.add((byte) 2);
        this.blocks.add(Material.SNOW);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.WOOD);
        this.bytes.add((byte) 1);
        this.blocks.add(Material.LONG_GRASS);
        this.bytes.add((byte) 2);
        this.blocks.add(Material.SPONGE);
        this.bytes.add((byte) 1);
        this.blocks.add(Material.CACTUS);
        this.bytes.add((byte) 1);
        this.blocks.add(Material.SANDSTONE);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.LOG);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.EMERALD_ORE);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.RED_ROSE);
        this.bytes.add((byte) 2);
        this.blocks.add(Material.YELLOW_FLOWER);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.ICE);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.SNOW_BLOCK);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.PACKED_ICE);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.LOG);
        this.bytes.add((byte) 3);
        this.blocks.add(Material.WOOD);
        this.bytes.add((byte) 3);
        this.blocks.add(Material.VINE);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.DIRT);
        this.bytes.add((byte) 2);
        this.blocks.add(Material.MOSSY_COBBLESTONE);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.HARD_CLAY);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.STAINED_CLAY);
        this.bytes.add((byte) 1);
        this.blocks.add(Material.STAINED_CLAY);
        this.bytes.add((byte) 13);
        this.blocks.add(Material.HUGE_MUSHROOM_1);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.MYCEL);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.SPONGE);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.GRASS);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.WATER_BUCKET);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.LOG_2);
        this.bytes.add((byte) 1);
        this.blocks.add(Material.LOG_2);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.WOOD);
        this.bytes.add((byte) 4);
        this.blocks.add(Material.STONE);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.WATER_LILY);
        this.bytes.add((byte) 0);
        this.blocks.add(Material.LOG);
        this.bytes.add((byte) 1);
        this.blocks.add(Material.WOOD);
        this.bytes.add((byte) 0);
        this.gui = getItemStack();
    }

    private ItemStack[] getItemStack() {

        ItemStack[] is = new ItemStack[54];
        int i = 0;

        for (Map.Entry<String, Integer> map : biomes.entrySet()) {
            ItemStack biome = new ItemStack(blocks.get(i), 1, bytes.get(i));
            ItemMeta im = biome.getItemMeta();
            im.setDisplayName(map.getKey());
            String done = (map.getValue() == 1) ? ChatColor.GREEN + "Visited" : ChatColor.RED + "Undiscovered";
            List<String> lore = new ArrayList<String>();
            lore.add(done);
            im.setLore(lore);
            biome.setItemMeta(im);
            is[i] = biome;
            i++;
        }

        return is;
    }

    public ItemStack[] getGUI() {
        return gui;
    }
}
