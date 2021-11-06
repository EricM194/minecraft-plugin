package main.java.com.senate.customizeitemdamage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class Hook extends JavaPlugin implements CommandExecutor {

    // Checks if the given string matches the name of a known entity type
    public static boolean isValidMaterialName(String inputMat) {
        for (Material mat : Material.values()) {
            if (mat.name().equals(inputMat)) {
                return true;
            }
        }
        return false;
    }

    static Map<String, Integer> itemDamageChanceMap = new HashMap<String, Integer>();

    public void onEnable() {
        PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new ItemDamageListener(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin CustomizeItemDamage turned on");
    }
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin CustomizeItemDamage turned off");
    }
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){
        if(cmd.getName().equalsIgnoreCase("reduce_item_damage")){
            if(args.length < 2){
                sender.sendMessage(ChatColor.GRAY + "/reduce_item_damage ITEM_NAME 0 - Remove the chance of item taking damage");
                sender.sendMessage(ChatColor.GRAY + "/reduce_item_damage ITEM_NAME 100 - Sets the chance of item taking damage to normal");
                return true;
            }

            try {
                String itemName = args[0];
                if (isValidMaterialName(itemName)) {
                    Integer damageChancePercentage = Integer.parseInt(args[1]);
                    itemDamageChanceMap.put(itemName, damageChancePercentage);
                    sender.sendMessage(ChatColor.GREEN + itemName + " now has a " + damageChancePercentage + "% chance of taking damage.");
                }
                return true;
            } catch (NumberFormatException ignored) {
                // Default return is the error message below. So we can ignore the error.
            }
            sender.sendMessage(ChatColor.YELLOW + "Provide a valid item name and integer between 0-100 for chance of taking damage");
            return false;
        }
        return false;
    }
}


