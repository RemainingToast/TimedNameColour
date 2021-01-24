package me.remainingtoast.namecolour;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameColourCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                String str = args[0].toLowerCase();
                switch (str){
                    case "darkred": {
                        setColour(p, "&4");
                        break;
                    }
                    case "red": {
                        setColour(p, "&c");
                        break;
                    }
                    case "gold": {
                        setColour(p, "&6");
                        break;
                    }
                    case "yellow": {
                        setColour(p, "&e");
                        break;
                    }
                    case "darkgreen": {
                        setColour(p, "&2");
                        break;
                    }
                    case "green": {
                        setColour(p, "&a");
                        break;
                    }
                    case "aqua": {
                        setColour(p, "&b");
                        break;
                    }
                    case "darkaqua": {
                        setColour(p, "&3");
                    }
                    case "darkblue": {
                        setColour(p, "&1");
                    }
                    case "blue": {
                        setColour(p, "&9");
                        break;
                    }
                    case "lightpurple": {
                        setColour(p, "&d");
                        break;
                    }
                    case "darkpurple": {
                        setColour(p, "&5");
                        break;
                    }
                    case "white": {
                        setColour(p, "&f");
                        break;
                    }
                    case "gray": {
                        setColour(p, "&7");
                        break;
                    }
                    case "darkgray": {
                        setColour(p, "&8");
                        break;
                    }
                    case "black": {
                        setColour(p, "&0");
                        break;
                    }
                    case "add": {
                        addTime(args, p);
                        break;
                    }
                    default: {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid colour, Available colours: &4darkred &cred &6gold &eyellow &2darkgreen &agreen &baqua &3darkaqua &1darkblue &9blue &dlightpurple &5darkpurple &fwhite &7gray &8darkgray &0black"));
                    }
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid colour, Available colours: &4darkred &cred &6gold &eyellow &2darkgreen &agreen &baqua &3darkaqua &1darkblue &9blue &dlightpurple &5darkpurple &fwhite &7gray &8darkgray &0black"));
            }
        } else {
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("add")){
                    addTime(args, sender);
                }
            }
        }
        return false;
    }

    private void addTime(String[] args, CommandSender sender) {
        if (args.length == 4) {
            int time;
            NCPlayer ncp;
            if(sender instanceof Player){
                if(!sender.isOp()) return;
                ncp = PlayerUtil.loadPlayerData((Player) sender);
            } else {
                ncp = PlayerUtil.loadPlayerData(sender.getServer().getPlayer(args[1]));
            }
            time = parseTime(args[3], args[2]);
            int currTime = ncp.getNameColourTime();
            ncp.setNameColourTime(currTime + time);
            ncp.save();
            sender.sendMessage((ChatColor.translateAlternateColorCodes('&',"&aSuccessfully added.")));
        } else {
            sender.sendMessage((ChatColor.translateAlternateColorCodes('&',"&cInvalid arguments, type /nc help.")));
        }
    }

    private int parseTime(String str, String t) {
        int time = 0;
        switch (str) {
            case "hours":
                time = Integer.parseInt(t) * 3600;
                break;
            case "minutes":
                time = Integer.parseInt(t) * 60;
                break;
            case "seconds":
                time = Integer.parseInt(t);
                break;
        }
        return time;
    }

    private void setColour(Player player, String color){
        try {
            NCPlayer ncp = PlayerUtil.loadPlayerData(player);
            boolean boo = ncp.getNameColourTime() == 0;
            ncp.setColour(color);
            if(!(boo)){
                player.setDisplayName(ncp.getColoredName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', color+"Your name colour was successfully set!"));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Your " + color +  "NameColour &6was successfully set, &cBUT you have no NameColour Time do /vote"));
            }
            ncp.save();
        } catch (Exception e){
            System.out.println("Error trying to set " + player.getDisplayName() + "'s name to the color: " + color);
        }
    }
}
