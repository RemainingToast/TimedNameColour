package me.remainingtoast.namecolour;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class NCPlayer {

    @SerializedName("ColourTime")
    private int nameColourTime = 0;

    @SerializedName("ColourCode")
    private String colour = "&f";

    @SerializedName("PlayerName")
    private String name;

    @SerializedName("PlayerUUID")
    private UUID uuid;


    public NCPlayer(OfflinePlayer player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
    }

    public void setNameColourTime(int nameColourTime) {
        this.nameColourTime = nameColourTime;
    }

    public int getNameColourTime() {
        return nameColourTime;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColoredName() {
        return ChatColor.translateAlternateColorCodes('&',colour + name + ChatColor.RESET);
    }

    public NCPlayer setUuidIfNull(UUID uuid) {
        if (this.uuid == null) {
            this.uuid = uuid;
        }
        return this;
    }

    public NCPlayer save(){
        PlayerUtil.playerData.remove(uuid);
        PlayerUtil.playerData.putIfAbsent(uuid, this);
        File fileLoc = new File(NameColourMain.INSTANCE.getDataFolder(), "/players/" + uuid.toString() + ".json");
        fileLoc.getParentFile().mkdir();
        try {
            fileLoc.createNewFile();
            FileWriter fw = new FileWriter(fileLoc);
            fw.write(NameColourMain.GSON.toJson(this));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to save namecolour for player: " + uuid.toString() + ", error:");
            e.printStackTrace();
        }
        return this;
    }

    public NCPlayer load(){
        File fileLoc = new File(NameColourMain.INSTANCE.getDataFolder(), "/players/" + uuid.toString() + ".json");
        fileLoc.getParentFile().mkdirs();
        try{
            return NameColourMain.GSON.fromJson(new FileReader(fileLoc), new TypeToken<NCPlayer>(){}.getType());
        } catch (Exception exception) {
            return this.save();
        }
    }
}
