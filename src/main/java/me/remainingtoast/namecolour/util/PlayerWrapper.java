package me.remainingtoast.namecolour.util;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import me.remainingtoast.namecolour.Main;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class PlayerWrapper {

    private final Player player;

    @SerializedName("time")
    private int hours = 0;

    @SerializedName("code")
    private String colour = "&f";

    @SerializedName("uuid")
    private String uuid;

    private PlayerWrapper(OfflinePlayer offlinePlayer) {
        this.player = offlinePlayer.getPlayer();
        this.uuid = offlinePlayer.getUniqueId().toString();
        this.load();
    }

    private PlayerWrapper(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId().toString();
        this.load();
    }

    public static PlayerWrapper get(OfflinePlayer player) {
        return Main.DATA.getOrDefault(player.getUniqueId().toString(), new PlayerWrapper(player));
    }

    public static PlayerWrapper get(Player player) {
        return Main.DATA.getOrDefault(player.getUniqueId().toString(), new PlayerWrapper(player));
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setColor(String colour) {
        this.colour = colour;
    }

    public void setColoredName() {
        player.setDisplayName(getColoredName());
        player.setPlayerListName(getColoredName());
    }

    public String getColoredName() {
        String name = ChatColor.stripColor(player.getDisplayName());
        if (hours <= 0) return name;
        else return ChatColor.translateAlternateColorCodes('&', colour + name);
    }

    public Player getPlayer() {
        return player;
    }

    public void sendMessage(String message) {
        if (player != null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        } else {
            System.out.println("Player offline");
        }
    }

    public void save() {
        Main.DATA.putIfAbsent(uuid, this);

        File fileLoc = new File(Main.INSTANCE.getDataFolder(), "data.json");
        fileLoc.getParentFile().mkdir();

        try {
            fileLoc.createNewFile();
            FileWriter fw = new FileWriter(fileLoc);
            fw.write(Main.GSON.toJson(Main.DATA));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to save name colour for players, error:");
            e.printStackTrace();
        }
    }

    public void load() {
        File fileLoc = new File(Main.INSTANCE.getDataFolder(), "data.json");
        fileLoc.getParentFile().mkdirs();

        try {
            Type typeToken = new TypeToken<HashMap<String, PlayerWrapper>>(){}.getType();
            HashMap<String, PlayerWrapper> wrapperMap = Main.GSON.fromJson(new FileReader(fileLoc), typeToken);
            PlayerWrapper wrapper = wrapperMap.getOrDefault(uuid, this);
            this.setColor(wrapper.colour);
            this.setHours(wrapper.getHours());
            Main.DATA.putAll(wrapperMap);
        } catch (Exception exception) {
            save();
        }
    }

}
