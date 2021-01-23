package me.remainingtoast.namecolour;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalTime;

public class NameColourCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                String str = args[0].toLowerCase();
                switch (str){
                    case "darkred": {
                        setColour(p, "4");
                        break;
                    }
                    case "red": {
                        setColour(p, "c");
                        break;
                    }
                    case "gold": {
                        setColour(p, "6");
                        break;
                    }
                    case "yellow": {
                        setColour(p, "e");
                        break;
                    }
                    case "darkgreen": {
                        setColour(p, "2");
                        break;
                    }
                    case "green": {
                        setColour(p, "a");
                        break;
                    }
                    case "aqua": {
                        setColour(p, "b");
                        break;
                    }
                    case "darkaqua": {
                        setColour(p, "3");
                    }
                    case "darkblue": {
                        setColour(p, "1");
                    }
                    case "blue": {
                        setColour(p, "9");
                        break;
                    }
                    case "lightpurple": {
                        setColour(p, "d");
                        break;
                    }
                    case "darkpurple": {
                        setColour(p, "5");
                        break;
                    }
                    case "white": {
                        setColour(p, "f");
                        break;
                    }
                    case "gray": {
                        setColour(p, "7");
                        break;
                    }
                    case "darkgray": {
                        setColour(p, "8");
                        break;
                    }
                    case "black": {
                        setColour(p, "0");
                        break;
                    }
                    case "add": {
                        addTime(args, sender);
                        break;
                    }
                    default: {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid colour, Available colours: &4darkred &cred &6gold &eyellow &2darkgreen &agreen &baqua &3darkaqua &1darkblue &9blue &dlightpurple &5darkpurple &fwhite &7gray &8darkgray &0black"));
                    }
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid colour, Available colours: &4darkred &cred &6gold &eyellow &2darkgreen &agreen &baqua &3darkaqua &1darkblue &9blue &dlightpurple &5darkpurple &fwhite &7gray &8darkgray &0black"));
            }
        }
        return false;
    }

    private void addTime(String[] args, CommandSender player) {
        if (args.length == 4) {
            if (player.hasPermission("namecolour.admin")) {
                int time = 0;
                String str = args[3].toLowerCase();
                switch(str) {
                    case "hours":
                        time = Integer.parseInt(args[2]) * 3600;
                        break;
                    case "minutes":
                        time = Integer.parseInt(args[2]) * 60;
                        break;
                    case "seconds":
                        time = Integer.parseInt(args[2]);
                        break;
                }
                addTime(Bukkit.getPlayer(args[1]), time);
                player.sendMessage((ChatColor.translateAlternateColorCodes('&',"&aSuccessfully added.")));
            } else {
                player.sendMessage((ChatColor.translateAlternateColorCodes('&',"&cHa! You thought...")));
            }
        } else {
            player.sendMessage((ChatColor.translateAlternateColorCodes('&',"&cInvalid arguments, type /nc help.")));
        }
    }

    private void setColour(Player player, String colorCode){
        try {
            NameColourMain.INSTANCE.getPlayerSection(player).set("colour", "&" + colorCode);
            player.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&"+colorCode+player.getDisplayName()));
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&"+colorCode+player.getPlayerListName()));
            NameColourMain.INSTANCE.savePlayerData();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&"+colorCode+"Your name colour was set successfully!"));
        } catch (Exception e){
            System.out.println("Error trying to set " + player.getDisplayName() + "'s name to the colorCode: &" + colorCode);
        }
    }

    private void addTime(Player player, Integer time){
        int curr = NameColourMain.INSTANCE.getPlayerSection(player).getInt("time");
        NameColourMain.INSTANCE.getPlayerSection(player).set("time", curr + time);
        NameColourMain.INSTANCE.savePlayerData();
    }
}
