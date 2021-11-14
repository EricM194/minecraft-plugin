package com.senate.thesenate;

import com.senate.thesenate.commands.*;
import com.senate.thesenate.event.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public class Main extends JavaPlugin implements CommandExecutor {

    public void onEnable() {
        PluginManager manager = this.getServer().getPluginManager();
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //add the events
        manager.registerEvents(new ItemDamageListener(), this);
        manager.registerEvents(new OnPlayerLeaveBed(), this);

        //add the commands
        Objects.requireNonNull(getCommand("SetItemDamage")).setExecutor(new SetItemDamage());
        Objects.requireNonNull(getCommand("Debug")).setExecutor(new Debug());


        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin TheSenate turned on");
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Plugin TheSenate turned off");
    }

}


