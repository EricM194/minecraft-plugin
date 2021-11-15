package com.senate.thesenate.commands;

import com.senate.thesenate.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class Cords implements CommandExecutor {

    //Needed to get the config
    Plugin plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            String[] keys = plugin.getConfig().getKeys(true).toArray(new String[0]);


            switch (args.length) {
                case 0:
                    return false;
                case 1:
                    if (args[0].equalsIgnoreCase("list")) {
                        for (String key : keys) {
                            if (key.startsWith("Cords.")) {
                                sender.sendMessage(key.substring(6) + ": " + plugin.getConfig().getString("Cords." + key.substring(6)));
                            }
                        }
                    }

                    if (plugin.getConfig().contains("Cords." + args[0])) {
                        sender.sendMessage(args[0] + ": " + plugin.getConfig().getString("Cords." + args[0]));
                        break;
                    }
                    return true;

                case 2:
                    if (args[0].equalsIgnoreCase("add")) {
                        plugin.getConfig().set("Cords." + args[1], (int) player.getLocation().getX() + "," + (int) player.getLocation().getY() + "," + (int) player.getLocation().getZ()); //save that to the config
                        plugin.saveConfig();
                        plugin.saveDefaultConfig();
                        sender.sendMessage(args[1] + " added!");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("remove")) {
                        plugin.getConfig().set("Cords." + args[1], null); //save that to the config
                        plugin.saveConfig();
                        plugin.saveDefaultConfig();
                        sender.sendMessage(args[1] + " removed!");
                        return true;
                    }
                case 5:
                    if (args[0].equalsIgnoreCase("add")) {
                        plugin.getConfig().set("Cords." + args[1], args[2] + "," + args[3] + "," + args[4]); //save that to the config
                        plugin.saveConfig();
                        plugin.saveDefaultConfig();
                        sender.sendMessage(args[1] + " added!");
                        return true;
                    }
            }


        }

        return false;
    }
}
