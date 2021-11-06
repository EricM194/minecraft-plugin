package main.java.com.senate.customizeitemdamage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class ItemDamageListener implements Listener {

    @EventHandler
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {
        try {
            Integer damageChancePercentage = Hook.itemDamageChanceMap.get(event.getItem().getType().toString());
            if (damageChancePercentage != null &&
                    Math.random()*100 > damageChancePercentage
            ) {
                event.setCancelled(true);
            }
        } catch (Exception ignored) {
        }

    }
}