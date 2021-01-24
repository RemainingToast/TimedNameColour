package me.remainingtoast.namecolour;

import com.google.common.reflect.TypeToken;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.UUID;

public class PlayerUtil {

    public static HashMap<UUID, NCPlayer> playerData = new HashMap<>();

    public static NCPlayer loadExistingPlayer(UUID player){
        NCPlayer ncp = playerData.getOrDefault(player, null);
        if(ncp != null){
            return ncp;
        } else {
            return loadPlayerNoCreate(player);
        }
    }

    public static NCPlayer loadPlayerData(OfflinePlayer player) {
        NCPlayer loadedData = loadExistingPlayer(player.getUniqueId());
        if (loadedData == null) {
            loadedData = new NCPlayer(player).load();
        }
        loadedData.setUuidIfNull(player.getUniqueId());
        playerData.remove(player.getUniqueId());
        playerData.put(player.getUniqueId(), loadedData);
        return loadedData;
    }

    public static NCPlayer loadPlayerNoCreate(UUID player) {
        File playerDataFile = new File(NameColourMain.INSTANCE.getDataFolder(), "/players/" + player.toString() + ".json");
        playerDataFile.getParentFile().mkdirs();
        try {
            return ((NCPlayer) NameColourMain.GSON.fromJson(new FileReader(playerDataFile), new TypeToken<NCPlayer>() {
            }.getType())).setUuidIfNull(player);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
