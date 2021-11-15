package com.senate.thesenate.commands;

import com.senate.thesenate.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;


public class SetItemDamage implements CommandExecutor {
    // Checks if the given string matches the name of a known entity type
    public static boolean isValidMaterialName(String inputMat) {
        for (Material mat : Material.values()) {
            if (mat.name().equals(inputMat)) {
                return true;
            }
        }
        return false;
    }

    //Needed to get the config
    Plugin plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        try {
            String itemName = args[0].toUpperCase(); //get item name in uppercase (or the keyword list)

            if (itemName.equals("LIST")) {
                String[] keys = plugin.getConfig().getKeys(true).toArray(new String[0]);
                for (String key : keys) {
                    if (key.startsWith("ItemDamage.")) {
                        sender.sendMessage("Item: " + key.substring(11) + " Value: " + plugin.getConfig().getInt("ItemDamage." + key.substring(11)));
                    }
                }
                return true;
            }


            if (isValidMaterialName(itemName)) {
                Integer damageChancePercentage = Integer.parseInt(args[1]); //get new damage chance
                plugin.getConfig().set("ItemDamage." + itemName, damageChancePercentage); //save that to the config
                plugin.saveConfig();
                plugin.saveDefaultConfig();
                sender.sendMessage(ChatColor.GREEN + itemName + " now has a " + damageChancePercentage + "% chance of taking damage.");
            } else {
                sender.sendMessage(ChatColor.RED + "Provide a valid item name");
            }
            return true;
        } catch (Exception e) {
            // Default return is the error message below. So we can ignore the error.
        }
        sender.sendMessage(ChatColor.YELLOW + "Provide a valid item name and integer between 0-100 for percent chance of taking damage");
        return false;
    }

}
