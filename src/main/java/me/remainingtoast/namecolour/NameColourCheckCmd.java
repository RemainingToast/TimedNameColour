package me.remainingtoast.namecolour;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalTime;

public class NameColourCheckCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(getTime(p) != 0) p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have &c" + getTimeFormatted(p) + " &7of name colour remaining."));
            else p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have any name colour time, do /vote to get some!"));
        }
        return false;
    }

    public int getTime(Player player) {
        return NameColourMain.INSTANCE.getPlayerSection(player).getInt("time");
    }

    public String getTimeFormatted(Player player) {
        LocalTime time = LocalTime.MIN.plusSeconds(getTime(player));
        return time.getHour() + " hours, " + time.getMinute() + " minutes and " + time.getSecond() + " seconds";
    }
}
