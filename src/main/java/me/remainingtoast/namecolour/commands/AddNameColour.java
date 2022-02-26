package me.remainingtoast.namecolour.commands;

import me.remainingtoast.namecolour.util.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddNameColour implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 1) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);

            PlayerWrapper wrapper;
            if (player.getPlayer() != null) {
                wrapper = PlayerWrapper.get(player.getPlayer());
            } else {
                wrapper = PlayerWrapper.get(player);
            }

            int hours = wrapper.getHours() + getHours(args[1]);

            wrapper.sendMessage("&6Set name colour time for " + player.getName() + " to " + hours + " hours!");
            wrapper.setHours(hours);
            wrapper.save();
        }

        return true;
    }

    private int getHours(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
