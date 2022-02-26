package me.remainingtoast.namecolour.commands;

import me.remainingtoast.namecolour.util.PlayerWrapper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class NameColour implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            PlayerWrapper wrapper = PlayerWrapper.get((Player) sender);

            if(args.length > 0) {
                if (switchColour(wrapper, args[0].toLowerCase(Locale.ROOT))) {
                    wrapper.sendMessage("&6Name colour set successfully!");
                }

                wrapper.setColoredName();
            } else {
                sendInvalidColor(wrapper.getPlayer());
            }
        }
        return true;
    }

    private void sendInvalidColor(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid colour, Available colours: &4darkred &cred &6gold &eyellow &2darkgreen &agreen &baqua &3darkaqua &1darkblue &9blue &dlightpurple &5darkpurple &fwhite &7gray &8darkgray &0black"));
    }

    private boolean switchColour(PlayerWrapper wrapper, String str) {
        switch (str) {
            case "darkred": {
                wrapper.setColor("&4");
                return true;
            }
            case "red": {
                wrapper.setColor("&c");
                return true;
            }
            case "gold": {
                wrapper.setColor("&6");
                return true;
            }
            case "yellow": {
                wrapper.setColor("&e");
                return true;
            }
            case "darkgreen": {
                wrapper.setColor("&2");
                return true;
            }
            case "green": {
                wrapper.setColor("&a");
                return true;
            }
            case "aqua": {
                wrapper.setColor("&b");
                return true;
            }
            case "darkaqua": {
                wrapper.setColor("&3");
                return true;
            }
            case "darkblue": {
                wrapper.setColor("&1");
                return true;
            }
            case "blue": {
                wrapper.setColor("&9");
                return true;
            }
            case "lightpurple": {
                wrapper.setColor("&d");
                return true;
            }
            case "darkpurple": {
                wrapper.setColor("&5");
                return true;
            }
            case "white": {
                wrapper.setColor("&f");
                return true;
            }
            case "gray": {
                wrapper.setColor("&7");
                return true;
            }
            case "darkgray": {
                wrapper.setColor("&8");
                return true;
            }
            case "black": {
                wrapper.setColor("&0");
                return true;
            }
            default: {
                sendInvalidColor(wrapper.getPlayer());
                return false;
            }
        }
    }

}
