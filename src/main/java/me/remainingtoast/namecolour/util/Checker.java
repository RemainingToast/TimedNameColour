package me.remainingtoast.namecolour.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Checker implements Runnable, Listener {

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            PlayerWrapper wrapper = PlayerWrapper.get(p);

            int hours = wrapper.getHours();
            if (hours <= 0) continue;

            wrapper.setHours(hours - 1);
            wrapper.setColoredName();
            wrapper.save();
        }
    }

    @EventHandler
    private void on(PlayerJoinEvent e) {
        PlayerWrapper wrapper = PlayerWrapper.get(e.getPlayer());
        wrapper.setColoredName();
    }

}
