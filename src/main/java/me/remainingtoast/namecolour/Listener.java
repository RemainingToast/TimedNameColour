package me.remainingtoast.namecolour;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        NCPlayer ncp = PlayerUtil.loadPlayerData(player);
        player.setDisplayName(ncp.getColoredName());
        ncp.save();
    }
}
