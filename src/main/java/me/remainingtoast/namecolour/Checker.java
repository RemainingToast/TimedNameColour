package me.remainingtoast.namecolour;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Checker implements Runnable {
    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){
            NCPlayer ncp = PlayerUtil.loadPlayerData(p);
            int i = ncp.getNameColourTime();
            if(i > 0){
                p.setDisplayName(ncp.getColoredName());
                ncp.setNameColourTime(i - 1);
                ncp.save();
            } else return;
        }
    }
}
