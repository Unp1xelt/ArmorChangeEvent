package de.unpixelt.armorchange;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorPlugin extends JavaPlugin {
    private static Plugin plugin;

    /**
     * Return this plugin. <br>
     * It is initialized after the {@link #onEnable()} of this plugin is called.
     *
     * @return This plugin.
     */
    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new ArmorListener(), this);
    }

    /**
     * Reequip the armor of a Player. <br>
     * This only calls the events {@link de.unpixelt.armorchange.ArmorUnequipEvent}
     * and {@link de.unpixelt.armorchange.ArmorEquipEvent} and is not 'actually'
     * unequipping and equipping the armor of the player.
     *
     * @param p The Player which armor should be reequipped.
     */
    public static void reequipPlayer(Player p) {
        final ItemStack[] armor = p.getInventory().getArmorContents().clone();
        for (int i = 0; i < armor.length; i++) {
            if (armor[i] == null) continue;
            Bukkit.getPluginManager().callEvent(new ArmorUnequipEvent(p, armor[i], EquipmentSlot.values()[i+2]));
            Bukkit.getPluginManager().callEvent(new ArmorEquipEvent(p, armor[i], EquipmentSlot.values()[i+2]));
        }
    }

    /**
     * Reequip the armor for all online Player's. <br>
     * 
     * @see de.unpixelt.armorchange.ArmorPlugin#reequipPlayer(Player)
     */
    public static void reequipAllPlayer() {
        for (Player p : Bukkit.getOnlinePlayers()) {
           reequipPlayer(p);
        }
    }
}
