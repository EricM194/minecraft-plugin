package com.senate.thesenate.event;

import com.senate.thesenate.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.plugin.Plugin;

public class ItemDamageListener implements Listener {

    //Needed to get the config
    Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent e) {

        try {
            int damageChancePercentage = plugin.getConfig().getInt("ItemDamage." + e.getItem().getType());
            if (plugin.getConfig().contains("ItemDamage." + e.getItem().getType()) && Math.random() * 100 > damageChancePercentage) {
                if (plugin.getConfig().getBoolean("Debug")) {
                    e.getPlayer().sendMessage(e.getItem().getType() + " damage cancelled");
                }
                e.setCancelled(true);

            } else {
                if (plugin.getConfig().getBoolean("Debug")) {
                    e.getPlayer().sendMessage(e.getItem().getType() + " damaged");
                }
            }

        } catch (Exception ignored) {
        }

    }
}