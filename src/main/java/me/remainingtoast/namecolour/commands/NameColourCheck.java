package me.remainingtoast.namecolour.commands;

import me.remainingtoast.namecolour.util.PlayerWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameColourCheck implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            PlayerWrapper wrapper = PlayerWrapper.get((Player) sender);

            if (wrapper.getHours() <= 0) {
                wrapper.sendMessage("&cYou do not have any name colour time, do /vote to get some!");
            } else {
                wrapper.sendMessage("&7You have &c" + wrapper.getHours() + " &7of name colour remaining.");
            }
        }
        return true;
    }

}
