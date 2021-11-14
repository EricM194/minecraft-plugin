package com.senate.thesenate.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class OnPlayerLeaveBed implements Listener {
    public void onLeaveBed(PlayerBedLeaveEvent e) {
        Player player = e.getPlayer();
        player.sendMessage("Hey, you, you’re finally awake.");
    }
}
