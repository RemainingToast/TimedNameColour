package me.remainingtoast.namecolour;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (NameColourMain.INSTANCE.getPlayerSection(player) == null) {
            NameColourMain.INSTANCE.getPlayerDataYaml().createSection(player.getUniqueId().toString());
            NameColourMain.INSTANCE.getPlayerDataYaml().getConfigurationSection(player.getUniqueId().toString()).set("colour", "f");
            NameColourMain.INSTANCE.getPlayerDataYaml().getConfigurationSection(player.getUniqueId().toString()).set("time", 0);
            NameColourMain.INSTANCE.savePlayerData();
        }
        player.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&"+getColour(player)+player.getDisplayName()));
        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&"+getColour(player)+player.getPlayerListName()));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        String name = ChatColor.translateAlternateColorCodes('&', "&" + (this.checkTime(player) ? this.getColour(player) : "f") + player.getDisplayName() + "&f");
        e.setFormat("<" + name + "> " + message);
    }

    public String getColour(Player player) {
        return NameColourMain.INSTANCE.getPlayerDataYaml().getConfigurationSection(player.getUniqueId().toString()).get("colour").toString();
    }

    private boolean checkTime(Player player) {
        return NameColourMain.INSTANCE.getPlayerSection(player).getInt("time") > 0;
    }

}
