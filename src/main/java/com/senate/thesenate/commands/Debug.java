package com.senate.thesenate.commands;

import com.senate.thesenate.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;


public class Debug implements CommandExecutor {

    //Needed to get the config
    Plugin plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Debug is set to " + plugin.getConfig().getBoolean("Debug"));
        }
        if (args.length == 1) {
            plugin.getConfig().set("Debug", Boolean.parseBoolean(args[0])); //save that to the config
            plugin.saveConfig();
            plugin.saveDefaultConfig();
            sender.sendMessage("Debug set to " + args[0]);
        }

        return true;
    }
}
